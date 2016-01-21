package br.com.oca.i18n.janelas;

import java.util.Locale;
import java.util.ResourceBundle;

public class JanelasSource {
	private ResourceBundle bundle;
	private Locale local;
	
	public JanelasSource(String idiomaEscolhido) {
		local = verificaIdioma(idiomaEscolhido);
		bundle = ResourceBundle.getBundle("br.com.oca.i18n.janelas/janelas", local);
	}

	private Locale verificaIdioma(String idiomaEscolhido) {

		switch (idiomaEscolhido) {
			case "Ingl�s (English)":
				return new Locale("en", "US");
			case "Portugu�s Brasil  (Portuguese)":
				return new Locale("pt", "BR");
			default:
				return new Locale("pt", "BR");
		}
	}
	
	public String getString(String key) {
		return bundle.getString(key);
	}
}
