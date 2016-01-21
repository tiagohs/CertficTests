package br.com.oca.i18n.perguntas;

import java.util.Locale;
import java.util.ResourceBundle;

public class PerguntasSource {
	private ResourceBundle bundle;
	private Locale local;

	public PerguntasSource(String idiomaEscolhido, String nomeExame) {
		local = verificaIdioma(idiomaEscolhido);
		bundle = ResourceBundle.getBundle("br.com.oca.i18n.perguntas/perguntas" + nomeExame, local);
	}

	private Locale verificaIdioma(String idiomaEscolhido) {

		switch (idiomaEscolhido) {
			case "Inglês":
				return new Locale("en", "US");
			default:
				return new Locale("en", "en");
		}
	}
	
	public String getString(String key) {
		return bundle.getString(key);
	}
	
	public ResourceBundle getBundle() {
		return bundle;
	}
	
}
