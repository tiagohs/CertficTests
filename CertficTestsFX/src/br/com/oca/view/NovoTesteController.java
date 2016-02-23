package br.com.oca.view;

import br.com.oca.MainApp;
import br.com.oca.config.JanelasConfig;
import br.com.oca.model.enums.Certificacao;
import br.com.oca.model.enums.Idioma;
import br.com.oca.model.enums.TipoTeste;
import br.com.oca.util.AlertDialogsFactory;
import br.com.oca.util.ConteudoFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class NovoTesteController {
	@FXML
	private ComboBox<Certificacao> comboExame;
	@FXML
	private ComboBox<TipoTeste> comboTipoTeste;
	@FXML
	private Button botaoNovo;

	private ObservableList<Certificacao> optionsExame;
	private ObservableList<TipoTeste> optionsTipoTeste;

	private Stage dialogStage;
	private Stage dialogHome;

	private Idioma idioma;
	private JanelasConfig label;
	private MainApp mainApp;

	public NovoTesteController() {
		optionsExame = FXCollections.observableArrayList(Certificacao.values());
		optionsTipoTeste = FXCollections.observableArrayList(TipoTeste.values());
	}

	@FXML
	private void initialize() {
		
	}
	
	public void inicializaJanela() {
		dialogStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	          public void handle(WindowEvent we) {
	        	  handleCancel();
	          }
	    });
		
		label = JanelasConfig.getInstance(idioma);
		botaoNovo.setDisable(true);
		comboExame.setItems(optionsExame);
		comboTipoTeste.setItems(optionsTipoTeste);
	}
	
	@FXML
	private void handleComboExame() {

		if (comboTipoTeste.getValue() != null)
			botaoNovo.setDisable(false);
	}

	@FXML
	private void handleComboTipoTeste() {

		if (comboExame.getValue() != null)
			botaoNovo.setDisable(false);
	}

	@FXML
	private void handleOk() {
		if (comboExame.getValue() != null && comboTipoTeste.getValue() != null) {
			mainApp.showQuiz(ConteudoFactory.getConteudo(comboExame.getValue(), idioma, comboTipoTeste.getValue()));
			dialogStage.close();
		} else {
			AlertDialogsFactory.getAlertDialog(AlertType.ERROR, label.getString("novoTesteAlertTitulo"),
					label.getString("novoTesteAlertCabecalho"), label.getString("novoTesteAlertConteudo"));
		}
	}

	@FXML
	private void handleCancel() {
		dialogStage.close();
		dialogHome.show();
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

}
