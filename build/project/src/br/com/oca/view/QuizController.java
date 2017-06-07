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
 * A Classe QuizController é o Controller da View Quiz, onde iremos trabalhar
 * toda a parte dinâmica da Janela Quiz. Todas as regras, detalhes das questões,
 * tempo, é configurado por aqui.
 * 
 * Classe <code>QuizController</code>
 * 
 * @author Tiago Henrique
 * @version 1.0
 *
 */
public class QuizController {
	/** Váriavel global representando a velocidade do Cornômetro. */
	public static final int VELOCIDADE_CRONOMETRO = 1000;

	/**
	 * Váriavel global representando um minuto em Segundos (Um Minuto tem 60
	 * Segundos).
	 */
	public static final int UM_MINUTO = 60;

	/**
	 * Váriavel global representando uma Hora em minutos (Uma Hora tem 60
	 * Minutos).
	 */
	public static final int UMA_HORA = 60;

	/** O Numero da Questão Atual. Variavel de referência a Label na view. */
	@FXML
	private Label labelNumeroQuestao;

	/**
	 * O Nome do Exame (Exame + Tipo de Teste). Variavel de referência a Label
	 * na view.
	 */
	@FXML
	private Label labelNomeExame;

	/** O Tempo Decorrido no Teste. Variavel de referência a Label na view. */
	@FXML
	private Label labelTempoDecorrido;

	/**
	 * Referência da Questão, dados de onde foi tirado aquela questão (Autor do
	 * Livro + Nome do Livro + Página + Numero da Questão). Variavel de
	 * referência a Label na view.
	 */
	@FXML
	private Label labelReferencia;

	/**
	 * Mostra o Numero De alternativas que estaram corretas na questão. Variavel
	 * de referência a Label na view.
	 */
	@FXML
	private Label labelNumOpcoesCorretas;

	/** O Enunciado da Questão Atual. Variavel de referência a Label na view. */
	@FXML
	private Label labelEnunciadoQuestao;

	/**
	 * Painel contendo as RadioButton. Variavel de referência a Pane na view.
	 */
	@FXML
	private Pane radioPainel;

	/**
	 * Alternativa A como RadioButton da Questão. Variavel de referência a
	 * RadioButton na view.
	 */
	@FXML
	private RadioButton radioAlternativaA;

	/**
	 * Alternativa B como RadioButton da Questão. Variavel de referência a
	 * RadioButton na view.
	 */
	@FXML
	private RadioButton radioAlternativaB;

	/**
	 * Alternativa C como RadioButton da Questão. Variavel de referência a
	 * RadioButton na view.
	 */
	@FXML
	private RadioButton radioAlternativaC;

	/**
	 * Alternativa D como RadioButton da Questão. Variavel de referência a
	 * RadioButton na view.
	 */
	@FXML
	private RadioButton radioAlternativaD;

	/**
	 * Alternativa E como RadioButton da Questão. Variavel de referência a
	 * RadioButton na view.
	 */
	@FXML
	private RadioButton radioAlternativaE;

	/** Painel contendo os ChackBox. Variavel de referência a Pane na view. */
	@FXML
	private Pane checkPainel;

	/**
	 * Alternativa A como CheckBox da Questão. Variavel de referência a CheckBox
	 * na view.
	 */
	@FXML
	private CheckBox checkAlternativaA;

	/**
	 * Alternativa B como CheckBox da Questão. Variavel de referência a CheckBox
	 * na view.
	 */
	@FXML
	private CheckBox checkAlternativaB;

	/**
	 * Alternativa C como CheckBox da Questão. Variavel de referência a CheckBox
	 * na view.
	 */
	@FXML
	private CheckBox checkAlternativaC;

	/**
	 * Alternativa D como CheckBox da Questão. Variavel de referência a CheckBox
	 * na view.
	 */
	@FXML
	private CheckBox checkAlternativaD;

	/**
	 * Alternativa E como CheckBox da Questão. Variavel de referência a CheckBox
	 * na view.
	 */
	@FXML
	private CheckBox checkAlternativaE;

	/**
	 * Botão para a Questão Anterior. Variavel de referência a Button na view.
	 */
	@FXML
	private Button anteriorQuestao;

	/**
	 * Botão para a Próxima Questão. Variavel de referência a Button na view.
	 */
	@FXML
	private Button proximaQuestao;

	/**
	 * Botão para a Ajuda com a Questão Atual. Variavel de referência a Button
	 * na view.
	 */
	@FXML
	private Button ajuda;

	/** Botão para sair do Teste. Variavel de referência a Button na view. */
	@FXML
	private Button sair;

	/** Referência ao Stage atual. */
	private Stage quizStage;

	/** Referência ao Stage da Home. */
	private Stage dialogHome;

	/** Contador para Referenciar uma Questão. */
	private Integer contQuestao;

	/** Numero de Cada Questão, auxiliar ao labelNumeroQuestao. */
	private Integer numeroAtualQuestao;

	/** O Idioma que a aplicação terá.. */
	private Idioma idioma;

	/** A Lista que conterá todas as respostas do Usuário para cada Questão. */
	private ArrayList<Resposta> listaRespostas;

	/**
	 * Referência ao Conteudo, onde contém as questões preenxidas, além de
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

	/** Objeto que irá guardar o tempo de duração registrado. */
	private Calendar tempoRegistrado;

	/** Objeto que irá guardar a data e hora Registrada. */
	private Date dataRegistrada;

	/** Referência a mainApp, classe controladora da Aplicação. */
	private MainApp mainApp;

	/**
	 * Se Instancia uma nova QuizController, chamado logo após o initialize() do
	 * JavaFX.
	 */
	public QuizController() {
		listaRespostas = new ArrayList<Resposta>();
		tempoRegistrado = Calendar.getInstance();
	}

	/**
	 * Método padrão no JavaFX, ele é usado para se chamar a classe e iniciá-la,
	 * quando se cria um novo controller pelo método getController(). O
	 * Construtor é chamado logo após.
	 */
	@FXML
	private void initialize() {
		contQuestao = 0;
		numeroAtualQuestao = 1;
		dataRegistrada = new Date();
		janelaLabels = JanelasConfig.getInstance(idioma);
	}

	/**
	 * Método para ser chamado quando se desejar preenxer os campos da Janela.
	 * No JavaFX não se inicializa os controller por construtores, então esse
	 * método substitui essa função. O Método primeiramente deixa referenciado
	 * que quando se fechar a janela pelo x no canto superior direito, se
	 * chamará o método handleSair(). Após isso, é setado todos os valores
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
	 * Realiza o Cronômetro para o Teste. Com um Objeto do Tipo TImer, e
	 * utilizando o schedule(), se realiza o timer, que será finalizado somente
	 * quando se terminar o teste completetamente. Se o tempo ultrapassar o
	 * tempo de duração máximo, variável para cada tipo de teste, um alert é
	 * lançado e se finaliza o teste.
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
	 * Método utilizado para se setar o numero da próxima questão no
	 * labelNumeroQuestao, que fica na View, acima no lado esquerdo.
	 */
	private void setNumeroProximaQuestao() {
		labelNumeroQuestao.setText(janelaLabels.getString("quizLabelNumQuestao1") + ++numeroAtualQuestao
				+ janelaLabels.getString("quizLabelNumQuestao2") + conteudo.getTotalQuestoes());
	}

	/**
	 * Método utilizado para se setar o numero da questão anterior no
	 * labelNumeroQuestao, quando se clica no botão anterior. A Label fica na
	 * View, acima no lado esquerdo.
	 */
	private void setNumeroAnteriorQuestao() {
		labelNumeroQuestao.setText(janelaLabels.getString("quizLabelNumQuestao1") + --numeroAtualQuestao
				+ janelaLabels.getString("quizLabelNumQuestao2") + conteudo.getTotalQuestoes());
	}

	/**
	 * 
	 * Quando se inicializa o Quiz, ou quando se clica em próximo, é chamado
	 * esse método. Ele fará todas as validações, com a ajuda de métodos
	 * auxilares. Ele vê se a questão contém uma ou várias respostas, e com base
	 * nisso se exibe os checkBox ou comboBox. Também seta o enunciado e tudo
	 * mais.
	 * 
	 * @param questao
	 *            A Questão atual.
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
	 * Preenxe os dados da Questão (Enunciado, numero de opções corretas,
	 * referências), menos as alternativas.
	 * 
	 * @param questao
	 *            A Questão atual.
	 */
	private void setQuestao(Questao questao) {
		labelEnunciadoQuestao.setText(questao.getEnunciado() + questao.getEnunciadoExtras());
		labelEnunciadoQuestao.setWrapText(true);
		labelNumOpcoesCorretas.setText(questao.getNumOpcoesCorretas());
		labelReferencia.setText(questao.getReferencia());
	}

	/**
	 * 
	 * Preenxe as alternativas, mas nesse caso somente se conter várias
	 * respostas corretas.
	 * 
	 * @param questao
	 *            A Questão atual.
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
	 * Método auxiliar, seta os valores nos CheckBox, quando a Questão é de
	 * Várias Respostas.
	 * 
	 * @param questao
	 *            A Questão atual.
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
	 *            A Questão atual.
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
	 * Método auxiliar, seta os valores nos RadioButton, quando a Questão contém
	 * somente uma Resposta.
	 * 
	 * @param questao
	 *            A Questão atual.
	 */
	private void setUnicaEscolha(Questao questao, RadioButton radioAlternativa, Character letra) {
		radioAlternativa.setText(questao.getAlternativa(letra));
		radioAlternativa.setWrapText(true);
		radioAlternativa.setSelected(false);
	}

	/**
	 * 
	 * Método que valida a Resposta Escolhida pelo usuário, e chama um método
	 * para adiciona-lá um HashMap.
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
	 * Método que valida as Respostas Escolhidas pelo usuário, e chama um método
	 * para adiciona-lás em um HashMap.
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
	 * Método Auxiliar nas Respostas Escolhidas pelo usuário, verifica em cada
	 * alternativa se o usuário a selecionou, se sim, adiciona no HashMap. Só é
	 * chamado quando for várias respostas.
	 * 
	 * @param alternativa
	 *            A Alternativa a ser verificada.
	 * @param letra
	 *            A Letra da Questão.
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
	 * Método Auxiliar nas Respostas Escolhidas pelo usuário, verifica em cada
	 * alternativa se o usuário a selecionou, se sim, adiciona no HashMap. Só é
	 * chamado quando for somente uma alternativa correta..
	 * 
	 * @param alternativa
	 *            A Alternativa a ser verificada.
	 * @param letra
	 *            A Letra da Questão.
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
	 * Método que faz validações finais antes de adicionar no HashMap,
	 * verificando se ele selecionou alguma alternativa, se sim, verifica se
	 * essa questão já foi Respondida (Ou Seja, ele clicou em Anterior, e voltou
	 * em alguma Questão). Se estiver tudo Ok, Adiciona ou Sobreescreve a
	 * Questão, se não, lança um Alert de Atenção para o usuário selecionar ao
	 * menos uma alternativa.
	 * 
	 * @param respostas
	 *            As Respostas escolhida pelo usuário.
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
	 * Verifica se o usuário selecionou alguma resposta, é obrigatório.
	 * 
	 * @param respostas
	 *            As Respostas escolhida pelo usuário.
	 * @return Retorna True se selecionou, False se não.
	 */
	private boolean isSelecionouAlgumaResposta(HashMap<Character, String> respostas) {
		return respostas.size() > 0;
	}

	/**
	 * 
	 * Verifica se a Questão atual já foi Respondida (Ou Seja, ele clicou em
	 * Anterior, e voltou em alguma Questão).
	 * 
	 * @return Retorna true se já foi respondida, false se não.
	 */
	private boolean isQuestaoJaRespondida() {
		return listaRespostas.contains(new Resposta(conteudo.getQuestao(contQuestao).getEnunciado()));
	}

	/**
	 * Método chamado assim que se faz as validações e adiciona as Respostas
	 * escolhidas pelo usuário, ele passa para a próxima questão, e seta os seus
	 * valores. Verifica se não for a ultima questão, passa para a próxima.
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
	 * Verifica se é a Ultima questão, com base em seu numero da Questão.
	 * 
	 * @return True se for a Ultima questão, false se não for.
	 */
	private boolean isUltimaQuestao() {
		return contQuestao <= (conteudo.getTotalQuestoes().intValue() - 1);
	}

	/**
	 * 
	 * Verifica se é a primeira questão, com base em seu numero da Questão.
	 * 
	 * @return True se for a primeira questão, false se não for.
	 */
	private boolean isPrimeiraQuestao() {
		return contQuestao == 0;
	}

	/**
	 * Método que trabalha a parte dinâmica do Botão Anterior. Quando se clicar
	 * nele, irá verificar se é a primeira questão, se não é, é possivel voltar,
	 * e assim se seta os valores da questão anterior. Variavel de referência a
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
	 * Método que trabalha a parte dinâmica do Botão Proximo. Verifica se a
	 * questão contem várias respostas, se sim, valida com base em checkbox, se
	 * não, em combobox. Variavel de referência a Button na view.
	 */
	@FXML
	private void handleProximo() {

		if (conteudo.getQuestao(contQuestao).isVariasRespostas())
			validarCheckBox();
		else
			validarComboBox();

	}

	/**
	 * Método que trabalha a parte dinâmica do Botão Ajuda. Abre um Alert
	 * contendo uma pequena ajuda sobre a questão para o Usuário. Variavel de
	 * referência a Button na view.
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
	 * Método que trabalha a parte dinâmica do Botão Sair. Ele Fecha o stage
	 * atual e volta a Home. Variavel de referência a Button na view.
	 */
	@FXML
	private void handleSair() {
		dialogHome.show();
		quizStage.close();
	}

	/**
	 * 
	 * Se Define uma nova Referência a Stage atual.
	 * 
	 * @param dialogStage
	 *            a nova Referência a Stage atual.
	 */
	public void setDialogStage(Stage dialogStage) {
		this.quizStage = dialogStage;
	}

	/**
	 * 
	 * Se Define um novo Idioma para a aplicação.
	 * 
	 * @param idioma
	 *            O novo Idioma para a aplicação.
	 */
	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}

	/**
	 * 
	 * Se Define uma nova Referência a MainApp, classe controladora da
	 * Aplicação.
	 * 
	 * @param mainApp
	 *            A nova MainApp, classe controladora da Aplicação.
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	/**
	 * 
	 * Se Define uma nova Lista que conterá todas as respostas do Usuário para
	 * cada Questão.
	 * 
	 * @param listaRespostas
	 *            A nova Lista que conterá todas as respostas do Usuário para
	 *            cada Questão.
	 */
	public void setListaRespostas(ArrayList<Resposta> listaRespostas) {
		this.listaRespostas = listaRespostas;
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
	 * Se Define uma nova Referência ao Conteudo, onde contém as questões
	 * preenxidas, além de outros detalhes.
	 * 
	 * @param conteudo
	 *            A Referência ao Conteudo, onde contém as questões preenxidas,
	 *            além de outros detalhes.
	 */
	public void setConteudo(Conteudo conteudo) {
		this.conteudo = conteudo;
	}

}
