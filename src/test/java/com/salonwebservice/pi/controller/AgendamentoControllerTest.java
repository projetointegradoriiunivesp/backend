package com.salonwebservice.pi.controller;

import com.salonwebservice.pi.entity.Agendamento;
import com.salonwebservice.pi.service.AgendamentoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AgendamentoController.class)
class AgendamentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AgendamentoService agendamentoService;

    @Test
    void testCreateAgendamento() throws Exception {
        Agendamento agendamento = new Agendamento();
        agendamento.setIdScheduling(1);
        agendamento.setPassword("1234");

        Mockito.when(agendamentoService.saveAgendamento(any(Agendamento.class))).thenReturn(agendamento);

        mockMvc.perform(post("/agendamentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"password\":\"1234\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idScheduling").value(1)); // Corrigido para idScheduling
    }

    @Test
    void testGetAllAgendamentos() throws Exception {
        Agendamento agendamento1 = new Agendamento();
        agendamento1.setIdScheduling(1);

        Agendamento agendamento2 = new Agendamento();
        agendamento2.setIdScheduling(2);

        Mockito.when(agendamentoService.getAllAgendamentos()).thenReturn(Arrays.asList(agendamento1, agendamento2));

        mockMvc.perform(get("/agendamentos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void testGetAgendamentoById() throws Exception {
        Agendamento agendamento = new Agendamento();
        agendamento.setIdScheduling(1);

        Mockito.when(agendamentoService.getAgendamentoById(1)).thenReturn(Optional.of(agendamento));

        mockMvc.perform(get("/agendamentos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idScheduling").value(1)); // Corrigido para idScheduling
    }

    @Test
    void testUpdateAgendamento() throws Exception {
        Agendamento updatedAgendamento = new Agendamento();
        updatedAgendamento.setIdScheduling(1);

        Mockito.when(agendamentoService.updateAgendamento(eq(1), any(Agendamento.class))).thenReturn(updatedAgendamento);

        mockMvc.perform(put("/agendamentos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"password\":\"1234\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idScheduling").value(1));
    }

    @Test
    void testDeleteAgendamento() throws Exception {
        Agendamento agendamento = new Agendamento();
        agendamento.setIdScheduling(1);
        agendamento.setPassword("1234");

        Mockito.when(agendamentoService.findAgendamentoById(1)).thenReturn(agendamento);

        mockMvc.perform(delete("/agendamentos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"password\":\"1234\"}"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteAgendamentoWithWrongPassword() throws Exception {
        Agendamento agendamento = new Agendamento();
        agendamento.setIdScheduling(1);
        agendamento.setPassword("1234");

        Mockito.when(agendamentoService.findAgendamentoById(1)).thenReturn(agendamento);

        mockMvc.perform(delete("/agendamentos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"password\":\"wrong\"}"))
                .andExpect(status().isForbidden());
    }
}