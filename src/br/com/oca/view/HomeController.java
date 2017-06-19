/**
 * 
 * Copyright (c) 2016, CertificTests and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * 
 */
package br.com.oca.view;

import br.com.oca.MainApp;
import br.com.oca.config.JanelasConfig;
import br.com.oca.model.Tentativa;
import br.com.oca.model.enums.Certificacao;
import br.com.oca.model.enums.Idioma;
import br.com.oca.model.enums.TipoTeste;
import br.com.oca.util.AlertDialogsFactory;
import br.com.oca.util.ConteudoFactory;
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

/**
 * 
 * A Classe HomeController é o Controller da View Home, ela é a janela base da
 * aplicação. Nela se contém a tabela com as tentativas realizadas, além de
 * campos para se realizar um novo teste.
 * 
 * Classe <code>HomeController</code>
 * 
 * @author Tiago Henrique
 * @version 1.0
 *
 */
public class HomeController {
	/**
	 * Tabela que exibe todas as tentativas realizadas na aplicação. Variavel de
	 * referência a TableView na view.
	 */
	@FXML
	private TableView<Tentativa> tabelaTentativas;

	/**
	 * Coluna de Teste escolhido (O Exame + o Tipo de Teste). Variavel de
	 * referência a TableColumn na view.
	 */
	@FXML
	private TableColumn<Tentativa, String> colunaTesteEscolhido;

	/**
	 * Coluna com a nota tirada nas tentativas. Variavel de referência a TableColumn
	 * na view.
	 */
	@FXML
	private TableColumn<Tentativa, String> colunaNota;

	/**
	 * Coluna com o numero de acertos nas tentativas. Variavel de referência a
	 * TableColumn na view.
	 */
	@FXML
	private TableColumn<Tentativa, String> colunaAcertos;

	/**
	 * Coluna com o tempo de duração em cada tentativa. Variavel de referência a
	 * TableColumn na view.
	 */
	@FXML
	private TableColumn<Tentativa, String> colunaTempo;

	/**
	 * Coluna com data e hora em que se realizou cada tentativa. Variavel de
	 * referência a TableColumn na view.
	 */
	@FXML
	private TableColumn<Tentativa, String> colunaData;

	/**
	 * Botão para se iniciar um novo Teste. Variavel de referência a Label na
	 * view.
	 */
	@FXML
	private Button botaoNovo;

	/**
	 * Combo contendo como opções os exames. Variavel de referência a ComboBox na
	 * view.
	 */
	@FXML
	private ComboBox<Certificacao> comboExame;

	/**
	 * Combo contendo como opções os tipos de testes (30, 60 ou 90 Questões).
	 * Variavel de referência a ComboBox na view.
	 */
	@FXML
	private ComboBox<TipoTeste> comboTipoTeste;

	/** Config referenciando o properties de i18n das Janelas. */
	private JanelasConfig label;

	/**
	 * Um ObservableList com as tentativas realizadas (Utilizado pela Tabela).
	 */
	private ObservableList<Tentativa> listaTentativas;

	/**
	 * Um ObservableList com os Exames disponiveis (Utilizado pelo ComboBox
	 * comboExame).
	 */
	private ObservableList<Certificacao> optionsExame;

	/**
	 * Um ObservableList com os Tipos de Testes disponiveis (Utilizado pelo
	 * ComboBox comboTipoTeste).
	 */
	private ObservableList<TipoTeste> optionsTipoTeste;

	/** O Idioma que a aplicação terá. */
	private Idioma idioma;

	/** Referência ao Stage atual. */
	private Stage homeStage;

	/** Referência a mainApp, classe controladora da Aplicação. */
	private MainApp mainApp;

	/**
	 * Se Instancia uma nova HomeController, chamado logo após o initialize() do
	 * JavaFX.
	 */
	public HomeController() {
		optionsExame = FXCollections.observableArrayList(Certificacao.values());
		optionsTipoTeste = FXCollections.observableArrayList(TipoTeste.values());
	}

	/**
	 *  Método padrão no JavaFX, ele é usado para se chamar a classe e iniciá-la,
	 * quando se cria um novo controller pelo método getController(). O
	 * Construtor é chamado logo após.
	 */
	@FXML
	private void initialize() {
		
	}

	/**
	 * Método para se setar e configurar opções na Home. No JavaFX não se
	 * inicializa os controller por construtores, então esse método substitui
	 * essa função. O Método primeiramente deixa referenciado que quando se
	 * fechar a janela pelo x no canto superior direito, se chamará o método
	 * handleSair(). Após isso, é setado todos os valores contidos no objeto
	 * tentativa, em seus respectivos Labels. Além de setar os valores
	 * das Lists nos comboBox. E por fim, iniciamos a tabela,
	 * configurando-a e se tiver tentativas já realizadas, preenxê-la.
	 */
	public void initHome() {
		homeStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				handleSair();
			}
		});

		botaoNovo.setDisable(true);
		comboExame.setItems(optionsExame);
		comboTipoTeste.setItems(optionsTipoTeste);
		label = JanelasConfig.getInstance(idioma);

		inicializaTabela();
	}

	/**
	 * 
	 * Aqui se inicializa e preenxe a tabela. Para Cada Coluna, se preenxe com
	 * os valores contidos na ObservableList listaTentativas. E Configuramos
	 * também que quando o usuário clicar em qualquer uma das linhas da tablela,
	 * se abrirá uma nova janela, a DetalhesQuestoesRespondidas, passando o
	 * objeto da tentativa contida naquela linha.
	 * 
	 */
	public void inicializaTabela() {

		colunaTesteEscolhido.setCellValueFactory(new PropertyValueFactory<>("testeEscolhido"));
		colunaNota.setCellValueFactory(new PropertyValueFactory<>("nota"));
		colunaAcertos.setCellValueFactory(new PropertyValueFactory<>("numeroAcertos"));
		colunaTempo.setCellValueFactory(new PropertyValueFactory<>("tempoRegistrado"));
		colunaData.setCellValueFactory(new PropertyValueFactory<>("dataRegistrada"));
		
		tabelaTentativas.setItems(listaTentativas);

		tabelaTentativas.getSelectionModel().selectedItemProperty()
				.addListener((observableValue, oldValue, newValue) -> {
					if (tabelaTentativas.getSelectionModel().getSelectedItem() != null) {
						homeStage.hide();
						mainApp.showDetalhesQuestoesRespondidas(newValue);
					}
				});

	}

	/**
	 * Método que trabalha a parte dinâmica do ComboBox comboExame. Assim que se
	 * escolhe uma opção do comboExame, se chama esse método. E se faz uma
	 * validação: se o outro combo, o tipo de teste, estiver selecionado, o
	 * botão Novo ficará Visible, ou sejá, será possível clicar nele. Se não,
	 * continuará Disable. Pois para se iniciar um novo Teste, é necessário
	 * escolher obrigatoriamente um Exame e Tipo de Teste. Variavel de
	 * referência a ComboBox na view.
	 */
	@FXML
	private void handleComboExame() {

		if (comboTipoTeste.getValue() != null)
			botaoNovo.setDisable(false);
	}

	/**
	 * Método que trabalha a parte dinâmica do ComboBox comboTipoTeste. Assim
	 * que se escolhe uma opção do comboTipoTeste, se chama esse método. E se
	 * faz uma validação: se o outro combo, o de Exames, estiver selecionado, o
	 * botão Novo ficará Visible, ou sejá, será possível clicar nele. Se não,
	 * continuará Disable. Pois para se iniciar um novo Teste, é necessário
	 * escolher obrigatoriamente um Exame e Tipo de Teste. Variavel de
	 * referência a ComboBox na view.
	 */
	@FXML
	private void handleComboTipoTeste() {

		if (comboExame.getValue() != null)
			botaoNovo.setDisable(false);
	}

	/**
	 * Método que trabalha a parte dinâmica do Botão Novo. Ele Simplesmente
	 * verifica se foi realmente selecionado um Exame e um Tipo de Teste, se
	 * sim, inicia um novo Teste, se não, exibe uma menssagem de erro. Variavel
	 * de referência a Button na view.
	 */
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

	/**
	 * Método que trabalha a parte dinâmica do Botão Sair. Irá finalizar a
	 * Aplicação quando se clicar nesse botão. Variavel
	 * de referência a Button na view.
	 */
	@FXML
	private void handleSair() {
		System.exit(0);
	}

	/**
	 * 
	 * Se Define uma nova Referência a MainApp, classe controladora da
	 * Aplicação.
	 * 
	 * @param mainApp
	 *            A nova MainApp, classe controladora da Aplicação.
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		tabelaTentativas.setItems(mainApp.getListaTentativas());
	}

	/**
	 * 
	 * Se Define um novo Idioma para a aplicação.
	 * 
	 * @param idioma
	 *            O novo Idioma para a aplicação.
	 */
	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}

	/**
	 * 
	 * Se Define uma nova Referência ao Stage atual.
	 * 
	 * @param homeStage
	 *            A nova Referência ao Stage atual.
	 */
	public void setHomeStage(Stage homeStage) {
		this.homeStage = homeStage;
	}

	/**
	 * 
	 * Se Define uma nova Lista com as tentativas realizadas (Utilizado pela
	 * Tabela).
	 * 
	 * @param listaTentativas
	 *            A Lista com as tentativas realizadas.
	 */
	public void setListaTentativas(ObservableList<Tentativa> listaTentativas) {
		this.listaTentativas = listaTentativas;
	}

}
