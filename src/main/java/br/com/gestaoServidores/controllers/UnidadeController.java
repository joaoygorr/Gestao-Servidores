package br.com.gestaoServidores.controllers;

import br.com.gestaoServidores.mappers.UnidadeMapper;
import br.com.gestaoServidores.record.unidade.UnidadeDTO;
import br.com.gestaoServidores.services.unidade.UnidadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/unidade")
@RequiredArgsConstructor
@Tag(name = "Unidade", description = "Endpoint relacionado a unidade")
public class UnidadeController {

    private final UnidadeMapper unidadeMapper;

    private final UnidadeService unidadeService;

    @PostMapping
    @Operation(summary = "Criar uma nova unidade", description = "Cria um novo registro de unidade e retorna a unidade criada")
    public ResponseEntity<UnidadeDTO> createUnit(@RequestBody @Valid UnidadeDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.unidadeMapper.toDTO(this.unidadeService.createUnit(this.unidadeMapper.toEntity(dto))));
    }
}
