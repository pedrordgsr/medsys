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

    @OneToMany(mappedBy = "filial",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Medicamento> medicamentos;

    @OneToMany(mappedBy = "filial",
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    private List<Venda> vendas;

}
