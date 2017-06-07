/**
 * 
 * Copyright (c) 2016, CertificTests and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * 
 */
package br.com.oca.model.conteudo;

import java.util.ArrayList;
import java.util.HashMap;

import br.com.oca.config.PerguntasConfig;
import br.com.oca.model.Questao;
import br.com.oca.model.enums.Certificacao;
import br.com.oca.model.enums.Idioma;
import br.com.oca.model.enums.TipoTeste;

/**
 * 
 * A Classe Conteudo contém todos os dados das questões de qualquer teste da
 * Aplicação.É Uma classe Abstrata que deve ser extendida por qualquer classe
 * auxiliadora, para implementar o preenximento das questões.
 * 
 * Classe <code>Conteudo</code>
 * 
 * @author Tiago Henrique
 * @version 1.0
 *
 */
public abstract class Conteudo {
	/** Váriavel global representando o tipo do teste, de 30 questões. */
	public static final int TESTE_30_QUESTOES = 30;

	/** Váriavel global representando o tipo do teste, de 60 questões. */
	public static final int TESTE_60_QUESTOES = 60;

	/** Váriavel global representando o tipo do teste, de 90 questões. */
	public static final int TESTE_90_QUESTOES = 90;

	/** A Lista contendo as Questões. */
	protected ArrayList<Questao> listaQuestoes;

	/** Config referenciando o properties de i18n das Questões. */
	protected PerguntasConfig perguntasSource;

	/** O Enumero refenciando o Exame Escolhido (OCA, OCP..). */
	protected Certificacao exame;

	/** O Idioma que terá as questões (EN, PT_BR..). */
	protected Idioma idiomaTeste;

	/** O Tipo de Teste (30, 60 ou 90 Questões). */
	protected TipoTeste tipoTeste;

	/**
	 * 
	 * Chamado pelo método super de seus filhos.
	 * 
	 * @param _exame
	 *            O Enumero refenciando o Exame Escolhido (OCA, OCP..).
	 * @param _idiomaTeste
	 *            O Idioma que terá as questões (EN, PT_BR..).
	 * @param _tipoTeste
	 *            O Tipo de Teste (30, 60 ou 90 Questões).
	 */
	protected Conteudo(Certificacao _exame, Idioma _idiomaTeste, TipoTeste _tipoTeste) {
		listaQuestoes = new ArrayList<Questao>();
		exame = _exame;
		idiomaTeste = _idiomaTeste;
		tipoTeste = _tipoTeste;
		perguntasSource = PerguntasConfig.getInstance(idiomaTeste, exame);

		preenxerQuestoes();
	}

	/**
	 * 
	 * Método chamado na instancia de um novo Conteudo, onde automaticamente se
	 * preenxe as questões, com base nos parametros passados.
	 * 
	 */
	protected void preenxerQuestoes() {
		switch (tipoTeste) {
		case TESTE_30_QUESTOES:
			preenxerTeste30Questoes();
			break;
		case TESTE_60_QUESTOES:
			preenxerTeste60Questoes();
			break;
		case TESTE_90_QUESTOES:
			preenxerTeste60Questoes();
			preenxerTeste30Questoes();
			//preenxerTeste90Questoes();
		}
	}

	/**
	 * 
	 * Preenxe uma Lista de 30 Questões.
	 * 
	 */
	protected abstract void preenxerTeste30Questoes();

	/**
	 * 
	 * Preenxe uma Lista de 60 Questões.
	 * 
	 */
	protected abstract void preenxerTeste60Questoes();

	/**
	 * 
	 * Preenxe uma Lista de 90 Questões.
	 * 
	 */
	protected abstract void preenxerTeste90Questoes();

	/**
	 * 
	 * Método Auxiliar para se adicionar um questão na lista.
	 * 
	 * @param quantidadeQuestao
	 *            A Quantidade de Questões do Teste, com base no Tipo (30, 60 ou
	 *            90).
	 * @param numQuestao
	 *            o Numero da questão que está sendo adicionada.
	 * @param alternativasCorretas
	 *            um HashMap contendo as Alternativas corretas.
	 */
	protected void addQuestao(int quantidadeQuestao, int numQuestao, HashMap<Character, String> alternativasCorretas) {

		Questao questao = new Questao(
				perguntasSource.getString("teste" + quantidadeQuestao + ".exercicio" + numQuestao + ".questao"),
				perguntasSource.getString("teste" + quantidadeQuestao + ".exercicio" + numQuestao + ".enunExtra"),
				perguntasSource.getString("teste" + quantidadeQuestao + ".exercicio" + numQuestao + ".numOpcao"),
				perguntasSource.getString("teste" + quantidadeQuestao + ".exercicio" + numQuestao + ".referencia"),
				alternativasCorretas);
		questao.addAlternativa('A',
				perguntasSource.getString("teste" + quantidadeQuestao + ".exercicio" + numQuestao + ".opcao_0"));
		questao.addAlternativa('B',
				perguntasSource.getString("teste" + quantidadeQuestao + ".exercicio" + numQuestao + ".opcao_1"));
		questao.addAlternativa('C',
				perguntasSource.getString("teste" + quantidadeQuestao + ".exercicio" + numQuestao + ".opcao_2"));
		questao.addAlternativa('D',
				perguntasSource.getString("teste" + quantidadeQuestao + ".exercicio" + numQuestao + ".opcao_3"));
		questao.addAlternativa('E',
				perguntasSource.getString("teste" + quantidadeQuestao + ".exercicio" + numQuestao + ".opcao_4"));

		listaQuestoes.add(questao);
	}

	/**
	 * 
	 * Obtém a Lista de Questões do Teste.
	 * 
	 * @return a Lista de Questões do Teste.
	 */
	public ArrayList<Questao> getListaQuestoes() {
		return listaQuestoes;
	}

	/**
	 * 
	 * Se define uma nova Lista de Questões do Teste.
	 * 
	 * @param listaQuestoes
	 *            a nova Lista de Questões do Teste.
	 */
	public void setListaQuestoes(ArrayList<Questao> listaQuestoes) {
		this.listaQuestoes = listaQuestoes;
	}

	/**
	 * 
	 * Obtém uma questão com base no seu numero.
	 * 
	 * @param numeroQuestao
	 *            o Numero da questão que se deseja obter.
	 * @return a Questão que se deseja obter.
	 */
	public Questao getQuestao(int numeroQuestao) {
		return listaQuestoes.get(numeroQuestao);
	}

	/**
	 * 
	 * Obtém o total de questões no teste.
	 * 
	 * @return o total de questões no teste.
	 */
	public Integer getTotalQuestoes() {
		return listaQuestoes.size();
	}

	/**
	 * 
	 * Se pergunta se contém ou não alguma questão no teste.
	 * 
	 * @return Se não tiver nenhuma questão, retorna true, se não, false.
	 */
	public boolean isEmpty() {
		return listaQuestoes.isEmpty();
	}

	/**
	 * 
	 * Obtém o Enumero refenciando o Exame Escolhido (OCA, OCP..).
	 * 
	 * @return o Enumero refenciando o Exame Escolhido.
	 */
	public Certificacao getExame() {
		return exame;
	}

	/**
	 * 
	 * Se define um novo Enumero refenciando o Exame Escolhido (OCA, OCP..).
	 * 
	 * @param nomeTeste
	 *            o novo Enumero refenciando o Exame Escolhido (OCA, OCP..).
	 */
	public void setExame(Certificacao exame) {
		this.exame = exame;
	}

	/**
	 * 
	 * Obtém o Idioma que terá as questões (EN, PT_BR..).
	 * 
	 * @return o Idioma que terá as questões (EN, PT_BR..).
	 */
	public Idioma getIdiomaTeste() {
		return idiomaTeste;
	}

	/**
	 * 
	 * Se Define um novo Idioma para as questões (EN, PT_BR..).
	 * 
	 * @param idiomaTeste
	 *            o novo Idioma para as questões (EN, PT_BR..).
	 */
	public void setIdiomaTeste(Idioma idiomaTeste) {
		this.idiomaTeste = idiomaTeste;
	}

	/**
	 * 
	 * Obtém o Tipo de Teste (30, 60 ou 90 Questões).
	 * 
	 * @return o Tipo de Teste (30, 60 ou 90 Questões).
	 */
	public TipoTeste getTipoTeste() {
		return tipoTeste;
	}

	/**
	 * 
	 * Se Define um novo Tipo de Teste (30, 60 ou 90 Questões).
	 * 
	 * @param tipoTeste
	 *            o novo Tipo de Teste (30, 60 ou 90 Questões).
	 */
	public void setTipoTeste(TipoTeste tipoTeste) {
		this.tipoTeste = tipoTeste;
	}

}
