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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
