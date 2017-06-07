/**
 * 
 * Copyright (c) 2016, CertificTests and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * 
 */
package br.com.oca.view;

import br.com.oca.model.Tentativa;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * 
 * A Classe DetalhesQuestoesRespondidasController � o Controller da View
 * DetalhesQuestoesRespondidas, toda o controle din�mico da View ser� feita por
 * Aqui. Ela exibe ao usu�rio detalhes de uma tentativa j� realizada nos testes.
 * Exibe por exemplo nota, nome do teste, data, dura��o e detalhes de cada
 * resposta escolhida.
 * 
 * Classe <code>DetalhesQuestoesRespondidasController</code>
 * 
 * @author Tiago Henrique
 * @version 1.0
 *
 */
public class DetalhesQuestoesRespondidasController {
	/**
	 * Exibe por extenso o nome do Teste escolhido na tentativa. Variavel de
	 * refer�ncia a Label na view.
	 */
	@FXML
	private Label testeEscolhido;

	/**
	 * Exibe a nota escolhida na tentativa. Variavel de refer�ncia a Label na
	 * view.
	 */
	@FXML
	private Label nota;

	/**
	 * Exibe o numero de acertos na tentativa. Variavel de refer�ncia a Label na
	 * view.
	 */
	@FXML
	private Label acertos;

	/**
	 * Exibe o tempo decorrido para se realizar o exame na tentativa escolhida.
	 * Variavel de refer�ncia a Label na view.
	 */
	@FXML
	private Label tempoDuracao;

	/**
	 * Exibe todas as quest�es Respondidas, a resposta correta e a resposta
	 * escolhida na tentativa. Variavel de refer�ncia a Label na view.
	 */
	@FXML
	private Label questoesRespondidas;

	/**
	 * Exibe a data e hora em que se realizou o teste. Variavel de refer�ncia a
	 * Label na view.
	 */
	@FXML
	private Label dataRegistrada;

	/** Bot�o ok da View. Variavel de refer�ncia a Button na view. */
	@FXML
	private Button botaoOk;

	/** Refer�ncia ao Stage da Home. */
	private Stage dialogHome;

	/** Refer�ncia a Stage atual. */
	private Stage detalhesQuestoesRespondidasStage;

	/** Refer�ncia a uma Tentativa, a que ser� exibida na tela. */
	private Tentativa tentativa;

	/**
	 * M�todo padr�o no JavaFX, ele � usado para se chamar a classe e inici�-la,
	 * quando se cria um novo controller pelo m�todo getController(). O
	 * Construtor � chamado logo ap�s.
	 */
	@FXML
	private void initialize() {

	}

	/**
	 * 
	 * M�todo para ser chamado quando se desejar preenxer os campos da Janela.
	 * No JavaFX n�o se inicializa os controller por construtores, ent�o esse
	 * m�todo substitui essa fun��o. O M�todo primeiramente deixa referenciado
	 * que quando se fechar a janela pelo x no canto superior direito, se
	 * chamar� o m�todo handleOk(). Ap�s isso, � setado todos os valores
	 * contidos no objeto tentativa, em seus respectivos Labels.
	 * 
	 */
	public void prenxerDados() {
		detalhesQuestoesRespondidasStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				handleOk();
			}
		});

		testeEscolhido.setText(tentativa.getTesteEscolhido());
		nota.setText(String.format("%.2f", tentativa.getNota()) + " de 100.0");
		acertos.setText(String.format("%.2f", tentativa.getNumeroAcertos()) + " de "
				+ tentativa.getTipoTeste().getTotalQuestoes());
		tempoDuracao.setText(tentativa.getTempoRegistrado());
		dataRegistrada.setText(tentativa.getDataRegistrada());
		questoesRespondidas.setText("Quest�es Respondidas: \n\n" + tentativa.getListaRespostas());
		questoesRespondidas.setWrapText(true);
	}

	/**
	 * M�todo que trabalha a parte din�mica do Bot�o OK. Quando se clicar nesse
	 * bot�o, voltaremos a janela Home e se fechar� essa janela. Variavel de refer�ncia a
	 * Button na view.
	 */
	@FXML
	private void handleOk() {
		dialogHome.show();
		detalhesQuestoesRespondidasStage.close();
	}

	/**
	 * 
	 * Obt�m Refer�ncia ao Stage da Home.
	 * 
	 * @return a Refer�ncia ao Stage da Home.
	 */
	public Stage getDialogHome() {
		return dialogHome;
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
	 * Obt�m a Refer�ncia a Stage atual.
	 * 
	 * @return a Refer�ncia a Stage atual.
	 */
	public Stage getDialogStage() {
		return detalhesQuestoesRespondidasStage;
	}

	/**
	 * 
	 * Se Define uma nova Refer�ncia a Stage atual.
	 * 
	 * @param dialogStage
	 *            a nova Refer�ncia a Stage atual.
	 */
	public void setDialogStage(Stage dialogStage) {
		this.detalhesQuestoesRespondidasStage = dialogStage;
	}

	/**
	 * 
	 * Obt�m a Tentativa que ser� exibida na tela.
	 * 
	 * @return a Tentativa que ser� exibida na tela.
	 */
	public Tentativa getTentativa() {
		return tentativa;
	}

	/**
	 * 
	 * Se Define uma nova Tentativa.
	 * 
	 * @param tentativa
	 *            a nova Tentativa.
	 */
	public void setTentativa(Tentativa tentativa) {
		this.tentativa = tentativa;
	}

}
