package br.com.oca.view;

import java.util.ArrayList;

import br.com.oca.model.Resposta;
import br.com.oca.model.enums.Idioma;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class ResultadoController {
	@FXML
	private Label suaNota;
	@FXML
	private Label questoesCorretas;
	@FXML
	private ScrollPane scrollEnunciado;
	@FXML
	private Label resultados;
	
	private Stage dialogHome;
	private Stage dialogStage;
	
	private ArrayList<Resposta> listaRespostas;
	private Idioma idioma;
	
	public ResultadoController() {
		
	}

	@FXML
	private void initialize() {
		
	}
	
	@FXML
	private void handleOk() {
		dialogHome.show();
		dialogStage.close();
	}
	
	public void setListaRespostas(ArrayList<Resposta> listaRespostas) {
		this.listaRespostas = listaRespostas;
	}
	
	public ArrayList<Resposta> getListaRespostas() {
		return listaRespostas;
	}
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public void setDialogHome(Stage dialogHome) {
		this.dialogHome = dialogHome;
	}
	
	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}
}
