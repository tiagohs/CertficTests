package br.com.oca.model.enums;

public enum Idioma {
	Portugues("Portugu�s"), Ingles("Ingl�s");
	
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
