package br.com.oca.view;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import br.com.oca.MainApp;
import br.com.oca.model.Questao;
import br.com.oca.model.Resposta;
import br.com.oca.model.conteudo.Conteudo;
import br.com.oca.model.enums.Idioma;
import br.com.oca.model.i18n.janelas.JanelasSource;
import br.com.oca.model.i18n.perguntas.PerguntasSource;
import br.com.oca.util.AlertDialogsFactory;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class QuizController {
	public static final int VELOCIDADE_CRONOMETRO = 1000;

	@FXML
	private Label labelNumeroQuestao;
	@FXML
	private Label labelNomeExame;
	@FXML
	private Label labelTempoDecorrido;
	@FXML
	private Label labelReferencia;
	@FXML
	private Label labelNumOpcoesCorretas;
	@FXML
	private Label labelEnunciadoQuestao;
	@FXML
	private ScrollPane scrollEnunciado;
	@FXML
	private Pane comboPainel;
	@FXML
	private ToggleGroup radioGroup;
	@FXML
	private RadioButton radioAlternativaA;
	@FXML
	private RadioButton radioAlternativaB;
	@FXML
	private RadioButton radioAlternativaC;
	@FXML
	private RadioButton radioAlternativaD;
	@FXML
	private RadioButton radioAlternativaE;
	@FXML
	private Pane checkPainel;
	@FXML
	private CheckBox checkAlternativaA;
	@FXML
	private CheckBox checkAlternativaB;
	@FXML
	private CheckBox checkAlternativaC;
	@FXML
	private CheckBox checkAlternativaD;
	@FXML
	private CheckBox checkAlternativaE;
	@FXML
	private Button anteriorQuestao;
	@FXML
	private Button proximaQuestao;
	@FXML
	private Button ajuda;
	@FXML
	private Button sair;

	private Stage dialogStage;
	private Stage dialogHome;

	private Integer contQuestao;
	private Integer numeroAtualQuestao;
	private Idioma idioma;
	private ArrayList<Resposta> listaRespostas;
	private Conteudo conteudo;

	private JanelasSource janelaLabels;
	private PerguntasSource perguntasLabel;

	private int currentSegundos = 0;
	private int currentMinutos = 0;
	private int currentHora = 0;
	private Calendar tempoRegistrado;
	private Date dataRegistrada;

	private MainApp mainApp;

	public QuizController() {
		listaRespostas = new ArrayList<Resposta>();
		tempoRegistrado = Calendar.getInstance();
	}

	@FXML
	private void initialize() {
		contQuestao = 0;
		numeroAtualQuestao = 1;
		dataRegistrada = new Date();
		janelaLabels = JanelasSource.getInstance(idioma);
	}

	public void iniciarQuiz() {
		perguntasLabel = PerguntasSource.getInstance(idioma, conteudo.getNomeTeste());
		
		dialogStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	          public void handle(WindowEvent we) {
	              handleSair();
	          }
	    });   
		
		labelNomeExame.setText(conteudo.getNomeTeste().getNomeExtenso());
		labelNumeroQuestao.setText(janelaLabels.getString("quizLabelNumQuestao1") + numeroAtualQuestao
				+ janelaLabels.getString("quizLabelNumQuestao2") + conteudo.getTotalQuestoes());
		verificaQuestao(conteudo.getQuestao(contQuestao));
		setQuestao(conteudo.getQuestao(contQuestao));
		iniciaTimer();
	}

	private void iniciaTimer() {
		Timer timer = new Timer();
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

		timer.schedule(new TimerTask() {
			public void run() {
				Platform.runLater(new Runnable() {
					public void run() {
						currentSegundos++;

						if (currentSegundos == 60) {
							currentMinutos++;
							currentSegundos = 0;
						}

						if (currentMinutos == 60) {
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

	private void setNumeroProximaQuestao() {
		labelNumeroQuestao.setText(janelaLabels.getString("quizLabelNumQuestao1") + ++numeroAtualQuestao
				+ janelaLabels.getString("quizLabelNumQuestao2") + conteudo.getTotalQuestoes());
	}

	private void setNumeroAnteriorQuestao() {
		labelNumeroQuestao.setText(janelaLabels.getString("quizLabelNumQuestao1") + --numeroAtualQuestao
				+ janelaLabels.getString("quizLabelNumQuestao2") + conteudo.getTotalQuestoes());
	}

	private void verificaQuestao(Questao questao) {

		radioGroup = new ToggleGroup();
		switch (questao.getTipoQuestao()) {
		case MULTIPLAS_ALTERNATIVAS:
			comboPainel.setVisible(false);
			checkPainel.setVisible(true);
			setQuestao(questao);
			setMultiplasEscolhas(questao);
			break;
		case UMA_ALTERNATIVA:
			checkPainel.setVisible(false);
			comboPainel.setVisible(true);
			setQuestao(questao);
			setUnicaEscolha(questao);
		}
	}

	private void setQuestao(Questao questao) {
		labelEnunciadoQuestao.setText(questao.getEnunciado() + questao.getEnunciadoExtras());
		labelEnunciadoQuestao.setWrapText(true);
		labelNumOpcoesCorretas.setText(questao.getNumOpcoesCorretas());
		labelReferencia.setText(questao.getReferencia());
	}

	private void setMultiplasEscolhas(Questao questao) {

		setMultiplasEscolhas(questao, checkAlternativaA, 'A');
		setMultiplasEscolhas(questao, checkAlternativaB, 'B');
		setMultiplasEscolhas(questao, checkAlternativaC, 'C');
		setMultiplasEscolhas(questao, checkAlternativaD, 'D');
		setMultiplasEscolhas(questao, checkAlternativaE, 'E');
	}

	private void setMultiplasEscolhas(Questao questao, CheckBox checkAlternativa, Character letra) {
		checkAlternativa.setText(questao.getAlternativa(letra));
		checkAlternativa.setWrapText(true);
		checkAlternativa.setSelected(false);
	}

	private void setUnicaEscolha(Questao questao) {

		setUnicaEscolha(questao, radioAlternativaA, 'A');
		setUnicaEscolha(questao, radioAlternativaB, 'B');
		setUnicaEscolha(questao, radioAlternativaC, 'C');
		setUnicaEscolha(questao, radioAlternativaD, 'D');
		setUnicaEscolha(questao, radioAlternativaE, 'E');
	}

	private void setUnicaEscolha(Questao questao, RadioButton radioAlternativa, Character letra) {
		radioAlternativa.setText(questao.getAlternativa(letra));
		radioAlternativa.setToggleGroup(radioGroup);
		radioAlternativa.setWrapText(true);
		radioAlternativa.setSelected(false);
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	@FXML
	private void handleAnterior() {

		if (contQuestao > 0) {
			setNumeroAnteriorQuestao();
			contQuestao--;
			verificaQuestao(conteudo.getQuestao(contQuestao));
			setQuestao(conteudo.getQuestao(contQuestao));
		}
	}

	@FXML
	private void handleProximo() {

		switch (conteudo.getQuestao(contQuestao).getTipoQuestao()) {
		case MULTIPLAS_ALTERNATIVAS:
			addAlternativasEscolhidas();
			break;
		case UMA_ALTERNATIVA:
			addAlternativaEscolhida();
		}
	}

	private void addAlternativaEscolhida() {

		RadioButton radioResposta = (RadioButton) radioGroup.getSelectedToggle();

		if (radioResposta != null) {
			if (!isQuestaoJaRespondida())
				listaRespostas.add(contQuestao,
						new Resposta(conteudo.getQuestao(contQuestao).getEnunciado(), radioResposta.getText()));
			else
				listaRespostas.set(contQuestao,
						new Resposta(conteudo.getQuestao(contQuestao).getEnunciado(), radioResposta.getText()));
			setProximaQuestao();
		} else {
			AlertDialogsFactory.getAlertDialog(AlertType.ERROR,
					janelaLabels.getString("quizAlertErroAleternativaTitulo"),
					janelaLabels.getString("quizAlertErroAleternativaCabecalho"),
					janelaLabels.getString("quizAlertErroAleternativaConteudo"));
		}
	}

	private void setProximaQuestao() {

		contQuestao++;
		if (contQuestao <= (conteudo.getTotalQuestoes().intValue() - 1)) {
			setNumeroProximaQuestao();
			verificaQuestao(conteudo.getQuestao(contQuestao));
			setQuestao(conteudo.getQuestao(contQuestao));
		} else {
			finalizarQuiz();
		}
	}

	private void finalizarQuiz() {
		mainApp.showResultados(listaRespostas, conteudo, tempoRegistrado, dataRegistrada);
		dialogStage.close();
	}

	private void addAlternativasEscolhidas() {
		ArrayList<String> respostas = new ArrayList<String>();

		verificaAlternativaSelecionada(checkAlternativaA, respostas);
		verificaAlternativaSelecionada(checkAlternativaB, respostas);
		verificaAlternativaSelecionada(checkAlternativaC, respostas);
		verificaAlternativaSelecionada(checkAlternativaD, respostas);
		verificaAlternativaSelecionada(checkAlternativaE, respostas);

		addAlternativasEscolhidas(respostas);
	}

	private void addAlternativasEscolhidas(ArrayList<String> respostas) {
		
		if (respostas.size() > 0) {
			if (!isQuestaoJaRespondida())
				listaRespostas.add(contQuestao,
						new Resposta(conteudo.getQuestao(contQuestao).getEnunciado(), respostas));
			else
				listaRespostas.set(contQuestao,
						new Resposta(conteudo.getQuestao(contQuestao).getEnunciado(), respostas));
			setProximaQuestao();
		} else {
			AlertDialogsFactory.getAlertDialog(AlertType.ERROR,
					janelaLabels.getString("quizAlertErroAleternativaTitulo"),
					janelaLabels.getString("quizAlertErroAleternativaCabecalho"),
					janelaLabels.getString("quizAlertErroAleternativaConteudo"));
		}
	}

	private boolean isQuestaoJaRespondida() {
		return listaRespostas.contains(new Resposta(conteudo.getQuestao(contQuestao).getEnunciado()));
	}

	private void verificaAlternativaSelecionada(CheckBox alternativa, ArrayList<String> respostas) {
		if (alternativa.isSelected()) {
			respostas.add(alternativa.getText());
		}
	}

	@FXML
	private void handleAjuda() {
		AlertDialogsFactory.getAlertDialog(AlertType.INFORMATION,
				janelaLabels.getString("quizAjudaTitulo") + numeroAtualQuestao,
				perguntasLabel.getString("teste" + conteudo.getTipoTeste().getTotalQuestoes() + ".exercicio"
						+ numeroAtualQuestao + ".ajuda.cabecalho"),
				perguntasLabel.getString("teste" + conteudo.getTipoTeste().getTotalQuestoes() + ".exercicio"
						+ numeroAtualQuestao + ".ajuda.conteudo"));
	}

	@FXML
	private void handleSair() {
		dialogHome.show();
		dialogStage.close();
	}

	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public void setListaRespostas(ArrayList<Resposta> listaRespostas) {
		this.listaRespostas = listaRespostas;
	}

	public void setDialogHome(Stage dialogHome) {
		this.dialogHome = dialogHome;
	}

	public void setConteudo(Conteudo conteudo) {
		this.conteudo = conteudo;
	}

}
