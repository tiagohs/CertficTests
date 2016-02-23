package br.com.oca.view;

import br.com.oca.MainApp;
import br.com.oca.config.JanelasConfig;
import br.com.oca.model.enums.Idioma;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class RootLayoutController {
	@FXML
	private MenuItem itemRecortar;
	@FXML
	private MenuItem itemCopiar;
	@FXML
	private MenuItem itemColar;
	@FXML
	private MenuItem itemSelecionarTudo;
	@FXML
	private MenuItem itemDeletar;
	
	private MainApp mainApp;
	private JanelasConfig label;
	
	private Stage dialogHome;
	
	public RootLayoutController() {
		label = JanelasConfig.getInstance(Idioma.Portugues);
	}
	
	public void init() {
		itemRecortar.setDisable(true);
		itemCopiar.setDisable(true);
		itemColar.setDisable(true);
		itemSelecionarTudo.setDisable(true);
		itemDeletar.setDisable(true);
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
	
	public void setDialogHome(Stage dialogHome) {
		this.dialogHome = dialogHome;
	}

}
