package br.com.oca.model.enums;

import java.util.Locale;

public enum Idioma {
	Portugues("Português", new Locale("pt", "BR")), Ingles("Inglês", new Locale("en", "US"));
	
	private String nome;
	private Locale locale;
	
	private Idioma(String _nome, Locale _locale) {
		nome = _nome;
		locale = _locale;
	}
	
	public String getNome() {
		return nome;
	}
	
	public Locale getLocale() {
		return locale;
	}
	
	@Override
	public String toString() {
		return getNome();
	}
}
