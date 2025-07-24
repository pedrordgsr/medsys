package com.pedrosoft.medsys.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "filial")
public class Filial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100, nullable = false)
    private String endereco;

    @Column(length = 15, nullable = false)
    private String telefone;

    @OneToMany
    private List<Medicamento> medicamentos;

    @OneToMany
    private List<Venda> vendas;

}
