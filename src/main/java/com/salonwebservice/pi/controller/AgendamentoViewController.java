package com.salonwebservice.pi.controller;

import com.salonwebservice.pi.entity.Agendamento;
import com.salonwebservice.pi.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/delete/{id}")
    public String deleteAgendamento(@PathVariable int id, Model model) {
        agendamentoService.deleteAgendamento(id);
        return "redirect:/";
    }

    @PostMapping("/agendamentos/new")
    public String createAgendamento(@ModelAttribute Agendamento agendamento, RedirectAttributes redirectAttributes) {
        agendamentoService.saveAgendamento(agendamento);
        redirectAttributes.addFlashAttribute("mensagem", "Agendamento criado com sucesso!");
        return "redirect:/";
    }

    @GetMapping("/agendamento/form")
    public String mostrarAgendamentoForm(Model model) {
        model.addAttribute("agendamento", new Agendamento()); // Adiciona um novo objeto agendamento ao modelo
        return "index"; // Carrega a página index.html
    }


}
