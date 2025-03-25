package br.com.gestaoServidores.core.mappers;

import br.com.gestaoServidores.core.exceptions.Exception404;
import br.com.gestaoServidores.modules.Pessoa;
import br.com.gestaoServidores.modules.ServidorEfetivo;
import br.com.gestaoServidores.record.servidorEfetivo.ServidorEfetivoDTO;
import br.com.gestaoServidores.repositories.PessoaRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class ServidorEfetivoMapper {

    @Autowired
    private PessoaRepository pessoaRepository;

    public abstract ServidorEfetivo toEntity(ServidorEfetivoDTO dto);

    public abstract ServidorEfetivoDTO toDTO(ServidorEfetivo entity);

    @Mapping(target = "id", ignore = true)
    public abstract void updateServer(ServidorEfetivoDTO dto, @MappingTarget ServidorEfetivo entity);

    Pessoa mapPessoa(Long id) {
       return this.pessoaRepository.findById(id).orElseThrow(() -> new Exception404("Pessoa não encontrada"));
    }

    Long mapId(Pessoa entity) {
        return entity.getId();
    }
}
