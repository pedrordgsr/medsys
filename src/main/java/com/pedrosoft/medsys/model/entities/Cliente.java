package com.pedrosoft.medsys.model.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name="cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 14, nullable = false)
    private String cpf;

    @OneToMany(mappedBy = "cliente",
                cascade = CascadeType.ALL,
                orphanRemoval = true)
    private List<Venda> vendas;

}
