package br.com.gestaoServidores.services.lotacao;

import br.com.gestaoServidores.modules.Lotacao;
import br.com.gestaoServidores.record.lotacao.LotacaoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LotacaoService {

    Lotacao createCapacity(Lotacao entity);

    void deleteCapacity(Long id);

    Lotacao findByCapacity(Long id);

    Lotacao updateCapacity(Long id, LotacaoDTO dto);

    Page<Lotacao> findAllCapacity(Pageable pageable);
}
