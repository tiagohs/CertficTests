/**
 * 
 * Copyright (c) 2016, CertificTests and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * 
 */
package br.com.oca.model.enums;

import java.util.Locale;

/**
 * Enumerados com os <i>Idiomas</i>.
 * 
 * Classe <code>Idioma</code>
 * 
 * @author Tiago Henrique
 * @version 1.0
 *
 */
public enum Idioma {
	Portugues("Português", new Locale("pt", "BR")), Ingles("Inglês", new Locale("en", "US"));

	/** O nome em extenso do <code>Idioma</code>. */
	private String nome;

	/** O <i>Locale</i>, que representa a Região geográfica do <code>Idioma</code> escolhido. */
	private Locale locale;

	/**
	 * 
	 * Construtor padrão do Enumerado <code>Idioma</code>.
	 * 
	 * @param _nome
	 *            O <i>nome</i> em extenso do <code>Idioma</code>.
	 * @param _locale
	 *            O <i>Locale</i>, que representa a Região geográfica do <code>Idioma</code>
	 *            escolhido.
	 */
	private Idioma(String _nome, Locale _locale) {
		nome = _nome;
		locale = _locale;
	}

	/**
	 * Obtém o <i>nome</i> por extenso do <code>Idioma</code>.
	 * 
	 * @return Retorna o Nome do <code>Idioma</code>.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * 
	 * Obtém o <i>Locale</i> do <code>Idioma.
	 * 
	 * @return Retorna o <i>Locale</i>.
	 */
	public Locale getLocale() {
		return locale;
	}

	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return getNome();
	}
}
