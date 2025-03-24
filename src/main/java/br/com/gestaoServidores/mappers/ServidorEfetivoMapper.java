package br.com.gestaoServidores.mappers;

import br.com.gestaoServidores.controllers.ServidorEfetivoController;
import br.com.gestaoServidores.record.servidorEfetivo.ServidorEfetivoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedSourcePolicy = ReportingPolicy.ERROR)
public interface ServidorEfetivoMapper {

    ServidorEfetivoController toEntity(ServidorEfetivoDTO dto);

    ServidorEfetivoDTO toDTO(ServidorEfetivoController entity);
}
