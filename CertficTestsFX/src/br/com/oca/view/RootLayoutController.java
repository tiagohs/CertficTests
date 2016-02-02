package br.com.oca.view;

import org.controlsfx.dialog.Dialogs;

import br.com.oca.controllers.MainApp;
import br.com.oca.model.enums.Idioma;
import br.com.oca.model.i18n.janelas.JanelasSource;
import javafx.fxml.FXML;

public class RootLayoutController {
	private MainApp mainApp;
	private JanelasSource label;
	
	public RootLayoutController() {
		label = JanelasSource.getInstance(Idioma.Portugues);
	}
	
	@FXML
    private void itemSobre() {
        Dialogs.create()
            .title(label.getString("menuSobre"))
            .masthead(label.getString("menuSobre"))
            .message(label.getString("menuSobreDescricao"))
            .showInformation();
    }
	
	@FXML
    private void itemSair() {
        System.exit(0);
    }
	
	@FXML
	private void itemNovo() {
		
		//mainApp.showQuiz(nomeExameResult, tipoTesteResult);
	}
	
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

}
