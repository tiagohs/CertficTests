/**
 * 
 * Copyright (c) 2016, CertificTests and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * 
 */
package br.com.oca.model.enums;

/**
 * 
 * A Classe Certificacao representa os Exames que poderam ser feitos na
 * Aplicação, como OCA e OCP.
 * 
 * Classe <code>Certificacao</code>
 * 
 * @author Tiago Henrique
 * @version 1.0
 *
 */
public enum Certificacao {
	/** Exames disponivels na Aplicação. */
	OCA_JAVA7("Oracle Certified Associate, Java SE 7 Programmer", "OCA_JAVA7"), 
	OCP_JAVA7("Oracle Certified Professional, Java SE 7 Programmer", "OCP_JAVA7");

	/** Nome do Exame completo, por extensivel. */
	private String nomeExtenso;

	/** Nome curto do Exame, como uma abreviação, por exemplo. */
	private String nomeCurto;

	/**
	 * 
	 * Construtor Padrão de um Enumerado, recebendo o nome completo e curto de
	 * um exame.
	 * 
	 * @param _nomeExtenso
	 *            Nome do Exame completo, por extensivel.
	 * @param _nomeCurto
	 *            Nome curto do Exame, como uma abreviação, por exemplo.
	 */
	private Certificacao(String _nomeExtenso, String _nomeCurto) {
		nomeExtenso = _nomeExtenso;
		nomeCurto = _nomeCurto;
	}

	/**
	 * 
	 * Obtém o Nome do Exame completo, por extensivel.
	 * 
	 * @return o Nome do Exame completo, por extensivel.
	 */
	public String getNomeExtenso() {
		return nomeExtenso;
	}

	/**
	 * 
	 * Obtém o Nome curto do Exame, como uma abreviação, por exemplo.
	 * 
	 * @return o Nome curto do Exame
	 */
	public String getNomeCurto() {
		return nomeCurto;
	}

	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return getNomeExtenso();
	}
}
