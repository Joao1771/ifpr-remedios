package com.projeto.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PUBLICO_ALVO")
public class PublicoAlvo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PUBLICO_ALVO")
    private Integer id;

    @Column(name = "NOME", nullable = false, length = 50)
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