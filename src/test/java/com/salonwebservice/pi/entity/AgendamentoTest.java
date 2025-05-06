package com.salonwebservice.pi.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AgendamentoTest {

    @Test
    void testGettersAndSetters() {
        Agendamento agendamento = new Agendamento();

        agendamento.setIdScheduling(1);
        agendamento.setUserName("John Doe");
        agendamento.setTel("123456789");
        agendamento.setEmail("john.doe@example.com");
        agendamento.setDateDay("2023-10-01");
        agendamento.setDateTime("10:00");
        agendamento.setService("Haircut");
        agendamento.setPassword("password123");

        assertEquals(1, agendamento.getIdScheduling());
        assertEquals("John Doe", agendamento.getUserName());
        assertEquals("123456789", agendamento.getTel());
        assertEquals("john.doe@example.com", agendamento.getEmail());
        assertEquals("2023-10-01", agendamento.getDateDay());
        assertEquals("10:00", agendamento.getDateTime());
        assertEquals("Haircut", agendamento.getService());
        assertEquals("password123", agendamento.getPassword());
    }

    @Test
    void testAllArgsConstructor() {
        Agendamento agendamento = new Agendamento(1, "John Doe", "123456789", "john.doe@example.com", "2023-10-01", "10:00", "Haircut", "password123");

        assertEquals(1, agendamento.getIdScheduling());
        assertEquals("John Doe", agendamento.getUserName());
        assertEquals("123456789", agendamento.getTel());
        assertEquals("john.doe@example.com", agendamento.getEmail());
        assertEquals("2023-10-01", agendamento.getDateDay());
        assertEquals("10:00", agendamento.getDateTime());
        assertEquals("Haircut", agendamento.getService());
        assertEquals("password123", agendamento.getPassword());
    }

    @Test
    void testNoArgsConstructor() {
        Agendamento agendamento = new Agendamento();

        assertNotNull(agendamento);
    }

    @Test
    void testEqualsAndHashCode() {
        Agendamento agendamento1 = new Agendamento(1, "John Doe", "123456789", "john.doe@example.com", "2023-10-01", "10:00", "Haircut", "password123");
        Agendamento agendamento2 = new Agendamento(1, "John Doe", "123456789", "john.doe@example.com", "2023-10-01", "10:00", "Haircut", "password123");

        assertEquals(agendamento1, agendamento2);
        assertEquals(agendamento1.hashCode(), agendamento2.hashCode());
    }

    @Test
    void testToString() {
        Agendamento agendamento = new Agendamento(1, "John Doe", "123456789", "john.doe@example.com", "2023-10-01", "10:00", "Haircut", "password123");

        String expected = "Agendamento(idScheduling=1, userName=John Doe, tel=123456789, email=john.doe@example.com, dateDay=2023-10-01, dateTime=10:00, service=Haircut, password=password123)";
        assertEquals(expected, agendamento.toString());
    }
}