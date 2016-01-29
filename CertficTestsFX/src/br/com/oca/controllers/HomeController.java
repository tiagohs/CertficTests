package br.com.oca.controllers;

import br.com.oca.model.Tentativa;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class HomeController {
	@FXML
    private TableView<Tentativa> tabelaTentativas;
    @FXML
    private TableColumn<Tentativa, Double> colunaTentativas;
    @FXML
    private TableColumn<Tentativa, String> colunaTesteEscolhido;
    @FXML
    private TableColumn<Tentativa, String> colunaNota;
    @FXML
    private TableColumn<Tentativa, String> colunaAcertos;

    private MainApp mainApp;
    
    public HomeController() {
		
	}
    
    @FXML
    private void initialize() {
        // Inicializa a tablea de pessoa com duas colunas.
    	//colunaTentativas.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
    	colunaTesteEscolhido.setCellValueFactory(cellData -> cellData.getValue().getTesteEscolhido());
    	colunaNota.setCellValueFactory(cellData -> cellData.getValue().getNotaStringProperty());
    	colunaAcertos.setCellValueFactory(cellData -> cellData.getValue().getNumeroAcertosStringProperty());
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        tabelaTentativas.setItems(mainApp.getListaTentativas());
    }

}
