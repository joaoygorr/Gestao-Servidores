package br.com.gestaoServidores.services.servidorEfetivo;

import br.com.gestaoServidores.modules.ServidorEfetivo;

public interface ServidorEfetivoService {

    ServidorEfetivo createEffectiveServer(ServidorEfetivo entity);

    void deleteEffectiveServer(Long id);

    ServidorEfetivo findByEffectiveServer(Long id);
}
