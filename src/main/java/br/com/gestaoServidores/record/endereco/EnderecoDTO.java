package br.com.gestaoServidores.record.endereco;

import br.com.gestaoServidores.record.cidade.CidadeDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnderecoDTO(@Schema(hidden = true) Long id,
                          String tipoLogradouro,
                          @NotBlank(message = "Logradouro não pode estar em branco") String logradouro,
                          @NotNull(message = "Número não pode ser nulo") Long numero,
                          @NotBlank(message = "Bairro não pode estar em branco") String bairro,
                          @NotNull(message = "Cidade não pode ser nula") CidadeDTO cidade) {
}
