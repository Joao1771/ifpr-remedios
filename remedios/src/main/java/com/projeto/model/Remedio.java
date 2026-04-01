package com.projeto.model;

import jakarta.persistence.*;

@Entity
@Table(name = "REMEDIOS")
public class Remedio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_REMEDIO")
    private Long id;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "BULA", nullable = false)
    private String bula;

    @Column(name = "ID_USUARIO")
    private Long idUsuario;

    @ManyToOne
    @JoinColumn(name = "ID_DESCRICAO")
    private Descricao descricao;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBula() {
        return bula;
    }

    public void setBula(String bula) {
        this.bula = bula;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Descricao getDescricao() {
       return descricao;
   }

   public void setDescricao(Descricao descricao) {
       this.descricao = descricao;
   }
}