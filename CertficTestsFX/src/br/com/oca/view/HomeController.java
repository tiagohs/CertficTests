package br.com.oca.view;

import br.com.oca.MainApp;
import br.com.oca.model.Tentativa;
import br.com.oca.model.conteudo.ConteudoFactory;
import br.com.oca.model.enums.Certificacao;
import br.com.oca.model.enums.Idioma;
import br.com.oca.model.enums.TipoTeste;
import br.com.oca.model.i18n.janelas.JanelasSource;
import br.com.oca.util.AlertDialogsFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class HomeController {
	@FXML
	private TableView<Tentativa> tabelaTentativas;
	@FXML
	private TableColumn<Tentativa, String> colunaTesteEscolhido;
	@FXML
	private TableColumn<Tentativa, String> colunaNota;
	@FXML
	private TableColumn<Tentativa, String> colunaAcertos;
	@FXML
	private TableColumn<Tentativa, String> colunaTempo;
	@FXML
	private TableColumn<Tentativa, String> colunaData;
	@FXML
	private Button botaoNovo;
	@FXML
	private ComboBox<Certificacao> comboExame;
	@FXML
	private ComboBox<TipoTeste> comboTipoTeste;

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
		
	}
	
	public void init() {
		homeStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	          public void handle(WindowEvent we) {
	        	  handleSair();
	          }
	    });
		
		botaoNovo.setDisable(true);
		comboExame.setItems(optionsExame);
		comboTipoTeste.setItems(optionsTipoTeste);
		label = JanelasSource.getInstance(idioma);

		inicializaTabela();
	}

	public void inicializaTabela() {

		colunaTesteEscolhido.setCellValueFactory(new PropertyValueFactory<>("testeEscolhido"));
		colunaNota.setCellValueFactory(new PropertyValueFactory<>("nota"));
		colunaAcertos.setCellValueFactory(new PropertyValueFactory<>("numeroAcertos"));
		colunaTempo.setCellValueFactory(new PropertyValueFactory<>("tempoRegistrado"));
		colunaData.setCellValueFactory(new PropertyValueFactory<>("dataRegistrada"));
		
		tabelaTentativas.setItems(listaTentativas);
		
		tabelaTentativas.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (tabelaTentativas.getSelectionModel().getSelectedItem() != null) {
            	homeStage.hide();
            	mainApp.showDetalhesQuestoesRespondidas(newValue);
            }
        });

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
	public void handleBotaoNovo() {

		if (comboExame.getValue() != null && comboTipoTeste.getValue() != null) {
			mainApp.showQuiz(ConteudoFactory.getConteudo(comboExame.getValue(), idioma, comboTipoTeste.getValue()));
			homeStage.hide();
		} else {
			AlertDialogsFactory.getAlertDialog(AlertType.ERROR, label.getString("novoTesteAlertTitulo"),
					label.getString("novoTesteAlertCabecalho"), label.getString("novoTesteAlertConteudo"));
		}
	}
	
	@FXML
	private void handleSair() {
		System.exit(0);
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		tabelaTentativas.setItems(mainApp.getListaTentativas());
	}

	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}

	public void setHomeStage(Stage homeStage) {
		this.homeStage = homeStage;
	}

	public void setListaTentativas(ObservableList<Tentativa> listaTentativas) {
		this.listaTentativas = listaTentativas;
	}

}
