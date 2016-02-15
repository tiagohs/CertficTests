package br.com.oca.model.enums;

public enum TipoTeste {
	TESTE_1("30 Questions", 50, 30), TESTE_2("60 Questions", 100, 60), TESTE_3("90 Questions", 150, 60);
	
	private String nome;
	private int tempoMaximoDuracao;
	private int totalQuestoes;
	
	private TipoTeste(String _nome, int _tempoMaximoDuracao, int _totalQuestoes) {
		nome = _nome;
		tempoMaximoDuracao = _tempoMaximoDuracao;
		totalQuestoes = _totalQuestoes;
	}
	
	public String getNome() {
		return nome;
	}
	
	public int getTempoMaximoDuracao() {
		return tempoMaximoDuracao;
	}
	
	public int getTotalQuestoes() {
		return totalQuestoes;
	}
	
	@Override
	public String toString() {
		return getNome();
	}
}
