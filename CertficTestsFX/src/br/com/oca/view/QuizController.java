package br.com.oca.view;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import br.com.oca.controller.MainApp;
import br.com.oca.model.Questao;
import br.com.oca.model.Resposta;
import br.com.oca.model.conteudo.Conteudo;
import br.com.oca.model.enums.Idioma;
import br.com.oca.model.i18n.janelas.JanelasSource;
import br.com.oca.util.AlertDialogsFactory;
import javafx.application.Platform;
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
	private JanelasSource label;
	private Conteudo conteudo;

	private int currentSegundos = 0;
	private int currentMinutos = 0;
	private int currentHora = 0;
	private Calendar tempoRegistrado;

	private MainApp mainApp;

	public QuizController() {
		listaRespostas = new ArrayList<Resposta>();
		tempoRegistrado = Calendar.getInstance();
	}

	@FXML
	private void initialize() {
		contQuestao = 0;
		numeroAtualQuestao = 1;
		label = JanelasSource.getInstance(idioma);
	}

	public void iniciarQuiz() {

		labelNomeExame.setText(conteudo.getNomeTeste().getNomeExtenso());
		labelNumeroQuestao.setText(label.getString("quizLabelNumQuestao1") + numeroAtualQuestao
				+ label.getString("quizLabelNumQuestao2") + conteudo.getTotalQuestoes());
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

						labelTempoDecorrido.setText(label.getString("quizLabelTempoDecorrido")
								+ dateFormat.format(tempoRegistrado.getTime()));

						if (currentHora == conteudo.getTipoTeste().getTempoMaximoDuracao()) {
							timer.cancel();
							AlertDialogsFactory.getAlertDialog(AlertType.INFORMATION,
									label.getString("quizAlertTitulo"), label.getString("quizAlertCabecalho"),
									label.getString("quizAlertConteudo"));
							finalizarQuiz();
						}
					}
				});
			}
		}, 0, VELOCIDADE_CRONOMETRO);
	}

	private void setNumeroProximaQuestao() {
		labelNumeroQuestao.setText(label.getString("quizLabelNumQuestao1") + ++numeroAtualQuestao
				+ label.getString("quizLabelNumQuestao2") + conteudo.getTotalQuestoes());
	}

	private void setNumeroAnteriorQuestao() {
		labelNumeroQuestao.setText(label.getString("quizLabelNumQuestao1") + --numeroAtualQuestao
				+ label.getString("quizLabelNumQuestao2") + conteudo.getTotalQuestoes());
	}

	private void verificaQuestao(Questao questao) {

		radioGroup = new ToggleGroup();
		switch (questao.getTipoQuestao()) {
		case MULTIPLA:
			comboPainel.setVisible(false);
			checkPainel.setVisible(true);
			setQuestao(questao);
			setMultiplasEscolhas(questao);
			break;
		case UNICA:
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

		checkAlternativaA.setText(questao.getAlternativa('A'));
		checkAlternativaA.setWrapText(true);
		checkAlternativaB.setText(questao.getAlternativa('B'));
		checkAlternativaB.setWrapText(true);
		checkAlternativaC.setText(questao.getAlternativa('C'));
		checkAlternativaC.setWrapText(true);
		checkAlternativaD.setText(questao.getAlternativa('D'));
		checkAlternativaD.setWrapText(true);
		checkAlternativaE.setText(questao.getAlternativa('E'));
		checkAlternativaE.setWrapText(true);
	}

	private void setUnicaEscolha(Questao questao) {

		radioAlternativaA.setText(questao.getAlternativa('A'));
		radioAlternativaA.setToggleGroup(radioGroup);
		radioAlternativaA.setWrapText(true);
		radioAlternativaB.setText(questao.getAlternativa('B'));
		radioAlternativaB.setToggleGroup(radioGroup);
		radioAlternativaB.setWrapText(true);
		radioAlternativaC.setText(questao.getAlternativa('C'));
		radioAlternativaC.setToggleGroup(radioGroup);
		radioAlternativaC.setWrapText(true);
		radioAlternativaD.setText(questao.getAlternativa('D'));
		radioAlternativaD.setToggleGroup(radioGroup);
		radioAlternativaD.setWrapText(true);
		radioAlternativaE.setText(questao.getAlternativa('E'));
		radioAlternativaE.setToggleGroup(radioGroup);
		radioAlternativaE.setWrapText(true);
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
		case MULTIPLA:
			addAlternativasEscolhidas();
			break;
		case UNICA:
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
					label.getString("quizAlertErroAleternativaTitulo"),
					label.getString("quizAlertErroAleternativaCabecalho"),
					label.getString("quizAlertErroAleternativaConteudo"));
		}
	}

	private void setProximaQuestao() {

		contQuestao++;
		if (contQuestao <= (conteudo.getTotalQuestoes() - 1)) {
			setNumeroProximaQuestao();
			verificaQuestao(conteudo.getQuestao(contQuestao));
			setQuestao(conteudo.getQuestao(contQuestao));
		} else {
			finalizarQuiz();
		}
	}

	private void finalizarQuiz() {
		mainApp.showResultadoController(listaRespostas, conteudo, tempoRegistrado);
		dialogStage.close();
	}

	private void addAlternativasEscolhidas() {
		ArrayList<String> respostas = new ArrayList<String>();

		verificaAlternativa(checkAlternativaA, respostas);
		verificaAlternativa(checkAlternativaB, respostas);
		verificaAlternativa(checkAlternativaC, respostas);
		verificaAlternativa(checkAlternativaD, respostas);
		verificaAlternativa(checkAlternativaE, respostas);

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
					label.getString("quizAlertErroAleternativaTitulo"),
					label.getString("quizAlertErroAleternativaCabecalho"),
					label.getString("quizAlertErroAleternativaConteudo"));
		}
	}

	private boolean isQuestaoJaRespondida() {
		return listaRespostas.contains(new Resposta(conteudo.getQuestao(contQuestao).getEnunciado()));
	}

	private void verificaAlternativa(CheckBox alternativa, ArrayList<String> respostas) {
		if (alternativa.isSelected()) {
			respostas.add(alternativa.getText());
		}
	}

	@FXML
	private void handleAjuda() {

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
