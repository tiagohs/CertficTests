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
 * A Classe Principal da Aplica��o, ela � chamada pelo JavaFX e organiza as
 * Scenes da Aplica��o.
 * 
 * Classe <code>MainApp</code>
 * 
 * @author Tiago Henrique
 * @version 1.0
 *
 */
public class MainApp extends Application {
	/** O caminho no projeto para o <i>Icone</i> padr�o usado na Aplica��o. */
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
	 * A Refer�ncia a Classe <i>Controller</i> da Janela <i>DetalhesQuestoesRespondidas</i>.
	 */
	private DetalhesQuestoesRespondidasController detalhesQuestoesRespondidasController;

	/** A Refer�ncia a Classe <i>Controller</i> da Janela <i>RootLayout</i>. */
	private RootLayoutController rootLayoutController;

	/** A Refer�ncia a Classe <i>Controller</i> da Janela <i>Home</i>. */
	private HomeController homeController;

	/** A Refer�ncia a Classe <i>Controller</i> da Janela <i>NovoTeste</i>. */
	private NovoTesteController novoTesteController;

	/** A Refer�ncia a Classe <i>Controller</i> da Janela <i>Quiz</i>. */
	private QuizController quizController;

	/** A Refer�ncia a Classe <i>Controller</i> da Janela <i>Resultado</i>. */
	private ResultadoController resultadoController;

	/** A <i>Lista de Tentativas</i> Realizadas Anteriormente. */
	private ObservableList<Tentativa> listaTentativas;

	/** O <i>Idioma</i> Escolhido pelo Usu�rio. */
	private Idioma idioma;

	/** A Refer�ncia ao <i>i18n, com Base no <i>Idioma</i> Escolhido. */
	private JanelasSource label;

	/**
	 * Instancia uma Nova Classe <code>MainApp</code>
	 */
	public MainApp() {
	}

	/**
	 * Classe Padr�o das Aplica��es JavaFX. Ela � Chamada pela <i>Thread</i> do
	 * <i>JavaFX Application</i>, pelo m�todo <i>launched</i>.
	 * 
	 * @param homeStage
	 *            O <i>Stage Prim�rio</i>, da primeira Janela na Aplica��o.
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
	 * Uma <i>Janela Dialog</i> onde se � perguntado ao Cliente qual
	 * <i>Idioma</i> ele deseja que sua aplica��o tenha. � <i>Obrigat�rio</i>
	 * que se escolha um Idioma.
	 * 
	 * @return Retorna o <i>Idioma</i> escolhido, um Enumerado do Tipo
	 *         <i>Idioma</i>.
	 */
	private Idioma showEscolherIdioma() {
		ChoiceDialog<Idioma> dialog = new ChoiceDialog<Idioma>(Idioma.Ingles, Idioma.values());
		dialog.setTitle("Escolher Idioma");
		dialog.setHeaderText("Escolher o Idioma Padr�o da Aplica��o.");
		dialog.setContentText("Escolha o Idioma Desejado: ");

		// Verifica no ComboBox qual Op��o o Usu�rio Escolheu.
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
	 * Para se ler um <i>XML Layout</i>, � necess�rio um <i>Loader</i>. Se �
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
	 * Assim como em uma Pe�a de teatro, o <i>Stage</i> (Palco) � o container
	 * principal de uma <i>Window</i> (Janela). Dentro dela ter�o <i>Scenes</i>
	 * (Cenas) e dentro delas, os <i>Nodos</i> (N�s) de JavaFX. Esse m�todo cria
	 * um Stage com valores padr�es, conveni�nte a essa aplica��o. Adiciona um
	 * <i>Icone</i> Padr�o para Todas as Janelas.
	 * 
	 * @param page
	 *            O <i>Nodo</i> da Janela (<i>BorderPane, AnchorPane</i>) para
	 *            ser Adicionado a <i>Scene</i> da janela.
	 * @param tituloJanela
	 *            O <i>Titulo</i> da Janela em quest�o.
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
	 * Cria um Novo <i>Nodo</i> (N�s), que pode ser um BorderPane, AnchorPane,
	 * TextBox, etc..
	 * 
	 * @param loader
	 *            O <i>loader</i> usado para carregar o <i>XML Layout</i> da
	 *            janela em quest�o.
	 * @param caminhoFXML
	 *            O Caminho de onde se localiza o arquivo <i>.fxml</i>, contendo
	 *            o <i>XML Layout</i> da P�gina.
	 * 
	 * @return Retorna um novo <i>Nodo</i> (N�s), que ser� adicionado em uma
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
	 * Inicia a Janela Root, base da Aplica��o. Na Janela se encontra o
	 * <i>MenuBar</i>, e no Centro a Home.
	 * 
	 * @param _homeStage
	 *            O <i>Stage Prim�rio</i>, da primeira Janela na Aplica��o.
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
	 * Abre a janela <i>Home</i>. A Janela Home � a �nica com algumas peculiaridades,
	 * onde n�o se cria um novo <i>Stage</i>, e sim usado o Stage Primario, recebido
	 * pelo m�todo start do JavaFX. � Setado os Detalhes, adicionado no Centro
	 * do rootLayout a Janela Home, e iniciado o <i>Controller</i>, setando os valores
	 * de refer�ncia. Nesse momento se chama <i>atualizaListaTentativas</i>, onde com
	 * base no arquivo <i>tentativas.bin</i>, se recupera as tentativas j� feitas na
	 * aplica��o.
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
	 * quest�o por par�metro.
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
	 * Abre a Janela <i>NovoTeste</i>. Se voc� selecionar a Op��o '<i>new</i>' no menuBar da
	 * janela Home, ir� abrir esse <i>Dialog</i> pedindo os detalhes do Teste que voc�
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
	 * Abre a Janela <i>Quiz</i>. A Janela Quiz � onde o usu�rio ir� realizar o teste
	 * requerido, todos os detalhes do teste (<i>ArrayList contendo as quest�es,
	 * Tipo de Teste, Idioma, Nome do Exame..</i>) � encapsulado no Objeto conteudo,
	 * recebido por par�metro.
	 * 
	 * @param conteudo
	 *            Cont�m os Detalhes para se realizar o teste (Lista de
	 *            Quest�es, Tipo de Teste, Idioma, Nome do Exame..).
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
	 * Abre a Janela <i>Resultados</i>. � Aberta essa Janela logo ap�s se terminar o
	 * Teste. Nela � mostrado todos os detalhes de como o usu�rio se saiu no
	 * Teste.
	 * 
	 * @param listaRespostas
	 *            Lista com todas as respostas escolhidas pelo Usu�rio no Teste.
	 * @param conteudo
	 *            Cont�m os Detalhes para se realizar o teste (Lista de
	 *            Quest�es, Tipo de Teste, Idioma, Nome do Exame..).
	 * @param tempoRegistrado
	 *            Tempo que o usu�rio levou para se realizar o Teste.
	 * @param dataRegistrada
	 *            Data e Hora em que o usu�rio realizou o Teste.
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
	 * L� o Arquivo <i>tentativas.bat</i> e procura por novas <i>Tentativas</i> Realizadas.
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
			System.out.println("Arquivo ainda n�o Criado.");
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
	 * Obt�m a <i>Lista de Tentativas</i> Realizadas.
	 * 
	 * @return Retorna a Lista de Tentativas.
	 */
	public ObservableList<Tentativa> getListaTentativas() {
		return listaTentativas;
	}

	/**
	 * Obt�m o <i>Idioma</i> escolhido pelo Usu�rio.
	 * 
	 * @return Retorna um Idioma.
	 */
	public Idioma getIdioma() {
		return idioma;
	}

	/**
	 * 
	 * Se Define um Novo  <i>Idioma</i> Padr�o.
	 * 
	 * @param idioma
	 *            Novo Idioma Padr�o.
	 */
	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}
	
	/**
	 * M�todo  <i>Main</i> da Aplica��o.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
