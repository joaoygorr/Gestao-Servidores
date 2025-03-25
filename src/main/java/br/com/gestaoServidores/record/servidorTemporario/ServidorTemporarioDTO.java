package br.com.gestaoServidores.record.servidorTemporario;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ServidorTemporarioDTO(@Schema(hidden = true) Long id,
                                    @NotNull(message = "Data Admissão não deve ser nula") LocalDate dataAdmissao,
                                    @NotNull(message = "Data Demissão não deve ser nula") LocalDate dataDemissao,
                                    @NotNull(message = "Pessoa não deve ser nula") Long pessoa) {
}
