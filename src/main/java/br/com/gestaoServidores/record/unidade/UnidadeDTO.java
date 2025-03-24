package br.com.gestaoServidores.record.unidade;

import br.com.gestaoServidores.record.endereco.EnderecoDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record UnidadeDTO(@Schema(hidden = true) Long id,
                         @NotBlank(message = "Nome não pode estar em branco") String nome,
                         @NotBlank(message = "Sigla não pode estar em branco") String sigla,
                         @NotNull(message = "Endereço não pode ser nulo") Set<EnderecoDTO> enderecos) {
}
