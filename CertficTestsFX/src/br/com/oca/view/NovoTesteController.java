package br.com.oca.view;

import br.com.oca.controllers.MainApp;
import br.com.oca.model.enums.Certificacao;
import br.com.oca.model.enums.TipoTeste;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class NovoTesteController {
	@FXML
	private ComboBox<Certificacao> comboExame;
	@FXML
	private ComboBox<TipoTeste> comboTipoTeste;
	
	private ObservableList<Certificacao> optionsExame;
	private ObservableList<TipoTeste> optionsTipoTeste;
	private Stage dialogStage;
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
		mainApp.showQuiz(comboExame.getValue(), comboTipoTeste.getValue());
		dialogStage.close();
	}
	
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
}
