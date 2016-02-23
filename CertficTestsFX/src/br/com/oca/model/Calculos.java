/**
 * 
 * Copyright (c) 2016, CertificTests and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * 
 */

package br.com.oca.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import br.com.oca.model.conteudo.Conteudo;
import br.com.oca.model.questao.Questao;
import br.com.oca.model.questao.QuestaoVariasAlternativas;

/**
 * 
 * A Classe Calculos realiza todos os Calculos e formatações referentes aos
 * testes realizados, como O Numero de questões corretas, nota, tempo registrado
 * durante o teste, etc.
 * 
 * Classe <code>Calculos</code>
 * 
 * @author Tiago Henrique
 * @version 1.0
 *
 */

public class Calculos {
	/**
	 * Contém os Detalhes para se realizar o teste (Lista de Questões, Tipo de
	 * Teste, Idioma, Nome do Exame..).
	 */
	private Conteudo conteudo;

	/** Lista com todas as respostas escolhidas pelo Usuário no Teste. */
	private ArrayList<Resposta> listaRespostas;

	/** A nota que o usuário obteve no teste. */
	private Double nota;

	/** O numero de questões que o usuário acertou no teste. */
	private Double numeroQuestoesCorretas;

	/** O tempo que o usuário levou para se realizar o teste. */
	private Calendar tempoRegistrado;

	/** A data e hora em que o usuário realizou o teste. */
	private Date dataRegistrada;

	/**
	 * 
	 * Instancia uma nova Classe <code>Calculos</code>
	 * 
	 * A Classe Calculos realiza todos os Calculos e formatações referentes aos
	 * testes realizados, como O Numero de questões corretas, nota, tempo
	 * registrado durante o teste, etc. Recebe o conteudo, com detalhes do
	 * teste, a lista de respostas do usuário, o tempo registrado e a data
	 * registrada. Os Calculos por padrão, já são realizados assim que se cria
	 * uma instancia da classe Calculos. Se for setado novos valores durante a
	 * execucao da Aplicação, calcularNumeroQuestoesCorretas e calcularNota
	 * devem ser chamados novamente para se atualizar os resultados.
	 * 
	 * @param _conteudo
	 *            Contém os Detalhes para se realizar o teste (Lista de
	 *            Questões, Tipo de Teste, Idioma, Nome do Exame..).
	 * @param _listaRespostas
	 *            É a lista contendo as respostas do teste que o usuário
	 *            realizou.
	 * @param _tempoRegistrado
	 *            É o tempo de duração do teste realizado pelo Usuário
	 * @param _dataRegistrada
	 *            É a data e hora em que o usuário realizou o teste.
	 */
	public Calculos(Conteudo _conteudo, ArrayList<Resposta> _listaRespostas, Calendar _tempoRegistrado,
			Date _dataRegistrada) {
		nota = 0.0;
		numeroQuestoesCorretas = 0.0;

		conteudo = _conteudo;
		listaRespostas = _listaRespostas;
		tempoRegistrado = _tempoRegistrado;
		dataRegistrada = _dataRegistrada;

		calcularNumeroQuestoesCorretas();
		calcularNota();
	}

	/**
	 * 
	 * Calcula o numero de acertos que o usuário obteve no teste. Deve-se chamar
	 * esse método antes de calcularNota. Enquanto houver Respostas para serem
	 * verificadas, e com base no tipo de resposta da Questão (UMA ou MULTIPLA
	 * Escolha), se é realizado os Cálculos.
	 * 
	 */
	public void calcularNumeroQuestoesCorretas() {

		int numeroQuestao = 0;
		for (Resposta resposta : listaRespostas) {
			Questao questao = conteudo.getQuestao(numeroQuestao);
			switch (resposta.getTipoQuestao()) {
			case UMA_ALTERNATIVA:
				if (questao.isRespostaCorreta(questao.getLetraAlternativa(resposta.getEnunciadoResposta())))
					numeroQuestoesCorretas++;
				break;
			case MULTIPLAS_ALTERNATIVAS:
				numeroQuestoesCorretas += ((QuestaoVariasAlternativas) questao)
						.getTotalRespostasCorretas(listaRespostas.get(numeroQuestao).getListaRespostas());
			}
			numeroQuestao++;
		}

	}

	/**
	 * 
	 * Calcula a nota que o usuário recebeu no teste. Tendo o numero de questões
	 * Corretas e o total de questões realizadas, se faz a média.
	 * 
	 */
	public void calcularNota() {
		double media = (double) numeroQuestoesCorretas / conteudo.getTotalQuestoes();
		nota = media * 100;
	}

	/**
	 * 
	 * Obtém a Nota do Usuário no teste realizado.
	 * 
	 * @return A Nota do Usuário.
	 */
	public Double getNota() {
		return nota;
	}

	/**
	 * 
	 * Se Define uma nova Nota.
	 * 
	 * @param nota
	 *            A Nova nota.
	 */
	public void setNota(Double nota) {
		this.nota = nota;
	}

	/**
	 * 
	 * Obtém o numero de questões corretas no teste realizado.
	 * 
	 * @return O numero de questões corretas.
	 */
	public Double getNumeroQuestoesCorretas() {
		return numeroQuestoesCorretas;
	}

	/**
	 * 
	 * Se Define um novo Numero de Questões Corretas.
	 * 
	 * @param numeroQuestoesCorretas
	 *            O Novo Número de Questões Corretas.
	 */
	public void setNumeroQuestoesCorretas(Double numeroQuestoesCorretas) {
		this.numeroQuestoesCorretas = numeroQuestoesCorretas;
	}

	/**
	 * 
	 * Obtém o Tempo Decorrido Formatado, no formato String. O Padrão usado é o
	 * Brasileiro: 00:00:00.
	 * 
	 * @return O Tempo decorrido no Teste.
	 */
	public String getTempoDecorridoFormatado() {
		return new SimpleDateFormat("HH:mm:ss").format(tempoRegistrado.getTime());
	}

	/**
	 * 
	 * Obtém a Data e Hora em que o usuário fez o Teste, no formato String. O
	 * Padrão usado é o Brasileiro: 99/99/9999 00:00:00.
	 * 
	 * @return A Data e Hora em que o usuário fez o Teste.
	 */
	public String getDataRegistradaFormatado() {
		return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(dataRegistrada);
	}

	/**
	 * 
	 * Obtém a Data e Hora em que o usuário fez o Teste, no formato Date.
	 * 
	 * @return A Data e Hora em que o usuário fez o Teste
	 */
	public Date getDataRegistrada() {
		return dataRegistrada;
	}

	/**
	 * 
	 * Se Define uma Nova Data e Hora.
	 * 
	 * @param dataRegistrada
	 *            A Nova Data e Hora.
	 */
	public void setDataRegistrada(Date dataRegistrada) {
		this.dataRegistrada = dataRegistrada;
	}

	/**
	 * Obtém o Tempo Decorrido no Teste.
	 * 
	 * @return O Tempo Decorrido no Teste.
	 */
	public Calendar getTempoDecorrido() {
		return tempoRegistrado;
	}

	/**
	 * 
	 * Se Define um Novo Tempo de duração do teste.
	 * 
	 * @param tempoDecorrido
	 *            O Novo Tempo de duração do teste
	 */
	public void setTempoDecorrido(Calendar tempoDecorrido) {
		this.tempoRegistrado = tempoDecorrido;
	}

}
