package br.com.gestaoServidores.services.unidade;

import br.com.gestaoServidores.core.exceptions.Exception404;
import br.com.gestaoServidores.core.mappers.UnidadeMapper;
import br.com.gestaoServidores.modules.Cidade;
import br.com.gestaoServidores.modules.Endereco;
import br.com.gestaoServidores.modules.Unidade;
import br.com.gestaoServidores.record.unidade.UnidadeDTO;
import br.com.gestaoServidores.repositories.CidadeRepository;
import br.com.gestaoServidores.repositories.UnidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UnidadeServiceImpl implements UnidadeService {

    private final UnidadeRepository unidadeRepository;

    private final CidadeRepository cidadeRepository;

    private final UnidadeMapper unidadeMapper;

    @Transactional
    @Override
    public Unidade createUnit(Unidade unidade) {
        for (Endereco endereco : unidade.getEnderecos()) {
            Cidade cidade = endereco.getCidade();

            Optional<Cidade> cidadeOptional = this.cidadeRepository.findByNome(cidade.getNome());

            if (cidadeOptional.isPresent()) {
                endereco.setCidade(cidadeOptional.get());
            } else  {
                endereco.setCidade(this.cidadeRepository.save(new Cidade(cidade.getNome(), cidade.getUf())));
            }
        }
        return this.unidadeRepository.save(unidade);
    }

    @Override
    public Unidade findByUnit(Long id) {
        return this.unidadeRepository.findById(id).orElseThrow(() -> new Exception404("Unidade não encontrada"));
    }

    @Transactional
    @Override
    public void deleteUnit(Long id) {
        findByUnit(id);
        this.unidadeRepository.deleteById(id);
    }


    @Transactional
    @Override
    public Unidade updateUnit(Long id, UnidadeDTO dto) {
        Unidade unit = findByUnit(id);
        unidadeMapper.updateUnit(dto, unit);
        return this.unidadeRepository.save(unit);
    }

    @Override
    public Page<Unidade> findAllUnit(Pageable pageable) {
        return this.unidadeRepository.findAll(pageable);
    }
}
