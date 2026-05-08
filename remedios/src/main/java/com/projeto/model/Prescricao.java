package com.projeto.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PRESCRICOES")
public class Prescricao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRESCRICAO")
    private Integer id;

    @Column(name = "PUBLICO_ALVO", nullable = false)
    private String publicoAlvo;

    @Column(name = "PRECAUCOES", nullable = false)
    private String precaucoes;

    @Column(name = "CONTRA_INDICACOES", nullable = false)
    private String contraIndicacoes;

    @Column(name = "COMPOSICAO")
    private String composicao;
    
    @Column(name = "EFEITOS", nullable = false)
    private String efeitos;

    // Getters e Setters

    public Integer getId() {
        return id;
    }

    public String getPublicoAlvo() {
        return publicoAlvo;
    }

    public void setPublicoAlvo(String publicoAlvo) {
        this.publicoAlvo = publicoAlvo;
    }

    public String getPrecaucoes() {
        return precaucoes;
    }

    public void setPrecaucoes(String precaucoes) {
        this.precaucoes = precaucoes;
    }

    public String getContraIndicacoes() {
        return contraIndicacoes;
    }

    public void setContraIndicacoes(String contraIndicacoes) {
        this.contraIndicacoes = contraIndicacoes;
    }

    public String getComposicao() {
        return composicao;
    }

    public void setComposicao(String composicao) {
        this.composicao = composicao;
    }

    public String getEfeitos() {
        return efeitos;
    }

    public void setEfeitos(String efeitos) {
        this.efeitos = efeitos;
    }
}