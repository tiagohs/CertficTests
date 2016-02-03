package br.com.oca.view;

import org.controlsfx.dialog.Dialogs;

import br.com.oca.controllers.MainApp;
import br.com.oca.model.enums.Certificacao;
import br.com.oca.model.enums.Idioma;
import br.com.oca.model.enums.TipoTeste;
import br.com.oca.model.i18n.janelas.JanelasSource;
import br.com.oca.util.Observer;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class RootLayoutController implements Observer {
	private MainApp mainApp;
	private Idioma idioma;
	private JanelasSource label;
	
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
	}
	
	@FXML
	private void itemPreferencias() {
		mainApp.showPropriedades();
	}
	
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
	
	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}
	
	@Override
	public void update(Idioma idioma) {
		setIdioma(idioma);
	}

}
