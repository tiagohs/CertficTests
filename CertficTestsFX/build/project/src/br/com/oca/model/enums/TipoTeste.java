/**
 * 
 * Copyright (c) 2016, CertificTests and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * 
 */
package br.com.oca.model.enums;

/**
 * 
 * A Classe TipoTeste representa os Tipos de Teste da Quest�o. A principio h� 3:
 * 30, 60 e 90 Quest�es. Sendo o Ultimo o numero padr�o de muitas certifica��es.
 * 
 * Classe <code>TipoTeste</code>
 * 
 * @author Tiago Henrique
 * @version 1.0
 *
 */
public enum TipoTeste {
	/** Os Tipos de Teste Disponiveis na Aplica��o. */
	TESTE_30_QUESTOES("30 Questions", 50, 30), 
	TESTE_60_QUESTOES("60 Questions", 100, 60), 
	TESTE_90_QUESTOES("90 Questions", 150, 60);

	/** O nome extensivel do Tipo de Teste. */
	private String nome;

	/** A Dura��o M�xima do Teste, com base no numero m�ximo de quest�es. */
	private int tempoMaximoDuracao;

	/** O numero total de Quest�es do Tipo de Teste. */
	private int totalQuestoes;

	/**
	 * 
	 * Inst�ncia um novo Tipo de Teste, recebendo o seu nome; o tempo de
	 * dura��o, que � calculado com base no numero de quest�es totais; e o total
	 * de quest�es.
	 * 
	 * @param _nome
	 *            O nome extensivel do Tipo de Teste.
	 * @param _tempoMaximoDuracao
	 *            A Dura��o M�xima do Teste, com base no numero m�ximo de
	 *            quest�es.
	 * @param _totalQuestoes
	 *            O numero total de Quest�es do Tipo de Teste.
	 */
	private TipoTeste(String _nome, int _tempoMaximoDuracao, int _totalQuestoes) {
		nome = _nome;
		tempoMaximoDuracao = _tempoMaximoDuracao;
		totalQuestoes = _totalQuestoes;
	}

	/**
	 * 
	 * Obt�m o nome extensivel do Tipo de Teste.
	 * 
	 * @return o nome extensivel do Tipo de Teste.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * 
	 * Obt�m a Dura��o M�xima do Teste, com base no numero m�ximo de quest�es,
	 * em minutos.
	 * 
	 * @return a Dura��o M�xima do Teste, em Minutos.
	 */
	public int getTempoMaximoDuracao() {
		return tempoMaximoDuracao;
	}

	/**
	 * 
	 * Obt�m o numero total de Quest�es do Tipo de Teste.
	 * 
	 * @return O numero total de Quest�es do Tipo de Teste.
	 */
	public int getTotalQuestoes() {
		return totalQuestoes;
	}

	@Override
	public String toString() {
		return getNome();
	}
}
