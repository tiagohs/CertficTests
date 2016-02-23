package br.com.oca.model.questao;

/**
 * 
 * Copyright (c) 2016, CertificTests and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * 
 */

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Objects;

import br.com.oca.model.enums.TipoQuestao;
import br.com.oca.util.TipoDeQuestaoException;

/**
 * 
 * A Classe Quest�o guarda todas as informa��es necess�rias de uma quest�o nos
 * testes, o tipo, se for de multipla escolha ou n�o, as alternativas,
 * enunciado, e m�todos auxiliares na aplica��o que refer�ncie uma quest�o.
 * 
 * Classe <code>Questao</code>
 * 
 * @author Tiago Henrique
 * @version 1.0
 *
 */

public abstract class Questao implements Serializable {
	/** Serial UID usado pelo Java. */
	private static final long serialVersionUID = 1L;

	/** O Tipo de Quest�o (Uma ou Multipla Alternativas). */
	private TipoQuestao tipoQuestao;

	/** O Enunciado da Quest�o. */
	private String enunciado;

	/** Conte�do Extra dos Enunciados (C�digos, por exemplo). */
	private String enunciadoExtras;

	/**
	 * Numero de Opcoes Corretas da Quest�o, usado na view para auxiliar o
	 * usu�rio.
	 */
	private String numOpcoesCorretas;

	/** Refer�ncia da Quest�o, de onde foi retirada (De um Livro ou Site). */
	private String referencia;

	/** A lista de alternativas da Quest�o. */
	private HashMap<Character, String> listaAlternativas;

	/**
	 * 
	 * Recebe Valores na Instancia de uma Nova Quest�o.
	 * 
	 * @param tipoQuestao
	 *            O Tipo de Quest�o (Uma ou Multipla Alternativas).
	 * @param enunciado
	 *            O Enunciado da Quest�o.
	 * @param enunciadoExtras
	 *            Numero de Opcoes Corretas da Quest�o, usado na view para
	 *            auxiliar o usu�rio.
	 * @param numOpcoesCorretas
	 *            Numero de Opcoes Corretas da Quest�o, usado na view para
	 *            auxiliar o usu�rio.
	 * @param referencia
	 *            Refer�ncia da Quest�o, de onde foi retirada (De um Livro ou
	 *            Site).
	 * @param listaAlternativas
	 *            A lista de alternativas da Quest�o.
	 */
	protected Questao(TipoQuestao tipoQuestao, String enunciado, String enunciadoExtras, String numOpcoesCorretas,
			String referencia, HashMap<Character, String> listaAlternativas) {
		this.tipoQuestao = tipoQuestao;
		this.enunciado = enunciado;
		this.enunciadoExtras = enunciadoExtras;
		this.numOpcoesCorretas = numOpcoesCorretas;
		this.referencia = referencia;
		this.listaAlternativas = listaAlternativas;
	}

	/**
	 * 
	 * Adiciona uma Alternativa na Quest�o.
	 * 
	 * @param letra
	 *            Letra da Alternativa (A, B, C, D ou E).
	 * @param enunciado
	 *            O enunciado da Alternativa.
	 */
	public void addAlternativa(Character letra, String enunciado) {
		listaAlternativas.put(letra, enunciado);
	}

	public Character getLetraAlternativa(String enunciado) {
		for (Entry<Character, String> entry : listaAlternativas.entrySet()) {
			if (Objects.equals(enunciado, entry.getValue())) {
				return entry.getKey();
			}
		}
		return null;
	}

	/**
	 * 
	 * Obt�m o Tipo da Quest�o. TipoQuestao.UNICA_ALTERNATIVA ou
	 * TipoQuestao.MULTIPLAS_ALTERNTIVAS.
	 * 
	 * @return O Tipo da Quest�o
	 */
	public TipoQuestao getTipoQuestao() {
		return tipoQuestao;
	}

	/**
	 * 
	 * Se Define um novo tipo de Quest�o. TipoQuestao.UNICA_ALTERNATIVA ou
	 * TipoQuestao.MULTIPLAS_ALTERNTIVAS.
	 * 
	 * @param tipoQuestao
	 *            o novo tipo de Quest�o.
	 */
	public void setTipoQuestao(TipoQuestao tipoQuestao) {
		this.tipoQuestao = tipoQuestao;
	}

	/**
	 * 
	 * Obt�m o Enunciado da Quest�o.
	 * 
	 * @return O Enunciado da Quest�o.
	 */
	public String getEnunciado() {
		return enunciado;
	}

	/**
	 * 
	 * Se Define um Novo enunciado para a quest�o.
	 * 
	 * @param enunciado
	 *            O Novo Enunciado.
	 */
	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	/**
	 * 
	 * Verificia se cont�m determinada alternativa, a partir da letra.
	 * 
	 * @param letraAlternativa
	 *            A Letra da Alternativa.
	 * @return Retorna true se cont�m, false se n�o.
	 */
	public boolean containsAlternativa(Character letraAlternativa) {
		return listaAlternativas.containsKey(letraAlternativa);
	}

	/**
	 * 
	 * Obt�m o Numero total de Alternativas.
	 * 
	 * @return o Numero total de Alternativas.
	 */
	public int getNumeroTotalAlternativas() {
		return listaAlternativas.size();
	}

	/**
	 * 
	 * Obt�m a Lista de Alternativas da Quest�o.
	 * 
	 * @return A Lista de Alternativas da Quest�o.
	 */
	public HashMap<Character, String> getListaAlternativas() {
		return listaAlternativas;
	}

	/**
	 * 
	 * Se Define uma Nova lista de Alternativas.
	 * 
	 * @param listaAlternativas
	 *            A Nova lista de Alternativas.
	 */
	public void setListaAlternativas(HashMap<Character, String> listaAlternativas) {
		this.listaAlternativas = listaAlternativas;
	}

	/**
	 * 
	 * Se Obt�m uma Alternativa, com base na letra passada por par�metro.
	 * 
	 * @param letra
	 *            A Letra da Alternativa que se deseja.
	 * @return A Alternativa que se deseja.
	 */
	public String getAlternativa(Character letra) {
		return listaAlternativas.get(letra);
	}

	/**
	 * 
	 * Obt�m a Referencia da Quest�o, de onde foi retirada (De um Livro ou
	 * Site).
	 * 
	 * @return A Referencia da Quest�o.
	 */
	public String getReferencia() {
		return referencia;
	}

	/**
	 * 
	 * Se Define uma nova Refer�ncia, de onde foi retirada (De um Livro ou
	 * Site).
	 * 
	 * @param referencia
	 *            A nova Refer�ncia
	 */
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	/**
	 * 
	 * Obt�m o Conte�do Extra dos Enunciados (C�digos, por exemplo).
	 * 
	 * @return o Conte�do Extra dos Enunciados.
	 */
	public String getEnunciadoExtras() {
		return enunciadoExtras;
	}

	/**
	 * 
	 * Se Define o novo Conte�do Extra do Enunciado (C�digos, por exemplo).
	 * 
	 * @param enunciadoExtras
	 *            O novo Conte�do Extra do Enunciado.
	 */
	public void setEnunciadoExtras(String enunciadoExtras) {
		this.enunciadoExtras = enunciadoExtras;
	}

	/**
	 * 
	 * Obt�m o Numero de Opcoes Corretas da Quest�o, usado na view para auxiliar
	 * o usu�rio.
	 * 
	 * @return O Numero de Opcoes Corretas da Quest�o
	 */
	public String getNumOpcoesCorretas() {
		return numOpcoesCorretas;
	}

	/**
	 * 
	 * Se Define o novo Numero de Opcoes Corretas da Quest�o, usado na view para
	 * auxiliar o usu�rio.
	 * 
	 * @param numOpcoesCorretas
	 *            O novo Numero de Opcoes Corretas da Quest�o.
	 */
	public void setNumOpcoesCorretas(String numOpcoesCorretas) {
		this.numOpcoesCorretas = numOpcoesCorretas;
	}

	/**
	 * 
	 * Esse m�todo � chamado somente se a quest�o conter somente uma alternativa
	 * correta. Se verifica com base na alternativa correta e na alternativa
	 * escolhida pelo usu�rio se essa � correta.
	 * 
	 * @param questao
	 *            Refer�nca a quest�o com somente uma alternativa correta.
	 * @param resposta
	 *            Refer�ncia ao Objeto Resposta que cont�m detalhes sobre a
	 *            Resposta do usu�rio.
	 * @return Retorna true se a alternativa for correta, e false se n�o for.
	 * @throws TipoDeQuestaoException
	 */
	public abstract boolean isRespostaCorreta(Character letraAlternativa);

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {

		if (obj instanceof Questao) {
			Questao questao = (Questao) obj;
			return getEnunciado().equals(questao.getEnunciado());
		}

		return super.equals(obj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
