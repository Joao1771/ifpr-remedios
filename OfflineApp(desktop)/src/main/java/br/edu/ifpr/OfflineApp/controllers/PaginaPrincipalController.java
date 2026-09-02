package br.edu.ifpr.OfflineApp.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpr.OfflineApp.dao.RemedioDAO;
import br.edu.ifpr.OfflineApp.main.App;
import br.edu.ifpr.OfflineApp.model.Prescricao;
import br.edu.ifpr.OfflineApp.model.Remedio;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class PaginaPrincipalController {
	
	RemedioDAO rdao = new RemedioDAO(); //OBJETO SINGLETON
	
	private List<Remedio> listaRemedios = new ArrayList<Remedio>(); //LISTA PARA GUARDAR TODOS OS REMEDIOS COM UMA CERTA CARACTERÍSTICA PROCURADA
	
	public String nomeDigitado; //GUARDA O NOME DO REMÉDIO DIGITADO
	public Remedio remedioSelecionado; //PARA GUARDAR O REMÉDIO QUANDO SE CLICA O BOTÃO PARA VER PRESCRIÇÃO
	
	//CAMPO DE PESQUISA
	@FXML
	private TextField campoPesquisar;
	
	@FXML
	public void procurarNome() {
		this.nomeDigitado = campoPesquisar.getText();
		//TESTANDO A PROCURA
		System.out.println("procurando por: " + nomeDigitado);
		//LIMPA O DAO
		rdao.clear();
		//ENCONTRA OS REMEDIOS COM O NOME DIGITADO E COLOCA NA LISTA
		listaRemedios = rdao.findByName(this.nomeDigitado);
		//PEGA A LISTA E COLOCA NA tabelaRemedios
		colocarRemediosNaTabela(listaRemedios);
		System.out.println("Resultados encontrados: " + listaRemedios.size());
	}
	
	//TABELA
	@FXML private TableView<Remedio> tabelaRemedios;
	@FXML private TableColumn<Remedio, String> NOME;
	@FXML private TableColumn<Remedio, String> BULA;
	@FXML private TableColumn<Remedio, String> EMPRESA;
	@FXML private TableColumn<Remedio, Prescricao> PRESCRICAO;
	@FXML
	public void initialize() {
		//PERMITE QUE A ALTURA DAS LINHAS MUDE, PARA SUPORTAR CONTEÚDO COM VÁRIAS QUEBRA-LINHAS
		tabelaRemedios.setFixedCellSize(Control.USE_COMPUTED_SIZE);
		//BARRA HORIZONTAL E VERTICAL DA TABELA, QUANDO A JANELA FICA MENOR QUE A TABELA
		tabelaRemedios.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
		
	    NOME.setCellValueFactory(new PropertyValueFactory<>("nome"));
	    BULA.setCellValueFactory(new PropertyValueFactory<Remedio, String>("bula"));
	    BULA.setCellFactory(
		    	new Callback<TableColumn<Remedio, String>, TableCell<Remedio, String>>() {
		    		@Override
		    	    public TableCell<Remedio, String> call(TableColumn<Remedio, String> param) {
		    			return new TableCell<Remedio, String>() {
		    				private final Hyperlink link = new Hyperlink("Ver bula");
		    				{
		    					link.setOnAction(new EventHandler<ActionEvent>() {
		    						@Override
		    						public void handle(ActionEvent event) {
		    							//PEGA O REMEDIO CORRESPONDENTE AO CLICAR NO LINK "VER BULA"
		                                remedioSelecionado = getTableView().getItems().get(getIndex());
		                                try {
		                                   switchToVisualizacaoPDFdaBula(remedioSelecionado);  //MUDA DE VIEW TRANSFERINDO O REMEDIO SELECIONADO
		                                } catch (IOException e) {
		                                   e.printStackTrace();
		                                }
		    						}
		    					});
		    				}
		    				@Override
		    				protected void updateItem(String item, boolean empty) {
		    					super.updateItem(item, empty);
		    					if (empty || item == null || item.isEmpty()) {
		    						setGraphic(null);
		    					} else {
		    						setGraphic(link);
		    					}
		    				}
		    			};
		    		}
		    	}
		    );
	    EMPRESA.setCellValueFactory(
	    	new Callback<CellDataFeatures<Remedio, String>, ObservableValue<String>>() {
	    		@Override
	    		public ObservableValue<String> call(CellDataFeatures<Remedio, String> cellData) {
	    			if (cellData.getValue().getEmpresa() != null) {
	    				if (cellData.getValue().getEmpresa().getCnpj() != null) {
	    					return new SimpleStringProperty(cellData.getValue().getEmpresa().getNome() + " - " + cellData.getValue().getEmpresa().getCnpj());	
	    				} else {
	    					return new SimpleStringProperty(cellData.getValue().getEmpresa().getNome() + " - " + "N/A");
	    				}
	    			}
	    			return new SimpleStringProperty("");
	    		}
	    	}
	    );
	    PRESCRICAO.setCellValueFactory(new PropertyValueFactory<Remedio, Prescricao>("prescricao"));
	    PRESCRICAO.setCellFactory(
	    	new Callback<TableColumn<Remedio, Prescricao>, TableCell<Remedio, Prescricao>>() {
	    		@Override
				public TableCell<Remedio, Prescricao> call(TableColumn<Remedio, Prescricao> tableColumn) {
	    			return new TableCell<Remedio, Prescricao>() {
	    				private final Button botao = new Button("Ver Prescrição");
	    				{
	    				    botao.getStyleClass().add("botao-prescricao");  //UMA CLASSE PARA DAR ESTILO NO CSS
	    				    
	    				    //AÇÃO DE CLICAR NO BOTÃO
                      botao.setOnAction(
                         new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                               //PEGA O REMEDIO CORRESPONDENTE AO BOTÃO "VER PRESCRICAO" SELECIONADO
                               remedioSelecionado = getTableView().getItems().get(getIndex());
                               try {
                                  switchToVisualizacaoPrescricao(remedioSelecionado);  //MUDA DE VIEW TRANSFERINDO O REMEDIO SELECIONADO
                               } catch (IOException e) {
                                  e.printStackTrace();
                               }
                            }
                         }
                      );
	    				}
	    				@Override
	    				protected void updateItem(Prescricao item, boolean empty) {  //ATUALIZA O CONTEÚDO DA CÉLULA PARA O BOTÃO
	    					super.updateItem(item, empty);
	    					if (empty) {
	    						setGraphic(null);
	    						setText(null);
	    					} else {
	    						setGraphic(botao);
	    						setText(null);
	    					}
	    				}
	    			};
	    		}
	    	}
	    );
	    ativaQuebraDeLinha(EMPRESA);
	}
	
	public void colocarRemediosNaTabela(List<Remedio> listaRemedios) {
		//LIMPA A TABELA
		tabelaRemedios.getItems().clear();
		//COLOCA NA TABELA
		tabelaRemedios.getItems().setAll(listaRemedios);
	}
	
	private void ativaQuebraDeLinha(TableColumn<Remedio, String> coluna) {
		coluna.setCellFactory(new Callback<TableColumn<Remedio, String>, TableCell<Remedio, String>>() {
			
			@Override
			public TableCell<Remedio, String> call(TableColumn<Remedio, String> tableColumn) {
			
				return new TableCell<>() {
				
					// ELEMENTO LABEL QUE POSSUI AS REGRAS DE TAMANHO PARA O CONTEÚDO DA CELULA
					private final Label label = new Label();

					{
						//PERMITE O QUEBRA-LINHA
						label.setWrapText(true);
						
						//FAZ O TEXTO RESPEITAR A LARGURA DA COLUNA
						label.prefWidthProperty().bind(coluna.widthProperty().subtract(10));
						
						//ALTURA MÍNIMA PARA O LABEL
						label.setMinHeight(Control.USE_PREF_SIZE);
						//ALTURA MÁXIMA PARA O LABEL
						label.setMaxHeight(Double.MAX_VALUE);
					}

					//ATUALIZA O CONTEÚDO PARA O QUE TEM AS NOVAS REGRAS
					@Override
					protected void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						
						setText(null); //LIMPA O CONTEÚDO NORMAL DA TABELA, PARA QUE FIQUE SOMENTE VISIVEL O DO LABEL
							
						if (empty || item == null) {
							setGraphic(null);
						} else {
							label.setText(item);
							//AUMENTA A ALTURA DA CELULA SEGUINDO A ALTURA DO LABEL COM O CONTEÚDO E ADICIONA 5
							setPrefHeight(label.getHeight() + 5);
							setGraphic(label);
						}
					}
				};
			}
	    });
	}
	
	private void switchToVisualizacaoPrescricao(Remedio remedioSelecionado) throws IOException {
       FXMLLoader loader = new FXMLLoader(App.class.getResource("/fxml/VisualizacaoPrescricao.fxml"));
       Parent root = loader.load();

       VisualizacaoPrescricaoController controller = loader.getController(); // PEGA O CONTROLLER DE VISUALIZAR PRESCRICAO

       controller.setRemedioSelecionado(remedioSelecionado); // ENVIA A INSTÂNCIA PARA O CONTROLLER

       Stage stage = (Stage) campoPesquisar.getScene().getWindow();
       stage.getScene().setRoot(root);
    }
	
	private void switchToVisualizacaoPDFdaBula(Remedio remedioSelecionado) throws IOException {
	   FXMLLoader loader = new FXMLLoader(App.class.getResource("/fxml/VisualizacaoPDFdaBula.fxml"));
	   Parent root = loader.load();

	   VisualizacaoPDFdaBulaController controller = loader.getController(); // PEGA O CONTROLLER DE VISUALIZAR PDF DA BULA

	   controller.setRemedioSelecionado(remedioSelecionado); // ENVIA A INSTÂNCIA PARA O CONTROLLER

	   Stage stage = (Stage) campoPesquisar.getScene().getWindow();
	   stage.getScene().setRoot(root);
	}
}
