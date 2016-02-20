/**
 * 
 * Copyright (c) 2016, CertificTests and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * 
 */

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
 * A Classe Principal da Aplicação, ela é chamada pelo JavaFX e organiza as
 * Scenes da Aplicação.
 * 
 * Classe <code>MainApp</code>
 * 
 * @author Tiago Henrique
 * @version 1.0
 *
 */
public class MainApp extends Application {
	/** O caminho no projeto para o <i>Icone</i> padrão usado na Aplicação. */
	public static final String CAMINHO_ICONE_APLICACAO = "file:resources/images/CertificTests.png";

	/** O <i>rootLayout</i> que representa o Nodo do XML Root Layout. */
	private BorderPane rootLayout;

	/** O <i>loader</i> usado para carregar um arquivo FXML. */
	private FXMLLoader loader;

	/** O <i>Stage</i> que referencia a Janela DetalhesQuestoesRespondidas. */
	private Stage detalhesQuestoesRespondidasStage;

	/** O <i>Stage</i> que referencia a Janela Home. */
	private Stage homeStage;

	/** O <i>Stage</i> que referencia a Janela NovoTeste. */
	private Stage novoTesteStage;

	/** O <i>Stage</i> que referencia a Janela Quiz. */
	private Stage quizStage;

	/** O <i>Stage</i> que referencia a Janela Resultados. */
	private Stage resultadoStage;

	/**
	 * A Referência a Classe <i>Controller</i> da Janela <i>DetalhesQuestoesRespondidas</i>.
	 */
	private DetalhesQuestoesRespondidasController detalhesQuestoesRespondidasController;

	/** A Referência a Classe <i>Controller</i> da Janela <i>RootLayout</i>. */
	private RootLayoutController rootLayoutController;

	/** A Referência a Classe <i>Controller</i> da Janela <i>Home</i>. */
	private HomeController homeController;

	/** A Referência a Classe <i>Controller</i> da Janela <i>NovoTeste</i>. */
	private NovoTesteController novoTesteController;

	/** A Referência a Classe <i>Controller</i> da Janela <i>Quiz</i>. */
	private QuizController quizController;

	/** A Referência a Classe <i>Controller</i> da Janela <i>Resultado</i>. */
	private ResultadoController resultadoController;

	/** A <i>Lista de Tentativas</i> Realizadas Anteriormente. */
	private ObservableList<Tentativa> listaTentativas;

	/** O <i>Idioma</i> Escolhido pelo Usuário. */
	private Idioma idioma;

	/** A Referência ao <i>i18n, com Base no <i>Idioma</i> Escolhido. */
	private JanelasSource label;

	/**
	 * Instancia uma Nova Classe <code>MainApp</code>
	 */
	public MainApp() {
	}

	/**
	 * Classe Padrão das Aplicações JavaFX. Ela é Chamada pela <i>Thread</i> do
	 * <i>JavaFX Application</i>, pelo método <i>launched</i>.
	 * 
	 * @param homeStage
	 *            O <i>Stage Primário</i>, da primeira Janela na Aplicação.
	 * 
	 */
	@Override
	public void start(Stage _homeStage) {
		idioma = showEscolherIdioma();
		label = JanelasSource.getInstance(idioma);
		homeStage = _homeStage;

		initRootLayout();
		showHome();
	}

	/**
	 * Uma <i>Janela Dialog</i> onde se é perguntado ao Cliente qual
	 * <i>Idioma</i> ele deseja que sua aplicação tenha. É <i>Obrigatório</i>
	 * que se escolha um Idioma.
	 * 
	 * @return Retorna o <i>Idioma</i> escolhido, um Enumerado do Tipo
	 *         <i>Idioma</i>.
	 */
	private Idioma showEscolherIdioma() {
		ChoiceDialog<Idioma> dialog = new ChoiceDialog<Idioma>(Idioma.Ingles, Idioma.values());
		dialog.setTitle("Escolher Idioma");
		dialog.setHeaderText("Escolher o Idioma Padrão da Aplicação.");
		dialog.setContentText("Escolha o Idioma Desejado: ");

		// Verifica no ComboBox qual Opção o Usuário Escolheu.
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
	 * Para se ler um <i>XML Layout</i>, é necessário um <i>Loader</i>. Se é
	 * passado nesse momento o <i>Idioma</i> escolhido, para o <i>i18n</i> ser
	 * configurado.
	 * 
	 * @return Um Loader do Tipo <i>FXMLoader</i>
	 */
	private FXMLLoader getNovoLoader() {

		FXMLLoader carregarFXML = new FXMLLoader();
		carregarFXML.setResources(label.getBundle());

		return carregarFXML;
	}

	/**
	 * Assim como em uma Peça de teatro, o <i>Stage</i> (Palco) é o container
	 * principal de uma <i>Window</i> (Janela). Dentro dela terão <i>Scenes</i>
	 * (Cenas) e dentro delas, os <i>Nodos</i> (Nós) de JavaFX. Esse método cria
	 * um Stage com valores padrões, conveniênte a essa aplicação. Adiciona um
	 * <i>Icone</i> Padrão para Todas as Janelas.
	 * 
	 * @param page
	 *            O <i>Nodo</i> da Janela (<i>BorderPane, AnchorPane</i>) para
	 *            ser Adicionado a <i>Scene</i> da janela.
	 * @param tituloJanela
	 *            O <i>Titulo</i> da Janela em questão.
	 * 
	 * @return Retorna um <i>Stage</i> com uma <i>Window</i> configurada.
	 */
	private Stage getNovoStage(Parent page, String tituloJanela) {

		Stage tempStage = new Stage();
		tempStage.initModality(Modality.WINDOW_MODAL);
		tempStage.setResizable(false);
		tempStage.setTitle(tituloJanela);
		tempStage.getIcons().add(new Image(CAMINHO_ICONE_APLICACAO));
		tempStage.setScene(new Scene(page));

		tempStage.show();

		return tempStage;
	}

	/**
	 * Cria um Novo <i>Nodo</i> (Nós), que pode ser um BorderPane, AnchorPane,
	 * TextBox, etc..
	 * 
	 * @param loader
	 *            O <i>loader</i> usado para carregar o <i>XML Layout</i> da
	 *            janela em questão.
	 * @param caminhoFXML
	 *            O Caminho de onde se localiza o arquivo <i>.fxml</i>, contendo
	 *            o <i>XML Layout</i> da Página.
	 * 
	 * @return Retorna um novo <i>Nodo</i> (Nós), que será adicionado em uma
	 *         Cena.
	 */
	private Parent getNewNodo(FXMLLoader loader, String caminhoFXML) {
		try {
			return loader.load(this.getClass().getResource(caminhoFXML).openStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Inicia a Janela Root, base da Aplicação. Na Janela se encontra o
	 * <i>MenuBar</i>, e no Centro a Home.
	 * 
	 * @param _homeStage
	 *            O <i>Stage Primário</i>, da primeira Janela na Aplicação.
	 */
	public void initRootLayout() {

		loader = getNovoLoader();
		rootLayout = (BorderPane) getNewNodo(loader, "view/RootLayout.fxml");

		rootLayoutController = loader.getController();
		rootLayoutController.setMainApp(this);
		rootLayoutController.setDialogHome(homeStage);
		rootLayoutController.init();
	}

	/**
	 * 
	 * Abre a janela <i>Home</i>. A Janela Home é a única com algumas peculiaridades,
	 * onde não se cria um novo <i>Stage</i>, e sim usado o Stage Primario, recebido
	 * pelo método start do JavaFX. É Setado os Detalhes, adicionado no Centro
	 * do rootLayout a Janela Home, e iniciado o <i>Controller</i>, setando os valores
	 * de referência. Nesse momento se chama <i>atualizaListaTentativas</i>, onde com
	 * base no arquivo <i>tentativas.bin</i>, se recupera as tentativas já feitas na
	 * aplicação.
	 * 
	 */
	public void showHome() {

		loader = getNovoLoader();
		rootLayout.setCenter(getNewNodo(loader, "view/Home.fxml"));

		homeStage.setTitle("CertificTests");
		homeStage.setResizable(false);
		homeStage.setScene(new Scene(rootLayout));
		homeStage.getIcons().add(new Image(CAMINHO_ICONE_APLICACAO));
		homeStage.show();

		getTentativasRegistradas();

		homeController = loader.getController();
		homeController.setHomeStage(homeStage);
		homeController.setIdioma(idioma);
		homeController.setMainApp(this);
		homeController.setListaTentativas(listaTentativas);
		homeController.init();
	}

	/**
	 * 
	 * Abre a Janela <i>DetalhesQuestoesRespondidas</i>. Nessa Janela se mostra os
	 * <i>Detalhes da Tentativa</i> selecionada na tabela. Se recebe a <i>Tentativa</i> em
	 * questão por parâmetro.
	 * 
	 * @param tentativa
	 *            A Tentativa selecionada na Tabela.
	 */
	public void showDetalhesQuestoesRespondidas(Tentativa tentativa) {

		loader = getNovoLoader();
		detalhesQuestoesRespondidasStage = getNovoStage(
				(AnchorPane) getNewNodo(loader, "view/DetalhesQuestoesRespondidas.fxml"),
				label.getString("detalhesQuestoesTitulo"));

		detalhesQuestoesRespondidasController = loader.getController();
		detalhesQuestoesRespondidasController.setDialogHome(homeStage);
		detalhesQuestoesRespondidasController.setDialogStage(detalhesQuestoesRespondidasStage);
		detalhesQuestoesRespondidasController.setTentativa(tentativa);
		detalhesQuestoesRespondidasController.prenxerDados();
	}

	/**
	 * 
	 * Abre a Janela <i>NovoTeste</i>. Se você selecionar a Opção '<i>new</i>' no menuBar da
	 * janela Home, irá abrir esse <i>Dialog</i> pedindo os detalhes do Teste que você
	 * deseja realizar.
	 * 
	 */
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

	/**
	 * 
	 * Abre a Janela <i>Quiz</i>. A Janela Quiz é onde o usuário irá realizar o teste
	 * requerido, todos os detalhes do teste (<i>ArrayList contendo as questões,
	 * Tipo de Teste, Idioma, Nome do Exame..</i>) é encapsulado no Objeto conteudo,
	 * recebido por parâmetro.
	 * 
	 * @param conteudo
	 *            Contém os Detalhes para se realizar o teste (Lista de
	 *            Questões, Tipo de Teste, Idioma, Nome do Exame..).
	 */
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

	/**
	 * 
	 * Abre a Janela <i>Resultados</i>. É Aberta essa Janela logo após se terminar o
	 * Teste. Nela é mostrado todos os detalhes de como o usuário se saiu no
	 * Teste.
	 * 
	 * @param listaRespostas
	 *            Lista com todas as respostas escolhidas pelo Usuário no Teste.
	 * @param conteudo
	 *            Contém os Detalhes para se realizar o teste (Lista de
	 *            Questões, Tipo de Teste, Idioma, Nome do Exame..).
	 * @param tempoRegistrado
	 *            Tempo que o usuário levou para se realizar o Teste.
	 * @param dataRegistrada
	 *            Data e Hora em que o usuário realizou o Teste.
	 */
	public void showResultados(ArrayList<Resposta> listaRespostas, Conteudo conteudo, Calendar tempoRegistrado,
			Date dataRegistrada) {

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

	/**
	 * 
	 * Lê o Arquivo <i>tentativas.bat</i> e procura por novas <i>Tentativas</i> Realizadas.
	 * 
	 */
	public void getTentativasRegistradas() {
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

	/**
	 * Obtem o <i>Stage</i> da Janela <i>Home</i>.
	 * 
	 * @return Retorna o Stage.
	 */
	public Stage getHomeStage() {
		return homeStage;
	}

	/**
	 * Obtém a <i>Lista de Tentativas</i> Realizadas.
	 * 
	 * @return Retorna a Lista de Tentativas.
	 */
	public ObservableList<Tentativa> getListaTentativas() {
		return listaTentativas;
	}

	/**
	 * Obtém o <i>Idioma</i> escolhido pelo Usuário.
	 * 
	 * @return Retorna um Idioma.
	 */
	public Idioma getIdioma() {
		return idioma;
	}

	/**
	 * 
	 * Se Define um Novo  <i>Idioma</i> Padrão.
	 * 
	 * @param idioma
	 *            Novo Idioma Padrão.
	 */
	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}
	
	/**
	 * Método  <i>Main</i> da Aplicação.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
