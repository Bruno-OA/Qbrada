package br.com.qbrada.qbrada.service;

import br.com.qbrada.qbrada.model.Evento;
import br.com.qbrada.qbrada.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    private EventoRepository repository;

    public Evento cadastrarEvento(Evento evento) {
        evento.setAtivo(Boolean.TRUE);
        evento.setDataCriacao(LocalDate.now());
        return repository.save(evento);
    }

    public Iterable<Evento> listarEventos() {
        Iterable<Evento> eventos = repository.findAll();
        return eventos;
    }

    public Evento buscarNome(String nome){
        Optional<Evento> eventos = repository.findByNome(nome);
        if (eventos.isPresent()) {
            return eventos.get();
        }
        return null;
    }

    public void excluirEvento(long id) {
        repository.deleteById(id);
    }

    public void desativar(long id) {
        Optional<Evento> eventos = repository.findById(id);
        if(eventos.get() != null){
            eventos.get().setAtivo(false);
            repository.save(eventos.get());
        }
    }

    public Iterable<Evento> listarNome(String nome) {
        return repository.findEventoByNome(nome);
    }

    public Evento buscarId(long id) {
        Optional<Evento> eventos = repository.findById(id);
        if (eventos.isPresent()) {
            return eventos.get();
        }
        return null;
    }
}