package br.com.gestaoServidores.controllers;

import br.com.gestaoServidores.core.mappers.FotoPessoaMapper;
import br.com.gestaoServidores.modules.FotoPessoa;
import br.com.gestaoServidores.record.fotoPessoa.FotoPessoaDTO;
import br.com.gestaoServidores.services.fotoPessoa.FotoPessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/fotoPessoa")
@RequiredArgsConstructor
@Tag(name = "Foto Pessoa", description = "Endpoint relacionado a Foto Pessoa")
public class FotoPessoaController {

    private final FotoPessoaService fotoPessoaService;

    private final FotoPessoaMapper fotoPessoaMapper;

    @PostMapping(consumes = "multipart/form-data")
    @Operation(summary = "Criar uma nova foto pessoa", description = "Cria um novo registro de foto pessoa e retorna a foto criada")
    public ResponseEntity<List<FotoPessoaDTO>> createImagePerson(@ModelAttribute FotoPessoaDTO dto) throws Exception {
        try {
            List<FotoPessoa> fotoPessoas = this.fotoPessoaService.upload(dto);
            List<FotoPessoaDTO> fotoPessoaDTOs = fotoPessoas.stream()
                    .map(fotoPessoaMapper::toDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.CREATED).body(fotoPessoaDTOs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
