package br.edu.ifpr.OfflineApp.controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import br.edu.ifpr.OfflineApp.main.App;
import br.edu.ifpr.OfflineApp.model.Remedio;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class VisualizacaoPDFdaBulaController {
	
	public Remedio remedioSelecionado;
	
	@FXML
	private VBox campoPDF;
	
	private double zoom = 0.25; //USADO PARA CALCULAR O ZOOM DA PERSPECTIVA DO ImageView
	
	// SETTER
	public void setRemedioSelecionado(Remedio remedioSelecionado) {
		if(remedioSelecionado != null) {
			this.remedioSelecionado = remedioSelecionado;
			carregarPDF();
		} else {
			System.out.println("Erro ao pegar o remedio do qual o respectivo botão link para ver bula pega.");
		}
	}
	
	private void carregarPDF() {
        try {
        	String caminho = remedioSelecionado.getBula();

            File arquivo = new File(caminho);

            PDDocument documento = Loader.loadPDF(arquivo);

            PDFRenderer renderer = new PDFRenderer(documento);

            campoPDF.getChildren().clear();

            for (int i = 0; i < documento.getNumberOfPages(); i++) {

                BufferedImage bufferedImage = renderer.renderImageWithDPI(i, 150);

                Image image = SwingFXUtils.toFXImage(bufferedImage, null);

                ImageView imageView = new ImageView(image);

                imageView.setPreserveRatio(true);

                imageView.setFitWidth(800 * zoom);

                campoPDF.getChildren().add(imageView);
            }
            documento.close();
            
            System.out.println("Carregando arquivo tipo pdf: " + caminho);
            System.out.println("Zoom atual: " + zoom);
        } catch (Exception e) {
            e.printStackTrace();
            Label aviso = new Label();
            aviso.setText("Não foi possível carregar o PDF.");
            campoPDF.getChildren().clear();
            campoPDF.getChildren().add(aviso);
            System.out.println("Erro ao carregar PDF, nao foi possivel abrir a bula. Talvez a bula nao esteja armazenado no dispositivo.");
        }
    }
	
	@FXML
	private void aproximar() {
		if (zoom < 2.5) {
			zoom += 0.25;
		}
	    carregarPDF();
	}
	
	@FXML
	private void afastar() {
		if (zoom > 0.25) {
			zoom-= 0.25;
		}
		carregarPDF();
	}
	
	@FXML
	private void switchToPaginaPrincipal() throws IOException {
		try {
			App.setRoot("PaginaPrincipal");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erro ao mudar da view 'VisualizacaoPDFdaBula' para a view 'PaginaPrincipal'");
		}
	}
}
