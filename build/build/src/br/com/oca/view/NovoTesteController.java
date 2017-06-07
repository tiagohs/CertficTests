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
 * A Classe NovoTesteController � o Controller da View NovoTeste, uma Janela
 * onde com base na escolha do Exame e de Tipo, se iniciar� um novo Teste.
 * 
 * Classe <code>NovoTesteController</code>
 * 
 * @author Tiago Henrique
 * @version 1.0
 *
 */
public class NovoTesteController {
	/**
	 * Combo contendo como op��es os exames. Variavel de refer�ncia a ComboBox
	 * na view.
	 */
	@FXML
	private ComboBox<Certificacao> comboExame;

	/**
	 * Combo contendo como op��es os tipos de testes (30, 60 ou 90 Quest�es).
	 * Variavel de refer�ncia a ComboBox na view.
	 */
	@FXML
	private ComboBox<TipoTeste> comboTipoTeste;

	/**
	 * Bot�o para se iniciar um novo Teste. Variavel de refer�ncia a Button na
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

	/** Refer�ncia ao Stage atual. */
	private Stage novoTesteStage;

	/** Refer�ncia ao Stage da Home. */
	private Stage homeStage;

	/** O Idioma que a aplica��o ter�. */
	private Idioma idioma;

	/** Config referenciando o properties de i18n das Janelas. */
	private JanelasConfig label;

	/** Refer�ncia a mainApp, classe controladora da Aplica��o. */
	private MainApp mainApp;

	/**
	 * Se Instancia uma nova NovoTesteController, chamado logo ap�s o
	 * initialize() do JavaFX.
	 */
	public NovoTesteController() {
		optionsExame = FXCollections.observableArrayList(Certificacao.values());
		optionsTipoTeste = FXCollections.observableArrayList(TipoTeste.values());
	}

	/**
	 * M�todo padr�o no JavaFX, ele � usado para se chamar a classe e inici�-la,
	 * quando se cria um novo controller pelo m�todo getController(). O
	 * Construtor � chamado logo ap�s.
	 */
	@FXML
	private void initialize() {

	}

	/**
	 * M�todo para se setar e configurar op��es na Janela Novo Teste. No JavaFX
	 * n�o se inicializa os controller por construtores, ent�o esse m�todo
	 * substitui essa fun��o. O M�todo primeiramente deixa referenciado que
	 * quando se fechar a janela pelo x no canto superior direito, se chamar� o
	 * m�todo handleCancel(). Ap�s isso, � setado todos os valores contidos no
	 * objeto tentativa, em seus respectivos Labels. Al�m de setar os valores
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
	 * M�todo que trabalha a parte din�mica do Bot�o Ok. Ele Simplesmente
	 * verifica se foi realmente selecionado um Exame e um Tipo de Teste, se
	 * sim, inicia um novo Teste, se n�o, exibe uma menssagem de erro. Variavel
	 * de refer�ncia a Button na view.
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
	 * M�todo que trabalha a parte din�mica do Bot�o Cancel. Quando se clicar
	 * nele, ir� fechar a janela atual e voltar� a Home. Variavel de refer�ncia
	 * a Button na view.
	 */
	@FXML
	private void handleCancel() {
		novoTesteStage.close();
		homeStage.show();
	}

	/**
	 * 
	 * Se Define uma nova Refer�ncia a Stage atual.
	 * 
	 * @param dialogStage
	 *            a nova Refer�ncia a Stage atual.
	 */
	public void setDialogStage(Stage dialogStage) {
		this.novoTesteStage = dialogStage;
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

}
