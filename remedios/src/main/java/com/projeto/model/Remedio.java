package com.projeto.model;

import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "REMEDIOS")
public class Remedio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_REMEDIO")
    private Integer id;

    @Column(name = "NOME", nullable = false, length = 100)
    private String nome;

    @Column(name = "BULA", nullable = false, length = 255)
    private String bula;

    @Column(name = "TIPO", nullable = false, length = 20)
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO")
    private Usuario usuario;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_PRESCRICAO")
    private Prescricao prescricao;

    @ManyToOne
    @JoinColumn(name = "ID_TARJA")
    private Tarja tarja;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_SUBSTANCIA")
    private Substancia substancia;

    @OneToMany(mappedBy = "remedio")
    @JsonIgnore
    private Set<RemedioPublicoAlvo> remediosPublicoAlvo;
    
    @ManyToOne
    @JoinColumn(name = "ID_EMPRESA")
    private Empresa empresa;

    // Getters e Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Prescricao getPrescricao() {
        return prescricao;
    }

    public void setPrescricao(Prescricao prescricao) {
        this.prescricao = prescricao;
    }

    public Tarja getTarjas() {
        return tarja;
    }

    public void setTarjas(Tarja tarja) {
        this.tarja = tarja;
    }

    public Set<RemedioPublicoAlvo> getRemediosPublicoAlvo() {
        return remediosPublicoAlvo;
    }

    public void setRemediosPublicoAlvo(Set<RemedioPublicoAlvo> remediosPublicoAlvo) {
        this.remediosPublicoAlvo = remediosPublicoAlvo;
    }
    
    public Substancia getSubstancia() {
       return substancia;
    }

     public void setSubstancia(Substancia substancia) {
        this.substancia = substancia;
     }
     public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}