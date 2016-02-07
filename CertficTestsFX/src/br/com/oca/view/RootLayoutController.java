package br.com.oca.view;

import br.com.oca.controller.MainApp;
import br.com.oca.model.enums.Idioma;
import br.com.oca.model.i18n.janelas.JanelasSource;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class RootLayoutController {
	private MainApp mainApp;
	private Idioma idioma;
	private JanelasSource label;
	
	private Stage dialogHome;
	
	public RootLayoutController() {
		label = JanelasSource.getInstance(Idioma.Portugues);
	}
	
	@FXML
    private void itemSobre() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(label.getString("menuSobre"));
		alert.setHeaderText(label.getString("menuSobre"));
		alert.setContentText(label.getString("menuSobreDescricao"));

		alert.showAndWait();
    }
	
	@FXML
    private void itemSair() {
        System.exit(0);
    }
	
	@FXML
	private void itemNovo() {
		mainApp.showNovoTesteDialog();
		dialogHome.hide();
	}
	
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
	
	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}
	
	public void setDialogHome(Stage dialogHome) {
		this.dialogHome = dialogHome;
	}

}
