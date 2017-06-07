package br.com.oca.config;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.ResourceBundle;

import br.com.oca.model.enums.Certificacao;
import br.com.oca.model.enums.Idioma;

public class PerguntasConfig {
	private volatile static PerguntasConfig instance;

	private ResourceBundle bundle;
	private Certificacao nomeExame;
	private Locale local;

	private PerguntasConfig(Idioma idiomaEscolhido, Certificacao _nomeExame) throws MalformedURLException {
		nomeExame = _nomeExame;
		setConfigPerguntas(idiomaEscolhido);
	}

	public static PerguntasConfig getInstance(Idioma idiomaEscolhido, Certificacao nomeExame) {

		if (instance == null) {
			synchronized (PerguntasConfig.class) {
				if (instance == null) {
					try {
						instance = new PerguntasConfig(idiomaEscolhido, nomeExame);
					} catch (MalformedURLException e) {
						e.printStackTrace();
					}
				}
			}
		}

		return instance;
	}

	private void setConfigPerguntas(Idioma idiomaEscolhido) throws MalformedURLException {

		File file = new File("config/perguntas");
		URL[] urls = { file.toURI().toURL() };
		ClassLoader loader = new URLClassLoader(urls);
		bundle = ResourceBundle.getBundle("perguntas" + nomeExame.getNomeCurto(), idiomaEscolhido.getLocale(), loader);
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
		try {
			setConfigPerguntas(novoIdioma);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
