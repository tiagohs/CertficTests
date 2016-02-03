package br.com.oca.view;

import br.com.oca.controller.MainApp;
import br.com.oca.model.Tentativa;
import br.com.oca.model.enums.Certificacao;
import br.com.oca.model.enums.Idioma;
import br.com.oca.model.enums.TipoTeste;
import br.com.oca.model.i18n.janelas.JanelasSource;
import br.com.oca.util.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class HomeController implements Observer {
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
	
	private FXMLLoader loader;
	private JanelasSource label;
	private ObservableList<Certificacao> optionsExame;
	private ObservableList<TipoTeste> optionsTipoTeste;
	private Idioma idioma;
	
	private Stage homeStage;
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
		label = JanelasSource.getInstance(idioma); 
	}

	@FXML
	public void handleBotaoNovo() {
		if (comboExame.getValue() != null && comboTipoTeste.getValue() != null) {
			mainApp.showQuiz(comboExame.getValue(), comboTipoTeste.getValue());
			homeStage.hide();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Atenção!");
			alert.setHeaderText("Erro na criação de um Novo Teste");
			alert.setContentText("A Escolha de um Exame e de Um tipo de Teste é obrigatória.");

			alert.showAndWait();
		}
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		tabelaTentativas.setItems(mainApp.getListaTentativas());
	}
	
	public void setIdioma(Idioma idioma) {
		label.setNovoIdioma(idioma);
		loader.setResources(label.getBundle());
		this.idioma = idioma;
	}
	
	public void setLoader(FXMLLoader loader) {
		this.loader = loader;
	}
	
	public void setHomeStage(Stage homeStage) {
		this.homeStage = homeStage;
	}
	
	@FXML
	private void handleSair() {
		System.exit(0);
	}
	
	@Override
	public void update(Idioma idioma) {
		label.setNovoIdioma(idioma);
		loader.setResources(label.getBundle());
	}

}
