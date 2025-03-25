package br.com.gestaoServidores.controllers;

import br.com.gestaoServidores.mappers.ServidorEfetivoMapper;
import br.com.gestaoServidores.modules.ServidorEfetivo;
import br.com.gestaoServidores.record.servidorEfetivo.ServidorEfetivoDTO;
import br.com.gestaoServidores.services.servidorEfetivo.ServidorEfetivoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/servidorEfetivo")
@RequiredArgsConstructor
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
        Page<ServidorEfetivo> efetivos = this.efetivoService.findAllEffective(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(efetivos.map(this.efetivoMapper::toDTO));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar um servidor efetivo por ID", description = "Retorna os detalhes de um servidor efetivo pelo ID informado")
    public ResponseEntity<ServidorEfetivoDTO> getByIdEffective(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.efetivoMapper.toDTO(this.efetivoService.findByEffectiveServer(id)));
    }
}
