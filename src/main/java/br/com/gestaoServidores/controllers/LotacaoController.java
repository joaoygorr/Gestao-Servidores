package br.com.gestaoServidores.controllers;

import br.com.gestaoServidores.core.mappers.LotacaoMapper;
import br.com.gestaoServidores.modules.Lotacao;
import br.com.gestaoServidores.record.lotacao.LotacaoDTO;
import br.com.gestaoServidores.services.lotacao.LotacaoService;
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

@RestController
@RequestMapping("/lotacao")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Lotação", description = "Endpoint relacionado a lotação")
public class LotacaoController {


    private final LotacaoMapper lotacaoMapper;

    private final LotacaoService lotacaoService;

    @PostMapping
    @Operation(summary = "Criar uma nova lotação", description = "Cria um novo registro de lotação e retorna a lotação criada")
    public ResponseEntity<LotacaoDTO> createUnit(@RequestBody @Valid LotacaoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.lotacaoMapper.toDTO(this.lotacaoService.createCapacity(this.lotacaoMapper.toEntity(dto))));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclusão de uma lotação", description = "Exclui um registro de uma lotação")
    public ResponseEntity<Void> deleteUnit(@PathVariable Long id) {
        this.lotacaoService.deleteCapacity(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @PutMapping("/{id}")
    @Operation(summary = "Atualização de uma lotação", description = "Atualiza informações de uma lotação")
    public ResponseEntity<LotacaoDTO> updateEffectiveServer(@PathVariable Long id,
                                                            @RequestBody @Valid LotacaoDTO dto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.lotacaoMapper.toDTO(this.lotacaoService.updateCapacity(id, dto)));
    }

    @GetMapping
    @Operation(summary = "Listar lotações", description = "Retorna uma lista paginada de lotações")
    public ResponseEntity<Page<LotacaoDTO>> getAllEffectiveServer(Pageable pageable) {
        Page<Lotacao> efetivos = this.lotacaoService.findAllCapacity(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(efetivos.map(this.lotacaoMapper::toDTO));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar uma lotação efetivo por ID", description = "Retorna os detalhes de uma lotação pelo ID informado")
    public ResponseEntity<LotacaoDTO> getByIdEffective(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.lotacaoMapper.toDTO(this.lotacaoService.findByCapacity(id)));
    }
}
