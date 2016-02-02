package br.com.oca.view;

import br.com.oca.controllers.MainApp;
import br.com.oca.model.Tentativa;
import br.com.oca.model.enums.Certificacao;
import br.com.oca.model.enums.TipoTeste;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class HomeController {
	@FXML
	private TableView<Tentativa> tabelaTentativas;
	@FXML
	private TableColumn<Tentativa, Double> colunaTentativas;
	@FXML
	private TableColumn<Tentativa, String> colunaTesteEscolhido;
	@FXML
	private TableColumn<Tentativa, String> colunaNota;
	@FXML
	private TableColumn<Tentativa, String> colunaAcertos;
	@FXML
	private ComboBox<Certificacao> comboExame; 
	@FXML
	private ComboBox<TipoTeste> comboTipoTeste;

	private ObservableList<Certificacao> optionsExame;
	private ObservableList<TipoTeste> optionsTipoTeste;

	private MainApp mainApp;

	public HomeController() {
		optionsExame = FXCollections.observableArrayList(Certificacao.values());
		optionsTipoTeste = FXCollections.observableArrayList(TipoTeste.values());
	}

	@FXML
	private void initialize() {
		// Inicializa a tablea de pessoa com duas colunas.
		// colunaTentativas.setCellValueFactory(cellData ->
		// cellData.getValue().firstNameProperty());
		colunaTesteEscolhido.setCellValueFactory(cellData -> cellData.getValue().getTesteEscolhido());
		colunaNota.setCellValueFactory(cellData -> cellData.getValue().getNotaStringProperty());
		colunaAcertos.setCellValueFactory(cellData -> cellData.getValue().getNumeroAcertosStringProperty());
		comboExame.setItems(optionsExame);
		comboTipoTeste.setItems(optionsTipoTeste);
	}

	@FXML
	public void handleQuiz() {
		if (comboExame.getValue() != null && comboTipoTeste.getValue() != null)
			mainApp.showQuiz(comboExame.getValue(), comboTipoTeste.getValue());
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		tabelaTentativas.setItems(mainApp.getListaTentativas());
	}

}
