package br.com.oca.conteudo;

public abstract class Conteudo {

	protected String[][] alternativas;
	protected String[][] perguntas;
	private Integer numeroMaximoQuestoes;
	
	protected Conteudo(int _numeroMaximoQuestoes) {
		numeroMaximoQuestoes = _numeroMaximoQuestoes;
		alternativas = new String[numeroMaximoQuestoes][5];
		perguntas = new String[numeroMaximoQuestoes][2];
		
		preenxerAlternativas();
		preenxerPerguntas();
	}
	
	public String[][] getAlternativas() {
		return alternativas;
	}

	public String[][] getPerguntas() {
		return perguntas;
	}
	
	public Integer getNumeroMaximoQuestoes() {
		return numeroMaximoQuestoes;
	}
	
	protected abstract void preenxerAlternativas();
	protected abstract void preenxerPerguntas();
}
