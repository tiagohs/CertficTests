/**
 * 
 * Copyright (c) 2016, CertificTests and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * 
 */
package br.com.oca.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Map.Entry;

import br.com.oca.MainApp;
import br.com.oca.config.JanelasConfig;
import br.com.oca.model.Calculos;
import br.com.oca.model.Questao;
import br.com.oca.model.Resposta;
import br.com.oca.model.Tentativa;
import br.com.oca.model.conteudo.Conteudo;
import br.com.oca.model.enums.Idioma;
import br.com.oca.util.AppendingObjectOutputStream;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * A Classe ResultadoController � o Controller da View Quiz, onde ir� mostrar
 * todos os Detalhes do Teste Realizado pelo Usu�rio. Ela � chamada logo ap�s a
 * finaliza��o do Teste.
 * 
 * Classe <code>ResultadoController</code>
 * 
 * @author Tiago Henrique
 * @version 1.0
 *
 */
public class ResultadoController {
	/**
	 * A Nota do Usu�rio na Tentativa Atual. Variavel de refer�ncia a Label na
	 * view.
	 */
	@FXML
	private Label labelSuaNota;
	/**
	 * O Numero de Quest�es Corretas do Usu�rio na Tentativa Atual. Variavel de
	 * refer�ncia a Label na view.
	 */
	@FXML
	private Label labelQuestoesCorretas;
	/**
	 * O Tempo Decorido na Tentativa Atual. Variavel de refer�ncia a Label na
	 * view.
	 */
	@FXML
	private Label labelTempoDecorrido;
	/**
	 * A Data e Hora Que se iniciou a Tentativa Atual. Variavel de refer�ncia a
	 * Label na view.
	 */
	@FXML
	private Label labelDataRegistrada;
	/**
	 * Os Detalhes do Teste, contendo as respostas corretas e as que o usu�rio
	 * escolheu. Variavel de refer�ncia a Label na view.
	 */
	@FXML
	private Label resultados;

	/** Refer�ncia ao Stage da Home. */
	private Stage dialogHome;

	/** Refer�ncia ao Stage atual. */
	private Stage resultadoStage;

	/** Refer�ncia ao Controller da Home. */
	private HomeController homeController;

	/** Refer�ncia a mainApp, classe controladora da Aplica��o. */
	private MainApp mainApp;

	/** A Lista que contem todas as respostas do Usu�rio para cada Quest�o. */
	private ArrayList<Resposta> listaRespostas;

	/** Config referenciando o properties de i18n das Janelas. */
	private JanelasConfig label;

	/** O Idioma que a aplica��o ter�. */
	private Idioma idioma;

	/**
	 * Refer�ncia ao Conteudo, onde cont�m as quest�es preenxidas, al�m de
	 * outros detalhes.
	 */
	private Conteudo conteudo;

	/**
	 * Os Detalhes do Teste, contendo as respostas corretas e as que o usu�rio
	 * escolheu.
	 */
	private StringBuilder stringResultados;

	/**
	 * Referencia a Classe calculos, onde ser� realizado todos os calculos do
	 * teste.
	 */
	private Calculos calculos;

	/**
	 * Se Instancia uma nova ResultadoController, chamado logo ap�s o
	 * initialize() do JavaFX.
	 */
	public ResultadoController() {
		label = JanelasConfig.getInstance(idioma);
	}

	/**
	 * M�todo padr�o no JavaFX, ele � usado para se chamar a classe e inici�-la,
	 * quando se cria um novo controller pelo m�todo getController(). O
	 * Construtor � chamado logo ap�s.
	 */
	@FXML
	private void initialize() {

	}

	/**
	 * M�todo para ser chamado quando se desejar preenxer os campos da Janela.
	 * No JavaFX n�o se inicializa os controller por construtores, ent�o esse
	 * m�todo substitui essa fun��o.
	 */
	public void calcularResultados() {
		stringResultados = new StringBuilder();
		showResultados();
		registrarTentativa();
	}

	/**
	 * M�todo auxiliar de calcularResultados(). O M�todo primeiramente deixa
	 * referenciado que quando se fechar a janela pelo x no canto superior
	 * direito, se chamar� o m�todo handleSair(). Ap�s isso, � setado todos os
	 * valores contidos no objeto tentativa, em seus respectivos Labels.
	 */
	private void showResultados() {
		resultadoStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				handleOk();
			}
		});

		labelSuaNota
				.setText(String.format("%.2f", calculos.getNota()) + label.getString("resultadosLabelDe") + "100.00");
		labelQuestoesCorretas.setText(String.format("%.2f", calculos.getNumeroQuestoesCorretas())
				+ label.getString("resultadosLabelDe") + conteudo.getTotalQuestoes());
		labelTempoDecorrido.setText(calculos.getTempoDecorridoFormatado());
		labelDataRegistrada.setText(calculos.getDataRegistradaFormatado());

		if (listaRespostas.size() > 0) {
			for (int cont = 0; cont < conteudo.getTotalQuestoes().intValue(); cont++)
				setTextoRespostas(cont);
		} else {
			stringResultados.append(label.getString("resultadosLabelNenhumaQuestao"));
		}

		resultados.setText(stringResultados.toString());
		resultados.setWrapText(true);
	}

	/**
	 * 
	 * M�todo auxiliar de calcularResultados(). Ele seta todas as respostas
	 * corretas e o que o usu�rio marcou como correto nas quest�es.
	 * 
	 * @param numeroQuestao
	 *            O Numero da Quest�o atual.
	 */
	private void setTextoRespostas(int numeroQuestao) {
		Questao questao = conteudo.getQuestao(numeroQuestao);
		Resposta resposta = listaRespostas.get(numeroQuestao);

		stringResultados.append(label.getString("quizLabelNumQuestao1") + " " + numeroQuestao + ":\n\n");

		if (questao.isVariasRespostas())
			exibirRespostas(questao, resposta, label.getString("resultadosLabelRespostasCorretas") + "\n\n",
					"\n" + label.getString("resultadosLabelSuasRespostas") + "\n\n");
		else
			exibirRespostas(questao, resposta, label.getString("resultadosLabelRespostaCorreta"),
					label.getString("resultadosLabelSuaResposta") + " ");

		numeroQuestao++;
	}

	/**
	 * 
	 * M�todo Auxiliar de setTextoRespostas(), onde se exibe as op��es corretas
	 * e as respostas do usu�rio, em cada Quest�o.
	 * 
	 * @param questao
	 *            A Quest�o Atual.
	 * @param resposta
	 *            A Resposta do Usu�rio Atual.
	 * @param auxRespostaCorreta
	 *            O Texto Auxiliar de Resposta ("Resposta Correta: ")
	 * @param auxSuasRespostas
	 *            O Texto Auxiliar de Sua Resposta ("Sua Respostas: ")
	 */
	private void exibirRespostas(Questao questao, Resposta resposta, String auxRespostaCorreta,
			String auxSuasRespostas) {

		stringResultados.append(auxRespostaCorreta);
		for (Entry<Character, String> entry : questao.getAlternativasCorretas().entrySet()) {
			stringResultados.append(entry.getValue() + "\n");
		}

		stringResultados.append(auxSuasRespostas);
		for (Entry<Character, String> entry : resposta.getListaRespostas().entrySet()) {
			stringResultados.append(entry.getValue() + "\n");
		}

		stringResultados.append("\n");
	}

	/**
	 * Registra uma tentativa em um arquivo bat. Ap�s Verificar e exibir os
	 * dados, Registra a Tentativa em um Arquivo bat.
	 */
	private void registrarTentativa() {

		File file = new File(Tentativa.filename);
		ObjectOutputStream out = null;

		try {
			if (!file.exists())
				out = new ObjectOutputStream(new FileOutputStream(Tentativa.filename));
			else
				out = new AppendingObjectOutputStream(new FileOutputStream(Tentativa.filename, true));
			out.writeObject(new Tentativa(conteudo.getExame(), conteudo.getTipoTeste(), calculos.getNota(),
					calculos.getNumeroQuestoesCorretas(), calculos.getTempoDecorridoFormatado(),
					stringResultados.toString(), calculos.getDataRegistradaFormatado()));
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * M�todo que trabalha a parte din�mica do Bot�o OK. Quando se clicar nesse
	 * bot�o, voltaremos a janela Home, com a lista de tentativas na tabela
	 * atualizada, e se fechar� essa janela. Variavel de refer�ncia a Button na
	 * view.
	 */
	@FXML
	private void handleOk() {
		mainApp.getTentativasRegistradas();
		homeController.setListaTentativas(mainApp.getListaTentativas());
		homeController.inicializaTabela();
		dialogHome.show();
		resultadoStage.close();
	}

	/**
	 * 
	 * Se Define uma nova Lista que contem todas as respostas do Usu�rio para
	 * cada Quest�o.
	 * 
	 * @param listaRespostas
	 *            a nova Lista que contem todas as respostas do Usu�rio para
	 *            cada Quest�o.
	 */
	public void setListaRespostas(ArrayList<Resposta> listaRespostas) {
		this.listaRespostas = listaRespostas;
	}

	/**
	 * 
	 * Obt�m a Lista que contem todas as respostas do Usu�rio para cada Quest�o.
	 * 
	 * @return a Lista que contem todas as respostas do Usu�rio para cada
	 *         Quest�o.
	 */
	public ArrayList<Resposta> getListaRespostas() {
		return listaRespostas;
	}

	/**
	 * 
	 * Obt�m a Refer�ncia a Stage atual.
	 * 
	 * @return a Refer�ncia a Stage atual.
	 */
	public void setDialogStage(Stage resultadoStage) {
		this.resultadoStage = resultadoStage;
	}

	/**
	 * 
	 * Se Define uma nova Refer�ncia ao Stage da Home.
	 * 
	 * @param dialogHome
	 *            a nova Refer�ncia ao Stage da Home.
	 */
	public void setDialogHome(Stage dialogHome) {
		this.dialogHome = dialogHome;
	}

	/**
	 * 
	 * Se Define uma nova Refer�ncia ao Conteudo, onde cont�m as quest�es
	 * preenxidas, al�m de outros detalhes.
	 * 
	 * @param conteudo
	 *            A nova Refer�ncia ao Conteudo, onde cont�m as quest�es
	 *            preenxidas, al�m de outros detalhes.
	 */
	public void setConteudo(Conteudo conteudo) {
		this.conteudo = conteudo;
	}

	/**
	 * 
	 * Define uma nova Referencia a Classe calculos, onde ser� realizado todos
	 * os calculos do teste.
	 * 
	 * @param calculos
	 *            A nova Referencia a Classe calculos, onde ser� realizado todos
	 *            os calculos do teste.
	 */
	public void setCalculos(Calculos calculos) {
		this.calculos = calculos;
	}
	
	/**
	 * 
	 * Se Define um novo Idioma para a aplica��o.
	 * 
	 * @param idioma
	 *            O novo Idioma para a aplica��o.
	 */
	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}
	
	/**
	 * 
	 * Se Define uma nova Refer�ncia a MainApp, classe controladora da
	 * Aplica��o.
	 * 
	 * @param mainApp
	 *            A nova MainApp, classe controladora da Aplica��o.
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	/**
	 * 
	 * Se Define uma nova Refer�ncia ao Controller da Home.
	 * 
	 * @param homeController A nova Refer�ncia ao Controller da Home.
	 */
	public void setHomeController(HomeController homeController) {
		this.homeController = homeController;
	}
}
