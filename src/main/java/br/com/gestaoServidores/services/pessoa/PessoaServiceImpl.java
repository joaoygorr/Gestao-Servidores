package br.com.gestaoServidores.services.pessoa;

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
}
