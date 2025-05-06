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

@WebMvcTest(AgendamentoViewController.class)
class AgendamentoViewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AgendamentoService agendamentoService;

    @Test
    void testGetAllAgendamentos() throws Exception {
        Agendamento agendamento1 = new Agendamento();
        agendamento1.setIdScheduling(1);

        Agendamento agendamento2 = new Agendamento();
        agendamento2.setIdScheduling(2);

        Mockito.when(agendamentoService.getAllAgendamentos()).thenReturn(Arrays.asList(agendamento1, agendamento2));

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("agendamentos"))
                .andExpect(view().name("index"));
    }

    @Test
    void testEditAgendamentoForm() throws Exception {
        Agendamento agendamento = new Agendamento();
        agendamento.setIdScheduling(1);

        Mockito.when(agendamentoService.getAgendamentoById(1)).thenReturn(Optional.of(agendamento));

        mockMvc.perform(get("/edit/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("agendamento"))
                .andExpect(view().name("agendamento-form"));
    }

    @Test
    void testEditAgendamentoFormNotFound() throws Exception {
        Mockito.when(agendamentoService.getAgendamentoById(1)).thenReturn(Optional.empty());

        mockMvc.perform(get("/edit/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void testUpdateAgendamento() throws Exception {
        Agendamento agendamento = new Agendamento();
        agendamento.setIdScheduling(1);
        agendamento.setPassword("1234");

        Mockito.when(agendamentoService.getAgendamentoById(1)).thenReturn(Optional.of(agendamento));

        mockMvc.perform(post("/edit/1")
                        .param("senha", "1234")
                        .flashAttr("agendamento", agendamento))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void testUpdateAgendamentoWrongPassword() throws Exception {
        Agendamento agendamento = new Agendamento();
        agendamento.setIdScheduling(1);
        agendamento.setPassword("1234");

        Mockito.when(agendamentoService.getAgendamentoById(1)).thenReturn(Optional.of(agendamento));

        mockMvc.perform(post("/edit/1")
                        .param("senha", "wrong")
                        .flashAttr("agendamento", agendamento))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/edit/1?error=senhaIncorreta"));
    }

    @Test
    void testDeleteAgendamento() throws Exception {
        mockMvc.perform(get("/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void testCreateAgendamento() throws Exception {
        Agendamento agendamento = new Agendamento();
        agendamento.setIdScheduling(1);

        mockMvc.perform(post("/agendamentos/new")
                        .flashAttr("agendamento", agendamento))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void testMostrarAgendamentoForm() throws Exception {
        mockMvc.perform(get("/agendamento/form"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("agendamento"))
                .andExpect(view().name("index"));
    }
}