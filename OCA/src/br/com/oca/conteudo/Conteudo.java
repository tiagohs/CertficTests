package br.com.oca.conteudo;

import java.util.ArrayList;

import br.com.oca.i18n.perguntas.PerguntasSource;

public abstract class Conteudo {
	protected ArrayList<Questao> listaQuestoes;
	protected PerguntasSource perguntasSource;
	
	protected String[][] alternativas;
	protected String[][] perguntas;
	private Integer numeroMaximoQuestoes;
	
	protected Conteudo(int _numeroMaximoQuestoes, String nomeTeste, String idiomaTeste) {
		numeroMaximoQuestoes = _numeroMaximoQuestoes;
		listaQuestoes = new ArrayList<Questao>();
		perguntasSource = new PerguntasSource(idiomaTeste, nomeTeste);
		
		alternativas = new String[numeroMaximoQuestoes][5];
		perguntas = new String[numeroMaximoQuestoes][2];
		
		preenxerQuestoes();
	}
	
	public ArrayList<Questao> getListaQuestoes() {
		return listaQuestoes;
	}

	public void setListaQuestoes(ArrayList<Questao> listaQuestoes) {
		this.listaQuestoes = listaQuestoes;
	}

	public String[][] getAlternativas() {
		return alternativas;
	}

	public String[][] getPerguntas() {
		return perguntas;
	}
	
	public Questao getQuestao(int numeroQuestao) {
		return listaQuestoes.get(numeroQuestao);
	}
	
	public Integer getNumeroMaximoQuestoes() {
		return numeroMaximoQuestoes;
	}
	
	public boolean isEmpty() {
		return listaQuestoes.isEmpty();
	}
	
	protected abstract void preenxerQuestoes();
}
