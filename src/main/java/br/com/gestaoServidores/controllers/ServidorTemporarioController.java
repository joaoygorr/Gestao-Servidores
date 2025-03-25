package br.com.gestaoServidores.controllers;

import br.com.gestaoServidores.core.mappers.ServidorTemporarioMapper;
import br.com.gestaoServidores.modules.ServidorTemporario;
import br.com.gestaoServidores.record.servidorTemporario.ServidorTemporarioDTO;
import br.com.gestaoServidores.services.servidorTemporario.ServidorTemporarioService;
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
@RequestMapping("/servidorTemporario")
@RequiredArgsConstructor
@Tag(name = "Servidor Temporário", description = "Endpoint relacionado a servidores temporários")
public class ServidorTemporarioController {

    private final ServidorTemporarioMapper temporarioMapper;

    private final ServidorTemporarioService temporarioService;

    @PostMapping
    @Operation(summary = "Criar um novo servidor temporário",
            description = "Cria um novo registro de servidor temporário e retorna o servidor criado.")
    public ResponseEntity<ServidorTemporarioDTO> createEffectiveTemporary(@RequestBody @Valid ServidorTemporarioDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.temporarioMapper.toDTO(
                        this.temporarioService.createEffectiveTemporary(this.temporarioMapper.toEntity(dto))));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclusão de um servidor temporário", description = "Exclui um registro de servidor temporário")
    public ResponseEntity<Void> deleteEffectiveTemporary(@PathVariable Long id) {
        this.temporarioService.deleteEffectiveTemporary(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualização de um servidor temporário", description = "Atualiza informações de um servidor temporário")
    public ResponseEntity<ServidorTemporarioDTO> updateEffectiveTemporary(@PathVariable Long id,
                                                                    @RequestBody @Valid ServidorTemporarioDTO dto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.temporarioMapper.toDTO(this.temporarioService.updateEffectiveTemporary(id, dto)));
    }

    @GetMapping
    @Operation(summary = "Listar servidores temporários", description = "Retorna uma lista paginada de servidores temporários")
    public ResponseEntity<Page<ServidorTemporarioDTO>> getAllEffectiveTemporary(Pageable pageable) {
        Page<ServidorTemporario> efetivos = this.temporarioService.findAllEffectiveTemporary(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(efetivos.map(this.temporarioMapper::toDTO));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar um servidor temporário por ID", description = "Retorna os detalhes de um servidor temporário pelo ID informado")
    public ResponseEntity<ServidorTemporarioDTO> getByIdEffectiveTemporary(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.temporarioMapper.toDTO(this.temporarioService.findByEffectiveTemporary(id)));
    }
}
