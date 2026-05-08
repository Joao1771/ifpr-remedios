package com.projeto.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "SUBSTANCIAS")
public class Substancia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SUBSTANCIA")
    private int id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "TIPO")
    private String tipo;
    
    @OneToMany(mappedBy = "substancia")
    private List<SubstanciaRemedio> substanciasRemedios = new ArrayList<>();

    public int getId() {
       return id;
    }

    public void setId(int id) {
       this.id = id;
    }

    public String getNome() {
       return nome;
    }

    public void setNome(String nome) {
       this.nome = nome;
    }

    public String getTipo() {
       return tipo;
    }

    
    public void setTipo(String tipo) {
       this.tipo = tipo;
    }

    public List<SubstanciaRemedio> getSubstanciasRemedios() {
       return substanciasRemedios;
   }

   public void setSubstanciasRemedios(List<SubstanciaRemedio> substanciasRemedios) {
       this.substanciasRemedios = substanciasRemedios;
   }
 
}