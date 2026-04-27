package com.projeto.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import jakarta.persistence.*;

@Entity
@Table(name = "REMEDIOS")
public class Remedio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_REMEDIO")
    private Integer id;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "BULA", nullable = false)
    private String bula;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_USUARIO")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_DESCRICAO")
    private Descricao descricao;
    
    @Expose(serialize = false, deserialize = false)
    @OneToMany(mappedBy = "remedio")
    private transient List<EmpresaRemedio> empresaRemedios;

    public List<EmpresaRemedio> getEmpresaRemedios() {
      return empresaRemedios;
   }

    public void setEmpresaRemedios(List<EmpresaRemedio> empresaRemedios) {
       this.empresaRemedios = empresaRemedios;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBula() {
        return bula;
    }

    public void setBula(String bula) {
        this.bula = bula;
    }

    public Usuario getUsuario() {
       return usuario;
   }

   public void setUsuario(Usuario usuario) {
       this.usuario = usuario;
   }

    public Descricao getDescricao() {
       return descricao;
   }

   public void setDescricao(Descricao descricao) {
       this.descricao = descricao;
   }
}