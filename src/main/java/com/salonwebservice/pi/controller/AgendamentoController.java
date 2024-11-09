package com.salonwebservice.pi.controller;

import com.salonwebservice.pi.entity.Agendamento;
import com.salonwebservice.pi.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/agendamentos")  // Alterado de "/users" para "/agendamentos"
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @PostMapping
    public ResponseEntity<Agendamento> createAgendamento(@RequestBody Agendamento agendamento) {
        Agendamento createdAgendamento = agendamentoService.saveAgendamento(agendamento);
        return new ResponseEntity<>(createdAgendamento, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Agendamento>> getAllAgendamentos() {
        List<Agendamento> agendamentos = agendamentoService.getAllAgendamentos();
        return new ResponseEntity<>(agendamentos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> getAgendamentoById(@PathVariable int id) {
        Optional<Agendamento> agendamento = agendamentoService.getAgendamentoById(id);
        return agendamento.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agendamento> updateAgendamento(@PathVariable int id, @RequestBody Agendamento updatedAgendamento) {
        try {
            Agendamento agendamento = agendamentoService.updateAgendamento(id, updatedAgendamento);
            return new ResponseEntity<>(agendamento, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgendamento(@PathVariable int id, @RequestBody Map<String, String> requestBody) {
        String password = requestBody.get("password");

        Agendamento agendamento = agendamentoService.findAgendamentoById(id);
        if (agendamento == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Verifica se a senha fornecida corresponde à senha do agendamento
        if (!agendamento.getPassword().equals(password)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);  // Retorna 403 se a senha estiver incorreta
        }

        agendamentoService.deleteAgendamento(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
