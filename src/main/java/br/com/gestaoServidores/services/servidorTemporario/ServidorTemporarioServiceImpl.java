package br.com.gestaoServidores.services.servidorTemporario;

import br.com.gestaoServidores.core.exceptions.Exception404;
import br.com.gestaoServidores.core.mappers.ServidorTemporarioMapper;
import br.com.gestaoServidores.core.utils.ValidaData;
import br.com.gestaoServidores.modules.ServidorTemporario;
import br.com.gestaoServidores.record.servidorTemporario.ServidorTemporarioDTO;
import br.com.gestaoServidores.repositories.ServidorTemporarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ServidorTemporarioServiceImpl implements ServidorTemporarioService {

    private final ServidorTemporarioRepository temporarioRepository;

    private final ServidorTemporarioMapper temporarioMapper;

    @Transactional
    @Override
    public ServidorTemporario createEffectiveTemporary(ServidorTemporario entity) {
        ValidaData.validarDatas(entity.getDataAdmissao(), entity.getDataDemissao());
        return this.temporarioRepository.save(entity);
    }

    @Override
    public ServidorTemporario findByEffectiveTemporary(Long id) {
        return this.temporarioRepository.findById(id)
                .orElseThrow(() -> new Exception404("Servidor Temporario não encontrado"));
    }

    @Transactional
    @Override
    public void deleteEffectiveTemporary(Long id) {
        findByEffectiveTemporary(id);
        this.temporarioRepository.deleteById(id);
    }

    @Transactional
    @Override
    public ServidorTemporario updateEffectiveTemporary(Long id, ServidorTemporarioDTO dto) {
        ValidaData.validarDatas(dto.dataAdmissao(), dto.dataDemissao());
        ServidorTemporario efetivo = findByEffectiveTemporary(id);
        temporarioMapper.updateServer(dto, efetivo);
        return this.temporarioRepository.save(efetivo);
    }

    @Override
    public Page<ServidorTemporario> findAllEffectiveTemporary(Pageable pageable) {
        return this.temporarioRepository.findAll(pageable);
    }
}
