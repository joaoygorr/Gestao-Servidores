package br.com.gestaoServidores.services.servidorEfetivo;

import br.com.gestaoServidores.modules.ServidorEfetivo;
import br.com.gestaoServidores.record.servidorEfetivo.ServidorDTO;
import br.com.gestaoServidores.record.servidorEfetivo.ServidorEfetivoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ServidorEfetivoService {

    ServidorEfetivo createEffectiveServer(ServidorEfetivo entity);

    void deleteEffectiveServer(Long id);

    ServidorEfetivo findByEffectiveServer(Long id);

    ServidorEfetivo updateEffectiveServer(Long id, ServidorEfetivoDTO dto);

    Page<ServidorEfetivo> findAllEffectiveServer(Pageable pageable);

    List<ServidorEfetivo> findByServidorAndPessoaNome(String nome);

    List<ServidorDTO> getServerByUnit(Long unidId) ;
}
