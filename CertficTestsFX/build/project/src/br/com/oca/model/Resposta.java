/**
 * 
 * Copyright (c) 2016, CertificTests and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * 
 */
package br.com.oca.model;

import java.util.HashMap;

/**
 * 
 * A Classe Resposta representa uma Resposta do Usuário para cada Questão que
 * ele resolve. Ele obém o tipo da questão junto com o enunciado da questão e a
 * o enunciado da resposta, se for de multipla escolha, um ArrayList com o
 * enunciado das repostas.
 * 
 * Classe <code>Resposta</code>
 * 
 * @author Tiago Henrique
 * @version 1.0
 *
 */

public class Resposta {
	/** O Enunciado da Questão. */
	private String enunciadoQuestao;
	
	/**
	 * Lista com sds Alternativas Corretas, caso a Questão for de
	 * Multipla Escolha.
	 */
	private HashMap<Character, String> listaRespostas;

	/**
	 * 
	 * Instância uma nova Resposta. Passando somente o Enunciado. Usado
	 * normalmente para se usar no método equals.
	 * 
	 * @param _enunciadoQuestao
	 *            O Enunciado da Questão.
	 */
	public Resposta(String _enunciadoQuestao) {
		this(_enunciadoQuestao, null);
	}

	/**
	 * 
	 * Instância uma nova Resposta.
	 * 
	 * @param _enunciadoQuestao
	 *            O Enunciado da Questão.
	 * @param _enunciadoResposta
	 *            O Enunciado da Resposta.
	 * @param _enunciadoRespostas
	 *            Lista com os Enunciados das Alternativas Corretas.
	 * @param _tipoQuestao
	 *            O Tipo de Questão (Uma ou Multipla Alternativas).
	 */
	public Resposta(String _enunciadoQuestao, HashMap<Character, String> _listaRespostas) {
		enunciadoQuestao = _enunciadoQuestao;
		listaRespostas = _listaRespostas;
	}

	/**
	 * 
	 * Obtém o Enunciado da Questão.
	 * 
	 * @return O Enunciado da Questão.
	 */
	public String getEnunciadoQuestao() {
		return enunciadoQuestao;
	}

	/**
	 * 
	 * Se Define o novo Enunciado da Questão.
	 * 
	 * @param enunciado
	 *            O novo Enunciado da Questão.
	 */
	public void setEnunciadoQuestao(String enunciado) {
		this.enunciadoQuestao = enunciado;
	}

	/**
	 * 
	 * Obtém a lista com a lista de respostas, caso seja uma questão de
	 * multipla escolha.
	 * 
	 * @return A lista com a lista de respostas
	 */
	public HashMap<Character, String> getListaRespostas() {
		return listaRespostas;
	}
	
	/**
	 * 
	 * Se Define a nova lista com a lista de respostas.
	 * 
	 * @param respostas
	 *            A nova lista com a lista de respostas.
	 */
	public void setListaRespostas(HashMap<Character, String> listaRespostas) {
		this.listaRespostas = listaRespostas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {

		if (obj instanceof Resposta) {
			Resposta resposta = (Resposta) obj;
			return this.getEnunciadoQuestao().equals(resposta.getEnunciadoQuestao());
		}
		return false;
	}
}
