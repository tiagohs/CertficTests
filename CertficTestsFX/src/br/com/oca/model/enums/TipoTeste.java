/**
 * 
 * Copyright (c) 2016, CertificTests and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * 
 */
package br.com.oca.model.enums;

/**
 * 
 * A Classe TipoTeste representa os Tipos de Teste da Questão. A principio há 3:
 * 30, 60 e 90 Questões. Sendo o Ultimo o numero padrão de muitas certificações.
 * 
 * Classe <code>TipoTeste</code>
 * 
 * @author Tiago Henrique
 * @version 1.0
 *
 */
public enum TipoTeste {
	/** Os Tipos de Teste Disponiveis na Aplicação. */
	TESTE_30_QUESTOES("30 Questions", 50, 30), 
	TESTE_60_QUESTOES("60 Questions", 100, 60), 
	TESTE_90_QUESTOES("90 Questions", 150, 60);

	/** O nome extensivel do Tipo de Teste. */
	private String nome;

	/** A Duração Máxima do Teste, com base no numero máximo de questões. */
	private int tempoMaximoDuracao;

	/** O numero total de Questões do Tipo de Teste. */
	private int totalQuestoes;

	/**
	 * 
	 * Instância um novo Tipo de Teste, recebendo o seu nome; o tempo de
	 * duração, que é calculado com base no numero de questões totais; e o total
	 * de questões.
	 * 
	 * @param _nome
	 *            O nome extensivel do Tipo de Teste.
	 * @param _tempoMaximoDuracao
	 *            A Duração Máxima do Teste, com base no numero máximo de
	 *            questões.
	 * @param _totalQuestoes
	 *            O numero total de Questões do Tipo de Teste.
	 */
	private TipoTeste(String _nome, int _tempoMaximoDuracao, int _totalQuestoes) {
		nome = _nome;
		tempoMaximoDuracao = _tempoMaximoDuracao;
		totalQuestoes = _totalQuestoes;
	}

	/**
	 * 
	 * Obtém o nome extensivel do Tipo de Teste.
	 * 
	 * @return o nome extensivel do Tipo de Teste.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * 
	 * Obtém a Duração Máxima do Teste, com base no numero máximo de questões,
	 * em minutos.
	 * 
	 * @return a Duração Máxima do Teste, em Minutos.
	 */
	public int getTempoMaximoDuracao() {
		return tempoMaximoDuracao;
	}

	/**
	 * 
	 * Obtém o numero total de Questões do Tipo de Teste.
	 * 
	 * @return O numero total de Questões do Tipo de Teste.
	 */
	public int getTotalQuestoes() {
		return totalQuestoes;
	}

	@Override
	public String toString() {
		return getNome();
	}
}
