/**
 * 
 * Copyright (c) 2016, CertificTests and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * 
 */
package br.com.oca.view;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import br.com.oca.MainApp;
import br.com.oca.config.JanelasConfig;
import br.com.oca.config.PerguntasConfig;
import br.com.oca.model.Questao;
import br.com.oca.model.Resposta;
import br.com.oca.model.conteudo.Conteudo;
import br.com.oca.model.enums.Idioma;
import br.com.oca.util.AlertDialogsFactory;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * 
 * A Classe QuizController � o Controller da View Quiz, onde iremos trabalhar
 * toda a parte din�mica da Janela Quiz. Todas as regras, detalhes das quest�es,
 * tempo, � configurado por aqui.
 * 
 * Classe <code>QuizController</code>
 * 
 * @author Tiago Henrique
 * @version 1.0
 *
 */
public class QuizController {
	/** V�riavel global representando a velocidade do Corn�metro. */
	public static final int VELOCIDADE_CRONOMETRO = 1000;

	/**
	 * V�riavel global representando um minuto em Segundos (Um Minuto tem 60
	 * Segundos).
	 */
	public static final int UM_MINUTO = 60;

	/**
	 * V�riavel global representando uma Hora em minutos (Uma Hora tem 60
	 * Minutos).
	 */
	public static final int UMA_HORA = 60;

	/** O Numero da Quest�o Atual. Variavel de refer�ncia a Label na view. */
	@FXML
	private Label labelNumeroQuestao;

	/**
	 * O Nome do Exame (Exame + Tipo de Teste). Variavel de refer�ncia a Label
	 * na view.
	 */
	@FXML
	private Label labelNomeExame;

	/** O Tempo Decorrido no Teste. Variavel de refer�ncia a Label na view. */
	@FXML
	private Label labelTempoDecorrido;

	/**
	 * Refer�ncia da Quest�o, dados de onde foi tirado aquela quest�o (Autor do
	 * Livro + Nome do Livro + P�gina + Numero da Quest�o). Variavel de
	 * refer�ncia a Label na view.
	 */
	@FXML
	private Label labelReferencia;

	/**
	 * Mostra o Numero De alternativas que estaram corretas na quest�o. Variavel
	 * de refer�ncia a Label na view.
	 */
	@FXML
	private Label labelNumOpcoesCorretas;

	/** O Enunciado da Quest�o Atual. Variavel de refer�ncia a Label na view. */
	@FXML
	private Label labelEnunciadoQuestao;

	/**
	 * Painel contendo as RadioButton. Variavel de refer�ncia a Pane na view.
	 */
	@FXML
	private Pane radioPainel;

	/**
	 * Alternativa A como RadioButton da Quest�o. Variavel de refer�ncia a
	 * RadioButton na view.
	 */
	@FXML
	private RadioButton radioAlternativaA;

	/**
	 * Alternativa B como RadioButton da Quest�o. Variavel de refer�ncia a
	 * RadioButton na view.
	 */
	@FXML
	private RadioButton radioAlternativaB;

	/**
	 * Alternativa C como RadioButton da Quest�o. Variavel de refer�ncia a
	 * RadioButton na view.
	 */
	@FXML
	private RadioButton radioAlternativaC;

	/**
	 * Alternativa D como RadioButton da Quest�o. Variavel de refer�ncia a
	 * RadioButton na view.
	 */
	@FXML
	private RadioButton radioAlternativaD;

	/**
	 * Alternativa E como RadioButton da Quest�o. Variavel de refer�ncia a
	 * RadioButton na view.
	 */
	@FXML
	private RadioButton radioAlternativaE;

	/** Painel contendo os ChackBox. Variavel de refer�ncia a Pane na view. */
	@FXML
	private Pane checkPainel;

	/**
	 * Alternativa A como CheckBox da Quest�o. Variavel de refer�ncia a CheckBox
	 * na view.
	 */
	@FXML
	private CheckBox checkAlternativaA;

	/**
	 * Alternativa B como CheckBox da Quest�o. Variavel de refer�ncia a CheckBox
	 * na view.
	 */
	@FXML
	private CheckBox checkAlternativaB;

	/**
	 * Alternativa C como CheckBox da Quest�o. Variavel de refer�ncia a CheckBox
	 * na view.
	 */
	@FXML
	private CheckBox checkAlternativaC;

	/**
	 * Alternativa D como CheckBox da Quest�o. Variavel de refer�ncia a CheckBox
	 * na view.
	 */
	@FXML
	private CheckBox checkAlternativaD;

	/**
	 * Alternativa E como CheckBox da Quest�o. Variavel de refer�ncia a CheckBox
	 * na view.
	 */
	@FXML
	private CheckBox checkAlternativaE;

	/**
	 * Bot�o para a Quest�o Anterior. Variavel de refer�ncia a Button na view.
	 */
	@FXML
	private Button anteriorQuestao;

	/**
	 * Bot�o para a Pr�xima Quest�o. Variavel de refer�ncia a Button na view.
	 */
	@FXML
	private Button proximaQuestao;

	/**
	 * Bot�o para a Ajuda com a Quest�o Atual. Variavel de refer�ncia a Button
	 * na view.
	 */
	@FXML
	private Button ajuda;

	/** Bot�o para sair do Teste. Variavel de refer�ncia a Button na view. */
	@FXML
	private Button sair;

	/** Refer�ncia ao Stage atual. */
	private Stage quizStage;

	/** Refer�ncia ao Stage da Home. */
	private Stage dialogHome;

	/** Contador para Referenciar uma Quest�o. */
	private Integer contQuestao;

	/** Numero de Cada Quest�o, auxiliar ao labelNumeroQuestao. */
	private Integer numeroAtualQuestao;

	/** O Idioma que a aplica��o ter�.. */
	private Idioma idioma;

	/** A Lista que conter� todas as respostas do Usu�rio para cada Quest�o. */
	private ArrayList<Resposta> listaRespostas;

	/**
	 * Refer�ncia ao Conteudo, onde cont�m as quest�es preenxidas, al�m de
	 * outros detalhes.
	 */
	private Conteudo conteudo;

	/** Config referenciando o properties de i18n das Janelas. */
	private JanelasConfig janelaLabels;

	/** Config referenciando o properties de i18n das Perguntas. */
	private PerguntasConfig perguntasLabel;

	/** Referencia cada Segundos passado no Cronometro. */
	private int currentSegundos = 0;

	/** Referencia cada Minuto passado no Cronometro. */
	private int currentMinutos = 0;

	/** Referencia cada Hora passados no Cronometro. */
	private int currentHora = 0;

	/** Objeto que ir� guardar o tempo de dura��o registrado. */
	private Calendar tempoRegistrado;

	/** Objeto que ir� guardar a data e hora Registrada. */
	private Date dataRegistrada;

	/** Refer�ncia a mainApp, classe controladora da Aplica��o. */
	private MainApp mainApp;

	/**
	 * Se Instancia uma nova QuizController, chamado logo ap�s o initialize() do
	 * JavaFX.
	 */
	public QuizController() {
		listaRespostas = new ArrayList<Resposta>();
		tempoRegistrado = Calendar.getInstance();
	}

	/**
	 * M�todo padr�o no JavaFX, ele � usado para se chamar a classe e inici�-la,
	 * quando se cria um novo controller pelo m�todo getController(). O
	 * Construtor � chamado logo ap�s.
	 */
	@FXML
	private void initialize() {
		contQuestao = 0;
		numeroAtualQuestao = 1;
		dataRegistrada = new Date();
		janelaLabels = JanelasConfig.getInstance(idioma);
	}

	/**
	 * M�todo para ser chamado quando se desejar preenxer os campos da Janela.
	 * No JavaFX n�o se inicializa os controller por construtores, ent�o esse
	 * m�todo substitui essa fun��o. O M�todo primeiramente deixa referenciado
	 * que quando se fechar a janela pelo x no canto superior direito, se
	 * chamar� o m�todo handleSair(). Ap�s isso, � setado todos os valores
	 * contidos no objeto tentativa, em seus respectivos Labels. E por fim,
	 * inicia o Timer.
	 */
	public void iniciarQuiz() {
		perguntasLabel = PerguntasConfig.getInstance(idioma, conteudo.getExame());

		quizStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				handleSair();
			}
		});

		labelNomeExame.setText(conteudo.getExame().getNomeExtenso());
		labelNumeroQuestao.setText(janelaLabels.getString("quizLabelNumQuestao1") + numeroAtualQuestao
				+ janelaLabels.getString("quizLabelNumQuestao2") + conteudo.getTotalQuestoes());
		verificaQuestao(conteudo.getQuestao(contQuestao));
		setQuestao(conteudo.getQuestao(contQuestao));
		iniciaTimer();
	}

	/**
	 * Realiza o Cron�metro para o Teste. Com um Objeto do Tipo TImer, e
	 * utilizando o schedule(), se realiza o timer, que ser� finalizado somente
	 * quando se terminar o teste completetamente. Se o tempo ultrapassar o
	 * tempo de dura��o m�ximo, vari�vel para cada tipo de teste, um alert �
	 * lan�ado e se finaliza o teste.
	 */
	private void iniciaTimer() {
		Timer timer = new Timer();
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

		timer.schedule(new TimerTask() {
			public void run() {
				Platform.runLater(new Runnable() {
					public void run() {
						currentSegundos++;

						if (currentSegundos == UM_MINUTO) {
							currentMinutos++;
							currentSegundos = 0;
						}

						if (currentMinutos == UMA_HORA) {
							currentHora++;
							currentMinutos = 0;
						}

						tempoRegistrado.set(Calendar.HOUR_OF_DAY, currentHora);
						tempoRegistrado.set(Calendar.MINUTE, currentMinutos);
						tempoRegistrado.set(Calendar.SECOND, currentSegundos);

						labelTempoDecorrido.setText(janelaLabels.getString("quizLabelTempoDecorrido")
								+ dateFormat.format(tempoRegistrado.getTime()));

						if (currentHora == conteudo.getTipoTeste().getTempoMaximoDuracao()) {
							timer.cancel();
							AlertDialogsFactory.getAlertDialog(AlertType.INFORMATION,
									janelaLabels.getString("quizAlertTitulo"),
									janelaLabels.getString("quizAlertCabecalho"),
									janelaLabels.getString("quizAlertConteudo"));
							finalizarQuiz();
						}
					}
				});
			}
		}, 0, VELOCIDADE_CRONOMETRO);
	}

	/**
	 * M�todo utilizado para se setar o numero da pr�xima quest�o no
	 * labelNumeroQuestao, que fica na View, acima no lado esquerdo.
	 */
	private void setNumeroProximaQuestao() {
		labelNumeroQuestao.setText(janelaLabels.getString("quizLabelNumQuestao1") + ++numeroAtualQuestao
				+ janelaLabels.getString("quizLabelNumQuestao2") + conteudo.getTotalQuestoes());
	}

	/**
	 * M�todo utilizado para se setar o numero da quest�o anterior no
	 * labelNumeroQuestao, quando se clica no bot�o anterior. A Label fica na
	 * View, acima no lado esquerdo.
	 */
	private void setNumeroAnteriorQuestao() {
		labelNumeroQuestao.setText(janelaLabels.getString("quizLabelNumQuestao1") + --numeroAtualQuestao
				+ janelaLabels.getString("quizLabelNumQuestao2") + conteudo.getTotalQuestoes());
	}

	/**
	 * 
	 * Quando se inicializa o Quiz, ou quando se clica em pr�ximo, � chamado
	 * esse m�todo. Ele far� todas as valida��es, com a ajuda de m�todos
	 * auxilares. Ele v� se a quest�o cont�m uma ou v�rias respostas, e com base
	 * nisso se exibe os checkBox ou comboBox. Tamb�m seta o enunciado e tudo
	 * mais.
	 * 
	 * @param questao
	 *            A Quest�o atual.
	 */
	private void verificaQuestao(Questao questao) {

		if (questao.isVariasRespostas()) {
			radioPainel.setVisible(false);
			checkPainel.setVisible(true);
			setQuestao(questao);
			setVariasRespostas(questao);
		} else {
			checkPainel.setVisible(false);
			radioPainel.setVisible(true);
			setQuestao(questao);
			setUnicaEscolha(questao);
		}
	}

	/**
	 * 
	 * Preenxe os dados da Quest�o (Enunciado, numero de op��es corretas,
	 * refer�ncias), menos as alternativas.
	 * 
	 * @param questao
	 *            A Quest�o atual.
	 */
	private void setQuestao(Questao questao) {
		labelEnunciadoQuestao.setText(questao.getEnunciado() + questao.getEnunciadoExtras());
		labelEnunciadoQuestao.setWrapText(true);
		labelNumOpcoesCorretas.setText(questao.getNumOpcoesCorretas());
		labelReferencia.setText(questao.getReferencia());
	}

	/**
	 * 
	 * Preenxe as alternativas, mas nesse caso somente se conter v�rias
	 * respostas corretas.
	 * 
	 * @param questao
	 *            A Quest�o atual.
	 */
	private void setVariasRespostas(Questao questao) {

		setVariasRespostas(questao, checkAlternativaA, 'A');
		setVariasRespostas(questao, checkAlternativaB, 'B');
		setVariasRespostas(questao, checkAlternativaC, 'C');
		setVariasRespostas(questao, checkAlternativaD, 'D');
		setVariasRespostas(questao, checkAlternativaE, 'E');
	}

	/**
	 * 
	 * M�todo auxiliar, seta os valores nos CheckBox, quando a Quest�o � de
	 * V�rias Respostas.
	 * 
	 * @param questao
	 *            A Quest�o atual.
	 */
	private void setVariasRespostas(Questao questao, CheckBox checkAlternativa, Character letra) {
		checkAlternativa.setText(questao.getAlternativa(letra));
		checkAlternativa.setWrapText(true);
		checkAlternativa.setSelected(false);
	}

	/**
	 * 
	 * Preenxe as alternativas, mas nesse caso somente se conter uma resposta
	 * correta.
	 * 
	 * @param questao
	 *            A Quest�o atual.
	 */
	private void setUnicaEscolha(Questao questao) {

		setUnicaEscolha(questao, radioAlternativaA, 'A');
		setUnicaEscolha(questao, radioAlternativaB, 'B');
		setUnicaEscolha(questao, radioAlternativaC, 'C');
		setUnicaEscolha(questao, radioAlternativaD, 'D');
		setUnicaEscolha(questao, radioAlternativaE, 'E');
	}

	/**
	 * 
	 * M�todo auxiliar, seta os valores nos RadioButton, quando a Quest�o cont�m
	 * somente uma Resposta.
	 * 
	 * @param questao
	 *            A Quest�o atual.
	 */
	private void setUnicaEscolha(Questao questao, RadioButton radioAlternativa, Character letra) {
		radioAlternativa.setText(questao.getAlternativa(letra));
		radioAlternativa.setWrapText(true);
		radioAlternativa.setSelected(false);
	}

	/**
	 * 
	 * M�todo que valida a Resposta Escolhida pelo usu�rio, e chama um m�todo
	 * para adiciona-l� um HashMap.
	 * 
	 */
	private void validarComboBox() {
		HashMap<Character, String> respostas = new HashMap<Character, String>();

		verificaAlternativaSelecionada(radioAlternativaA, 'A', respostas);
		verificaAlternativaSelecionada(radioAlternativaB, 'B', respostas);
		verificaAlternativaSelecionada(radioAlternativaC, 'C', respostas);
		verificaAlternativaSelecionada(radioAlternativaD, 'D', respostas);
		verificaAlternativaSelecionada(radioAlternativaE, 'E', respostas);

		addAlternativasEscolhidas(respostas);
	}

	/**
	 * 
	 * M�todo que valida as Respostas Escolhidas pelo usu�rio, e chama um m�todo
	 * para adiciona-l�s em um HashMap.
	 * 
	 */

	private void validarCheckBox() {
		HashMap<Character, String> respostas = new HashMap<Character, String>();

		verificaAlternativaSelecionada(checkAlternativaA, 'A', respostas);
		verificaAlternativaSelecionada(checkAlternativaB, 'B', respostas);
		verificaAlternativaSelecionada(checkAlternativaC, 'C', respostas);
		verificaAlternativaSelecionada(checkAlternativaD, 'D', respostas);
		verificaAlternativaSelecionada(checkAlternativaE, 'E', respostas);

		addAlternativasEscolhidas(respostas);
	}

	/**
	 * 
	 * M�todo Auxiliar nas Respostas Escolhidas pelo usu�rio, verifica em cada
	 * alternativa se o usu�rio a selecionou, se sim, adiciona no HashMap. S� �
	 * chamado quando for v�rias respostas.
	 * 
	 * @param alternativa
	 *            A Alternativa a ser verificada.
	 * @param letra
	 *            A Letra da Quest�o.
	 * @param respostas
	 *            A(s) resposta(s) escolhida(s).
	 */
	private void verificaAlternativaSelecionada(CheckBox alternativa, Character letra,
			HashMap<Character, String> respostas) {

		if (alternativa.isSelected())
			respostas.put(letra, alternativa.getText());
	}

	/**
	 * 
	 * M�todo Auxiliar nas Respostas Escolhidas pelo usu�rio, verifica em cada
	 * alternativa se o usu�rio a selecionou, se sim, adiciona no HashMap. S� �
	 * chamado quando for somente uma alternativa correta..
	 * 
	 * @param alternativa
	 *            A Alternativa a ser verificada.
	 * @param letra
	 *            A Letra da Quest�o.
	 * @param respostas
	 *            A(s) resposta(s) escolhida(s).
	 */
	private void verificaAlternativaSelecionada(RadioButton alternativa, Character letra,
			HashMap<Character, String> respostas) {

		if (alternativa.isSelected())
			respostas.put(letra, alternativa.getText());
	}

	/**
	 * 
	 * M�todo que faz valida��es finais antes de adicionar no HashMap,
	 * verificando se ele selecionou alguma alternativa, se sim, verifica se
	 * essa quest�o j� foi Respondida (Ou Seja, ele clicou em Anterior, e voltou
	 * em alguma Quest�o). Se estiver tudo Ok, Adiciona ou Sobreescreve a
	 * Quest�o, se n�o, lan�a um Alert de Aten��o para o usu�rio selecionar ao
	 * menos uma alternativa.
	 * 
	 * @param respostas
	 *            As Respostas escolhida pelo usu�rio.
	 */
	private void addAlternativasEscolhidas(HashMap<Character, String> respostas) {

		if (isSelecionouAlgumaResposta(respostas)) {
			if (!isQuestaoJaRespondida())
				listaRespostas.add(contQuestao,
						new Resposta(conteudo.getQuestao(contQuestao).getEnunciado(), respostas));
			else
				listaRespostas.set(contQuestao,
						new Resposta(conteudo.getQuestao(contQuestao).getEnunciado(), respostas));
			setProximaQuestao();
		} else {
			AlertDialogsFactory.getAlertDialog(AlertType.CONFIRMATION,
					janelaLabels.getString("quizAlertErroAleternativaTitulo"),
					janelaLabels.getString("quizAlertErroAleternativaCabecalho"),
					janelaLabels.getString("quizAlertErroAleternativaConteudo"));
		}
	}

	/**
	 * 
	 * Verifica se o usu�rio selecionou alguma resposta, � obrigat�rio.
	 * 
	 * @param respostas
	 *            As Respostas escolhida pelo usu�rio.
	 * @return Retorna True se selecionou, False se n�o.
	 */
	private boolean isSelecionouAlgumaResposta(HashMap<Character, String> respostas) {
		return respostas.size() > 0;
	}

	/**
	 * 
	 * Verifica se a Quest�o atual j� foi Respondida (Ou Seja, ele clicou em
	 * Anterior, e voltou em alguma Quest�o).
	 * 
	 * @return Retorna true se j� foi respondida, false se n�o.
	 */
	private boolean isQuestaoJaRespondida() {
		return listaRespostas.contains(new Resposta(conteudo.getQuestao(contQuestao).getEnunciado()));
	}

	/**
	 * M�todo chamado assim que se faz as valida��es e adiciona as Respostas
	 * escolhidas pelo usu�rio, ele passa para a pr�xima quest�o, e seta os seus
	 * valores. Verifica se n�o for a ultima quest�o, passa para a pr�xima.
	 */
	private void setProximaQuestao() {

		contQuestao++;
		if (isUltimaQuestao()) {
			setNumeroProximaQuestao();
			verificaQuestao(conteudo.getQuestao(contQuestao));
			setQuestao(conteudo.getQuestao(contQuestao));
		} else {
			finalizarQuiz();
		}
	}

	private void finalizarQuiz() {
		mainApp.showResultados(listaRespostas, conteudo, tempoRegistrado, dataRegistrada);
		quizStage.close();
	}

	/**
	 * 
	 * Verifica se � a Ultima quest�o, com base em seu numero da Quest�o.
	 * 
	 * @return True se for a Ultima quest�o, false se n�o for.
	 */
	private boolean isUltimaQuestao() {
		return contQuestao <= (conteudo.getTotalQuestoes().intValue() - 1);
	}

	/**
	 * 
	 * Verifica se � a primeira quest�o, com base em seu numero da Quest�o.
	 * 
	 * @return True se for a primeira quest�o, false se n�o for.
	 */
	private boolean isPrimeiraQuestao() {
		return contQuestao == 0;
	}

	/**
	 * M�todo que trabalha a parte din�mica do Bot�o Anterior. Quando se clicar
	 * nele, ir� verificar se � a primeira quest�o, se n�o �, � possivel voltar,
	 * e assim se seta os valores da quest�o anterior. Variavel de refer�ncia a
	 * Button na view.
	 */
	@FXML
	private void handleAnterior() {

		if (!isPrimeiraQuestao()) {
			setNumeroAnteriorQuestao();
			contQuestao--;
			verificaQuestao(conteudo.getQuestao(contQuestao));
			setQuestao(conteudo.getQuestao(contQuestao));
		}
	}

	/**
	 * M�todo que trabalha a parte din�mica do Bot�o Proximo. Verifica se a
	 * quest�o contem v�rias respostas, se sim, valida com base em checkbox, se
	 * n�o, em combobox. Variavel de refer�ncia a Button na view.
	 */
	@FXML
	private void handleProximo() {

		if (conteudo.getQuestao(contQuestao).isVariasRespostas())
			validarCheckBox();
		else
			validarComboBox();

	}

	/**
	 * M�todo que trabalha a parte din�mica do Bot�o Ajuda. Abre um Alert
	 * contendo uma pequena ajuda sobre a quest�o para o Usu�rio. Variavel de
	 * refer�ncia a Button na view.
	 */
	@FXML
	private void handleAjuda() {
		AlertDialogsFactory.getAlertDialog(AlertType.INFORMATION,
				janelaLabels.getString("quizAjudaTitulo") + numeroAtualQuestao,
				perguntasLabel.getString("teste" + conteudo.getTipoTeste().getTotalQuestoes() + ".exercicio"
						+ numeroAtualQuestao + ".ajuda.cabecalho"),
				perguntasLabel.getString("teste" + conteudo.getTipoTeste().getTotalQuestoes() + ".exercicio"
						+ numeroAtualQuestao + ".ajuda.conteudo"));
	}

	/**
	 * M�todo que trabalha a parte din�mica do Bot�o Sair. Ele Fecha o stage
	 * atual e volta a Home. Variavel de refer�ncia a Button na view.
	 */
	@FXML
	private void handleSair() {
		dialogHome.show();
		quizStage.close();
	}

	/**
	 * 
	 * Se Define uma nova Refer�ncia a Stage atual.
	 * 
	 * @param dialogStage
	 *            a nova Refer�ncia a Stage atual.
	 */
	public void setDialogStage(Stage dialogStage) {
		this.quizStage = dialogStage;
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
	 * Se Define uma nova Lista que conter� todas as respostas do Usu�rio para
	 * cada Quest�o.
	 * 
	 * @param listaRespostas
	 *            A nova Lista que conter� todas as respostas do Usu�rio para
	 *            cada Quest�o.
	 */
	public void setListaRespostas(ArrayList<Resposta> listaRespostas) {
		this.listaRespostas = listaRespostas;
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
	 *            A Refer�ncia ao Conteudo, onde cont�m as quest�es preenxidas,
	 *            al�m de outros detalhes.
	 */
	public void setConteudo(Conteudo conteudo) {
		this.conteudo = conteudo;
	}

}
