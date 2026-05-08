package com.projeto.model;

import jakarta.persistence.*;

@Entity
@Table(name = "EMPRESAS_REMEDIOS")
public class EmpresaRemedio {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "ID_EMPRESA_REMEDIO")
   private int id;

   @ManyToOne
   @JoinColumn(name = "ID_EMPRESA", nullable = false)
   private Empresa empresa;

   @ManyToOne
   @JoinColumn(name = "ID_REMEDIO", nullable = false)
   private Remedio remedio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Remedio getRemedio() {
        return remedio;
    }

    public void setRemedio(Remedio remedio) {
        this.remedio = remedio;
    }
}