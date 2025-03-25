package br.com.gestaoServidores.core.mappers;

import br.com.gestaoServidores.modules.FotoPessoa;
import br.com.gestaoServidores.modules.Pessoa;
import br.com.gestaoServidores.record.fotoPessoa.FotoPessoaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class FotoPessoaMapper {

    @Mapping(target = "images", ignore = true)
    public abstract FotoPessoaDTO toDTO(FotoPessoa fotoPessoa);

    Long mapId(Pessoa entity) {
        return entity.getId();
    }
}

