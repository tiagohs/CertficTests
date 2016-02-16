package br.com.oca.model.i18n.perguntas;

import java.util.Locale;
import java.util.ResourceBundle;

import br.com.oca.model.enums.Certificacao;
import br.com.oca.model.enums.Idioma;

public class PerguntasSource {
	private volatile static PerguntasSource instance;
	
	private ResourceBundle bundle;
	private Certificacao nomeExame;
	private Locale local;
	
	private PerguntasSource(Idioma idiomaEscolhido, Certificacao _nomeExame) {
		nomeExame = _nomeExame;
		bundle = ResourceBundle.getBundle("br.com.oca.model.i18n.perguntas/perguntas" + nomeExame.getNomeCurto(), idiomaEscolhido.getLocale());
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
	
	public String getString(String key) {
		return bundle.getString(key);
	}
	
	public ResourceBundle getBundle() {
		return bundle;
	}
	
	public Locale getLocal() {
		return local;
	}
	
	public Certificacao getNomeExame() {
		return nomeExame;
	}
	
	public void setNovoIdioma(Idioma novoIdioma) {
		bundle = ResourceBundle.getBundle("br.com.oca.model.i18n.perguntas/perguntas" + nomeExame.getNomeCurto(), novoIdioma.getLocale());
	}
	
}
