package br.com.oca;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import br.com.oca.model.Calculos;
import br.com.oca.model.Resposta;
import br.com.oca.model.Tentativa;
import br.com.oca.model.conteudo.Conteudo;
import br.com.oca.model.enums.Idioma;
import br.com.oca.model.i18n.janelas.JanelasSource;
import br.com.oca.view.DetalhesQuestoesRespondidasController;
import br.com.oca.view.HomeController;
import br.com.oca.view.NovoTesteController;
import br.com.oca.view.QuizController;
import br.com.oca.view.ResultadoController;
import br.com.oca.view.RootLayoutController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * A Classe Principal da Aplicação, ela é chamada pelo JavaFX e organiza as Scenes da Aplicação.
 * 
 * Classe <code>MainApp</code>
 * 
 * @author Tiago Henrique
 * @version 1.0 
 *
 */
public class MainApp extends Application {
	/** rootLayout */
	private BorderPane rootLayout;
	
	/** loader */
	private FXMLLoader loader;

	/** detalhesQuestoesRespondidasStage */
	private Stage detalhesQuestoesRespondidasStage;

	/** homeStage */
	private Stage homeStage;

	/** novoTesteStage */
	private Stage novoTesteStage;

	/** quizStage */
	private Stage quizStage;

	/** resultadoStage */
	private Stage resultadoStage;
	
	/** detalhesQuestoesRespondidasController */
	private DetalhesQuestoesRespondidasController detalhesQuestoesRespondidasController;

	/** rootLayoutController */
	private RootLayoutController rootLayoutController;

	/** homeController */
	private HomeController homeController;

	/** novoTesteController */
	private NovoTesteController novoTesteController;

	/** quizController */
	private QuizController quizController;

	/** resultadoController */
	private ResultadoController resultadoController;

	/** listaTentativas */
	private ObservableList<Tentativa> listaTentativas;

	/** label */
	private JanelasSource label;

	/** idioma */
	private Idioma idioma;
	
	/**
	 * Instancia uma Nova Classe MainApp
	 */
	public MainApp() {
	}
	
	/**
	 * Classe Padrão das Aplicações JavaFX.
	 * Ela é Chamada pela <i>Thread</i> do <i>JavaFX Application</i>, pelo método <i>launched</i>.
	 * 
	 * @param homeStage O <i>Stage Primário</i>, da primeira Janela na Aplicação.
	 * 
	 */
	@Override
	public void start(Stage homeStage) {
		idioma = showEscolherIdioma();
		label = JanelasSource.getInstance(idioma);
		
		initRootLayout(homeStage);
		showHome();
	}

	/**
	 * Uma <i>Janela Dialog</i> onde se é perguntado ao Cliente qual <i>Idioma</i> ele deseja que sua aplicação tenha.
	 * É <i>Obrigatório</i> que se escolha um Idioma.
	 * 
	 * @return Retorna o <i>Idioma</i> escolhido, um Enumerado do Tipo <i>Idioma</i>.
	 */
	private Idioma showEscolherIdioma() {
		ChoiceDialog<Idioma> dialog = new ChoiceDialog<Idioma>(Idioma.Ingles, Idioma.values());
		dialog.setTitle("Escolher Idioma");
		dialog.setHeaderText("Escolher o Idioma Padrão da Aplicação.");
		dialog.setContentText("Escolha o Idioma Desejado: ");
		
		//Verifica no ComboBox qual Opção o Usuário Escolheu.
		Optional<Idioma> idiomaEscolhido = dialog.showAndWait();
		if (idiomaEscolhido.isPresent()) {
			return idiomaEscolhido.get();
		} else {
			System.exit(0);
		}

		return null;
	}
	
	/**
	 * 
	 * Para se ler um <i>XML Layout</i>, é necessário um <i>Loader</i>.
	 * Se é passado nesse momento o <i>Idioma</i> escolhido, para o <i>i18n</i> ser configurado.
	 * 
	 * @return um Loader do Tipo <i>FXMLoader</i>
	 */
	private FXMLLoader getNovoLoader() {
		
		FXMLLoader carregarFXML = new FXMLLoader();
		carregarFXML.setResources(label.getBundle());

		return carregarFXML;
	}
	
	/**
	 * Assim como em uma Peça de teatro, o <i>Stage</i> (Palco) é o container principal de uma <i>Window</i> (Janela).
	 * Dentro dela terão <i>Scenes</i> (Cenas) e dentro delas, os <i>Nodos</i> (Nós) de JavaFX.
	 * Esse método cria um Stage com valores padrões, conveniênte a essa aplicação.
	 * Adiciona um <i>Icone</i> Padrão para Todas as Janelas.
	 * 
	 * @param page O <i>Nodo</i> da Janela (<i>BorderPane, AnchorPane</i>) para ser Adicionado a <i>Scene</i> da janela.
	 * @param tituloJanela O <i>Titulo</i> da Janela em questão.
	 * 
	 * @return Retorna um <i>Stage</i> com uma <i>Window</i> configurada.
	 */
	private Stage getNovoStage(Parent page, String tituloJanela) {

		Stage tempStage = new Stage();
		tempStage.initModality(Modality.WINDOW_MODAL);
		tempStage.setResizable(false);
		tempStage.setTitle(tituloJanela);
		tempStage.getIcons().add(new Image("file:resources/images/CertificTests.png"));
		tempStage.setScene(new Scene(page));
		
		tempStage.show();

		return tempStage;
	}

	/**
	 * Cria um Novo <i>Nodo (Nós), que pode ser um BorderPane, AnchorPane, TextBox, etc..
	 * 
	 * @param loader O <i>loader</i> usado para carregar o <i>XML Layout</i> da janela em questão.
	 * @param caminhoFXML O Caminho de onde se localiza o arquivo <i>.fxml</i>, contendo o <i>XML Layout</i> da Página.
	 * @return Retorna um novo <i>Nodo</i> (Nós), que será adicionado em uma Cena.
	 */
	private Parent getNewNodo(FXMLLoader loader, String caminhoFXML) {
		try {
			return loader.load(this.getClass().getResource(caminhoFXML).openStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void initRootLayout(Stage _homeStage) {
		
		homeStage = _homeStage;
		homeStage.setTitle("CertificTests");
		loader = getNovoLoader();
		rootLayout = (BorderPane) getNewNodo(loader, "view/RootLayout.fxml");
		homeStage.setScene(new Scene(rootLayout));
		homeStage.show();
		
		rootLayoutController = loader.getController();
		rootLayoutController.setMainApp(this);
		rootLayoutController.setDialogHome(homeStage);
		rootLayoutController.init();
	}

	public void showHome() {

		loader = getNovoLoader();
		rootLayout.setCenter(getNewNodo(loader, "view/Home.fxml"));
		
		atualizaListaTentativas();
		homeController = loader.getController();
		homeController.setHomeStage(homeStage);
		homeController.setIdioma(idioma);
		homeController.setMainApp(this);
		homeController.setListaTentativas(listaTentativas);
		homeController.init();
	}
	
	public void showDetalhesQuestoesRespondidas(Tentativa tentativa) {
		
		loader = getNovoLoader();
		detalhesQuestoesRespondidasStage = getNovoStage((AnchorPane) getNewNodo(loader, "view/DetalhesQuestoesRespondidas.fxml"),
				label.getString("detalhesQuestoesTitulo"));
		
		detalhesQuestoesRespondidasController = loader.getController();
		detalhesQuestoesRespondidasController.setDialogHome(homeStage);
		detalhesQuestoesRespondidasController.setDialogStage(detalhesQuestoesRespondidasStage);
		detalhesQuestoesRespondidasController.setTentativa(tentativa);
		detalhesQuestoesRespondidasController.prenxerDados();
	}

	public void showNovoTesteDialog() {

		loader = getNovoLoader();
		novoTesteStage = getNovoStage((AnchorPane) getNewNodo(loader, "view/NovoTeste.fxml"),
				label.getString("homeTituloNovoTeste"));

		novoTesteController = loader.getController();
		novoTesteController.setIdioma(idioma);
		novoTesteController.setDialogStage(novoTesteStage);
		novoTesteController.setDialogHome(homeStage);
		novoTesteController.setMainApp(this);
		novoTesteController.inicializaJanela();
	}

	public void showQuiz(Conteudo conteudo) {

		loader = getNovoLoader();
		quizStage = getNovoStage((AnchorPane) getNewNodo(loader, "view/Quiz.fxml"),
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
			Calendar tempoRegistrado, Date dataRegistrada) {

		loader = getNovoLoader();
		resultadoStage = getNovoStage((AnchorPane) getNewNodo(loader, "view/Resultado.fxml"),
				label.getString("homeTituloResultados"));

		resultadoController = loader.getController();
		resultadoController.setDialogStage(resultadoStage);
		resultadoController.setListaRespostas(listaRespostas);
		resultadoController.setConteudo(conteudo);
		resultadoController.setCalculos(new Calculos(conteudo, listaRespostas, tempoRegistrado, dataRegistrada));
		resultadoController.setDialogHome(homeStage);
		resultadoController.setIdioma(idioma);
		resultadoController.setHomeController(homeController);
		resultadoController.setMainApp(this);
		resultadoController.calcularResultados();

	}

	public void atualizaListaTentativas() {
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
