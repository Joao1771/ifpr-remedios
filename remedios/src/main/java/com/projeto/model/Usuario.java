package com.projeto.model;

import jakarta.persistence.*;

@Entity
@Table(name = "USUARIOS")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private Integer id;

    @Column(name = "SENHA", nullable = false)
    private String senha;

    @Column(name = "TIPO", nullable = false)
    private String tipo;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    // Getters e Setters

    public Integer getId() {
        return id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return email;
    }

    public void setNome(String nome) {
        this.email = nome;
    }
}