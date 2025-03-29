package br.com.gestaoServidores.core.mappers;

import br.com.gestaoServidores.modules.User;
import br.com.gestaoServidores.record.user.UserDTO;
import br.com.gestaoServidores.record.user.UserLoginDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedSourcePolicy = ReportingPolicy.ERROR)
public interface UserMapper {

    User toEntity(UserLoginDTO userLoginDTO);

    User toEntity(UserDTO userDTO);
}
