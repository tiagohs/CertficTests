/**
 * 
 * Copyright (c) 2016, CertificTests and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * 
 */
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

/**
 * 
 * A Classe NovoTesteController é o Controller da View NovoTeste, uma Janela
 * onde com base na escolha do Exame e de Tipo, se iniciará um novo Teste.
 * 
 * Classe <code>NovoTesteController</code>
 * 
 * @author Tiago Henrique
 * @version 1.0
 *
 */
public class NovoTesteController {
	/**
	 * Combo contendo como opções os exames. Variavel de referência a ComboBox
	 * na view.
	 */
	@FXML
	private ComboBox<Certificacao> comboExame;

	/**
	 * Combo contendo como opções os tipos de testes (30, 60 ou 90 Questões).
	 * Variavel de referência a ComboBox na view.
	 */
	@FXML
	private ComboBox<TipoTeste> comboTipoTeste;

	/**
	 * Botão para se iniciar um novo Teste. Variavel de referência a Button na
	 * view.
	 */
	@FXML
	private Button botaoNovo;

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

	/** Referência ao Stage atual. */
	private Stage novoTesteStage;

	/** Referência ao Stage da Home. */
	private Stage homeStage;

	/** O Idioma que a aplicação terá. */
	private Idioma idioma;

	/** Config referenciando o properties de i18n das Janelas. */
	private JanelasConfig label;

	/** Referência a mainApp, classe controladora da Aplicação. */
	private MainApp mainApp;

	/**
	 * Se Instancia uma nova NovoTesteController, chamado logo após o
	 * initialize() do JavaFX.
	 */
	public NovoTesteController() {
		optionsExame = FXCollections.observableArrayList(Certificacao.values());
		optionsTipoTeste = FXCollections.observableArrayList(TipoTeste.values());
	}

	/**
	 * Método padrão no JavaFX, ele é usado para se chamar a classe e iniciá-la,
	 * quando se cria um novo controller pelo método getController(). O
	 * Construtor é chamado logo após.
	 */
	@FXML
	private void initialize() {

	}

	/**
	 * Método para se setar e configurar opções na Janela Novo Teste. No JavaFX
	 * não se inicializa os controller por construtores, então esse método
	 * substitui essa função. O Método primeiramente deixa referenciado que
	 * quando se fechar a janela pelo x no canto superior direito, se chamará o
	 * método handleCancel(). Após isso, é setado todos os valores contidos no
	 * objeto tentativa, em seus respectivos Labels. Além de setar os valores
	 * das Lists nos comboBox.
	 */
	public void inicializaJanela() {
		novoTesteStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				handleCancel();
			}
		});

		label = JanelasConfig.getInstance(idioma);
		botaoNovo.setDisable(true);
		comboExame.setItems(optionsExame);
		comboTipoTeste.setItems(optionsTipoTeste);
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
	 * Método que trabalha a parte dinâmica do Botão Ok. Ele Simplesmente
	 * verifica se foi realmente selecionado um Exame e um Tipo de Teste, se
	 * sim, inicia um novo Teste, se não, exibe uma menssagem de erro. Variavel
	 * de referência a Button na view.
	 */
	@FXML
	private void handleOk() {
		if (comboExame.getValue() != null && comboTipoTeste.getValue() != null) {
			mainApp.showQuiz(ConteudoFactory.getConteudo(comboExame.getValue(), idioma, comboTipoTeste.getValue()));
			novoTesteStage.close();
		} else {
			AlertDialogsFactory.getAlertDialog(AlertType.ERROR, label.getString("novoTesteAlertTitulo"),
					label.getString("novoTesteAlertCabecalho"), label.getString("novoTesteAlertConteudo"));
		}
	}

	/**
	 * Método que trabalha a parte dinâmica do Botão Cancel. Quando se clicar
	 * nele, irá fechar a janela atual e voltará a Home. Variavel de referência
	 * a Button na view.
	 */
	@FXML
	private void handleCancel() {
		novoTesteStage.close();
		homeStage.show();
	}

	/**
	 * 
	 * Se Define uma nova Referência a Stage atual.
	 * 
	 * @param dialogStage
	 *            a nova Referência a Stage atual.
	 */
	public void setDialogStage(Stage dialogStage) {
		this.novoTesteStage = dialogStage;
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

}
