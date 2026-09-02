package br.edu.ifpr.OfflineApp.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "REMEDIOS")
public class Remedio {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_REMEDIO")
    private Integer id;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "BULA", nullable = false)
    private String bula;
    
    @Column(name = "TIPO", nullable = false)
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO")
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name = "ID_TARJA")
    private Tarja tarja;
    
    @ManyToOne
    @JoinColumn(name = "ID_EMPRESA")
    private Empresa empresa;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_PRESCRICAO")
    private Prescricao prescricao;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_SUBSTANCIA")
    private Substancia substancia;
    
    @OneToMany(mappedBy = "remedio")
    private List<RemediosPublicoAlvo> RemediosPublicoAlvo;

    public Integer getId() {
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
    
    public String getTipo() {
    	return tipo;
    }
    public void setTipo(String tipo) {
    	this.tipo = tipo;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public Tarja getTarja() {
    	return tarja;
    }
    public void setTarja(Tarja tarja) {
    	this.tarja = tarja;
    }
    
    public Empresa getEmpresa() {
    	return empresa;
    }
    public void setEmpresa(Empresa empresa) {
    	this.empresa = empresa;
    }
    
    public Prescricao getPrescricao() {
	    return prescricao;
    }
    public void setPrescricao(Prescricao prescricao) {
    	this.prescricao = prescricao;
    }
    
    public Substancia getSubstancia() {
    	return substancia;
    }
    public void setSubstancia(Substancia substancia) {
    	this.substancia = substancia;
    }
    
    public List<RemediosPublicoAlvo> getRemediosPublicoAlvo() {
    	return RemediosPublicoAlvo;
    }
    public void setRemediosPublicoAlvo(List<RemediosPublicoAlvo> remediosPublicoAlvo) {
    	this.RemediosPublicoAlvo = remediosPublicoAlvo;
    }
}
