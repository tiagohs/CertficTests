package br.com.oca.model;

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

public class Questao implements Serializable {
	/** Serial UID usado pelo Java. */
	private static final long serialVersionUID = 1L;

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

	/** As alternativas corretas da Quest�o. */
	private HashMap<Character, String> alternativasCorretas;

	/**
	 * 
	 * Instancia uma nova quest�o com v�rias alternativas corretas, sem passar a
	 * lista de alternativas.
	 * 
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
	 * @param alternativasCorretas
	 *            As alternativas corretas da Quest�o.
	 */
	public Questao(String enunciado, String enunciadoExtras, String numOpcoesCorretas, String referencia,
			HashMap<Character, String> alternativasCorretas) {
		this(enunciado, enunciadoExtras, numOpcoesCorretas, referencia, new HashMap<Character, String>(),
				alternativasCorretas);
	}

	/**
	 * 
	 * Instancia uma nova quest�o com v�rias alternativas corretas.
	 * 
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
	 * @param alternativasCorretas
	 *            As alternativas corretas da Quest�o.
	 */
	public Questao(String enunciado, String enunciadoExtras, String numOpcoesCorretas, String referencia,
			HashMap<Character, String> listaAlternativas, HashMap<Character, String> alternativasCorretas) {
		this.enunciado = enunciado;
		this.enunciadoExtras = enunciadoExtras;
		this.numOpcoesCorretas = numOpcoesCorretas;
		this.referencia = referencia;
		this.listaAlternativas = listaAlternativas;
		this.alternativasCorretas = alternativasCorretas;
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

	/**
	 * 
	 * Usado para se adicionar alternativas corretas.
	 * 
	 * @param alternativa
	 *            A Alternativa Correta.
	 * @throws TipoDeQuestaoException
	 *             Verifica o Tipo de Quest�o, se for de multipla escolha, lan�a
	 *             uma excess�o.
	 */
	public void addAlternativaCorreta(Character letra, String alternativa) {
		alternativasCorretas.put(letra, alternativa);
	}

	/**
	 * 
	 * Se Calcula o total de alternativas corretas.
	 * 
	 * @return o total de alternativas corretas.
	 */
	public int getTotalAlternativasCorretas() {
		return alternativasCorretas.size();
	}

	/**
	 * 
	 * Verifica se o numero de respostas corretas � maior que 1.
	 * 
	 * @return se o numero de Respostas Corretas � maior que 1, retorna true, se
	 *         n�o, false.
	 */
	public boolean isVariasRespostas() {
		return getTotalAlternativasCorretas() > 1;
	}

	/**
	 * 
	 * Se Realiza os C�lculos para se saber o n�mero de acertos em uma quest�o
	 * que seja de multipla escolha. Esse m�todo � chamado somente se a quest�o
	 * for de multipla Escolha, onde com base nas alternativas corretas j�
	 * registradas, se calcula o n�mero de acertos do usu�rio.
	 * 
	 * @param listaRespostas
	 *            a lista de respostas.
	 * @return Retorna a m�dia de total de Acertos.
	 */
	public Double getTotalAcertos(HashMap<Character, String> listaRespostas) {

		Double totalAcertos = 0.0;
		Double mediaTotalAcertos = 0.0;

		for (Character alternativaAtual : listaRespostas.keySet()) {
			if (isRespostaCorreta(alternativaAtual))
				totalAcertos++;
		}

		mediaTotalAcertos = totalAcertos / getTotalAlternativasCorretas();

		return mediaTotalAcertos;
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

	/**
	 * 
	 * Retorna todas as alternativas corretas.
	 * 
	 * @return as alternativas corretas.
	 * @throws TipoDeQuestaoException
	 *             Verifica o Tipo de Quest�o, se for de Somente uma alternativa
	 *             Correta, lan�a uma excess�o.
	 */
	public HashMap<Character, String> getAlternativasCorretas() {
		return alternativasCorretas;
	}

	/**
	 * 
	 * Se Define novas alternativas corretas.
	 * 
	 * @param alternativasCorretas
	 *            As novas alternativas corretas.
	 * @throws TipoDeQuestaoException
	 *             Verifica o Tipo de Quest�o, se for de Somente uma alternativa
	 *             Correta, lan�a uma excess�o.
	 */
	public void setAlternativasCorretas(HashMap<Character, String> alternativasCorretas) {
		this.alternativasCorretas = alternativasCorretas;
	}

	public boolean isRespostaCorreta(Character letraAlternativa) {
		return alternativasCorretas.containsKey(letraAlternativa);
	}

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
