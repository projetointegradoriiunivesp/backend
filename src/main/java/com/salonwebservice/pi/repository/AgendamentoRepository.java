package com.salonwebservice.pi.repository;

import com.salonwebservice.pi.entity.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {
}
