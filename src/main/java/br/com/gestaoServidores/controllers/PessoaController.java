package br.com.gestaoServidores.controllers;

import br.com.gestaoServidores.mappers.PessoaMapper;
import br.com.gestaoServidores.record.pessoa.PessoaDTO;
import br.com.gestaoServidores.services.pessoa.PessoaService;
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
@RequestMapping("/pessoa")
@RequiredArgsConstructor
//@SecurityRequirement(name = "bearer-key")
@Tag(name = "Pessoa", description = "Endpoint relacionado a pessoa")
public class PessoaController {

    private final PessoaService pessoaService;

    private final PessoaMapper pessoaMapper;

    @PostMapping
    @Operation(summary = "Criar uma nova pessoa", description = "Cria um novo registro de pessoa e retorna a pessoa criada.")
    public ResponseEntity<PessoaDTO> createPerson(@RequestBody @Valid PessoaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.pessoaMapper.toDTO(this.pessoaService.createPerson(this.pessoaMapper.toEntity(dto))));
    }
}
