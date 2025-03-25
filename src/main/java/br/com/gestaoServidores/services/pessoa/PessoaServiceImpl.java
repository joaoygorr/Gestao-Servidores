package br.com.gestaoServidores.services.pessoa;

import br.com.gestaoServidores.core.exceptions.Exception404;
import br.com.gestaoServidores.modules.Cidade;
import br.com.gestaoServidores.modules.Endereco;
import br.com.gestaoServidores.modules.Pessoa;
import br.com.gestaoServidores.repositories.CidadeRepository;
import br.com.gestaoServidores.repositories.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository pessoaRepository;

    private final CidadeRepository cidadeRepository;

    @Transactional
    @Override
    public Pessoa createPerson(Pessoa pessoa) {
        for (Endereco endereco : pessoa.getEnderecos()) {
            // TODO: aplicar função lambda
            // TODO: separar serviços cidade de pessoa
            Cidade cidade = endereco.getCidade();
            Optional<Cidade> cidadeOptional = this.cidadeRepository.findByNome(cidade.getNome());

            if (cidadeOptional.isPresent()) {
                endereco.setCidade(cidadeOptional.get());
            } else {
                endereco.setCidade(this.cidadeRepository.save(new Cidade(cidade.getNome(), cidade.getUf())));
            }
        }
        return this.pessoaRepository.save(pessoa);
    }

    @Override
    public Pessoa findByPerson(Long id) {
        return this.pessoaRepository.findById(id).orElseThrow(() -> new Exception404("Pessoa não encontrada"));
    }
}
