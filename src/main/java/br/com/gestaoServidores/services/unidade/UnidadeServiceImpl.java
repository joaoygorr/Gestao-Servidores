package br.com.gestaoServidores.services.unidade;

import br.com.gestaoServidores.core.exceptions.Exception404;
import br.com.gestaoServidores.core.mappers.UnidadeMapper;
import br.com.gestaoServidores.modules.Cidade;
import br.com.gestaoServidores.modules.Unidade;
import br.com.gestaoServidores.record.unidade.UnidadeDTO;
import br.com.gestaoServidores.repositories.UnidadeRepository;
import br.com.gestaoServidores.services.cidade.CidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UnidadeServiceImpl implements UnidadeService {

    private final UnidadeRepository unidadeRepository;

    private final CidadeService cidadeService;

    private final UnidadeMapper unidadeMapper;

    @Transactional
    @Override
    public Unidade createUnit(Unidade unidade) {
        unidade.getEnderecos().forEach(endereco -> {
            Cidade cidade = endereco.getCidade();
            endereco.setCidade(cidadeService.verifyAndCreateCity(cidade));
        });
        return unidadeRepository.save(unidade);
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
