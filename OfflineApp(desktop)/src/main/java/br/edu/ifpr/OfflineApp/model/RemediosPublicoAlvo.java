package br.edu.ifpr.OfflineApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "REMEDIOS_PUBLICO_ALVO")
public class RemediosPublicoAlvo {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "ID_REMEDIO_PUBLICO")
   private Integer id;
   
   @ManyToOne
   @JoinColumn(name = "ID_REMEDIO", nullable = false)
   private Remedio remedio;

   @ManyToOne
   @JoinColumn(name = "ID_PUBLICO_ALVO", nullable = false)
   private PublicoAlvo publicoAlvo;

   public Integer getId() {
      return id;
   }
   public void setId(Integer id) {
      this.id = id;
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
