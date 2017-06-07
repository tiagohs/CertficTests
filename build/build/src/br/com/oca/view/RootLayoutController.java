/**
 * 
 * Copyright (c) 2016, CertificTests and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * 
 */
package br.com.oca.view;

import br.com.oca.MainApp;
import br.com.oca.config.JanelasConfig;
import br.com.oca.model.enums.Idioma;
import br.com.oca.util.AlertDialogsFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * A Classe RootLayoutController � onde se cont�m o menu, e ficara junto com a
 * Home. � O Layout da Home.
 * 
 * Classe <code>RootLayoutController</code>
 * 
 * @author Tiago Henrique
 * @version 1.0
 *
 */
public class RootLayoutController {
	/** O SubMenu Recortar. Variavel de refer�ncia a MenuItem na view. */
	@FXML
	private MenuItem itemRecortar;

	/** O SubMenu Copiar. Variavel de refer�ncia a MenuItem na view. */
	@FXML
	private MenuItem itemCopiar;

	/** O SubMenu Colar. Variavel de refer�ncia a MenuItem na view. */
	@FXML
	private MenuItem itemColar;

	/** O SubMenu Selecionar Tudo. Variavel de refer�ncia a MenuItem na view. */
	@FXML
	private MenuItem itemSelecionarTudo;

	/** O SubMenu Deletar. Variavel de refer�ncia a MenuItem na view. */
	@FXML
	private MenuItem itemDeletar;

	/** Refer�ncia a mainApp, classe controladora da Aplica��o. */
	private MainApp mainApp;

	/** Config referenciando o properties de i18n das Janelas. */
	private JanelasConfig label;

	/** Refer�ncia ao Stage da Home. */
	private Stage dialogHome;

	/**
	 * Se Instancia uma nova ResultadoController, chamado logo ap�s o
	 * initialize() do JavaFX.
	 */
	public RootLayoutController() {
		label = JanelasConfig.getInstance(Idioma.Portugues);
	}

	/**
	 * M�todo para ser chamado quando se desejar preenxer os campos da Janela.
	 * No JavaFX n�o se inicializa os controller por construtores, ent�o esse
	 * m�todo substitui essa fun��o. Nele � setado todos os valores contidos no
	 * objeto tentativa.
	 */
	public void init() {
		itemRecortar.setDisable(true);
		itemCopiar.setDisable(true);
		itemColar.setDisable(true);
		itemSelecionarTudo.setDisable(true);
		itemDeletar.setDisable(true);
	}

	/**
	 * M�todo que trabalha a parte din�mica do item Sobre. Quando Cliclar nele
	 * um Alert ir� abrir contendo delalhes sobre a aplica��o . Variavel de
	 * refer�ncia a MenuItem na view.
	 */
	@FXML
	private void itemSobre() {
		AlertDialogsFactory.getAlertDialog(AlertType.CONFIRMATION, label.getString("menuSobre"),
				label.getString("menuSobre"), label.getString("menuSobreDescricao"));
	}

	/**
	 * M�todo que trabalha a parte din�mica do item Sair. Quando se Clicar nele,
	 * a aplica��o ir� finalizar. Variavel de refer�ncia a MenuItem na view.
	 */
	@FXML
	private void itemSair() {
		System.exit(0);
	}

	/**
	 * M�todo que trabalha a parte din�mica do item Novo. Quando se clica nele,
	 * se abre a janela Novo Teste. Variavel de refer�ncia a MenuItem na view.
	 */
	@FXML
	private void itemNovo() {
		mainApp.showNovoTesteDialog();
		dialogHome.hide();
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
	 * Se Define uma nova Refer�ncia ao Stage da Home.
	 * 
	 * @param dialogHome
	 *            a nova Refer�ncia ao Stage da Home.
	 */
	public void setDialogHome(Stage dialogHome) {
		this.dialogHome = dialogHome;
	}

}
