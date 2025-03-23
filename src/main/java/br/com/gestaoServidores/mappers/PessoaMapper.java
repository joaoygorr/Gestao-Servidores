package br.com.gestaoServidores.mappers;

import br.com.gestaoServidores.modules.Pessoa;
import br.com.gestaoServidores.record.pessoa.PessoaDTO;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedSourcePolicy = ReportingPolicy.ERROR)
public interface PessoaMapper {

    PessoaDTO toDTO(Pessoa pessoa);

    Pessoa toEntity(PessoaDTO pessoaRecord);
}
