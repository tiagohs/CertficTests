package br.com.oca.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import br.com.oca.model.conteudo.Conteudo;

public class Calculos {
	private Conteudo conteudo;
	private ArrayList<Resposta> listaRespostas;
	private Double nota;
	private Double numeroQuestoesCorretas;
	private Calendar tempoRegistrado;
	
	public Calculos(Conteudo _conteudo, ArrayList<Resposta> _listaRespostas, Calendar _tempoRegistrado) {
		nota = 0.0;
		numeroQuestoesCorretas = 0.0;
		conteudo =  _conteudo;
		listaRespostas = _listaRespostas;
		tempoRegistrado = _tempoRegistrado;
		
		calcularNumeroQuestoesCorretas();
		calcularNota();
	}
	
	private void calcularNota() {
		double media = (double) numeroQuestoesCorretas / conteudo.getTotalQuestoes();
		nota = media * 100;
	}
	
	private void calcularNumeroQuestoesCorretas() {
		
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

	}
	
	private Double totalAcertoQuestao(int count) {
		Double soma = 0.0;
		
		for (String resp : listaRespostas.get(count).getRespostas()) {
			if (conteudo.getQuestao(count).getListaAlternativas().containsValue(resp)) 
				soma++;
		}
		
		Double temp = (Double) soma / listaRespostas.get(count).getRespostas().size();
		return temp;
	}
	
	public boolean isRespostaCorreta(int count) {
		return conteudo.getQuestao(count).getEnunciadoAlternativaCorreta().equals(listaRespostas.get(count).getResposta());
	}
	
	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	public Double getNumeroQuestoesCorretas() {
		return numeroQuestoesCorretas;
	}

	public void setNumeroQuestoesCorretas(Double numeroQuestoesCorretas) {
		this.numeroQuestoesCorretas = numeroQuestoesCorretas;
	}

	public String getTempoDecorridoFormatado() {
		return new SimpleDateFormat("HH:mm:ss").format(tempoRegistrado.getTime());
	}
	
	public Calendar getTempoDecorrido() {
		return tempoRegistrado;
	}

	public void setTempoDecorrido(Calendar tempoDecorrido) {
		this.tempoRegistrado = tempoDecorrido;
	}

}
