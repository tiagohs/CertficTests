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
 * A Classe DetalhesQuestoesRespondidasController é o Controller da View
 * DetalhesQuestoesRespondidas, toda o controle dinâmico da View será feita por
 * Aqui. Ela exibe ao usuário detalhes de uma tentativa já realizada nos testes.
 * Exibe por exemplo nota, nome do teste, data, duração e detalhes de cada
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
	 * referência a Label na view.
	 */
	@FXML
	private Label testeEscolhido;

	/**
	 * Exibe a nota escolhida na tentativa. Variavel de referência a Label na
	 * view.
	 */
	@FXML
	private Label nota;

	/**
	 * Exibe o numero de acertos na tentativa. Variavel de referência a Label na
	 * view.
	 */
	@FXML
	private Label acertos;

	/**
	 * Exibe o tempo decorrido para se realizar o exame na tentativa escolhida.
	 * Variavel de referência a Label na view.
	 */
	@FXML
	private Label tempoDuracao;

	/**
	 * Exibe todas as questões Respondidas, a resposta correta e a resposta
	 * escolhida na tentativa. Variavel de referência a Label na view.
	 */
	@FXML
	private Label questoesRespondidas;

	/**
	 * Exibe a data e hora em que se realizou o teste. Variavel de referência a
	 * Label na view.
	 */
	@FXML
	private Label dataRegistrada;

	/** Botão ok da View. Variavel de referência a Button na view. */
	@FXML
	private Button botaoOk;

	/** Referência ao Stage da Home. */
	private Stage dialogHome;

	/** Referência a Stage atual. */
	private Stage detalhesQuestoesRespondidasStage;

	/** Referência a uma Tentativa, a que será exibida na tela. */
	private Tentativa tentativa;

	/**
	 * Método padrão no JavaFX, ele é usado para se chamar a classe e iniciá-la,
	 * quando se cria um novo controller pelo método getController(). O
	 * Construtor é chamado logo após.
	 */
	@FXML
	private void initialize() {

	}

	/**
	 * 
	 * Método para ser chamado quando se desejar preenxer os campos da Janela.
	 * No JavaFX não se inicializa os controller por construtores, então esse
	 * método substitui essa função. O Método primeiramente deixa referenciado
	 * que quando se fechar a janela pelo x no canto superior direito, se
	 * chamará o método handleOk(). Após isso, é setado todos os valores
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
		questoesRespondidas.setText("Questões Respondidas: \n\n" + tentativa.getListaRespostas());
		questoesRespondidas.setWrapText(true);
	}

	/**
	 * Método que trabalha a parte dinâmica do Botão OK. Quando se clicar nesse
	 * botão, voltaremos a janela Home e se fechará essa janela. Variavel de referência a
	 * Button na view.
	 */
	@FXML
	private void handleOk() {
		dialogHome.show();
		detalhesQuestoesRespondidasStage.close();
	}

	/**
	 * 
	 * Obtém Referência ao Stage da Home.
	 * 
	 * @return a Referência ao Stage da Home.
	 */
	public Stage getDialogHome() {
		return dialogHome;
	}

	/**
	 * 
	 * Se Define uma nova Referência ao Stage da Home.
	 * 
	 * @param dialogHome
	 *            a nova Referência ao Stage da Home.
	 */
	public void setDialogHome(Stage dialogHome) {
		this.dialogHome = dialogHome;
	}

	/**
	 * 
	 * Obtém a Referência a Stage atual.
	 * 
	 * @return a Referência a Stage atual.
	 */
	public Stage getDialogStage() {
		return detalhesQuestoesRespondidasStage;
	}

	/**
	 * 
	 * Se Define uma nova Referência a Stage atual.
	 * 
	 * @param dialogStage
	 *            a nova Referência a Stage atual.
	 */
	public void setDialogStage(Stage dialogStage) {
		this.detalhesQuestoesRespondidasStage = dialogStage;
	}

	/**
	 * 
	 * Obtém a Tentativa que será exibida na tela.
	 * 
	 * @return a Tentativa que será exibida na tela.
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
