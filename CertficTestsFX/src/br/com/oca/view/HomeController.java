package br.com.oca.view;

import br.com.oca.controller.MainApp;
import br.com.oca.model.Tentativa;
import br.com.oca.model.conteudo.OCA;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class HomeController implements Observer {
	@FXML
	private TableView<Tentativa> tabelaTentativas;
	@FXML
	private TableColumn<Tentativa, String> colunaTentativas;
	@FXML
	private TableColumn<Tentativa, String> colunaTesteEscolhido;
	@FXML
	private TableColumn<Tentativa, String> colunaNota;
	@FXML
	private TableColumn<Tentativa, String> colunaAcertos;
	@FXML
	private TableColumn<Tentativa, String> colunaTempo;
	@FXML
	private ComboBox<Certificacao> comboExame; 
	@FXML
	private ComboBox<TipoTeste> comboTipoTeste;
	
	private FXMLLoader loader;
	private JanelasSource label;
	private ObservableList<Tentativa> listaTentativas;
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
		comboExame.setItems(optionsExame);
		comboTipoTeste.setItems(optionsTipoTeste);
		label = JanelasSource.getInstance(idioma); 
		
		inicializaTabela();
	}
	
	private void inicializaTabela() {
		
		colunaTesteEscolhido.setCellValueFactory(new PropertyValueFactory<>("testeEscolhido"));
		colunaNota.setCellValueFactory(new PropertyValueFactory<>("nota"));
		colunaAcertos.setCellValueFactory(new PropertyValueFactory<>("numeroAcertos"));
		colunaTempo.setCellValueFactory(new PropertyValueFactory<>("tempoRegistrado"));
		
        tabelaTentativas.setItems(listaTentativas);
        
	}
	
	@FXML
	public void handleBotaoNovo() {
		
		if (comboExame.getValue() != null && comboTipoTeste.getValue() != null) {
			mainApp.showQuiz(OCA.getInstance(comboExame.getValue(), idioma, comboTipoTeste.getValue()));
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
	
	public void setListaTentativas(ObservableList<Tentativa> listaTentativas) {
		this.listaTentativas = listaTentativas;
	}
	
	@Override
	public void update(Idioma idioma) {
		label.setNovoIdioma(idioma);
		loader.setResources(label.getBundle());
	}

}
