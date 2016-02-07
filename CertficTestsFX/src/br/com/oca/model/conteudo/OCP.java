package br.com.oca.model.conteudo;

import br.com.oca.model.enums.Certificacao;
import br.com.oca.model.enums.Idioma;
import br.com.oca.model.enums.TipoTeste;

public class OCP extends Conteudo {
	private volatile static OCP instance;
	
	protected OCP(Certificacao nomeTeste, Idioma idiomaTeste, TipoTeste tipoTeste) {
		super(nomeTeste, idiomaTeste, tipoTeste);
	}
	
	public static OCP getInstance(Certificacao nomeTeste, Idioma idiomaTeste, TipoTeste tipoTeste) {

		if (instance == null) {
			synchronized (OCP.class) {
				if (instance == null) {
					instance = new OCP(nomeTeste, idiomaTeste, tipoTeste);
				}
			}
		}

		return instance;
	}
	
	@Override
	protected void preenxerQuestoes() {
		
	}

}
