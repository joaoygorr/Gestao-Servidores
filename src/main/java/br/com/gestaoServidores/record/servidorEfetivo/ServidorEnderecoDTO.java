package br.com.gestaoServidores.record.servidorEfetivo;

import br.com.gestaoServidores.record.endereco.EnderecoDTO;

import java.util.Set;

public record ServidorEnderecoDTO(String nome,
                                  Set<EnderecoDTO> enderecos) {
}
