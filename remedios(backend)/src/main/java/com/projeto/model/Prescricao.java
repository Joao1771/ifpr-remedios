package com.projeto.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PRESCRICOES")
public class Prescricao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRESCRICAO")
    private Integer id;

    @Column(name = "RESTRICAO", nullable = false)
    private String restricao;

    @Column(name = "CONTRA_INDICACOES", nullable = false)
    private String contraIndicacoes;

	@Column(name = "EFEITOS", nullable = false)
    private String efeitos;
    
    @Column(name = "VALIDADE", nullable = false)
    private String validade;
    
    @Column(name = "CONSERVACAO", nullable = false)
    private String conservacao;

    public String getConservacao() {
      return conservacao;
   }

    public void setConservacao(String conservacao) {
       this.conservacao = conservacao;
    }

    public Integer getId() {
        return id;
    }

    public String getValidade() {
		return validade;
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
    
    public void setEfeitos(String efeitos) {
        this.efeitos = efeitos;
    }
    
	public void setValidade(String validade) {
		this.validade = validade;
	}
}