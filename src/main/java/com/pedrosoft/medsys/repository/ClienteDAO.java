package com.pedrosoft.medsys.repository;

import com.pedrosoft.medsys.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteDAO extends JpaRepository <Cliente, Long> {
}
