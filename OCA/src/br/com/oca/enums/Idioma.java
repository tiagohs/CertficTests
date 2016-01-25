package br.com.oca.enums;

public enum Idioma {
	Portugues("Português"), Ingles("Inglês");
	
	private String nome;
	
	private Idioma(String _nome) {
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
