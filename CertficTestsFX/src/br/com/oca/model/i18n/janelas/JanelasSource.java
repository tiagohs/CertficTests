package br.com.oca.model.i18n.janelas;

import java.util.Locale;
import java.util.ResourceBundle;

import br.com.oca.model.enums.Idioma;

public class JanelasSource {
	private volatile static JanelasSource instance;
	
	private ResourceBundle bundle;
	private Locale local;
	
	private JanelasSource(Idioma idiomaEscolhido) {
		bundle = ResourceBundle.getBundle("br.com.oca.model.i18n.janelas/janelas", idiomaEscolhido.getLocale());
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

	public String getString(String key) {
		return bundle.getString(key);
	}
	
	public ResourceBundle getBundle() {
		return bundle;
	}
	
	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}
	
	public void setNovoIdioma(Idioma novoIdioma) {
		bundle = ResourceBundle.getBundle("br.com.oca.model.i18n.janelas/janelas", novoIdioma.getLocale());
	}
}
