package br.com.oca.i18n.perguntas;

import java.util.Locale;
import java.util.ResourceBundle;

public class PerguntasSource {
	private ResourceBundle bundle;
	private String nomeExame;
	private Locale local;

	public PerguntasSource(String idiomaEscolhido) {
		local = verificaIdioma(idiomaEscolhido);
		bundle = ResourceBundle.getBundle("br.com.oca.i18n.perguntas/perguntasOCA", local);
	}

	private Locale verificaIdioma(String idiomaEscolhido) {

		switch (idiomaEscolhido) {
			case "Inglês":
				return new Locale("en", "US");
			default:
				return new Locale("en", "en");
		}
	}
	
	public static void main(String[] args) {
		new PerguntasSource("Inglês");
	}
}
