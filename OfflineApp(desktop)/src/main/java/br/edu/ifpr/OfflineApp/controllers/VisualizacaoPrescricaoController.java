package br.edu.ifpr.OfflineApp.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpr.OfflineApp.dao.PublicoAlvoDAO;
import br.edu.ifpr.OfflineApp.dao.SubstanciaDAO;
import br.edu.ifpr.OfflineApp.main.App;
import br.edu.ifpr.OfflineApp.model.PublicoAlvo;
import br.edu.ifpr.OfflineApp.model.Remedio;
import br.edu.ifpr.OfflineApp.model.Substancia;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class VisualizacaoPrescricaoController {

   public Remedio remedioSelecionado;
   
   PublicoAlvoDAO paDAO = new PublicoAlvoDAO();
   private List<PublicoAlvo> listaPublicoAlvos = new ArrayList<PublicoAlvo>();
   
   SubstanciaDAO sDAO = new SubstanciaDAO();
   private List<Substancia> listaSubstancias = new ArrayList<Substancia>();
   
   //INFORMAÇÕES DO REMÉDIO
   @FXML private Label NOME_REMEDIO;
   @FXML private Label EMPRESA_CNPJ;
   @FXML private Label ID_REMEDIO;
   @FXML private Label TARJA;
   @FXML private Label PUBLICO_ALVO;
   
   //GRID PRESCRICAO
   @FXML private GridPane GRIDPANE;
   //PRESCRICAO
   @FXML private Label CONTRA_INDICACOES;
   @FXML private Label EFEITOS;
   @FXML private Label RESTRICAO;
   @FXML private Label VALIDADE;
   @FXML private Label COMPOSICAO;
   @FXML private Label CONSERVACAO;
   
   // SETTER
   public void setRemedioSelecionado(Remedio remedioSelecionado) {
      this.remedioSelecionado = remedioSelecionado;

      if(remedioSelecionado != null) {
    	 this.remedioSelecionado = remedioSelecionado;
    	 //COLOCA OS VALORES DO REMÉDIO SOMENTE DEPOIS DE O REMÉDIO É CONFIRMADO QUE EXISTE
         NOME_REMEDIO.setText(remedioSelecionado.getNome());
         if (remedioSelecionado.getEmpresa().getCnpj() != null) {
   	  	 	EMPRESA_CNPJ.setText(remedioSelecionado.getEmpresa().getNome() + " - " + remedioSelecionado.getEmpresa().getCnpj());
         } else {
        	EMPRESA_CNPJ.setText(remedioSelecionado.getEmpresa().getNome() + " - " + "N/A");
         }
         
   	     ID_REMEDIO.setText(Integer.toString(remedioSelecionado.getId()));
   	     TARJA.setText(remedioSelecionado.getTarja().getNome());
   	     
   	     //ENCONTRA TODOS OS PUBLICOS ALVOS QUE FAZEM RELAÇÃO COM O REMÉDIO SELECIONADO E OS COLOCA EM UMA LISTA
   	     listaPublicoAlvos = paDAO.findByRelation(remedioSelecionado);
	   	 String nomesPublicosAlvos = "";
	   	 // PEGA O NOME DE TODOS OS PUBLICOS ALVOS E OS COLOCA EM UMA STRING
	   	 if (listaPublicoAlvos != null) {
		   	 for (int i = 0; i < listaPublicoAlvos.size(); i++) {
		   		 if (i != 0) {
		   			 nomesPublicosAlvos += ", ";
		   		 }
		   		 nomesPublicosAlvos += listaPublicoAlvos.get(i).getNome();
		   	 }
	   	 }
	   	 //SE COLOCA A STRING COMO TEXTO DA LABEL
	   	 PUBLICO_ALVO.setText(nomesPublicosAlvos);
	   	 
	   	 //GRIDPANE PRESCRIÇÃO
	   	 GRIDPANE.getStyleClass().add("gridpane-ver-prescricao");
   	    //VALORES PRECRIÇÃO
   	    CONTRA_INDICACOES.setText(remedioSelecionado.getPrescricao().getContraIndicacoes());
   	    EFEITOS.setText(remedioSelecionado.getPrescricao().getEfeitos());
   	    RESTRICAO.setText(remedioSelecionado.getPrescricao().getRestricao());
   	    VALIDADE.setText(remedioSelecionado.getPrescricao().getValidade());
   	    CONSERVACAO.setText(remedioSelecionado.getPrescricao().getConservacao());
   	    COMPOSICAO.setText(remedioSelecionado.getSubstancia().getNome());
      } else {
    	  System.out.println("Erro ao pegar o remedio do qual o respectivo botao para ver prescricao pega");
      }
   }
   
   @FXML
   private void switchToPaginaPrincipal() throws IOException {
      try {
         App.setRoot("PaginaPrincipal");
      } catch (IOException e) {
         e.printStackTrace();
         System.out.println("Erro ao mudar da view 'VisualizacaoPrescricao' para a view 'PaginaPrincipal'");
      }
   }
}