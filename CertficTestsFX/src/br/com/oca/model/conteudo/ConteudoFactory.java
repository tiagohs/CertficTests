package br.com.oca.model.conteudo;

import br.com.oca.model.enums.Certificacao;
import br.com.oca.model.enums.Idioma;
import br.com.oca.model.enums.TipoTeste;

public class ConteudoFactory {

	public static Conteudo getConteudo(Certificacao nomeExame, Idioma idioma, TipoTeste tipoTeste) {

		switch (nomeExame) {
			case OCA_JAVA7:
				return OCAJava7.getInstance(nomeExame, idioma, tipoTeste);
//			case OCP_JAVA7:
//				return OCPJava7.getInstance(nomeExame, idioma, tipoTeste);
			default:
				return null;
		}
	}
}
