package br.com.gestaoServidores.repositories;

import br.com.gestaoServidores.modules.FotoPessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoPessoaRepository extends JpaRepository<FotoPessoa, Long> {
}
