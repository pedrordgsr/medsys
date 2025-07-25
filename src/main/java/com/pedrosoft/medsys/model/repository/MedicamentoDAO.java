package com.pedrosoft.medsys.model.repository;

import com.pedrosoft.medsys.model.entities.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentoDAO extends JpaRepository<Medicamento, Long> {
}
