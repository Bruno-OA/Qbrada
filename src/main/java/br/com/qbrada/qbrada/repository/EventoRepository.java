
package br.com.qbrada.qbrada.repository;

import br.com.qbrada.qbrada.model.Evento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EventoRepository extends CrudRepository<Evento, Long> {

    Optional<Evento> findByNome(String nome);

    @Override
    @Query("select f from Evento f where f.ativo=true")
    List<Evento> findAll();

    @Query("select e from Evento e where e.nome like %?1%")
    List<Evento> findEventoByNome (String nome);
}