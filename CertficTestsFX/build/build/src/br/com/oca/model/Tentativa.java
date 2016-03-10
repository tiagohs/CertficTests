/**
 * 
 * Copyright (c) 2016, CertificTests and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * 
 */
package br.com.oca.model;

import java.io.Serializable;

import br.com.oca.model.enums.Certificacao;
import br.com.oca.model.enums.TipoTeste;

/**
 * 
 * A Classe Tentativa contém todos os dados de cada Tentativa do usuário nos
 * testes, a tabela de resumo de tentativas na home tem como base esse objeto.
 * 
 * Classe <code>Tentativa</code>
 * 
 * @author Tiago Henrique
 * @version 1.0
 *
 */

public class Tentativa implements Serializable {
	/** Serial UID usado pelo Java. */
	private static final long serialVersionUID = 1L;

	/** O nome do Arquivo que será gravado na memória contendo as tentativas. */
	public static final String filename = "tentativas.bin";

	/** O Nome do Teste Realizado. */
	private Certificacao nomeTeste;

	/** O Tipo de Teste Realizado, se de 30, 60 ou 90 Questões. */
	private TipoTeste tipoTeste;

	/** Referencia para a view do Teste Escolhido, formatado. */
	private String testeEscolhido;

	/** Nota Recebida no Teste. */
	private Double nota;

	/** O Numero de acertos no teste. */
	private Double numeroAcertos;

	/** O Tempo Registrado no Teste. */
	private String tempoRegistrado;

	/** A Data e Hora registrada no Teste. */
	private String dataRegistrada;

	/**
	 * A Lista com as Repostas, mostrando todos os detalhes de qual o usuário
	 * acertou ou errou.
	 */
	private String listaRespostas;

	/**
	 * 
	 * Instancia uma nova Tentativa.
	 * 
	 * @param _nomeTeste
	 *            O nome do Arquivo que será gravado na memória contendo as
	 *            tentativas.
	 * @param _tipoTeste
	 *            O Tipo de Teste Realizado, se de 30, 60 ou 90 Questões.
	 * @param _nota
	 *            Nota Recebida no Teste.
	 * @param _numeroAcertos
	 *            O Numero de acertos no teste.
	 * @param _tempoRegistrado
	 *            O Tempo Registrado no Teste.
	 * @param _listaRespostas
	 *            A Lista com as Repostas, mostrando todos os detalhes de qual o
	 *            usuário acertou ou errou.
	 * @param _dataRegistrada
	 *            A Data e Hora registrada no Teste.
	 */
	public Tentativa(Certificacao _nomeTeste, TipoTeste _tipoTeste, Double _nota, Double _numeroAcertos,
			String _tempoRegistrado, String _listaRespostas, String _dataRegistrada) {
		nomeTeste = _nomeTeste;
		tipoTeste = _tipoTeste;
		testeEscolhido = nomeTeste.getNomeExtenso() + " - " + tipoTeste.getNome();
		nota = _nota;
		numeroAcertos = _numeroAcertos;
		tempoRegistrado = _tempoRegistrado;
		listaRespostas = _listaRespostas;
		dataRegistrada = _dataRegistrada;
	}

	/**
	 * 
	 * Obtém o nome do Teste.
	 * 
	 * @return
	 */
	public Certificacao getNomeTeste() {
		return nomeTeste;
	}

	/**
	 * 
	 * Se Define o novo
	 * 
	 * @param nomeTeste
	 */
	public void setNomeTeste(Certificacao nomeTeste) {
		this.nomeTeste = nomeTeste;
	}

	/**
	 * 
	 * Obtém a Nota Recebida no Teste.
	 * 
	 * @return A Nota Recebida no Teste.
	 */
	public Double getNota() {
		return nota;
	}

	/**
	 * 
	 * Se Define a nova
	 * 
	 * @param nota
	 */
	public void setNota(Double nota) {
		this.nota = nota;
	}

	/**
	 * 
	 * Obtém O Numero de acertos no teste.
	 * 
	 * @return O Numero de acertos no teste.
	 */
	public Double getNumeroAcertos() {
		return numeroAcertos;
	}

	/**
	 * 
	 * Se Define o novo
	 * 
	 * @param numeroAcertos
	 */
	public void setNumeroAcertos(Double numeroAcertos) {
		this.numeroAcertos = numeroAcertos;
	}

	/**
	 * 
	 * Obtém o Tipo de Teste Realizado. As Opções são TipoTeste.TESTE_1 (30
	 * questões), TipoTeste.TESTE_2 (60 questões), TipoTeste.TESTE_3 (90
	 * questões).
	 * 
	 * @return O Tipo de Teste Realizado.
	 */
	public TipoTeste getTipoTeste() {
		return tipoTeste;
	}

	/**
	 * 
	 * Se Define o novo Tipo de Teste Realizado. As Opções são TipoTeste.TESTE_1
	 * (30 questões), TipoTeste.TESTE_2 (60 questões), TipoTeste.TESTE_3 (90
	 * questões).
	 * 
	 * @param tipoTeste
	 *            O novo Tipo de Teste Realizado.
	 */
	public void setTipoTeste(TipoTeste tipoTeste) {
		this.tipoTeste = tipoTeste;
	}

	/**
	 * 
	 * Obtém a Referencia para a view do Teste Escolhido, formatado.
	 * 
	 * @return A Referencia para a view do Teste Escolhido, formatado.
	 */
	public String getTesteEscolhido() {
		return testeEscolhido;
	}

	/**
	 * 
	 * Se Define a nova Referencia para a view do Teste Escolhido, formatado.
	 * 
	 * @param testeEscolhido
	 *            A nova Referencia para a view do Teste Escolhido, formatado.
	 */
	public void setTesteEscolhido(String testeEscolhido) {
		this.testeEscolhido = testeEscolhido;
	}

	/**
	 * 
	 * Obtém o Tempo Registrado no Teste.
	 * 
	 * @return O Tempo Registrado no Teste.
	 */
	public String getTempoRegistrado() {
		return tempoRegistrado;
	}

	/**
	 * 
	 * Se Define o novo Tempo Registrado no Teste.
	 * 
	 * @param tempoRegistrado
	 *            O novo Tempo Registrado no Teste.
	 */
	public void setTempoRegistrado(String tempoRegistrado) {
		this.tempoRegistrado = tempoRegistrado;
	}

	/**
	 * 
	 * Obtém a Lista com as Repostas, mostrando todos os detalhes de qual o
	 * usuário acertou ou errou.
	 * 
	 * @return A Lista com as Repostas.
	 */
	public String getListaRespostas() {
		return listaRespostas;
	}

	/**
	 * 
	 * Se Define a nova Lista com as Repostas, mostrando todos os detalhes de
	 * qual o usuário acertou ou errou.
	 * 
	 * @param listaRespostas
	 *            A nova Lista com as Repostas.
	 */
	public void setListaRespostas(String listaRespostas) {
		this.listaRespostas = listaRespostas;
	}

	/**
	 * 
	 * Obtém a Data e Hora registrada no Teste.
	 * 
	 * @return A Data e Hora registrada no Teste.
	 */
	public String getDataRegistrada() {
		return dataRegistrada;
	}

	/**
	 * 
	 * Se Define a nova Data e Hora registrada no Teste.
	 * 
	 * @param dataRegistrada
	 *            A nova Data e Hora registrada no Teste.
	 */
	public void setDataRegistrada(String dataRegistrada) {
		this.dataRegistrada = dataRegistrada;
	}

}
