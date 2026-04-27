package com.projeto.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "EMPRESAS")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EMPRESA")
    private Integer id;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "CNPJ", nullable = false)
    private String cnpj;

    public List<EmpresaRemedio> getEmpresaRemedios() {
      return empresaRemedios;
   }

    public void setEmpresaRemedios(List<EmpresaRemedio> empresaRemedios) {
       this.empresaRemedios = empresaRemedios;
    }

    @ManyToOne
    @JoinColumn(name = "ID_CIDADE")
    private Cidade cidade;
    
    @OneToMany(mappedBy = "empresa")
    private List<EmpresaRemedio> empresaRemedios;


    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
}