package br.com.oca.view;

import br.com.oca.model.Tentativa;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class DetalhesQuestoesRespondidasController {
	@FXML
	private Label testeEscolhido;
	@FXML
	private Label nota;
	@FXML
	private Label acertos;
	@FXML
	private Label tempoDuracao;
	@FXML
	private Label questoesRespondidas;
	@FXML
	private Label dataRegistrada;
	@FXML
	private Button botaoOk;
	
	private Stage dialogHome;
	private Stage dialogStage;
	private Tentativa tentativa;
	
	@FXML
	private void initialize() {
		
	}
	
	public void prenxerDados() {
		dialogStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	          public void handle(WindowEvent we) {
	              handleOk();
	          }
	    });
		
		testeEscolhido.setText(tentativa.getTesteEscolhido());
		nota.setText(tentativa.getNota().toString() + " de 100.0");
		acertos.setText(tentativa.getNumeroAcertos().toString() + " de " + tentativa.getTipoTeste().getTotalQuestoes());
		tempoDuracao.setText(tentativa.getTempoRegistrado());
		dataRegistrada.setText(tentativa.getDataRegistrada());
		questoesRespondidas.setText("Questões Respondidas: \n\n" + tentativa.getListaRespostas());
		questoesRespondidas.setWrapText(true);
	}
	
	@FXML
	private void handleOk() {
		dialogHome.show();
		dialogStage.close();
	}

	public Stage getDialogHome() {
		return dialogHome;
	}

	public void setDialogHome(Stage dialogHome) {
		this.dialogHome = dialogHome;
	}

	public Stage getDialogStage() {
		return dialogStage;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public Tentativa getTentativa() {
		return tentativa;
	}

	public void setTentativa(Tentativa tentativa) {
		this.tentativa = tentativa;
	}
	
}
