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
 * A Classe Questão guarda todas as informações necessárias de uma questão nos
 * testes, o tipo, se for de multipla escolha ou não, as alternativas,
 * enunciado, e métodos auxiliares na aplicação que referêncie uma questão.
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

	/** O Enunciado da Questão. */
	private String enunciado;

	/** Conteúdo Extra dos Enunciados (Códigos, por exemplo). */
	private String enunciadoExtras;

	/**
	 * Numero de Opcoes Corretas da Questão, usado na view para auxiliar o
	 * usuário.
	 */
	private String numOpcoesCorretas;

	/** Referência da Questão, de onde foi retirada (De um Livro ou Site). */
	private String referencia;

	/** A lista de alternativas da Questão. */
	private HashMap<Character, String> listaAlternativas;

	/** As alternativas corretas da Questão. */
	private HashMap<Character, String> alternativasCorretas;

	/**
	 * 
	 * Instancia uma nova questão com várias alternativas corretas, sem passar a
	 * lista de alternativas.
	 * 
	 * @param enunciado
	 *            O Enunciado da Questão.
	 * @param enunciadoExtras
	 *            Numero de Opcoes Corretas da Questão, usado na view para
	 *            auxiliar o usuário.
	 * @param numOpcoesCorretas
	 *            Numero de Opcoes Corretas da Questão, usado na view para
	 *            auxiliar o usuário.
	 * @param referencia
	 *            Referência da Questão, de onde foi retirada (De um Livro ou
	 *            Site).
	 * @param alternativasCorretas
	 *            As alternativas corretas da Questão.
	 */
	public Questao(String enunciado, String enunciadoExtras, String numOpcoesCorretas, String referencia,
			HashMap<Character, String> alternativasCorretas) {
		this(enunciado, enunciadoExtras, numOpcoesCorretas, referencia, new HashMap<Character, String>(),
				alternativasCorretas);
	}

	/**
	 * 
	 * Instancia uma nova questão com várias alternativas corretas.
	 * 
	 * @param enunciado
	 *            O Enunciado da Questão.
	 * @param enunciadoExtras
	 *            Numero de Opcoes Corretas da Questão, usado na view para
	 *            auxiliar o usuário.
	 * @param numOpcoesCorretas
	 *            Numero de Opcoes Corretas da Questão, usado na view para
	 *            auxiliar o usuário.
	 * @param referencia
	 *            Referência da Questão, de onde foi retirada (De um Livro ou
	 *            Site).
	 * @param listaAlternativas
	 *            A lista de alternativas da Questão.
	 * @param alternativasCorretas
	 *            As alternativas corretas da Questão.
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
	 * Adiciona uma Alternativa na Questão.
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
	 *             Verifica o Tipo de Questão, se for de multipla escolha, lança
	 *             uma excessão.
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
	 * Verifica se o numero de respostas corretas é maior que 1.
	 * 
	 * @return se o numero de Respostas Corretas é maior que 1, retorna true, se
	 *         não, false.
	 */
	public boolean isVariasRespostas() {
		return getTotalAlternativasCorretas() > 1;
	}

	/**
	 * 
	 * Se Realiza os Cálculos para se saber o número de acertos em uma questão
	 * que seja de multipla escolha. Esse método é chamado somente se a questão
	 * for de multipla Escolha, onde com base nas alternativas corretas já
	 * registradas, se calcula o número de acertos do usuário.
	 * 
	 * @param listaRespostas
	 *            a lista de respostas.
	 * @return Retorna a média de total de Acertos.
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
	 * Obtém o Enunciado da Questão.
	 * 
	 * @return O Enunciado da Questão.
	 */
	public String getEnunciado() {
		return enunciado;
	}

	/**
	 * 
	 * Se Define um Novo enunciado para a questão.
	 * 
	 * @param enunciado
	 *            O Novo Enunciado.
	 */
	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	/**
	 * 
	 * Verificia se contém determinada alternativa, a partir da letra.
	 * 
	 * @param letraAlternativa
	 *            A Letra da Alternativa.
	 * @return Retorna true se contém, false se não.
	 */
	public boolean containsAlternativa(Character letraAlternativa) {
		return listaAlternativas.containsKey(letraAlternativa);
	}

	/**
	 * 
	 * Obtém o Numero total de Alternativas.
	 * 
	 * @return o Numero total de Alternativas.
	 */
	public int getNumeroTotalAlternativas() {
		return listaAlternativas.size();
	}

	/**
	 * 
	 * Obtém a Lista de Alternativas da Questão.
	 * 
	 * @return A Lista de Alternativas da Questão.
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
	 * Se Obtém uma Alternativa, com base na letra passada por parâmetro.
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
	 * Obtém a Referencia da Questão, de onde foi retirada (De um Livro ou
	 * Site).
	 * 
	 * @return A Referencia da Questão.
	 */
	public String getReferencia() {
		return referencia;
	}

	/**
	 * 
	 * Se Define uma nova Referência, de onde foi retirada (De um Livro ou
	 * Site).
	 * 
	 * @param referencia
	 *            A nova Referência
	 */
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	/**
	 * 
	 * Obtém o Conteúdo Extra dos Enunciados (Códigos, por exemplo).
	 * 
	 * @return o Conteúdo Extra dos Enunciados.
	 */
	public String getEnunciadoExtras() {
		return enunciadoExtras;
	}

	/**
	 * 
	 * Se Define o novo Conteúdo Extra do Enunciado (Códigos, por exemplo).
	 * 
	 * @param enunciadoExtras
	 *            O novo Conteúdo Extra do Enunciado.
	 */
	public void setEnunciadoExtras(String enunciadoExtras) {
		this.enunciadoExtras = enunciadoExtras;
	}

	/**
	 * 
	 * Obtém o Numero de Opcoes Corretas da Questão, usado na view para auxiliar
	 * o usuário.
	 * 
	 * @return O Numero de Opcoes Corretas da Questão
	 */
	public String getNumOpcoesCorretas() {
		return numOpcoesCorretas;
	}

	/**
	 * 
	 * Se Define o novo Numero de Opcoes Corretas da Questão, usado na view para
	 * auxiliar o usuário.
	 * 
	 * @param numOpcoesCorretas
	 *            O novo Numero de Opcoes Corretas da Questão.
	 */
	public void setNumOpcoesCorretas(String numOpcoesCorretas) {
		this.numOpcoesCorretas = numOpcoesCorretas;
	}

	/**
	 * 
	 * Esse método é chamado somente se a questão conter somente uma alternativa
	 * correta. Se verifica com base na alternativa correta e na alternativa
	 * escolhida pelo usuário se essa é correta.
	 * 
	 * @param questao
	 *            Referênca a questão com somente uma alternativa correta.
	 * @param resposta
	 *            Referência ao Objeto Resposta que contém detalhes sobre a
	 *            Resposta do usuário.
	 * @return Retorna true se a alternativa for correta, e false se não for.
	 * @throws TipoDeQuestaoException
	 */

	/**
	 * 
	 * Retorna todas as alternativas corretas.
	 * 
	 * @return as alternativas corretas.
	 * @throws TipoDeQuestaoException
	 *             Verifica o Tipo de Questão, se for de Somente uma alternativa
	 *             Correta, lança uma excessão.
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
	 *             Verifica o Tipo de Questão, se for de Somente uma alternativa
	 *             Correta, lança uma excessão.
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
