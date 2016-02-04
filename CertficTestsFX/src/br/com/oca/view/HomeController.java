package br.com.oca.view;

import br.com.oca.controller.MainApp;
import br.com.oca.model.Tentativas;
import br.com.oca.model.conteudo.OCA;
import br.com.oca.model.enums.Certificacao;
import br.com.oca.model.enums.Idioma;
import br.com.oca.model.enums.TipoTeste;
import br.com.oca.model.i18n.janelas.JanelasSource;
import br.com.oca.util.Observer;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

public class HomeController implements Observer {
	@FXML
	private TableView<Tentativas> tabelaTentativas;
	@FXML
	private TableColumn<Tentativas, String> colunaTentativas;
	@FXML
	private TableColumn<Tentativas, String> colunaTesteEscolhido;
	@FXML
	private TableColumn<Tentativas, String> colunaNota;
	@FXML
	private TableColumn<Tentativas, String> colunaAcertos;
	@FXML
	private ComboBox<Certificacao> comboExame; 
	@FXML
	private ComboBox<TipoTeste> comboTipoTeste;
	
	private FXMLLoader loader;
	private JanelasSource label;
	private ObservableList<Tentativas> listaTentativas;
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
        
        tabelaTentativas.setItems(listaTentativas);
        
	}
	
	@FXML
	public void handleBotaoNovo() {
		
		if (comboExame.getValue() != null && comboTipoTeste.getValue() != null) {
			mainApp.showQuiz(OCA.getInstance(comboExame.getValue(), idioma, comboTipoTeste.getValue()));
			homeStage.hide();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Aten��o!");
			alert.setHeaderText("Erro na cria��o de um Novo Teste");
			alert.setContentText("A Escolha de um Exame e de Um tipo de Teste � obrigat�ria.");

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
	
	public void setListaTentativas(ObservableList<Tentativas> listaTentativas) {
		this.listaTentativas = listaTentativas;
	}
	
	@Override
	public void update(Idioma idioma) {
		label.setNovoIdioma(idioma);
		loader.setResources(label.getBundle());
	}

}
