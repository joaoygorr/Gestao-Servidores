package br.com.gestaoServidores.core.mappers;

import br.com.gestaoServidores.modules.Cidade;
import br.com.gestaoServidores.record.cidade.CidadeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedSourcePolicy = ReportingPolicy.ERROR)
public interface CidadeMapper {

    CidadeDTO toDTO(Cidade cidade);

    Cidade toEntity(CidadeDTO cidadeDTO);
}
