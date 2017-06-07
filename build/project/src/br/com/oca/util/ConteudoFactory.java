/**
 * 
 * Copyright (c) 2016, CertificTests and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * 
 */
package br.com.oca.util;

import br.com.oca.model.conteudo.Conteudo;
import br.com.oca.model.conteudo.OCAJava7;
import br.com.oca.model.conteudo.OCPJava7;
import br.com.oca.model.enums.Certificacao;
import br.com.oca.model.enums.Idioma;
import br.com.oca.model.enums.TipoTeste;

/**
 * 
 * A Classe ConteudoFactory com base no design pattners Factory, para a criação
 * de Classes auxiliares a Classe Conteudo. Elas são usadas para preenxer as
 * listas contendo as questões.
 * 
 * Classe <code>ConteudoFactory</code>
 * 
 * @author Tiago Henrique
 * @version 1.0
 *
 */
public class ConteudoFactory {

	/**
	 * Possui um Construtor Private para se assegurar que não se criará um
	 * objeto dessa classe.
	 */
	private ConteudoFactory() {

	}

	/**
	 * 
	 * Unico método public da classe, se obtém uma filha de Conteudo, passando o
	 * exame, o idioma que se deseja que tenha as questões e o tipo do Teste
	 * (30, 60 ou 90 Questões).
	 * 
	 * @param exame
	 *            O Enumero refenciando o Exame Escolhido (OCA, OCP..).
	 * @param idioma
	 *            O Idioma que terá as questões (EN, PT_BR..).
	 * @param tipoTeste
	 *            O Tipo de Teste (30, 60 ou 90 Questões).
	 * @return a subclasse de Conteudo Desejada.
	 */
	public static Conteudo getConteudo(Certificacao exame, Idioma idioma, TipoTeste tipoTeste) {

		switch (exame) {
			case OCA_JAVA7:
				return OCAJava7.getInstance(exame, idioma, tipoTeste);
			case OCP_JAVA7:
				return OCPJava7.getInstance(exame, idioma, tipoTeste);
			default:
				return null;
		}
	}
}
