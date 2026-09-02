package com.projeto.model;

import jakarta.persistence.*;

@Entity
@Table(name = "CIDADES")
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CIDADE")
    private Integer id;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "UF", columnDefinition = "CHAR(2)")
    private String uf;

    // Getters e Setters

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}