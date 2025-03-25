package br.com.gestaoServidores.services.fotoPessoa;

import br.com.gestaoServidores.modules.FotoPessoa;
import br.com.gestaoServidores.record.fotoPessoa.FotoPessoaDTO;

import java.util.List;

public interface FotoPessoaService {

    List<FotoPessoa> upload(FotoPessoaDTO dto) throws Exception;
}
