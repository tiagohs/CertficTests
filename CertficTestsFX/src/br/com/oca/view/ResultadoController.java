package br.com.oca.view;

import java.util.ArrayList;

import br.com.oca.model.Questao;
import br.com.oca.model.Resposta;
import br.com.oca.model.conteudo.Conteudo;
import br.com.oca.model.enums.Idioma;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class ResultadoController {
	@FXML
	private Label suaNota;
	@FXML
	private Label questoesCorretas;
	@FXML
	private ScrollPane scrollEnunciado;
	@FXML
	private Label resultados;
	
	private Stage dialogHome;
	private Stage dialogStage;
	
	private ArrayList<Resposta> listaRespostas;
	private Idioma idioma;
	private Conteudo conteudo;
	private String stringResultados;
	private Integer numeroQuestao;
	
	private Double nota;
	private Double numQuestoesCorretas;
	
	public ResultadoController() {
		stringResultados = "";
		numeroQuestao = 1;
	}

	@FXML
	private void initialize() {
		
	}
	
	public void calcularResultados() {
		numQuestoesCorretas = calcularNumeroQuestoesCorretas();
		nota = calcularNota();
		
		showResultados();
	}
	
	private void showResultados() {
		suaNota.setText(nota + " de 100.00");
		questoesCorretas.setText(numQuestoesCorretas + " de " + conteudo.getTotalQuestoes());
		
		for (int cont = 0; cont < conteudo.getTotalQuestoes(); cont++) 
			setTextoRespostas(cont);
		
		resultados.setWrapText(true);
		resultados.setText(stringResultados);
	}
	
	private void setTextoRespostas(int count) {
		
		stringResultados += numeroQuestao + " - " + conteudo.getQuestao(count).getEnunciado() + "\n\n";
		
		switch (listaRespostas.get(count).getTipoQuestao()) {
			case UNICA:
				stringResultados += "Resposta Correta: " + conteudo.getQuestao(count).getEnunciadoAlternativaCorreta() + "\n";
				if (isRespostaCorreta(count)) 
					stringResultados += "Sua Resposta: " + listaRespostas.get(count).getResposta() + "\n\n";
				else 
					stringResultados += "Sua Resposta: " + listaRespostas.get(count).getResposta() + "\n\n";
				break;
			case MULTIPLA:
				exibirMultiplasRespostas(conteudo.getQuestao(count), listaRespostas.get(count));
		}
		
		numeroQuestao++;
	}
	
	private void exibirMultiplasRespostas(Questao questao, Resposta resposta) {
		
		stringResultados += "Respostas Corretas: \n\n";
		for (int cont = 0; cont < questao.getAlternativasCorretas().size(); cont++) {
			stringResultados += questao.getAlternativasCorretas().get(cont) + "\n";
		}
		
		stringResultados += "\nSuas Respostas: \n\n";
		for (int cont = 0; cont < resposta.getRespostas().size(); cont++) {
			if (questao.getAlternativasCorretas().contains(resposta.getRespostas().get(cont))) {
				stringResultados += resposta.getRespostas().get(cont) + "\n";
			} else {
				stringResultados += resposta.getRespostas().get(cont) + "\n";
			}
		}
		
		stringResultados += "\n";
	}
	
	private Double calcularNumeroQuestoesCorretas() {
		Double numeroQuestoesCorretas = 0.0;

		for (int count = 0; count < conteudo.getTotalQuestoes(); count++) {
			
			switch (listaRespostas.get(count).getTipoQuestao()) {
				case UNICA:
					if (isRespostaCorreta(count))
						numeroQuestoesCorretas++;
					break;
				case MULTIPLA:
					numeroQuestoesCorretas += totalAcertoQuestao(count);
			}
			
		}

		return numeroQuestoesCorretas;
	}
	
	private boolean isRespostaCorreta(int count) {
		return conteudo.getQuestao(count).getEnunciadoAlternativaCorreta().equals(listaRespostas.get(count).getResposta());
	}
	
	private Double totalAcertoQuestao(int count) {
		Double soma = 0.0;
		
		for (String resp : listaRespostas.get(count).getRespostas()) {
			if (conteudo.getQuestao(count).getListaAlternativas().containsValue(resp)) 
				soma++;
		}
		
		Double temp = (Double) soma / conteudo.getQuestao(count).getListaAlternativas().size();
		return temp;
	}
	
	private Double calcularNota() {
		double media = (double) numQuestoesCorretas / conteudo.getTotalQuestoes();
		return media * 100;
	}
	
	@FXML
	private void handleOk() {
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
	
	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}
}
