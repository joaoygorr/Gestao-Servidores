package br.com.gestaoServidores.repositories;

import br.com.gestaoServidores.modules.Lotacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LotacaoRepository extends JpaRepository<Lotacao, Long> {
}
