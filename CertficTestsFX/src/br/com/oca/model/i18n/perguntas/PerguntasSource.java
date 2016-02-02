package br.com.oca.model.i18n.perguntas;

import java.util.Locale;
import java.util.ResourceBundle;

import br.com.oca.model.enums.Certificacao;
import br.com.oca.model.enums.Idioma;

public class PerguntasSource {
	private volatile static PerguntasSource instance;
	
	private ResourceBundle bundle;
	private Locale local;
	
	private PerguntasSource(Idioma idiomaEscolhido, Certificacao nomeExame) {
		local = verificaIdioma(idiomaEscolhido);
		bundle = ResourceBundle.getBundle("br.com.oca.model.i18n.perguntas/perguntas" + nomeExame, local);
	}
	
	public static PerguntasSource getInstance(Idioma idiomaEscolhido, Certificacao nomeExame) {

		if (instance == null) {
			synchronized (PerguntasSource.class) {
				if (instance == null) {
					instance = new PerguntasSource(idiomaEscolhido, nomeExame);
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
