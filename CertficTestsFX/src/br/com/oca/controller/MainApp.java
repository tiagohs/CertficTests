package br.com.oca.controller;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;

import br.com.oca.model.Calculos;
import br.com.oca.model.Resposta;
import br.com.oca.model.Tentativa;
import br.com.oca.model.conteudo.Conteudo;
import br.com.oca.model.enums.Idioma;
import br.com.oca.model.i18n.janelas.JanelasSource;
import br.com.oca.view.HomeController;
import br.com.oca.view.NovoTesteController;
import br.com.oca.view.QuizController;
import br.com.oca.view.ResultadoController;
import br.com.oca.view.RootLayoutController;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainApp extends Application {
	private BorderPane rootLayout;
	private FXMLLoader loader;
	private Stage homeStage;
	private Stage novoTesteStage;
	private Stage quizStage;
	private Stage resultadoStage;

	private RootLayoutController rootLayoutController;
	private HomeController homeController;
	private NovoTesteController novoTesteController;
	private QuizController quizController;
	private ResultadoController resultadoController;

	private ObservableList<Tentativa> listaTentativas;
	private JanelasSource label;
	private Idioma idioma;

	public MainApp() {
	}

	@Override
	public void start(Stage _homeStage) {
		idioma = showEscolherIdioma();
		label = JanelasSource.getInstance(idioma);

		atualizaTabelaTentativas();

		initRootLayout();
		showHome();
	}

	private Idioma showEscolherIdioma() {
		ChoiceDialog<Idioma> dialog = new ChoiceDialog<Idioma>(Idioma.Ingles, Idioma.values());
		dialog.setTitle("Escolher Idioma");
		dialog.setHeaderText("Escolher o Idioma Padrão da Aplicação.");
		dialog.setContentText("Escolha o Idioma Desejado: ");

		Optional<Idioma> idiomaEscolhido = dialog.showAndWait();
		if (idiomaEscolhido.isPresent()) {
			return idiomaEscolhido.get();
		} else {
			System.exit(0);
		}

		return null;
	}
	
	private FXMLLoader getNovoLoader() {
		
		abrirNovaJanela();
		FXMLLoader carregarFXML = new FXMLLoader();
		carregarFXML.setResources(label.getBundle());

		return carregarFXML;
	}
	
	private void abrirNovaJanela() {
		PauseTransition pause = new PauseTransition(Duration.seconds(30));
		pause.setOnFinished(e -> new Stage().hide());
		pause.play();
	}
	
	private Stage getNovoStage(FXMLLoader loader, Parent page, String tituloJanela) {

		Stage tempStage = new Stage();
		tempStage.initModality(Modality.WINDOW_MODAL);
		tempStage.setResizable(false);
		tempStage.setTitle(tituloJanela);
		tempStage.setScene(new Scene(page));
		tempStage.show();

		return tempStage;
	}

	private Parent getNovaCena(FXMLLoader loader, String caminhoFXML) {
		try {
			return loader.load(this.getClass().getResource(caminhoFXML).openStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void initRootLayout() {

		loader = getNovoLoader();
		rootLayout = (BorderPane) getNovaCena(loader, "../view/RootLayout.fxml");
		homeStage = getNovoStage(loader, rootLayout, "CertificTests");

		rootLayoutController = loader.getController();
		rootLayoutController.setMainApp(this);
		rootLayoutController.setDialogHome(homeStage);
	}

	public void showHome() {

		loader = getNovoLoader();
		rootLayout.setCenter(getNovaCena(loader, "../view/Home.fxml"));

		homeController = loader.getController();
		homeController.setHomeStage(homeStage);
		homeController.setIdioma(idioma);
		homeController.setMainApp(this);
		homeController.setListaTentativas(listaTentativas);
		homeController.setLoader(loader);
	}

	public void showNovoTesteDialog() {

		loader = getNovoLoader();
		novoTesteStage = getNovoStage(loader, (AnchorPane) getNovaCena(loader, "../view/NovoTeste.fxml"),
				label.getString("homeTituloNovoTeste"));

		novoTesteController = loader.getController();
		novoTesteController.setIdioma(idioma);
		novoTesteController.setDialogStage(novoTesteStage);
		novoTesteController.setDialogHome(homeStage);
		novoTesteController.setMainApp(this);
	}

	public void showQuiz(Conteudo conteudo) {

		loader = getNovoLoader();
		quizStage = getNovoStage(loader, (AnchorPane) getNovaCena(loader, "../view/Quiz.fxml"),
				label.getString("homeTituloNovoQuiz"));

		quizController = loader.getController();
		quizController.setDialogStage(quizStage);
		quizController.setDialogHome(homeStage);
		quizController.setIdioma(idioma);
		quizController.setConteudo(conteudo);
		quizController.setMainApp(this);
		quizController.iniciarQuiz();
	}

	public void showResultadoController(ArrayList<Resposta> listaRespostas, Conteudo conteudo,
			Calendar tempoRegistrado) {

		loader = getNovoLoader();
		resultadoStage = getNovoStage(loader, (AnchorPane) getNovaCena(loader, "../view/Resultado.fxml"),
				label.getString("homeTituloResultados"));

		resultadoController = loader.getController();
		resultadoController.setDialogStage(resultadoStage);
		resultadoController.setListaRespostas(listaRespostas);
		resultadoController.setConteudo(conteudo);
		resultadoController.setCalculos(new Calculos(conteudo, listaRespostas, tempoRegistrado));
		resultadoController.setDialogHome(homeStage);
		resultadoController.setIdioma(idioma);
		resultadoController.setMainApp(this);
		resultadoController.calcularResultados();

	}

	public void atualizaTabelaTentativas() {
		listaTentativas = FXCollections.observableArrayList();

		try {
			FileInputStream fileInputStream = new FileInputStream(Tentativa.filename);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			while (true) {
				try {
					Tentativa tentativas = (Tentativa) objectInputStream.readObject();
					listaTentativas.add(tentativas);
				} catch (EOFException e) {
					break;
				} catch (FileNotFoundException e) {
					break;
				}
			}
			objectInputStream.close();
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo ainda não Criado.");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Stage getPrimaryStage() {
		return homeStage;
	}

	public ObservableList<Tentativa> getListaTentativas() {
		return listaTentativas;
	}

	public static void main(String[] args) {
		launch(args);
	}

	public Idioma getIdioma() {
		return idioma;
	}

	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}

}
