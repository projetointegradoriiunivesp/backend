package com.salonwebservice.pi.service;

import com.salonwebservice.pi.entity.Agendamento;
import com.salonwebservice.pi.exception.ResourceNotFoundException;
import com.salonwebservice.pi.repository.AgendamentoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AgendamentoServiceTest {

    @Mock
    private AgendamentoRepository agendamentoRepository;

    @InjectMocks
    private AgendamentoService agendamentoService;

    @Test
    void testSaveAgendamento() {
        Agendamento agendamento = new Agendamento();
        agendamento.setIdScheduling(1);

        when(agendamentoRepository.save(agendamento)).thenReturn(agendamento);

        Agendamento savedAgendamento = agendamentoService.saveAgendamento(agendamento);

        assertNotNull(savedAgendamento);
        assertEquals(1, savedAgendamento.getIdScheduling());
        verify(agendamentoRepository, times(1)).save(agendamento);
    }

    @Test
    void testGetAllAgendamentos() {
        Agendamento agendamento1 = new Agendamento();
        Agendamento agendamento2 = new Agendamento();

        when(agendamentoRepository.findAll()).thenReturn(Arrays.asList(agendamento1, agendamento2));

        List<Agendamento> agendamentos = agendamentoService.getAllAgendamentos();

        assertNotNull(agendamentos);
        assertEquals(2, agendamentos.size());
        verify(agendamentoRepository, times(1)).findAll();
    }

    @Test
    void testGetAgendamentoById() {
        Agendamento agendamento = new Agendamento();
        agendamento.setIdScheduling(1);

        when(agendamentoRepository.findById(1)).thenReturn(Optional.of(agendamento));

        Optional<Agendamento> foundAgendamento = agendamentoService.getAgendamentoById(1);

        assertTrue(foundAgendamento.isPresent());
        assertEquals(1, foundAgendamento.get().getIdScheduling());
        verify(agendamentoRepository, times(1)).findById(1);
    }

    @Test
    void testFindAgendamentoByIdNotFound() {
        when(agendamentoRepository.findById(1)).thenReturn(Optional.empty());

        Agendamento agendamento = agendamentoService.findAgendamentoById(1);

        assertNull(agendamento);
        verify(agendamentoRepository, times(1)).findById(1);
    }

    @Test
    void testUpdateAgendamento() {
        Agendamento existingAgendamento = new Agendamento();
        existingAgendamento.setIdScheduling(1);
        existingAgendamento.setUserName("Old Name");

        Agendamento updatedAgendamento = new Agendamento();
        updatedAgendamento.setUserName("New Name");

        when(agendamentoRepository.findById(1)).thenReturn(Optional.of(existingAgendamento));
        when(agendamentoRepository.save(existingAgendamento)).thenReturn(existingAgendamento);

        Agendamento result = agendamentoService.updateAgendamento(1, updatedAgendamento);

        assertNotNull(result);
        assertEquals("New Name", result.getUserName());
        verify(agendamentoRepository, times(1)).findById(1);
        verify(agendamentoRepository, times(1)).save(existingAgendamento);
    }

    @Test
    void testUpdateAgendamentoNotFound() {
        Agendamento updatedAgendamento = new Agendamento();

        when(agendamentoRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> agendamentoService.updateAgendamento(1, updatedAgendamento));
        verify(agendamentoRepository, times(1)).findById(1);
    }

    @Test
    void testDeleteAgendamento() {
        when(agendamentoRepository.existsById(1)).thenReturn(true);

        agendamentoService.deleteAgendamento(1);

        verify(agendamentoRepository, times(1)).existsById(1);
        verify(agendamentoRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteAgendamentoNotFound() {
        when(agendamentoRepository.existsById(1)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> agendamentoService.deleteAgendamento(1));
        verify(agendamentoRepository, times(1)).existsById(1);
    }

    @Test
    void testUpdateAgendamentoFields() {
        Agendamento existingAgendamento = new Agendamento();
        existingAgendamento.setIdScheduling(1);
        existingAgendamento.setUserName("Old Name");
        existingAgendamento.setTel("123456789");
        existingAgendamento.setEmail("old.email@example.com");
        existingAgendamento.setDateDay("2023-10-01");
        existingAgendamento.setDateTime("10:00");
        existingAgendamento.setService("Old Service");
        existingAgendamento.setPassword("oldPassword");

        Agendamento updatedAgendamento = new Agendamento();
        updatedAgendamento.setUserName("New Name");
        updatedAgendamento.setTel("987654321");
        updatedAgendamento.setEmail("new.email@example.com");
        updatedAgendamento.setDateDay("2023-11-01");
        updatedAgendamento.setDateTime("11:00");
        updatedAgendamento.setService("New Service");
        updatedAgendamento.setPassword("newPassword");

        when(agendamentoRepository.findById(1)).thenReturn(Optional.of(existingAgendamento));
        when(agendamentoRepository.save(existingAgendamento)).thenReturn(existingAgendamento);

        Agendamento result = agendamentoService.updateAgendamento(1, updatedAgendamento);

        assertNotNull(result);
        assertEquals("New Name", result.getUserName());
        assertEquals("987654321", result.getTel());
        assertEquals("new.email@example.com", result.getEmail());
        assertEquals("2023-11-01", result.getDateDay());
        assertEquals("11:00", result.getDateTime());
        assertEquals("New Service", result.getService());
        assertEquals("newPassword", result.getPassword());

        verify(agendamentoRepository, times(1)).findById(1);
        verify(agendamentoRepository, times(1)).save(existingAgendamento);
    }
}