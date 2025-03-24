package br.com.gestaoServidores.repositories;

import br.com.gestaoServidores.modules.ServidorEfetivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServidorEfetivoRepository extends JpaRepository<ServidorEfetivo, Long> {
}
