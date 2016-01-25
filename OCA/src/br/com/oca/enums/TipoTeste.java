package br.com.oca.enums;

public enum TipoTeste {
	TESTE_1("30 Questions"), TESTE_2("60 Questions"), TESTE_3("90 Questions");
	
	private String nome;
	
	private TipoTeste(String _nome) {
		nome = _nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	@Override
	public String toString() {
		return getNome();
	}
}
