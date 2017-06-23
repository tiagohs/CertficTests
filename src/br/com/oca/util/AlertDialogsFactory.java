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
 * A Classe AlertDialogsFactory realiza a criação de Pop ups em diversas
 * Finalidades. Com Base no Enum AlertType, se cria uma janelinha de informação,
 * erro, confirmação, perigo, ou sem nenhuma formatação, com titulo, cabeçalho e
 * descrição.
 * 
 * Classe <code>AlertDialogsFactory</code>
 * 
 * @author Tiago Henrique
 * @version 1.0
 *
 */
public class AlertDialogsFactory {

	/**
	 * Possui um Construtor Private para se assegurar que não se criará um
	 * objeto dessa classe.
	 */
	private AlertDialogsFactory() {

	}

	/**
	 * 
	 * Unico método public da classe, se obtém uma Alert totalmente formatada,
	 * com base nos parâmetros passados.
	 * 
	 * @param tipoDialog
	 *            O Tipo de Alert (CONFIRMATION, ERROR, INFORMATION, WARNING ou
	 *            NONE).
	 * @param titulo
	 *            O Titulo do Alert.
	 * @param cabecalho
	 *            O Cabeçalho, uma prévia informação da Alert em destaque.
	 * @param descricao
	 *            A Descrição, o conteudo da Alert.
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
	 * Método auxiliar para a criação da Alert. Se realiza os Sets no conteudo
	 * do Alert.
	 * 
	 * @param alert
	 *            o objeto do Alert já com o seu AlertType setado.
	 * @param titulo
	 *            O Titulo do Alert.
	 * @param cabecalho
	 *            O Cabeçalho, uma prévia informação da Alert em destaque.
	 * @param descricao
	 *            A Descrição, o conteudo da Alert.
	 * @return o Alert com suas informações setadas.
	 */
	private static Alert preenxer(Alert alert, String titulo, String cabecalho, String descricao) {
		alert.setTitle(titulo);
		alert.setHeaderText(cabecalho);
		alert.setContentText(descricao);

		alert.showAndWait();

		return alert;
	}
}
