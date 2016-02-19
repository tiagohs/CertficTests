package br.com.oca.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import br.com.oca.MainApp;
import br.com.oca.model.Calculos;
import br.com.oca.model.Questao;
import br.com.oca.model.Resposta;
import br.com.oca.model.Tentativa;
import br.com.oca.model.conteudo.Conteudo;
import br.com.oca.model.enums.Idioma;
import br.com.oca.model.i18n.janelas.JanelasSource;
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
	private JanelasSource label;
	private Idioma idioma;
	private Conteudo conteudo;
	private String stringResultados;
	private Integer numeroQuestao;
	private Calculos calculos;

	public ResultadoController() {
		stringResultados = "";
		numeroQuestao = 1;
		label = JanelasSource.getInstance(idioma);
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
		
		labelSuaNota.setText(calculos.getNotaDoubleValue() + label.getString("resultadosLabelDe") + "100.00");
		labelQuestoesCorretas.setText(calculos.getNumeroQuestoesCorretasDoubleValue() + label.getString("resultadosLabelDe")
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

		stringResultados += numeroQuestao + " - " + conteudo.getQuestao(count).getEnunciado() + "\n\n";

		switch (listaRespostas.get(count).getTipoQuestao()) {
		case UNICA:
			stringResultados += label.getString("resultadosLabelRespostaCorreta")
					+ conteudo.getQuestao(count).getEnunciadoAlternativaCorreta() + "\n";
			if (calculos.isRespostaCorreta(count)) {
				stringResultados += label.getString("resultadosLabelSuaResposta") + " "
						+ listaRespostas.get(count).getResposta() + "\n\n";
			}

			else {
				stringResultados += label.getString("resultadosLabelSuaResposta") + " "
						+ listaRespostas.get(count).getResposta() + "\n\n";
			}

			break;
		case MULTIPLA:
			exibirMultiplasRespostas(conteudo.getQuestao(count), listaRespostas.get(count));
		}

		numeroQuestao++;
	}

	private void exibirMultiplasRespostas(Questao questao, Resposta resposta) {

		stringResultados += label.getString("resultadosLabelRespostasCorretas") + "\n\n";
		for (int cont = 0; cont < questao.getAlternativasCorretas().size(); cont++) {
			stringResultados += questao.getAlternativasCorretas().get(cont) + "\n";
		}

		stringResultados += "\n" + label.getString("resultadosLabelSuasRespostas") + "\n\n";
		for (int cont = 0; cont < resposta.getRespostas().size(); cont++) {
			if (questao.getAlternativasCorretas().contains(resposta.getRespostas().get(cont))) {
				stringResultados += resposta.getRespostas().get(cont) + "\n";
			} else {
				stringResultados += resposta.getRespostas().get(cont) + "\n";
			}
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
			out.writeObject(new Tentativa(conteudo.getNomeTeste(), conteudo.getTipoTeste(), calculos.getNotaDoubleValue(),
					calculos.getNumeroQuestoesCorretasDoubleValue(), calculos.getTempoDecorridoFormatado(), stringResultados,
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
