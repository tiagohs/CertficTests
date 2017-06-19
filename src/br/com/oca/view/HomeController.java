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
 * A Classe HomeController � o Controller da View Home, ela � a janela base da
 * aplica��o. Nela se cont�m a tabela com as tentativas realizadas, al�m de
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
	 * Tabela que exibe todas as tentativas realizadas na aplica��o. Variavel de
	 * refer�ncia a TableView na view.
	 */
	@FXML
	private TableView<Tentativa> tabelaTentativas;

	/**
	 * Coluna de Teste escolhido (O Exame + o Tipo de Teste). Variavel de
	 * refer�ncia a TableColumn na view.
	 */
	@FXML
	private TableColumn<Tentativa, String> colunaTesteEscolhido;

	/**
	 * Coluna com a nota tirada nas tentativas. Variavel de refer�ncia a TableColumn
	 * na view.
	 */
	@FXML
	private TableColumn<Tentativa, String> colunaNota;

	/**
	 * Coluna com o numero de acertos nas tentativas. Variavel de refer�ncia a
	 * TableColumn na view.
	 */
	@FXML
	private TableColumn<Tentativa, String> colunaAcertos;

	/**
	 * Coluna com o tempo de dura��o em cada tentativa. Variavel de refer�ncia a
	 * TableColumn na view.
	 */
	@FXML
	private TableColumn<Tentativa, String> colunaTempo;

	/**
	 * Coluna com data e hora em que se realizou cada tentativa. Variavel de
	 * refer�ncia a TableColumn na view.
	 */
	@FXML
	private TableColumn<Tentativa, String> colunaData;

	/**
	 * Bot�o para se iniciar um novo Teste. Variavel de refer�ncia a Label na
	 * view.
	 */
	@FXML
	private Button botaoNovo;

	/**
	 * Combo contendo como op��es os exames. Variavel de refer�ncia a ComboBox na
	 * view.
	 */
	@FXML
	private ComboBox<Certificacao> comboExame;

	/**
	 * Combo contendo como op��es os tipos de testes (30, 60 ou 90 Quest�es).
	 * Variavel de refer�ncia a ComboBox na view.
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

	/** O Idioma que a aplica��o ter�. */
	private Idioma idioma;

	/** Refer�ncia ao Stage atual. */
	private Stage homeStage;

	/** Refer�ncia a mainApp, classe controladora da Aplica��o. */
	private MainApp mainApp;

	/**
	 * Se Instancia uma nova HomeController, chamado logo ap�s o initialize() do
	 * JavaFX.
	 */
	public HomeController() {
		optionsExame = FXCollections.observableArrayList(Certificacao.values());
		optionsTipoTeste = FXCollections.observableArrayList(TipoTeste.values());
	}

	/**
	 *  M�todo padr�o no JavaFX, ele � usado para se chamar a classe e inici�-la,
	 * quando se cria um novo controller pelo m�todo getController(). O
	 * Construtor � chamado logo ap�s.
	 */
	@FXML
	private void initialize() {
		
	}

	/**
	 * M�todo para se setar e configurar op��es na Home. No JavaFX n�o se
	 * inicializa os controller por construtores, ent�o esse m�todo substitui
	 * essa fun��o. O M�todo primeiramente deixa referenciado que quando se
	 * fechar a janela pelo x no canto superior direito, se chamar� o m�todo
	 * handleSair(). Ap�s isso, � setado todos os valores contidos no objeto
	 * tentativa, em seus respectivos Labels. Al�m de setar os valores
	 * das Lists nos comboBox. E por fim, iniciamos a tabela,
	 * configurando-a e se tiver tentativas j� realizadas, preenx�-la.
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
	 * tamb�m que quando o usu�rio clicar em qualquer uma das linhas da tablela,
	 * se abrir� uma nova janela, a DetalhesQuestoesRespondidas, passando o
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
	 * M�todo que trabalha a parte din�mica do ComboBox comboExame. Assim que se
	 * escolhe uma op��o do comboExame, se chama esse m�todo. E se faz uma
	 * valida��o: se o outro combo, o tipo de teste, estiver selecionado, o
	 * bot�o Novo ficar� Visible, ou sej�, ser� poss�vel clicar nele. Se n�o,
	 * continuar� Disable. Pois para se iniciar um novo Teste, � necess�rio
	 * escolher obrigatoriamente um Exame e Tipo de Teste. Variavel de
	 * refer�ncia a ComboBox na view.
	 */
	@FXML
	private void handleComboExame() {

		if (comboTipoTeste.getValue() != null)
			botaoNovo.setDisable(false);
	}

	/**
	 * M�todo que trabalha a parte din�mica do ComboBox comboTipoTeste. Assim
	 * que se escolhe uma op��o do comboTipoTeste, se chama esse m�todo. E se
	 * faz uma valida��o: se o outro combo, o de Exames, estiver selecionado, o
	 * bot�o Novo ficar� Visible, ou sej�, ser� poss�vel clicar nele. Se n�o,
	 * continuar� Disable. Pois para se iniciar um novo Teste, � necess�rio
	 * escolher obrigatoriamente um Exame e Tipo de Teste. Variavel de
	 * refer�ncia a ComboBox na view.
	 */
	@FXML
	private void handleComboTipoTeste() {

		if (comboExame.getValue() != null)
			botaoNovo.setDisable(false);
	}

	/**
	 * M�todo que trabalha a parte din�mica do Bot�o Novo. Ele Simplesmente
	 * verifica se foi realmente selecionado um Exame e um Tipo de Teste, se
	 * sim, inicia um novo Teste, se n�o, exibe uma menssagem de erro. Variavel
	 * de refer�ncia a Button na view.
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
	 * M�todo que trabalha a parte din�mica do Bot�o Sair. Ir� finalizar a
	 * Aplica��o quando se clicar nesse bot�o. Variavel
	 * de refer�ncia a Button na view.
	 */
	@FXML
	private void handleSair() {
		System.exit(0);
	}

	/**
	 * 
	 * Se Define uma nova Refer�ncia a MainApp, classe controladora da
	 * Aplica��o.
	 * 
	 * @param mainApp
	 *            A nova MainApp, classe controladora da Aplica��o.
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		tabelaTentativas.setItems(mainApp.getListaTentativas());
	}

	/**
	 * 
	 * Se Define um novo Idioma para a aplica��o.
	 * 
	 * @param idioma
	 *            O novo Idioma para a aplica��o.
	 */
	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}

	/**
	 * 
	 * Se Define uma nova Refer�ncia ao Stage atual.
	 * 
	 * @param homeStage
	 *            A nova Refer�ncia ao Stage atual.
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
