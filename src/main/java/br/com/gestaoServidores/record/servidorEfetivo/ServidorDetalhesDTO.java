package br.com.gestaoServidores.record.servidorEfetivo;


import br.com.gestaoServidores.record.unidade.UnidadeDetalhesDTO;

import java.time.LocalDate;

public record ServidorDetalhesDTO(String nome,
                                  LocalDate dataNascimento,
                                  UnidadeDetalhesDTO unidadeLotacao,
                                  String fotografia) {
}
