package br.com.qbrada.qbrada.service;

import br.com.qbrada.qbrada.model.Participante;
import br.com.qbrada.qbrada.repository.ParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParticipanteService {

    @Autowired
    private ParticipanteRepository participanteRepository;

    public Participante addParticipante(Participante participante) {
        return participanteRepository.save(participante);
    }

    public Iterable<Participante> listarParticipantes() {
        Iterable<Participante> participantes = participanteRepository.findAll();
        return participantes;
    }

}
