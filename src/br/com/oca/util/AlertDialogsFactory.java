/**
 * 
 * Copyright (c) 2016, CertificTests and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * 
 */
package br.com.oca.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * 
 * A Classe AlertDialogsFactory realiza a cria��o de Pop ups em diversas
 * Finalidades. Com Base no Enum AlertType, se cria uma janelinha de informa��o,
 * erro, confirma��o, perigo, ou sem nenhuma formata��o, com titulo, cabe�alho e
 * descri��o.
 * 
 * Classe <code>AlertDialogsFactory</code>
 * 
 * @author Tiago Henrique
 * @version 1.0
 *
 */
public class AlertDialogsFactory {

	/**
	 * Possui um Construtor Private para se assegurar que n�o se criar� um
	 * objeto dessa classe.
	 */
	private AlertDialogsFactory() {

	}

	/**
	 * 
	 * Unico m�todo public da classe, se obt�m uma Alert totalmente formatada,
	 * com base nos par�metros passados.
	 * 
	 * @param tipoDialog
	 *            O Tipo de Alert (CONFIRMATION, ERROR, INFORMATION, WARNING ou
	 *            NONE).
	 * @param titulo
	 *            O Titulo do Alert.
	 * @param cabecalho
	 *            O Cabe�alho, uma pr�via informa��o da Alert em destaque.
	 * @param descricao
	 *            A Descri��o, o conteudo da Alert.
	 * @return a Alert totalmente formatada.
	 */
	public static Alert getAlertDialog(AlertType tipoDialog, String titulo, String cabecalho, String descricao) {
		Alert alert = null;
		
		switch (tipoDialog) {
		case CONFIRMATION:
			alert = preenxer(new Alert(AlertType.CONFIRMATION), titulo, cabecalho, descricao);
		case ERROR:
			alert = preenxer(new Alert(AlertType.ERROR), titulo, cabecalho, descricao);
		case INFORMATION:
			alert = preenxer(new Alert(AlertType.INFORMATION), titulo, cabecalho, descricao);
		case WARNING:
			alert = preenxer(new Alert(AlertType.WARNING), titulo, cabecalho, descricao);
		case NONE:
			alert = preenxer(new Alert(AlertType.NONE), titulo, cabecalho, descricao);
		}
		
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image("file:resources/images/CertificTests.png"));
		alert.getDialogPane().getStylesheets().add(AlertDialogsFactory.class.getResource("/assets/Home.css").toExternalForm());
		
		return alert;
	}

	/**
	 * 
	 * M�todo auxiliar para a cria��o da Alert. Se realiza os Sets no conteudo
	 * do Alert.
	 * 
	 * @param alert
	 *            o objeto do Alert j� com o seu AlertType setado.
	 * @param titulo
	 *            O Titulo do Alert.
	 * @param cabecalho
	 *            O Cabe�alho, uma pr�via informa��o da Alert em destaque.
	 * @param descricao
	 *            A Descri��o, o conteudo da Alert.
	 * @return o Alert com suas informa��es setadas.
	 */
	private static Alert preenxer(Alert alert, String titulo, String cabecalho, String descricao) {
		alert.setTitle(titulo);
		alert.setHeaderText(cabecalho);
		alert.setContentText(descricao);

		alert.showAndWait();

		return alert;
	}
}
