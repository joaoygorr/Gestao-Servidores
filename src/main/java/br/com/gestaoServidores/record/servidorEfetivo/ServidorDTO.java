package br.com.gestaoServidores.record.servidorEfetivo;

import br.com.gestaoServidores.record.unidade.UnidadeDetalhesDTO;

public record ServidorDTO(String nome,
                          Integer idade,
                          UnidadeDetalhesDTO unidadeLotacao,
                          String fotografia) {
}
