package br.com.oca.config;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ResourceBundle;

import br.com.oca.model.enums.Idioma;

public class JanelasConfig {
	private volatile static JanelasConfig instance;

	private ResourceBundle bundle;
	
	private JanelasConfig(Idioma idiomaEscolhido) throws MalformedURLException {
		setConfigJanelas(idiomaEscolhido);
	}
	
	public static JanelasConfig getInstance(Idioma idiomaEscolhido) {

		if (instance == null) {
			synchronized (JanelasConfig.class) {
				if (instance == null) {
					try {
						instance = new JanelasConfig(idiomaEscolhido);
					} catch (MalformedURLException e) {
						e.printStackTrace();
					}
				}
			}
		}

		return instance;
	}
	
	private void setConfigJanelas(Idioma idiomaEscolhido) throws MalformedURLException {
		
		File file = new File("config/janelas");
		URL[] urls = {file.toURI().toURL()};
		ClassLoader loader = new URLClassLoader(urls);
		bundle = ResourceBundle.getBundle("janelas", idiomaEscolhido.getLocale(), loader);
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
		try {
			setConfigJanelas(novoIdioma);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
