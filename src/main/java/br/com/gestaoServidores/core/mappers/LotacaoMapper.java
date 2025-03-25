package br.com.gestaoServidores.core.mappers;

import br.com.gestaoServidores.core.exceptions.Exception404;
import br.com.gestaoServidores.modules.Lotacao;
import br.com.gestaoServidores.modules.Pessoa;
import br.com.gestaoServidores.modules.Unidade;
import br.com.gestaoServidores.record.lotacao.LotacaoDTO;
import br.com.gestaoServidores.repositories.PessoaRepository;
import br.com.gestaoServidores.repositories.UnidadeRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class LotacaoMapper {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private UnidadeRepository unidadeRepository;

    public abstract LotacaoDTO toDTO(Lotacao entity);

    public abstract Lotacao toEntity(LotacaoDTO dto);

    @Mapping(target = "id", ignore = true)
    public abstract void updateServer(LotacaoDTO dto, @MappingTarget Lotacao entity);

    Pessoa mapPessoa(Long id) {
        return this.pessoaRepository.findById(id).orElseThrow(() -> new Exception404("Pessoa não encontrada"));
    }

    Long mapId(Pessoa entity) {
        return entity.getId();
    }

    Unidade mapUnidade(Long id) {
        return this.unidadeRepository.findById(id).orElseThrow(() -> new Exception404("Unidade não encontrada"));
    }

    Long mapId(Unidade entity) {
        return entity.getId();
    }
}
