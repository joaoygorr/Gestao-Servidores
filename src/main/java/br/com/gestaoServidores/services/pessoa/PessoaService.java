package br.com.gestaoServidores.services.pessoa;

import br.com.gestaoServidores.modules.Pessoa;

public interface PessoaService {
    Pessoa createPerson(Pessoa pessoa);

    Pessoa findByPerson(Long id);
}
