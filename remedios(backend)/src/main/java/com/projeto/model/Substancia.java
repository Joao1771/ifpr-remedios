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
    
    @OneToOne(mappedBy = "substancia")
    private Remedio remedio;

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

    public Remedio getRemedio() {
       return remedio;
    }

    public void setRemedio(Remedio remedio) {
       this.remedio = remedio;
    }


 
}