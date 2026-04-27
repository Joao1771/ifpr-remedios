package com.projeto.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EmpresaRemedioId implements Serializable {

    @Column(name = "ID_EMPRESAS")
    private Integer idEmpresa;

    @Column(name = "ID_REMEDIOS")
    private Integer idRemedio;

    // equals e hashCode (OBRIGATÓRIO)

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmpresaRemedioId that = (EmpresaRemedioId) o;
        return Objects.equals(idEmpresa, that.idEmpresa) &&
               Objects.equals(idRemedio, that.idRemedio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEmpresa, idRemedio);
    }

    // Getters e Setters

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public int getIdRemedio() {
        return idRemedio;
    }

    public void setIdRemedio(int idRemedio) {
        this.idRemedio = idRemedio;
    }
}