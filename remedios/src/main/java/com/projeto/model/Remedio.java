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

    @ManyToOne
    @JoinColumn(name = "ID_PRESCRICAO")
    private Prescricao prescricao;

    @ManyToOne
    @JoinColumn(name = "ID_TARJA")
    private Tarjas Tarjas;

    @OneToMany(mappedBy = "remedio")
    @JsonIgnore
    private Set<SubstanciaRemedio> substanciasRemedios;

    @OneToMany(mappedBy = "remedio")
    @JsonIgnore
    private Set<EmpresaRemedio> empresasRemedios;

    @OneToMany(mappedBy = "remedio")
    @JsonIgnore
    private Set<RemedioPublicoAlvo> remediosPublicoAlvo;

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

    public Tarjas getTarjas() {
        return Tarjas;
    }

    public void setTarjas(Tarjas Tarjas) {
        this.Tarjas = Tarjas;
    }

    public Set<SubstanciaRemedio> getSubstanciasRemedios() {
        return substanciasRemedios;
    }

    public void setSubstanciasRemedios(Set<SubstanciaRemedio> substanciasRemedios) {
        this.substanciasRemedios = substanciasRemedios;
    }

    public Set<EmpresaRemedio> getEmpresasRemedios() {
        return empresasRemedios;
    }

    public void setEmpresaRemedios(Set<EmpresaRemedio> empresaRemedios) {
        this.empresasRemedios = empresaRemedios;
    }

    public Set<RemedioPublicoAlvo> getRemediosPublicoAlvo() {
        return remediosPublicoAlvo;
    }

    public void setRemediosPublicoAlvo(Set<RemedioPublicoAlvo> remediosPublicoAlvo) {
        this.remediosPublicoAlvo = remediosPublicoAlvo;
    }
}