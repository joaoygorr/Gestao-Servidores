package br.com.gestaoServidores.services.servidorEfetivo;

import br.com.gestaoServidores.core.exceptions.Exception404;
import br.com.gestaoServidores.core.mappers.ServidorEfetivoMapper;
import br.com.gestaoServidores.modules.ServidorEfetivo;
import br.com.gestaoServidores.record.servidorEfetivo.ServidorDTO;
import br.com.gestaoServidores.record.servidorEfetivo.ServidorEfetivoDTO;
import br.com.gestaoServidores.repositories.ServidorEfetivoRepository;
import br.com.gestaoServidores.repositories.UnidadeRepository;
import br.com.gestaoServidores.services.fotoPessoa.FotoPessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServidorEfetivoServiceImpl implements ServidorEfetivoService {

    private final ServidorEfetivoRepository servidorEfetivoRepository;

    private final ServidorEfetivoMapper efetivoMapper;

    private final UnidadeRepository unidadeRepository;

    private final FotoPessoaService fotoPessoaService;

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

    @Override
    public List<ServidorEfetivo> findByServidorAndPessoaNome(String nome) {
        return this.servidorEfetivoRepository.findByPessoaNomeWithFetch(nome);
    }

    @Override
    public List<ServidorDTO> getServerByUnit(Long unidId) {
        return this.unidadeRepository.findByServidoresByUnidade(unidId).stream()
                .map(dto -> {
                    try {
                        return new ServidorDTO(
                                dto.nome(),
                                Period.between(dto.dataNascimento(), LocalDate.now()).getYears(),
                                dto.unidadeLotacao(),
                                this.fotoPessoaService.getImageByhash(dto.fotografia())
                        );
                    } catch (Exception e) {
                        e.printStackTrace();
                        return new ServidorDTO(
                                dto.nome(),
                                Period.between(dto.dataNascimento(), LocalDate.now()).getYears(),
                                dto.unidadeLotacao(),
                                "imagem_default.jpg"
                        );
                    }
                })
                .toList();
    }
}
