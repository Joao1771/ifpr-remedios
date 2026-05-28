package com.projeto.dto;

import java.util.List;

public class RemedioDTO {

    public Integer id;
    public String nome;
    public String bula;
    public String tipo;

    // Tarja
    public String tarja;

    // Prescrição
    public String restricao;
    public String contraIndicacoes;
    public String efeitos;
    public String validade;
    public String conservacao;

    // Público alvo
    public List<String> publicoAlvo;

    // Relacionamentos
    public List<SubstanciaDTO> substancias;
    public List<String> empresas;
}