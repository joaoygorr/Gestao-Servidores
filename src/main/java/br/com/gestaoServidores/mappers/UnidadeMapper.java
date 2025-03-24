package br.com.gestaoServidores.mappers;

import br.com.gestaoServidores.modules.Unidade;
import br.com.gestaoServidores.record.unidade.UnidadeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedSourcePolicy = ReportingPolicy.ERROR)
public interface UnidadeMapper {

    Unidade toEntity(UnidadeDTO unidadeDTO);

    UnidadeDTO toDTO(Unidade unidade);
}
