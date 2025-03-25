package br.com.gestaoServidores.services.servidorTemporario;

import br.com.gestaoServidores.modules.ServidorTemporario;
import br.com.gestaoServidores.record.servidorTemporario.ServidorTemporarioDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ServidorTemporarioService {
    ServidorTemporario createEffectiveTemporary(ServidorTemporario entity);

    void deleteEffectiveTemporary(Long id);

    ServidorTemporario findByEffectiveTemporary(Long id);

    ServidorTemporario updateEffectiveTemporary(Long id, ServidorTemporarioDTO dto);

    Page<ServidorTemporario> findAllEffectiveTemporary(Pageable pageable);
}
