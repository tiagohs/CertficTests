package br.com.oca.model.enums;

public enum TipoTeste {
	TESTE_1("30 Questions", 50), TESTE_2("60 Questions", 100), TESTE_3("90 Questions", 150);
	
	private String nome;
	private int tempoMaximoDuracao;
	
	private TipoTeste(String _nome, int _tempoMaximoDuracao) {
		nome = _nome;
		tempoMaximoDuracao = _tempoMaximoDuracao;
	}
	
	public String getNome() {
		return nome;
	}
	
	public int getTempoMaximoDuracao() {
		return tempoMaximoDuracao;
	}
	
	@Override
	public String toString() {
		return getNome();
	}
}
