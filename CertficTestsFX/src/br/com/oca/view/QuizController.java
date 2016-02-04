package br.com.oca.view;

import java.util.ArrayList;

import br.com.oca.controller.MainApp;
import br.com.oca.model.Questao;
import br.com.oca.model.Resposta;
import br.com.oca.model.conteudo.Conteudo;
import br.com.oca.model.conteudo.OCA;
import br.com.oca.model.enums.Certificacao;
import br.com.oca.model.enums.Idioma;
import br.com.oca.model.enums.TipoTeste;
import br.com.oca.util.Observer;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class QuizController implements Observer {
	@FXML
	private Label labelNumeroQuestao;
	@FXML
	private Label labelNomeExame;
	@FXML
	private Label labelTempoDecorrido;
	@FXML
	private Label labelReferencia;
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
	
	private MainApp mainApp;

	public QuizController() {
		listaRespostas = new ArrayList<Resposta>();
	}

	@FXML
	private void initialize() {
		contQuestao = 0;
		numeroAtualQuestao = 1;
	}

	public void iniciarQuiz() {
		
		labelNomeExame.setText(conteudo.getNomeTeste().getNome());
		setNumeroQuestao();
		verificaQuestao(conteudo.getQuestao(contQuestao));
		setQuestao(conteudo.getQuestao(contQuestao));
	}

	private void setNumeroQuestao() {
		labelNumeroQuestao.setText("Questão " + numeroAtualQuestao + " de " + conteudo.getTotalQuestoes());
		numeroAtualQuestao++;
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
			listaRespostas.add(new Resposta(conteudo.getQuestao(contQuestao).getEnunciado(), radioResposta.getText()));
			setProximaQuestao();
		} else {
			exibirMensagemErro("É Necessário escolher uma Alternativa.");
		}
	}
	
	private void setProximaQuestao() {
		
		contQuestao++;
		if (contQuestao <= (conteudo.getTotalQuestoes() - 1)) {
			setNumeroQuestao();
			verificaQuestao(conteudo.getQuestao(contQuestao));
			setQuestao(conteudo.getQuestao(contQuestao));
		} else {
			mainApp.showResultadoController(listaRespostas, conteudo);
			dialogStage.close();
		}
	}
	
	private void addAlternativasEscolhidas() {
		ArrayList<String> respostas = new ArrayList<String>();
		
		verificaAlternativa(checkAlternativaA, respostas);
		verificaAlternativa(checkAlternativaB, respostas);
		verificaAlternativa(checkAlternativaC, respostas);
		verificaAlternativa(checkAlternativaD, respostas);
		verificaAlternativa(checkAlternativaE, respostas);
		
		if (respostas.size() > 0) {
			listaRespostas.add(new Resposta(conteudo.getQuestao(contQuestao).getEnunciado(), respostas));
			setProximaQuestao();
		} else {
			exibirMensagemErro("É Necessário Escolher pelo Menos uma Alternativa.");
		}
	}
	
	private void exibirMensagemErro(String mensagem) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Erro");
		alert.setHeaderText("Erro na Validação da Questão.");
		alert.setContentText(mensagem);

		alert.showAndWait();
	}
	
	private void verificaAlternativa(CheckBox alternativa, ArrayList<String> respostas) {
		if (alternativa.isSelected()) {
			respostas.add(alternativa.getText());
		}
	}

	@FXML
	private void handleAnterior() {

		if (contQuestao > 0) {
			contQuestao--;
			verificaQuestao(conteudo.getQuestao(contQuestao));
			setQuestao(conteudo.getQuestao(contQuestao));
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
	
	@Override
	public void update(Idioma idioma) {
		setIdioma(idioma);
	}
}
