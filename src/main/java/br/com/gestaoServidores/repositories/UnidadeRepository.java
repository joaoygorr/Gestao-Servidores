package br.com.gestaoServidores.repositories;

import br.com.gestaoServidores.modules.Unidade;
import br.com.gestaoServidores.record.servidorEfetivo.ServidorDetalhesDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnidadeRepository extends JpaRepository<Unidade, Long> {

    @Query("""
    SELECT new br.com.gestaoServidores.record.servidorEfetivo.ServidorDetalhesDTO(
        p.nome, 
        p.dataNascimento, 
        new br.com.gestaoServidores.record.unidade.UnidadeDetalhesDTO(u.id, u.nome, u.sigla), 
        fp.hash
    )
        FROM Unidade u
        JOIN Lotacao l ON l.unidade.id = u.id
        JOIN Pessoa p ON l.pessoa.id = p.id
        LEFT JOIN FotoPessoa fp ON p.id = fp.pessoa.id
        WHERE u.id = :unidId
    """)
    List<ServidorDetalhesDTO> findByServidoresByUnidade(@Param("unidId") Long unidId);
}
