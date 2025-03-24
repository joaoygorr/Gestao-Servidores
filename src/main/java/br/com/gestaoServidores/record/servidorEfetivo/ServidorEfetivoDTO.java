package br.com.gestaoServidores.record.servidorEfetivo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ServidorEfetivoDTO(@Schema(hidden = true) Long id,
                                 @NotBlank(message = "matricula não pode estar em branco") String matricula,
                                 @NotNull(message = "Pessoa não pode ser nula") Long pessoa) {
}
