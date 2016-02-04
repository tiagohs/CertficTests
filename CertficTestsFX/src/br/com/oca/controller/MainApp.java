package br.com.oca.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.controlsfx.dialog.Dialogs;

import br.com.oca.model.Calculos;
import br.com.oca.model.Resposta;
import br.com.oca.model.Tentativas;
import br.com.oca.model.conteudo.Conteudo;
import br.com.oca.model.enums.Certificacao;
import br.com.oca.model.enums.Idioma;
import br.com.oca.model.enums.TipoTeste;
import br.com.oca.model.i18n.janelas.JanelasSource;
import br.com.oca.util.TentativaWrapper;
import br.com.oca.view.HomeController;
import br.com.oca.view.NovoTesteController;
import br.com.oca.view.PropriedadesController;
import br.com.oca.view.QuizController;
import br.com.oca.view.ResultadoController;
import br.com.oca.view.RootLayoutController;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

public class MainApp extends Application {
	private ObservableList<Tentativas> listaTentativas;
	private Stage homeStage;
	private BorderPane rootLayout;
	private JanelasSource label;
	private Idioma idioma;

	public MainApp() {
		listaTentativas = listaTentativas = FXCollections.observableArrayList();
		listaTentativas.add(new Tentativas(Certificacao.OCA, TipoTeste.TESTE_1, 100.0, 5.0));
		listaTentativas.add(new Tentativas(Certificacao.OCA, TipoTeste.TESTE_2, 100.0, 5.0));
	}

	@Override
	public void start(Stage _stagePrimario) {

		homeStage = _stagePrimario;
		homeStage.setTitle("CertficTests");
		idioma = Idioma.Portugues;
		label = JanelasSource.getInstance(idioma);

		initRootLayout();

		showHome();
	}
	
	private FXMLLoader getNovoLoader() {

		FXMLLoader loader = new FXMLLoader();
		loader.setResources(label.getBundle());

		return loader;
	}
	
	private void abrirNovaJanela() {
		
		Window window = new Stage();
		PauseTransition pause = new PauseTransition(Duration.seconds(30));
		pause.setOnFinished(e -> window.hide());
		pause.play();
	}
	
	private Stage getNovoStage(FXMLLoader loader, AnchorPane page, String tituloJanela) {

		Stage stage = new Stage();
		stage.initModality(Modality.WINDOW_MODAL);
		stage.setTitle(tituloJanela);
		stage.setScene(new Scene(page));
		stage.show();

		return stage;
	}

	private AnchorPane getNovoAnchorPane(FXMLLoader loader, String caminhoFXML) {
		try {
			return (AnchorPane) loader.load(this.getClass().getResource(caminhoFXML).openStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void initRootLayout() {
		try {
			FXMLLoader loader = getNovoLoader();
			rootLayout = (BorderPane) loader.load(this.getClass().getResource("../view/RootLayout.fxml").openStream());

			Scene scene = new Scene(rootLayout);
			homeStage.setScene(scene);

			RootLayoutController controller = loader.getController();
			controller.setMainApp(this);
			controller.setDialogHome(homeStage);

			homeStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showPropriedades() {
		
		abrirNovaJanela();
		FXMLLoader loader = getNovoLoader();
		AnchorPane page = getNovoAnchorPane(loader, "../view/Propriedades.fxml");

		Stage propriedadesStage = getNovoStage(loader, page, label.getString("propriedadesTitulo"));

		PropriedadesController propriedadesController = loader.getController();
		propriedadesController.setDialogStage(propriedadesStage);

	}

	public void showHome() {
		
		FXMLLoader loader = getNovoLoader();
		AnchorPane personOverview = getNovoAnchorPane(loader, "../view/Home.fxml");

		rootLayout.setCenter(personOverview);

		HomeController homeController = loader.getController();
		homeController.setHomeStage(homeStage);
		homeController.setMainApp(this);
		homeController.setListaTentativas(listaTentativas);
		homeController.setLoader(loader);

	}

	public void showNovoTesteDialog() {
		
		abrirNovaJanela();
		FXMLLoader loader = getNovoLoader();
		AnchorPane page = getNovoAnchorPane(loader, "../view/NovoTeste.fxml");

		Stage novoTesteStage = getNovoStage(loader, page, "Novo Teste");

		NovoTesteController novoTesteController = loader.getController();
		novoTesteController.setIdioma(idioma);
		novoTesteController.setDialogStage(novoTesteStage);
		novoTesteController.setMainApp(this);
	}

	public void showQuiz(Conteudo conteudo) {

		abrirNovaJanela();
		FXMLLoader loader = getNovoLoader();
		AnchorPane page = getNovoAnchorPane(loader, "../view/Quiz.fxml");
		Stage quizStage = getNovoStage(loader, page, "Quiz - CertificTests");

		QuizController quizController = loader.getController();
		quizController.setDialogStage(quizStage);
		quizController.setDialogHome(homeStage);
		quizController.setIdioma(idioma);
		quizController.setConteudo(conteudo);
		quizController.setMainApp(this);
		quizController.iniciarQuiz();
	}

	public void showResultadoController(ArrayList<Resposta> listaRespostas, Conteudo conteudo) {

		abrirNovaJanela();
		FXMLLoader loader = getNovoLoader();
		AnchorPane page = getNovoAnchorPane(loader, "../view/Resultado.fxml");
		Stage resultadoStage = getNovoStage(loader, page, "Resultados - CertificTests");
		
		
		Calculos calculos = new Calculos(conteudo, listaRespostas);
		ResultadoController resultadoController = loader.getController();
		resultadoController.setDialogStage(resultadoStage);
		resultadoController.setListaRespostas(listaRespostas);
		resultadoController.setConteudo(conteudo);
		resultadoController.setCalculos(calculos);
		resultadoController.setDialogHome(homeStage);
		resultadoController.setIdioma(idioma);
		resultadoController.calcularResultados();

	}

	public void loadPersonDataFromFile(File file) {
		try {
			JAXBContext context = JAXBContext.newInstance(TentativaWrapper.class);
			Unmarshaller um = context.createUnmarshaller();

			TentativaWrapper wrapper = (TentativaWrapper) um.unmarshal(file);

			listaTentativas.clear();
			listaTentativas.addAll(wrapper.getPersons());

			setPersonFilePath(file);

		} catch (Exception e) { // catches ANY exception
			Dialogs.create().title("Erro").masthead("Não foi possível carregar dados do arquivo:\n" + file.getPath())
					.showException(e);
		}
	}

	public void savePersonDataToFile(File file) {
		try {
			JAXBContext context = JAXBContext.newInstance(TentativaWrapper.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			// Envolvendo nossos dados da pessoa.
			TentativaWrapper wrapper = new TentativaWrapper();
			wrapper.setPersons(listaTentativas);

			// Enpacotando e salvando XML no arquivo.
			m.marshal(wrapper, file);

			// Saalva o caminho do arquivo no registro.
			setPersonFilePath(file);
		} catch (Exception e) { // catches ANY exception
			Dialogs.create().title("Erro").masthead("Não foi possível salvar os dados do arquivo:\n" + file.getPath())
					.showException(e);
		}
	}

	public File getPersonFilePath() {
		Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
		String filePath = prefs.get("filePath", null);
		if (filePath != null) {
			return new File(filePath);
		} else {
			return null;
		}
	}

	public void setPersonFilePath(File file) {
		Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
		if (file != null) {
			prefs.put("filePath", file.getPath());

			// Update the stage title.
			homeStage.setTitle("CertficTests - " + file.getName());
		} else {
			prefs.remove("filePath");

			// Update the stage title.
			homeStage.setTitle("CertficTests");
		}
	}

	public Stage getPrimaryStage() {
		return homeStage;
	}

	public ObservableList<Tentativas> getListaTentativas() {
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
