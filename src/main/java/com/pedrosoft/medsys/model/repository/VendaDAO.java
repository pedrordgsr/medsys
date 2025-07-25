package com.pedrosoft.medsys.model.repository;

import com.pedrosoft.medsys.model.entities.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaDAO extends JpaRepository<Venda, Long> {
}
