package br.com.oca.model.i18n.janelas;

import java.util.Locale;
import java.util.ResourceBundle;

import br.com.oca.model.enums.Idioma;

public class JanelasSource {
	private volatile static JanelasSource instance;
	
	private ResourceBundle bundle;
	private Locale local;
	
	private JanelasSource(Idioma idiomaEscolhido) {
		local = verificaIdioma(idiomaEscolhido);
		bundle = ResourceBundle.getBundle("br.com.oca.i18n.janelas/janelas", local);
	}
	
	public static JanelasSource getInstance(Idioma idiomaEscolhido) {

		if (instance == null) {
			synchronized (JanelasSource.class) {
				if (instance == null) {
					instance = new JanelasSource(idiomaEscolhido);
				}
			}
		}

		return instance;
	}

	private Locale verificaIdioma(Idioma idiomaEscolhido) {

		switch (idiomaEscolhido) {
			case Ingles:
				return new Locale("en", "US");
			case Portugues:
				return new Locale("pt", "BR");
			default:
				return new Locale("pt", "BR");
		}
	}
	
	public String getString(String key) {
		return bundle.getString(key);
	}
}
