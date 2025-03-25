package br.com.gestaoServidores.services.pessoa;

import br.com.gestaoServidores.core.exceptions.Exception404;
import br.com.gestaoServidores.modules.Cidade;
import br.com.gestaoServidores.modules.Pessoa;
import br.com.gestaoServidores.repositories.PessoaRepository;
import br.com.gestaoServidores.services.cidade.CidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository pessoaRepository;

    private final CidadeService cidadeService;
    @Transactional
    @Override
    public Pessoa createPerson(Pessoa pessoa) {
        pessoa.getEnderecos().forEach(endereco -> {
            Cidade cidade = endereco.getCidade();
            endereco.setCidade(cidadeService.verifyAndCreateCity(cidade));
        });
        return this.pessoaRepository.save(pessoa);
    }

    @Override
    public Pessoa findByPerson(Long id) {
        return this.pessoaRepository.findById(id).orElseThrow(() -> new Exception404("Pessoa não encontrada"));
    }
}
