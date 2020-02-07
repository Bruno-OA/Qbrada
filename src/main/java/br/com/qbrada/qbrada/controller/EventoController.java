
package br.com.qbrada.qbrada.controller;

import br.com.qbrada.qbrada.model.Evento;
import br.com.qbrada.qbrada.model.Participante;
import br.com.qbrada.qbrada.repository.EventoRepository;
import br.com.qbrada.qbrada.service.EventoService;
import br.com.qbrada.qbrada.service.ParticipanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class EventoController {

    @Autowired
    private EventoService service;
    @Autowired
    private ParticipanteService participanteService;

    @GetMapping
    public String index(){
        return "index";
    }

    @GetMapping("/cadastro/admin/Qbrada")
    public String cadastrar() {
        return "cadastro";
    }

    @PostMapping("/cadastro/admin/cadastrar")
    public String cadastrarEvento(@Valid Evento evento) {
        service.cadastrarEvento(evento);
        return "cadastro";
    }

    @ExceptionHandler({BindException.class})
    public String tratarErrosValidacao(BindException exception, Model model) {
        model.addAttribute("erros", exception.getFieldErrors());
        return "cadastro";
    }

    @GetMapping("/eventos")
    public ModelAndView listarEventos() {
        ModelAndView pagina = new ModelAndView("listarEventos");
        Iterable<Evento> eventos = service.listarEventos();
        pagina.addObject("eventos", eventos);
        return pagina;
    }

    @PostMapping("/buscar")
    public ModelAndView pesquisa(@RequestParam ("nome") String nome) {
        ModelAndView mdv = new ModelAndView("listarEventos");
        mdv.addObject("eventos", service.listarNome(nome));
        return mdv;
    }

    @GetMapping("evento")
    public String buscarNome(@RequestParam("nome") String nome, Model model) {
        Evento evento = service.buscarNome(nome);
        if(evento != null){
            model.addAttribute("evento", evento);
            return "listarEvento";
        }
        else{
            model.addAttribute("msg", "O evento " + nome + " não foi encontrado!! procure novamente");
            return "index";
        }
    }

    @GetMapping("evento/{id}")
    public String buscarNomePagina(@PathVariable("id") long id, Model model) {
        Evento evento = service.buscarId(id);
        if (evento != null) {
            Iterable<Participante> participantes = participanteService.listarParticipantes();
            model.addAttribute("evento", evento);
            model.addAttribute("participantes", participantes);
            return "listarEvento";
        } else {
            model.addAttribute("msg", "O evento não foi encontrado!! procure novamente");
            return "index";
        }
    }

    @PostMapping("evento/{id}")
    public String listarParticipantes(@PathVariable("id") long id, @Valid Participante participante){
        participanteService.addParticipante(participante);
        return "listarEvento";
    }

    @GetMapping("/excluir/{id}")
    public String excluirFilme(@PathVariable("id") long id, Model model){
        service.excluirEvento(id);
        model.addAttribute("excluir", "Um item foi excluido");
        return "redirect:/eventos";
    }

    @GetMapping("/desativar/{id}")
    public String desativar(@PathVariable("id") long id, Model model){
        service.desativar(id);
        model.addAttribute("desativar", "Um item foi desativado");
        return "redirect:/eventos";
    }

    @GetMapping("/empresas")
    public String empresas(){
        return "empresas";
    }

}
