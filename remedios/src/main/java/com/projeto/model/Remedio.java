package com.projeto.model;

import java.util.Set;

import jakarta.persistence.*;

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
    @JoinColumn(name = "ID_PRESCRICAO")
    private Prescricao prescricao;
    
    @OneToMany(mappedBy = "remedio")
    private Set<SubstanciaRemedio> substanciasRemedios;

    @OneToMany(mappedBy = "remedio")
    private Set<EmpresaRemedio> empresasRemedios;

    public Set<SubstanciaRemedio> getSubstanciasRemedios() {
       return substanciasRemedios;
    }
    public Set<EmpresaRemedio> getEmpresasRemedios() {
      return empresasRemedios;
   }
    
    public String getTipo() {
       return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
     }

    public void setEmpresaRemedios(Set<EmpresaRemedio> empresaRemedios) {
       this.empresasRemedios = empresaRemedios;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setId(Integer id) {
       this.id = id;
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
}