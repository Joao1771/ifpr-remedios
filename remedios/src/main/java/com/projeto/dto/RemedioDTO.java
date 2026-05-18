package com.projeto.dto;

import java.util.List;

public class RemedioDTO {

    public Integer id;
    public String nome;
    public String bula;
    public String tipo;

    // Prescrição
    public String publicoAlvo;
    public String restricao;
    public String contraIndicacoes;
    public String composicao;
    public String efeitos;
    public String validade;

    // Relacionamentos
    public List<SubstanciaDTO> substancias;
    public List<String> empresas;
}