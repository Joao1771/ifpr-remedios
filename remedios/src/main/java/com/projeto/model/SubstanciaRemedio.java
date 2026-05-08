package com.projeto.model;

import jakarta.persistence.*;

@Entity
@Table(name = "SUBSTANCIAS_REMEDIOS")
public class SubstanciaRemedio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SUBSTANCIAS_REMEDIOS")
    private int id;

    @ManyToOne
    @JoinColumn(name = "ID_REMEDIO")
    private Remedio remedio;

    @ManyToOne
    @JoinColumn(name = "ID_SUBSTANCIA")
    private Substancia substancia;

    // Getters e Setters

    public int getId() {
        return id;
    }

    public Remedio getRemedio() {
        return remedio;
    }

    public void setRemedio(Remedio remedio) {
        this.remedio = remedio;
    }

    public Substancia getSubstancia() {
        return substancia;
    }

    public void setSubstancia(Substancia substancia) {
        this.substancia = substancia;
    }
}