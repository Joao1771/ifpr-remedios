package com.projeto.model;

import jakarta.persistence.*;

@Entity
@Table(name = "REMEDIOS_PUBLICO_ALVO")
public class RemedioPublicoAlvo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_REMEDIO_PUBLICO")
    private int id;

    @ManyToOne
    @JoinColumn(name = "ID_REMEDIO")
    private Remedio remedio;

    @ManyToOne
    @JoinColumn(name = "ID_PUBLICO_ALVO")
    private PublicoAlvo publicoAlvo;


    public int getId() {
        return id;
    }

    public Remedio getRemedio() {
        return remedio;
    }

    public void setRemedio(Remedio remedio) {
        this.remedio = remedio;
    }

    public PublicoAlvo getPublicoAlvo() {
        return publicoAlvo;
    }

    public void setPublicoAlvo(PublicoAlvo publicoAlvo) {
        this.publicoAlvo = publicoAlvo;
    }
}