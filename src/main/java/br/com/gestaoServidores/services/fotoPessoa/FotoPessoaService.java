package br.com.gestaoServidores.services.fotoPessoa;

import br.com.gestaoServidores.modules.FotoPessoa;
import br.com.gestaoServidores.record.fotoPessoa.FotoPessoaDTO;

public interface FotoPessoaService {

    FotoPessoa upload(FotoPessoaDTO dto) throws Exception;
}
