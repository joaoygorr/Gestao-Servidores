package br.com.gestaoServidores.record.lotacao;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record LotacaoDTO(@Schema(hidden = true) Long id,
                         @NotNull(message = "Pessoa não pode ser nula") Long pessoa,
                         @NotNull(message = "Unidade não pode ser nula") Long unidade,
                         @NotNull(message = "Data Lotação não pode ser nula") LocalDate dataLotacao,
                         @NotNull(message = "Data Remoção não pode ser nula") LocalDate dataRemocao,
                         @NotBlank(message = "Portaria não pode estar em branco") String portaria) {
}
