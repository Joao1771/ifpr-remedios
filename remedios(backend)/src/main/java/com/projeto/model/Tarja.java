package com.projeto.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TARJAS")
public class Tarja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TARJA")
    private Integer id;

    @Column(name = "NOME", nullable = false, length = 30)
    private String nome;

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}