package br.com.oca.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertDialogsFactory {
	
	public static Alert getAlertDialog(AlertType tipoDialog, String titulo, String cabecalho, String descricao) {
		
		switch (tipoDialog) {
			case CONFIRMATION:
				return preenxer(new Alert(AlertType.ERROR), titulo, cabecalho, descricao);
			case ERROR:
				return preenxer(new Alert(AlertType.ERROR), titulo, cabecalho, descricao);
			case INFORMATION:
				return preenxer(new Alert(AlertType.ERROR), titulo, cabecalho, descricao);
			case WARNING:
				return preenxer(new Alert(AlertType.ERROR), titulo, cabecalho, descricao);
			case NONE:
				return preenxer(new Alert(AlertType.ERROR), titulo, cabecalho, descricao);
			default:
				return null;
		}
	}
	
	private static Alert preenxer(Alert alert, String titulo, String cabecalho, String descricao) {
		alert.setTitle(titulo);
		alert.setHeaderText(cabecalho);
		alert.setContentText(descricao);
		
		alert.showAndWait();
		
		return alert;
	}
}
