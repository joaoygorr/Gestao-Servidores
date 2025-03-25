package br.com.gestaoServidores.services.servidorEfetivo;

import br.com.gestaoServidores.core.exceptions.Exception404;
import br.com.gestaoServidores.core.mappers.ServidorEfetivoMapper;
import br.com.gestaoServidores.modules.ServidorEfetivo;
import br.com.gestaoServidores.record.servidorEfetivo.ServidorEfetivoDTO;
import br.com.gestaoServidores.repositories.ServidorEfetivoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ServidorEfetivoServiceImpl implements ServidorEfetivoService {

    private final ServidorEfetivoRepository servidorEfetivoRepository;

    private final ServidorEfetivoMapper efetivoMapper;

    @Transactional
    @Override
    public ServidorEfetivo createEffectiveServer(ServidorEfetivo entity) {
        return this.servidorEfetivoRepository.save(entity);
    }

    @Override
    public ServidorEfetivo findByEffectiveServer(Long id) {
        return this.servidorEfetivoRepository.findById(id)
                .orElseThrow(() -> new Exception404("Servidor Efetivo não encontrado"));
    }

    @Transactional
    @Override
    public void deleteEffectiveServer(Long id) {
        findByEffectiveServer(id);
        this.servidorEfetivoRepository.deleteById(id);
    }

    @Transactional
    @Override
    public ServidorEfetivo updateEffectiveServer(Long id, ServidorEfetivoDTO dto) {
        ServidorEfetivo efetivo = findByEffectiveServer(id);
        efetivoMapper.updateServer(dto, efetivo);
        return this.servidorEfetivoRepository.save(efetivo);
    }

    @Override
    public Page<ServidorEfetivo> findAllEffectiveServer(Pageable pageable) {
        return this.servidorEfetivoRepository.findAll(pageable);
    }
}
