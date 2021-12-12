package br.com.alura.forum.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.alura.forum.modelo.Estado;
import br.com.alura.forum.modelo.Regiao;

public interface EstadoRepository extends JpaRepository<Estado, Long> {

	List<Estado> findAllByRegiao(Regiao regiao);

}
