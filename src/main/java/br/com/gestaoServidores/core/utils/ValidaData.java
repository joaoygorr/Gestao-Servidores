package br.com.gestaoServidores.core.utils;

import java.time.LocalDate;

public class ValidaData {

    public static void validarDatas(LocalDate dataInicio, LocalDate dataFim) {
        if (dataInicio.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data de início não pode ser no futuro. Data fornecida: %s.".formatted(dataInicio));
        }

        if (dataFim.isBefore(dataInicio)) {
            throw new IllegalArgumentException("""
                                                A data de fim não pode ser anterior à data de início.\s
                                                Data de fim fornecida: %s, Data de início fornecida: %s."""
                    .formatted(dataFim, dataInicio));
        }

        if (dataFim.isEqual(dataInicio)) {
            throw new IllegalArgumentException("A data de fim não pode ser igual à data de início. Data fornecida: %s.".formatted(dataFim));
        }
    }
}
