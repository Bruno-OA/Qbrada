package br.com.qbrada.qbrada.repository;

import br.com.qbrada.qbrada.model.Evento;
import br.com.qbrada.qbrada.model.Participante;
import org.springframework.data.repository.CrudRepository;

public interface ParticipanteRepository extends CrudRepository<Participante, String> {
    Iterable<Participante> findAllByEvento(Evento evento);

}
