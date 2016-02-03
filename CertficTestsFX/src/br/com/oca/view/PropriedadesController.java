package br.com.oca.view;

import java.util.ArrayList;
import java.util.List;

import br.com.oca.model.enums.Idioma;
import br.com.oca.util.Observer;
import br.com.oca.util.Subject;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class PropriedadesController implements Subject {
	@FXML
	private Idioma idioma;
	
	private Stage dialogStage;
	private List<Observer> listaObservers;
	
	public PropriedadesController() {
		listaObservers = new ArrayList<Observer>();
	}
	
	@FXML
    private void initialize() {
    	
    }
	
	@FXML
	private void handleOk() {
		
	}

	@Override
	public void notifyAllObservers() {
		
		for (Observer o : listaObservers) {
			o.update(idioma);
		}
		
	}
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	@Override
	public void addObserver(Observer novoObserver) {
		listaObservers.add(novoObserver);
	}

	@Override
	public void removeObserve(Observer novoObserver) {
		listaObservers.remove(novoObserver);
	}
}
