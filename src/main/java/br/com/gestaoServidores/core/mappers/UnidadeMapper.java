package br.com.gestaoServidores.core.mappers;

import br.com.gestaoServidores.modules.Unidade;
import br.com.gestaoServidores.record.unidade.UnidadeDTO;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UnidadeMapper {

    Unidade toEntity(UnidadeDTO unidadeDTO);

    UnidadeDTO toDTO(Unidade unidade);

    @Mapping(target = "id", ignore = true)
    void updateUnit(UnidadeDTO dto, @MappingTarget Unidade entity);

}
