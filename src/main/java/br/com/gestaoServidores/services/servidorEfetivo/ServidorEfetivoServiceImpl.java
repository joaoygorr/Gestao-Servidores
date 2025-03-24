package br.com.gestaoServidores.services.servidorEfetivo;

import br.com.gestaoServidores.core.exceptions.Exception404;
import br.com.gestaoServidores.modules.Pessoa;
import br.com.gestaoServidores.modules.ServidorEfetivo;
import br.com.gestaoServidores.repositories.ServidorEfetivoRepository;
import br.com.gestaoServidores.services.pessoa.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ServidorEfetivoServiceImpl implements ServidorEfetivoService {

    private final PessoaService pessoaService;

    private final ServidorEfetivoRepository servidorEfetivoRepository;

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

    @Override
    public void deleteEffectiveServer(Long id) {
        findByEffectiveServer(id);
        this.servidorEfetivoRepository.deleteById(id);
    }
}
