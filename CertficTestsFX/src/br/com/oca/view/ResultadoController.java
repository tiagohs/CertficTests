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
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ResultadoController {
	@FXML
	private Label labelSuaNota;
	@FXML
	private Label labelQuestoesCorretas;
	@FXML
	private Label labelTempoDecorrido;
	@FXML
	private Label labelDataRegistrada;
	@FXML
	private ScrollPane scrollEnunciado;
	@FXML
	private Label resultados;

	private Stage dialogHome;
	private Stage dialogStage;
	HomeController homeController;
	private MainApp mainApp;

	private ArrayList<Resposta> listaRespostas;
	private JanelasConfig label;
	private Idioma idioma;
	private Conteudo conteudo;
	private String stringResultados;
	private Integer numeroQuestao;
	private Calculos calculos;

	public ResultadoController() {
		stringResultados = "";
		numeroQuestao = 1;
		label = JanelasConfig.getInstance(idioma);
	}

	@FXML
	private void initialize() {

	}

	public void calcularResultados() {
		showResultados();
		registrarTentativa();
	}

	private void showResultados() {
		dialogStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				handleOk();
			}
		});

		labelSuaNota
				.setText(String.format("%.2f", calculos.getNota()) + label.getString("resultadosLabelDe") + "100.00");
		labelQuestoesCorretas.setText(String.format("%.2f", calculos.getNumeroQuestoesCorretas()) + label.getString("resultadosLabelDe")
				+ conteudo.getTotalQuestoes());
		labelTempoDecorrido.setText(calculos.getTempoDecorridoFormatado());
		labelDataRegistrada.setText(calculos.getDataRegistradaFormatado());

		if (listaRespostas.size() > 0) {
			for (int cont = 0; cont < conteudo.getTotalQuestoes().intValue(); cont++)
				setTextoRespostas(cont);
		} else {
			stringResultados = label.getString("resultadosLabelNenhumaQuestao");
		}

		resultados.setText(stringResultados);
		resultados.setWrapText(true);
	}

	private void setTextoRespostas(int count) {
		Questao questao = conteudo.getQuestao(count);
		Resposta resposta = listaRespostas.get(count);

		stringResultados += numeroQuestao + " - " + questao.getEnunciado() + "\n\n";

		if (questao.isVariasRespostas())
			exibirRespostas(questao, resposta, label.getString("resultadosLabelRespostasCorretas") + "\n\n",
					"\n" + label.getString("resultadosLabelSuasRespostas") + "\n\n");
		else
			exibirRespostas(questao, resposta, label.getString("resultadosLabelRespostaCorreta"),
					label.getString("resultadosLabelSuaResposta") + " ");

		numeroQuestao++;
	}

	private void exibirRespostas(Questao questao, Resposta resposta, String auxRespostaCorreta,
			String auxSuasRespostas) {

		stringResultados += auxRespostaCorreta;
		for (Entry<Character, String> entry : questao.getAlternativasCorretas().entrySet()) {
			stringResultados += entry.getValue() + "\n";
		}
		
		stringResultados += auxSuasRespostas;
		for (Entry<Character, String> entry : resposta.getListaRespostas().entrySet()) {
			stringResultados += entry.getValue() + "\n";
		}
		
		stringResultados += "\n";
	}

	private void registrarTentativa() {

		File file = new File(Tentativa.filename);
		ObjectOutputStream out = null;

		try {
			if (!file.exists())
				out = new ObjectOutputStream(new FileOutputStream(Tentativa.filename));
			else
				out = new AppendingObjectOutputStream(new FileOutputStream(Tentativa.filename, true));
			out.writeObject(new Tentativa(conteudo.getNomeTeste(), conteudo.getTipoTeste(), calculos.getNota(),
					calculos.getNumeroQuestoesCorretas(), calculos.getTempoDecorridoFormatado(), stringResultados,
					calculos.getDataRegistradaFormatado()));
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void handleOk() {
		mainApp.getTentativasRegistradas();
		homeController.setListaTentativas(mainApp.getListaTentativas());
		homeController.inicializaTabela();
		dialogHome.show();
		dialogStage.close();
	}

	public void setListaRespostas(ArrayList<Resposta> listaRespostas) {
		this.listaRespostas = listaRespostas;
	}

	public ArrayList<Resposta> getListaRespostas() {
		return listaRespostas;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setDialogHome(Stage dialogHome) {
		this.dialogHome = dialogHome;
	}

	public void setConteudo(Conteudo conteudo) {
		this.conteudo = conteudo;
	}

	public void setCalculos(Calculos calculos) {
		this.calculos = calculos;
	}

	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public void setHomeController(HomeController homeController) {
		this.homeController = homeController;
	}
}
