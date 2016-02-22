/**
 * 
 * Copyright (c) 2016, CertificTests and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * 
 */
package br.com.oca.model;

import java.util.ArrayList;

import br.com.oca.model.enums.TipoQuestao;

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
	/** O Tipo de Questão (Uma ou Multipla Alternativas). */
	private TipoQuestao tipoQuestao;

	/** O Enunciado da Questão. */
	private String enunciadoQuestao;

	/** O Enunciado da Resposta. */
	private String enunciadoResposta;

	/**
	 * Lista com os Enunciados das Alternativas Corretas, caso a Questão for de
	 * Multipla Escolha.
	 */
	private ArrayList<String> enunciadoRespostas;

	/**
	 * 
	 * Instância uma nova Resposta. Se Instância uma Resposta onde a Questão é
	 * de Multipla Escolha.
	 * 
	 * @param enunciado
	 *            O Enunciado da Questão.
	 * @param respostas
	 *            Lista com os Enunciados das Alternativas Corretas.
	 */
	public Resposta(String enunciado, ArrayList<String> respostas) {
		this(enunciado, null, respostas, TipoQuestao.MULTIPLAS_ALTERNATIVAS);
	}

	/**
	 * 
	 * Instância uma nova Resposta. Se Instância uma Resposta onde a Questão
	 * contém somente uma alternativa.
	 * 
	 * @param _enunciadoQuestao
	 *            O Enunciado da Questão.
	 * @param _enunciadoResposta
	 *            O Enunciado da Resposta.
	 */
	public Resposta(String _enunciadoQuestao, String _enunciadoResposta) {
		this(_enunciadoQuestao, _enunciadoResposta, null, TipoQuestao.UMA_ALTERNATIVA);
	}

	/**
	 * 
	 * Instância uma nova Resposta. Passando somente o Enunciado. Usado
	 * normalmente para se usar no método equals.
	 * 
	 * @param _enunciadoQuestao
	 *            O Enunciado da Questão.
	 */
	public Resposta(String _enunciadoQuestao) {
		this(_enunciadoQuestao, null, null, null);
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
	public Resposta(String _enunciadoQuestao, String _enunciadoResposta, ArrayList<String> _enunciadoRespostas,
			TipoQuestao _tipoQuestao) {
		enunciadoQuestao = _enunciadoQuestao;
		enunciadoResposta = _enunciadoResposta;
		enunciadoRespostas = _enunciadoRespostas;
		tipoQuestao = _tipoQuestao;
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
	 * Obtém o Enunciado da Resposta.
	 * 
	 * @return O Enunciado da Resposta.
	 */
	public String getEnunciadoResposta() {
		return enunciadoResposta;
	}

	/**
	 * 
	 * Se Define o novo Enunciado da Resposta.
	 * 
	 * @param resposta
	 *            O novo Enunciado da Resposta.
	 */
	public void setEnunciadoResposta(String resposta) {
		this.enunciadoResposta = resposta;
	}

	/**
	 * 
	 * Obtém a lista com o Enunciado das respostas, caso seja uma questão de
	 * multipla escolha.
	 * 
	 * @return A lista com o Enunciado das respostas
	 */
	public ArrayList<String> getEnunciadoRespostas() {
		return enunciadoRespostas;
	}

	/**
	 * 
	 * Se Define a nova lista com o enunciado das respostas.
	 * 
	 * @param respostas
	 *            A nova lista com o enunciado das respostas.
	 */
	public void setEnunciadoRespostas(ArrayList<String> respostas) {
		this.enunciadoRespostas = respostas;
	}

	/**
	 * 
	 * Obtém o Tipo da Questão.
	 * 
	 * @return O Tipo da Questão.
	 */
	public TipoQuestao getTipoQuestao() {
		return tipoQuestao;
	}

	/**
	 * 
	 * Se Define o novo tipo da Questão.
	 * 
	 * @param tipoQuestao
	 *            O novo tipo da Questão.
	 */
	public void setTipoQuestao(TipoQuestao tipoQuestao) {
		this.tipoQuestao = tipoQuestao;
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
