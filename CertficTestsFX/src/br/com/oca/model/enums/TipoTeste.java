package br.com.oca.model.enums;

public enum TipoTeste {
	TESTE_30_QUESTOES("30 Questions", 50, 30),
	TESTE_60_QUESTOES("60 Questions", 100, 60), 
	TESTE_90_QUESTOES("90 Questions", 150, 60);
	
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
