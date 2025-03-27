package br.com.gestaoServidores.repositories;

import br.com.gestaoServidores.modules.ServidorEfetivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServidorEfetivoRepository extends JpaRepository<ServidorEfetivo, Long> {

    @Query("SELECT se FROM ServidorEfetivo se JOIN FETCH se.pessoa p WHERE p.nome = :nome")
    List<ServidorEfetivo> findByPessoaNomeWithFetch(@Param("nome") String nome);
}
