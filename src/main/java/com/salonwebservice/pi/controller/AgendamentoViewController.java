package com.salonwebservice.pi.controller;

import com.salonwebservice.pi.entity.Agendamento;
import com.salonwebservice.pi.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping()
public class AgendamentoViewController {

    @Autowired
    private AgendamentoService agendamentoService;

    //Lista de agendamento apresentada no index
    @GetMapping
    public String getAllAgendamentos(Model model) {
        List<Agendamento> agendamentos = agendamentoService.getAllAgendamentos();
        model.addAttribute("agendamentos", agendamentos);
        return "index";
    }

    //Renderiza página pra editar um agendamento
    @GetMapping("/edit/{id}")
    public String editAgendamentoForm(@PathVariable int id, Model model) {
        Optional<Agendamento> agendamento = agendamentoService.getAgendamentoById(id);
        if (agendamento.isPresent()) {
            model.addAttribute("agendamento", agendamento.get());
            return "agendamento-form";
        } else {
            return "index";
        }
    }

    //Salva agendamento no banco de dados e renderiza pra index
    @PostMapping("/edit/{id}")
    public String updateAgendamento(@PathVariable int id, @ModelAttribute Agendamento agendamento, @RequestParam("senha") String senha) {
        Optional<Agendamento> existingAgendamento = agendamentoService.getAgendamentoById(id);

        if (existingAgendamento.isPresent()) {
            if (existingAgendamento.get().getPassword().equals(senha)) {
                agendamentoService.updateAgendamento(id, agendamento);
                return "redirect:/";  // Redireciona para a página inicial
            } else {
                // Senha incorreta, redireciona com o parâmetro de erro
                return "redirect:/edit/" + id + "?error=senhaIncorreta";
            }
        } else {
            return "redirect:/";
        }
    }



//
//    // Exibe o formulário para criar um novo agendamento
//    @GetMapping("/new")
//    public String showAgendamentoForm(Model model) {
//        model.addAttribute("agendamento", new Agendamento());
//        return "agendamento-form"; // Nome da view para o formulário de agendamento
//    }
//
//    // Processa o formulário de criação de novo agendamento
//    @PostMapping("/new")
//    public String saveAgendamento(@ModelAttribute("agendamento") Agendamento agendamento) {
//        agendamentoService.saveAgendamento(agendamento);
//        return "redirect:/web/agendamentos"; // Redireciona após salvar o agendamento
//    }
//
//
//
//
//    // Exclui um agendamento
//    @GetMapping("/delete/{id}")
//    public String deleteAgendamento(@PathVariable int id) {
//        agendamentoService.deleteAgendamento(id);
//        return "redirect:/web/agendamentos";
//    }
}
