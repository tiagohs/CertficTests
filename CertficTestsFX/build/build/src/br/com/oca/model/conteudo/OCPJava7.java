package br.com.oca.model.conteudo;

import br.com.oca.model.enums.Certificacao;
import br.com.oca.model.enums.Idioma;
import br.com.oca.model.enums.TipoTeste;

public class OCPJava7 extends Conteudo {
	private volatile static OCPJava7 instance;
	
	protected OCPJava7(Certificacao nomeTeste, Idioma idiomaTeste, TipoTeste tipoTeste) {
		super(nomeTeste, idiomaTeste, tipoTeste);
	}
	
	public static OCPJava7 getInstance(Certificacao nomeTeste, Idioma idiomaTeste, TipoTeste tipoTeste) {

		if (instance == null) {
			synchronized (OCPJava7.class) {
				if (instance == null) {
					instance = new OCPJava7(nomeTeste, idiomaTeste, tipoTeste);
				}
			}
		}

		return instance;
	}
	
	@Override
	protected void preenxerQuestoes() {
		
	}

}
