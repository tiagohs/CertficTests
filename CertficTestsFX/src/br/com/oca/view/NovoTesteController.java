package br.com.oca.view;

import br.com.oca.controller.MainApp;
import br.com.oca.model.conteudo.Conteudo;
import br.com.oca.model.conteudo.OCA;
import br.com.oca.model.enums.Certificacao;
import br.com.oca.model.enums.Idioma;
import br.com.oca.model.enums.TipoTeste;
import br.com.oca.util.Observer;
import br.com.oca.util.Subject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class NovoTesteController implements Observer {
	@FXML
	private ComboBox<Certificacao> comboExame;
	@FXML
	private ComboBox<TipoTeste> comboTipoTeste;
	
	private ObservableList<Certificacao> optionsExame;
	private ObservableList<TipoTeste> optionsTipoTeste;
	
	private Stage dialogStage;
	private Stage dialogHome;
	
	private Subject controller;
	private Idioma idioma;
	private MainApp mainApp;
	
	public NovoTesteController() {
		optionsExame = FXCollections.observableArrayList(Certificacao.values());
		optionsTipoTeste = FXCollections.observableArrayList(TipoTeste.values());
	}
	
	@FXML
    private void initialize() {
		comboExame.setItems(optionsExame);
		comboTipoTeste.setItems(optionsTipoTeste);
    }
	
	@FXML
	private void handleOk() {
		if (comboExame.getValue() != null && comboTipoTeste.getValue() != null) {
			mainApp.showQuiz(getExameEscolhido());
			dialogStage.close();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Atenção!");
			alert.setHeaderText("Erro na criação de um Novo Teste");
			alert.setContentText("A Escolha de um Exame e de Um tipo de Teste é obrigatória.");

			alert.showAndWait();
		}
	}
	
	private Conteudo getExameEscolhido() {
		
		switch (comboExame.getValue()) {
			case OCA_JAVA7:
				return OCA.getInstance(comboExame.getValue(), idioma, comboTipoTeste.getValue());
			case OCP_JAVA7:
			
			default:
				return null;
		}
	}
	
	@FXML
	private void handleCancel() {
		dialogStage.close();
		dialogHome.show();
	}
	
	public void setController(Subject controller) {
		this.controller = controller;
		controller.addObserver(this);
	}
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}
	
	public void setDialogHome(Stage dialogHome) {
		this.dialogHome = dialogHome;
	}
	
	@Override
	public void update(Idioma idioma) {
		setIdioma(idioma);
	}
}
