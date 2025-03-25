package br.com.gestaoServidores.services.unidade;

import br.com.gestaoServidores.modules.Unidade;
import br.com.gestaoServidores.record.unidade.UnidadeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UnidadeService {

    Unidade createUnit(Unidade unidade);

    Unidade findByUnit(Long id);

    void deleteUnit(Long id);

    Unidade updateUnit(Long id, UnidadeDTO dto);

    Page<Unidade> findAllUnit(Pageable pageable);
}
