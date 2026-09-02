package br.edu.ifpr.OfflineApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "PRESCRICOES")
public class Prescricao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRESCRICAO")
    private Integer id;

    @Column(name = "CONTRA_INDICACOES", nullable = false, length = 2000)
    private String contraIndicacoes;
    
    @Column(name = "EFEITOS", nullable = false, length = 1000)
    private String efeitos;
    
    @Column(name = "RESTRICAO", nullable = false)
    private String restricao;

    @Column(name = "VALIDADE", nullable = false)
    private String validade;
    
    @Column(name = "CONSERVACAO", nullable = false)
    private String conservacao;
    
    @OneToOne(mappedBy = "prescricao")
    private Remedio remedio;

    // Getters e Setters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getContraIndicacoes() {
		return contraIndicacoes;
	}
	public void setContraIndicacoes(String contraIndicacoes) {
		this.contraIndicacoes = contraIndicacoes;
	}

	public String getEfeitos() {
		return efeitos;
	}
	public void setEfeitos(String efeitos) {
		this.efeitos = efeitos;
	}

	public String getRestricao() {
		return restricao;
	}
	public void setRestricao(String restricao) {
		this.restricao = restricao;
	}

	public String getValidade() {
		return validade;
	}
	public void setValidade(String validade) {
		this.validade = validade;
	}

	public String getConservacao() {
		return conservacao;
	}
	public void setConservacao(String conservacao) {
		this.conservacao = conservacao;
	}

	public Remedio getRemedio() {
		return remedio;
	}
	public void setRemedio(Remedio remedio) {
		this.remedio = remedio;
	}
}
