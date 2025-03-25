package br.com.gestaoServidores.mappers;

import br.com.gestaoServidores.core.exceptions.Exception404;
import br.com.gestaoServidores.modules.Pessoa;
import br.com.gestaoServidores.modules.ServidorTemporario;
import br.com.gestaoServidores.record.servidorTemporario.ServidorTemporarioDTO;
import br.com.gestaoServidores.repositories.PessoaRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class ServidorTemporarioMapper {

    @Autowired
    private PessoaRepository pessoaRepository;

    public abstract ServidorTemporario toEntity(ServidorTemporarioDTO dto);

    public abstract ServidorTemporarioDTO toDTO(ServidorTemporario entity);

    @Mapping(target = "id", ignore = true)
    public abstract void updateServer(ServidorTemporarioDTO dto, @MappingTarget ServidorTemporario entity);

    Pessoa mapPessoa(Long id) {
        return this.pessoaRepository.findById(id).orElseThrow(() -> new Exception404("Pessoa não encontrada"));
    }

    Long mapId(Pessoa entity) {
        return entity.getId();
    }
}
