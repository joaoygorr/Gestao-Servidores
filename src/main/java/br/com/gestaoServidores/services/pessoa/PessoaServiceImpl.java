package br.com.gestaoServidores.services.pessoa;

import br.com.gestaoServidores.mappers.PessoaMapper;
import br.com.gestaoServidores.record.pessoa.PessoaDTO;
import br.com.gestaoServidores.repositories.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository pessoaRepository;

    private final PessoaMapper pessoaMapper;

    @Transactional
    @Override
    public PessoaDTO createPerson(PessoaDTO dto) {
        return this.pessoaMapper.toDTO(
                this.pessoaRepository.save(
                this.pessoaMapper.toEntity(dto)));
    }
}
