package br.com.gestaoServidores.record.pessoa;

import br.com.gestaoServidores.record.endereco.EnderecoDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Set;

public record PessoaDTO(@Schema(hidden = true) Long id,
                        @NotBlank(message = "Nome não pode estar em branco") String nome,
                        @NotNull(message = "Data de Nascimento não pode ser nula") LocalDate dataNascimento,
                        @NotBlank(message = "Sexo não pode estar em branco") String sexo,
                        String mae,
                        String pai,
                        @NotNull(message = "Endereço não pode ser nulo") Set<EnderecoDTO> enderecos) {
}
