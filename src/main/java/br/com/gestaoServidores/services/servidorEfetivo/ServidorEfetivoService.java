package br.com.gestaoServidores.services.servidorEfetivo;

import br.com.gestaoServidores.modules.ServidorEfetivo;
import br.com.gestaoServidores.record.servidorEfetivo.ServidorEfetivoDTO;

public interface ServidorEfetivoService {

    ServidorEfetivo createEffectiveServer(ServidorEfetivo entity);

    void deleteEffectiveServer(Long id);

    ServidorEfetivo findByEffectiveServer(Long id);

    ServidorEfetivo updateEffectiveServer(Long id, ServidorEfetivoDTO dto);
}
