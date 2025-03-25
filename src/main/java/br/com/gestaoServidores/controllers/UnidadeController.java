package br.com.gestaoServidores.controllers;

import br.com.gestaoServidores.mappers.UnidadeMapper;
import br.com.gestaoServidores.modules.ServidorEfetivo;
import br.com.gestaoServidores.modules.Unidade;
import br.com.gestaoServidores.record.servidorEfetivo.ServidorEfetivoDTO;
import br.com.gestaoServidores.record.unidade.UnidadeDTO;
import br.com.gestaoServidores.services.unidade.UnidadeService;
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

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclusão de uma unidade", description = "Exclui um registro de uma unidade")
    public ResponseEntity<Void> deleteUnit(@PathVariable Long id) {
        this.unidadeService.deleteUnit(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @PutMapping("/{id}")
    @Operation(summary = "Atualização de uma unidade", description = "Atualiza informações de uma unidade")
    public ResponseEntity<UnidadeDTO> updateEffectiveServer(@PathVariable Long id,
                                                                    @RequestBody @Valid UnidadeDTO dto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.unidadeMapper.toDTO(this.unidadeService.updateUnit(id, dto)));
    }

    @GetMapping
    @Operation(summary = "Listar unidades", description = "Retorna uma lista paginada de unidades")
    public ResponseEntity<Page<UnidadeDTO>> getAllEffectiveServer(Pageable pageable) {
        Page<Unidade> efetivos = this.unidadeService.findAllUnit(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(efetivos.map(this.unidadeMapper::toDTO));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar uma unidade efetivo por ID", description = "Retorna os detalhes de uma unidade pelo ID informado")
    public ResponseEntity<UnidadeDTO> getByIdEffective(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.unidadeMapper.toDTO(this.unidadeService.findByUnit(id)));
    }
}
