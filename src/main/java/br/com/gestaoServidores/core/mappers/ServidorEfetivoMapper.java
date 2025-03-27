package br.com.gestaoServidores.core.mappers;

import br.com.gestaoServidores.core.exceptions.Exception404;
import br.com.gestaoServidores.modules.Endereco;
import br.com.gestaoServidores.modules.Pessoa;
import br.com.gestaoServidores.modules.ServidorEfetivo;
import br.com.gestaoServidores.record.endereco.EnderecoDTO;
import br.com.gestaoServidores.record.servidorEfetivo.ServidorEfetivoDTO;
import br.com.gestaoServidores.record.servidorEfetivo.ServidorEnderecoDTO;
import br.com.gestaoServidores.repositories.PessoaRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class ServidorEfetivoMapper {

    @Autowired
    private PessoaRepository pessoaRepository;

    public abstract ServidorEfetivo toEntity(ServidorEfetivoDTO dto);

    public abstract ServidorEfetivoDTO toDTO(ServidorEfetivo entity);

    @Mapping(target = "id", ignore = true)
    public abstract void updateServer(ServidorEfetivoDTO dto, @MappingTarget ServidorEfetivo entity);

    public abstract EnderecoDTO mapToEnderecoDTO(Endereco endereco);

    @Mapping(source = "pessoa.nome", target = "nome")
    @Mapping(source = "pessoa.enderecos", target = "enderecos")
    public abstract ServidorEnderecoDTO toServidorEndereco(ServidorEfetivo entity);

    public List<ServidorEnderecoDTO> toServidorEnderecoList(List<ServidorEfetivo> entityList) {
        return entityList.stream()
                .map(this::toServidorEndereco)
                .toList();
    }

    Pessoa mapPessoa(Long id) {
       return this.pessoaRepository.findById(id).orElseThrow(() -> new Exception404("Pessoa não encontrada"));
    }

    Long mapId(Pessoa entity) {
        return entity.getId();
    }
}
