package com.salonwebservice.pi.service;

import com.salonwebservice.pi.entity.Agendamento;
import com.salonwebservice.pi.exception.ResourceNotFoundException;
import com.salonwebservice.pi.repository.AgendamentoRepository; // Altere o nome do repositório para AgendamentoRepository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository; // Altere o nome do repositório para AgendamentoRepository

    // Salvar novo agendamento
    public Agendamento saveAgendamento(Agendamento agendamento) {
        return agendamentoRepository.save(agendamento);
    }

    // Buscar todos os agendamentos
    public List<Agendamento> getAllAgendamentos() {
        return agendamentoRepository.findAll();
    }

    // Buscar agendamento por ID
    public Optional<Agendamento> getAgendamentoById(int id) {
        return agendamentoRepository.findById(id);
    }

    public Agendamento findAgendamentoById(int id) {
        return agendamentoRepository.findById(id)
                .orElse(null); // Retorna null se o agendamento não for encontrado
    }

    // Atualizar um agendamento existente
    public Agendamento updateAgendamento(int id, Agendamento updatedAgendamento) {

        Agendamento existingAgendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agendamento não encontrado com o id: " + id));

        if (updatedAgendamento.getUserName() != null) {
            existingAgendamento.setUserName(updatedAgendamento.getUserName());
        }
        if (updatedAgendamento.getTel() != null) {
            existingAgendamento.setTel(updatedAgendamento.getTel());
        }
        if (updatedAgendamento.getEmail() != null) {
            existingAgendamento.setEmail(updatedAgendamento.getEmail());
        }
        if (updatedAgendamento.getDateDay() != null) {
            existingAgendamento.setDateDay(updatedAgendamento.getDateDay());
        }
        if (updatedAgendamento.getDateTime() != null) {
            existingAgendamento.setDateTime(updatedAgendamento.getDateTime());
        }
        if (updatedAgendamento.getService() != null) {
            existingAgendamento.setService(updatedAgendamento.getService());
        }
        if (updatedAgendamento.getPassword() != null) {
            existingAgendamento.setPassword(updatedAgendamento.getPassword());
        }

        return agendamentoRepository.save(existingAgendamento);
    }

    // Deletar um agendamento por ID
    public void deleteAgendamento(int id) {
        if (agendamentoRepository.existsById(id)) {
            agendamentoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Agendamento não encontrado com o id: " + id);
        }
    }
}
