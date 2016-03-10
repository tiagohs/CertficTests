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
 * A Classe Resposta representa uma Resposta do Usu�rio para cada Quest�o que
 * ele resolve. Ele ob�m o tipo da quest�o junto com o enunciado da quest�o e a
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
	/** O Enunciado da Quest�o. */
	private String enunciadoQuestao;
	
	/**
	 * Lista com sds Alternativas Corretas, caso a Quest�o for de
	 * Multipla Escolha.
	 */
	private HashMap<Character, String> listaRespostas;

	/**
	 * 
	 * Inst�ncia uma nova Resposta. Passando somente o Enunciado. Usado
	 * normalmente para se usar no m�todo equals.
	 * 
	 * @param _enunciadoQuestao
	 *            O Enunciado da Quest�o.
	 */
	public Resposta(String _enunciadoQuestao) {
		this(_enunciadoQuestao, null);
	}

	/**
	 * 
	 * Inst�ncia uma nova Resposta.
	 * 
	 * @param _enunciadoQuestao
	 *            O Enunciado da Quest�o.
	 * @param _enunciadoResposta
	 *            O Enunciado da Resposta.
	 * @param _enunciadoRespostas
	 *            Lista com os Enunciados das Alternativas Corretas.
	 * @param _tipoQuestao
	 *            O Tipo de Quest�o (Uma ou Multipla Alternativas).
	 */
	public Resposta(String _enunciadoQuestao, HashMap<Character, String> _listaRespostas) {
		enunciadoQuestao = _enunciadoQuestao;
		listaRespostas = _listaRespostas;
	}

	/**
	 * 
	 * Obt�m o Enunciado da Quest�o.
	 * 
	 * @return O Enunciado da Quest�o.
	 */
	public String getEnunciadoQuestao() {
		return enunciadoQuestao;
	}

	/**
	 * 
	 * Se Define o novo Enunciado da Quest�o.
	 * 
	 * @param enunciado
	 *            O novo Enunciado da Quest�o.
	 */
	public void setEnunciadoQuestao(String enunciado) {
		this.enunciadoQuestao = enunciado;
	}

	/**
	 * 
	 * Obt�m a lista com a lista de respostas, caso seja uma quest�o de
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
