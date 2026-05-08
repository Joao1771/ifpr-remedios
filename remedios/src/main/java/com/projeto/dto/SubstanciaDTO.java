package com.projeto.dto;

import com.projeto.model.Substancia;

public class SubstanciaDTO {
    public Integer id;
    public String nome;

    public SubstanciaDTO() {}

    public SubstanciaDTO(Substancia s) {
        this.id = s.getId();
        this.nome = s.getNome();
    }
}