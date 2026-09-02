package br.edu.ifpr.OfflineApp.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "EMPRESAS")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EMPRESA")
    private Integer id;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "CNPJ", columnDefinition = "CHAR(14)", unique = true)
    private String cnpj;

    @ManyToOne
    @JoinColumn(name = "ID_CIDADE") // FK no banco
    private Cidade cidade;
    
    @OneToMany(mappedBy = "empresa")
    private List<Remedio> remedios;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
       this.id = id;
    }

    public List<Remedio> getRemedios() {
      return remedios;
   }
    public void setRemedios(List<Remedio> remedios) {
       this.remedios = remedios;
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