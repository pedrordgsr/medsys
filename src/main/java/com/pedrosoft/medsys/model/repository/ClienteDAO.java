package com.pedrosoft.medsys.model.repository;

import com.pedrosoft.medsys.model.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteDAO extends JpaRepository <Cliente, Long> {
}
