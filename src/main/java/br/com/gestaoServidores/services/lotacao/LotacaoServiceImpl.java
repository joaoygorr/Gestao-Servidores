package br.com.gestaoServidores.services.lotacao;

import br.com.gestaoServidores.core.exceptions.Exception404;
import br.com.gestaoServidores.core.mappers.LotacaoMapper;
import br.com.gestaoServidores.core.utils.ValidaData;
import br.com.gestaoServidores.modules.Lotacao;
import br.com.gestaoServidores.record.lotacao.LotacaoDTO;
import br.com.gestaoServidores.repositories.LotacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LotacaoServiceImpl implements LotacaoService {

    private final LotacaoRepository lotacaoRepository;

    private final LotacaoMapper lotacaoMapper;

    @Transactional
    @Override
    public Lotacao createCapacity(Lotacao entity) {
        ValidaData.validarDatas(entity.getDataLotacao(), entity.getDataRemocao());
        return this.lotacaoRepository.save(entity);
    }

    @Override
    public Lotacao findByCapacity(Long id) {
        return this.lotacaoRepository.findById(id)
                .orElseThrow(() -> new Exception404("Lotação não encontrada"));
    }

    @Transactional
    @Override
    public void deleteCapacity(Long id) {
        findByCapacity(id);
        this.lotacaoRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Lotacao updateCapacity(Long id, LotacaoDTO dto) {
        ValidaData.validarDatas(dto.dataLotacao(), dto.dataRemocao());
        Lotacao efetivo = findByCapacity(id);
        lotacaoMapper.updateServer(dto, efetivo);
        return this.lotacaoRepository.save(efetivo);
    }

    @Override
    public Page<Lotacao> findAllCapacity(Pageable pageable) {
        return this.lotacaoRepository.findAll(pageable);
    }
}
