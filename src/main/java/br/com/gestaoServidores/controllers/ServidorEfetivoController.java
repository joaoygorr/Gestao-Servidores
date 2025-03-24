package br.com.gestaoServidores.controllers;

import br.com.gestaoServidores.mappers.ServidorEfetivoMapper;
import br.com.gestaoServidores.record.servidorEfetivo.ServidorEfetivoDTO;
import br.com.gestaoServidores.services.servidorEfetivo.ServidorEfetivoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
}
