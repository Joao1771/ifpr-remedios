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

    @Column(name = "RESTRICAO", nullable = false)
    private String restricao;

    @Column(name = "CONTRA_INDICACOES", nullable = false)
    private String contraIndicacoes;

    @Column(name = "COMPOSICAO")
    private String composicao;

	@Column(name = "EFEITOS", nullable = false)
    private String efeitos;
    
    @Column(name = "VALIDADE", nullable = false)
    private String validade;

    // Getters e Setters

    public Integer getId() {
        return id;
    }

    public String getValidade() {
		return validade;
	}

	public String getPublicoAlvo() {
        return publicoAlvo;
    }

    public void setPublicoAlvo(String publicoAlvo) {
        this.publicoAlvo = publicoAlvo;
    }

    public String getRestricao() {
        return restricao;
    }

    public String getEfeitos() {
        return efeitos;
    }


    public void setRestricao(String restricao) {
        this.restricao = restricao;
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
    
    public void setEfeitos(String efeitos) {
        this.efeitos = efeitos;
    }
    
	public void setValidade(String validade) {
		this.validade = validade;
	}
}