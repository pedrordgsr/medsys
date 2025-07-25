package com.pedrosoft.medsys.model.dto;

import com.pedrosoft.medsys.model.entities.Cliente;
import com.pedrosoft.medsys.model.entities.Filial;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class VendaDTO {
    private Long id;
    private LocalDateTime dataHora;
    private BigDecimal total;
    private Cliente cliente;
    private Filial filial;
}
