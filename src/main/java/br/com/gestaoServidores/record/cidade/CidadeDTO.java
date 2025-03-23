package br.com.gestaoServidores.record.cidade;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record CidadeDTO(@Schema(hidden = true) Long id,
                        @NotBlank(message = "Nome não pode estar em branco") String nome,
                        @NotBlank(message = "UF não pode estar em branco") String uf) {
}
