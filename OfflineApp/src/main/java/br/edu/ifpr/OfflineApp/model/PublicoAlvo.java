package br.edu.ifpr.OfflineApp.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "PUBLICO_ALVO")
public class PublicoAlvo {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "ID_PUBLICO_ALVO")
   private Integer id;
   
   @Column(name = "NOME")
   private String nome;

   @OneToMany(mappedBy = "publicoAlvo")
   private List<RemediosPublicoAlvo> RemediosPublicoAlvo;
   
   public Integer getId() {
      return id;
   }
   public void setId(Integer id) {
	  this.id = id;
   }

   public String getNome() {
      return nome;
   }
   public void setNome(String nome) {
      this.nome = nome;
   }
   
   public List<RemediosPublicoAlvo> getRemediosPublicoAlvo() {
	  return RemediosPublicoAlvo;
   }
   public void setRemediosPublicoAlvo(List<RemediosPublicoAlvo> remediosPublicoAlvo) {
	  RemediosPublicoAlvo = remediosPublicoAlvo;
   }
}
