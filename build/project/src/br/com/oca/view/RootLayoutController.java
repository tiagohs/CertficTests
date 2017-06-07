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
 * A Classe RootLayoutController é onde se contém o menu, e ficara junto com a
 * Home. É O Layout da Home.
 * 
 * Classe <code>RootLayoutController</code>
 * 
 * @author Tiago Henrique
 * @version 1.0
 *
 */
public class RootLayoutController {
	/** O SubMenu Recortar. Variavel de referência a MenuItem na view. */
	@FXML
	private MenuItem itemRecortar;

	/** O SubMenu Copiar. Variavel de referência a MenuItem na view. */
	@FXML
	private MenuItem itemCopiar;

	/** O SubMenu Colar. Variavel de referência a MenuItem na view. */
	@FXML
	private MenuItem itemColar;

	/** O SubMenu Selecionar Tudo. Variavel de referência a MenuItem na view. */
	@FXML
	private MenuItem itemSelecionarTudo;

	/** O SubMenu Deletar. Variavel de referência a MenuItem na view. */
	@FXML
	private MenuItem itemDeletar;

	/** Referência a mainApp, classe controladora da Aplicação. */
	private MainApp mainApp;

	/** Config referenciando o properties de i18n das Janelas. */
	private JanelasConfig label;

	/** Referência ao Stage da Home. */
	private Stage dialogHome;

	/**
	 * Se Instancia uma nova ResultadoController, chamado logo após o
	 * initialize() do JavaFX.
	 */
	public RootLayoutController() {
		label = JanelasConfig.getInstance(Idioma.Portugues);
	}

	/**
	 * Método para ser chamado quando se desejar preenxer os campos da Janela.
	 * No JavaFX não se inicializa os controller por construtores, então esse
	 * método substitui essa função. Nele é setado todos os valores contidos no
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
	 * Método que trabalha a parte dinâmica do item Sobre. Quando Cliclar nele
	 * um Alert irá abrir contendo delalhes sobre a aplicação . Variavel de
	 * referência a MenuItem na view.
	 */
	@FXML
	private void itemSobre() {
		AlertDialogsFactory.getAlertDialog(AlertType.CONFIRMATION, label.getString("menuSobre"),
				label.getString("menuSobre"), label.getString("menuSobreDescricao"));
	}

	/**
	 * Método que trabalha a parte dinâmica do item Sair. Quando se Clicar nele,
	 * a aplicação irá finalizar. Variavel de referência a MenuItem na view.
	 */
	@FXML
	private void itemSair() {
		System.exit(0);
	}

	/**
	 * Método que trabalha a parte dinâmica do item Novo. Quando se clica nele,
	 * se abre a janela Novo Teste. Variavel de referência a MenuItem na view.
	 */
	@FXML
	private void itemNovo() {
		mainApp.showNovoTesteDialog();
		dialogHome.hide();
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
	 * Se Define uma nova Referência ao Stage da Home.
	 * 
	 * @param dialogHome
	 *            a nova Referência ao Stage da Home.
	 */
	public void setDialogHome(Stage dialogHome) {
		this.dialogHome = dialogHome;
	}

}
