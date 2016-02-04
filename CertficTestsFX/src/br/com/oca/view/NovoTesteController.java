package br.com.oca.view;

import br.com.oca.controller.MainApp;
import br.com.oca.model.conteudo.OCA;
import br.com.oca.model.enums.Certificacao;
import br.com.oca.model.enums.Idioma;
import br.com.oca.model.enums.TipoTeste;
import br.com.oca.util.Observer;
import br.com.oca.util.Subject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
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
		mainApp.showQuiz(OCA.getInstance(comboExame.getValue(), idioma, comboTipoTeste.getValue()));
		dialogStage.close();
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
