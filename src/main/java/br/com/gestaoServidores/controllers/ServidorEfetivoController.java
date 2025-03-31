package br.com.gestaoServidores.controllers;

import br.com.gestaoServidores.core.mappers.ServidorEfetivoMapper;
import br.com.gestaoServidores.modules.ServidorEfetivo;
import br.com.gestaoServidores.record.servidorEfetivo.ServidorDTO;
import br.com.gestaoServidores.record.servidorEfetivo.ServidorEfetivoDTO;
import br.com.gestaoServidores.record.servidorEfetivo.ServidorEnderecoDTO;
import br.com.gestaoServidores.services.servidorEfetivo.ServidorEfetivoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/servidorEfetivo")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Servidor Efetivo", description = "Endpoint relacionado a servidores efetivos")
public class ServidorEfetivoController {

    private final ServidorEfetivoService efetivoService;

    private final ServidorEfetivoMapper efetivoMapper;

    @PostMapping
    @Operation(summary = "Criar um novo servidor efetivo",
            description = "Cria um novo registro de servidor efetivo e retorna o servidor criado.")
    public ResponseEntity<ServidorEfetivoDTO> createEffectiveServer(@RequestBody @Valid ServidorEfetivoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.efetivoMapper.toDTO(
                        this.efetivoService.createEffectiveServer(this.efetivoMapper.toEntity(dto))));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclusão de um servidor efetivo", description = "Exclui um registro de servidor efetivo")
    public ResponseEntity<Void> deleteEffectiveServer(@PathVariable Long id) {
        this.efetivoService.deleteEffectiveServer(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualização de um servidor efetivo", description = "Atualiza informações de um servidor efetivo")
    public ResponseEntity<ServidorEfetivoDTO> updateEffectiveServer(@PathVariable Long id,
                                                                    @RequestBody @Valid ServidorEfetivoDTO dto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.efetivoMapper.toDTO(this.efetivoService.updateEffectiveServer(id, dto)));
    }

    @GetMapping
    @Operation(summary = "Listar servidores efetivos", description = "Retorna uma lista paginada de servidores efetivos")
    public ResponseEntity<Page<ServidorEfetivoDTO>> getAllEffectiveServer(Pageable pageable) {
        Page<ServidorEfetivo> efetivos = this.efetivoService.findAllEffectiveServer(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(efetivos.map(this.efetivoMapper::toDTO));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar um servidor efetivo por ID", description = "Retorna os detalhes de um servidor efetivo pelo ID informado")
    public ResponseEntity<ServidorEfetivoDTO> getByIdEffectiveServer(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.efetivoMapper.toDTO(this.efetivoService.findByEffectiveServer(id)));
    }

    @GetMapping("/enderecoFuncional")
    @Operation(summary = "Buscar um servidor pelo nome", description = "Retorna o nome e o endereço funcional do servidor")
    public ResponseEntity<List<ServidorEnderecoDTO>> getByServerAndPersonName(@RequestParam String nome) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.efetivoMapper.toServidorEnderecoList(this.efetivoService.findByServidorAndPessoaNome(nome)));
    }

    @GetMapping("/servidor/unidade/{unitId}")
    @Operation(summary = "Retorna dados do servidor", description = "Busca dados do servidor através do id de uma unidade vinculada a uma lotação")
    public ResponseEntity<List<ServidorDTO>> getByIdUnitAndServer(@PathVariable Long unitId) {
        try {
            List<ServidorDTO> servidores = this.efetivoService.getServerByUnit(unitId);
            return ResponseEntity.status(HttpStatus.OK).body(servidores);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }
}
