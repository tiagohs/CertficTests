package br.com.oca.util;

import br.com.oca.model.conteudo.Conteudo;
import br.com.oca.model.conteudo.OCAJava7;
import br.com.oca.model.conteudo.OCPJava7;
import br.com.oca.model.enums.Certificacao;
import br.com.oca.model.enums.Idioma;
import br.com.oca.model.enums.TipoTeste;

public class ConteudoFactory {

	public static Conteudo getConteudo(Certificacao nomeExame, Idioma idioma, TipoTeste tipoTeste) {

		switch (nomeExame) {
			case OCA_JAVA7:
				return OCAJava7.getInstance(nomeExame, idioma, tipoTeste);
			case OCP_JAVA7:
				System.out.println("Entrou");
				return OCPJava7.getInstance(nomeExame, idioma, tipoTeste);
			default:
				return null;
		}
	}
}
