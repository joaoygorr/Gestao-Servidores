package br.com.gestaoServidores.services.cidade;

import br.com.gestaoServidores.modules.Cidade;
import br.com.gestaoServidores.repositories.CidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CidadeServiceImpl implements CidadeService {

    private final CidadeRepository cidadeRepository;

    public Cidade verifyAndCreateCity(Cidade cidade) {
        return this.cidadeRepository.findByNome(cidade.getNome())
                .orElseGet(() -> this.cidadeRepository.save(new Cidade(cidade.getNome(), cidade.getUf())));
    }
}
