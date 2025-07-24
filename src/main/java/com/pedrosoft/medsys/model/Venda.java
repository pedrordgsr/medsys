package com.pedrosoft.medsys.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "venda")
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    @Column(precision = 10, scale = 2)
    private BigDecimal total;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Filial filial;
}
