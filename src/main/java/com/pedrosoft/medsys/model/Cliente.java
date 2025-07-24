package com.pedrosoft.medsys.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 14, nullable = false)
    private String cpf;

}
