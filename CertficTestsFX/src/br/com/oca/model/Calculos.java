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
import java.util.HashMap;

import br.com.oca.model.conteudo.Conteudo;

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

		if (containsResposta()) {
			for (int numeroQuestao = 0; numeroQuestao < conteudo.getTotalQuestoes(); numeroQuestao++) {

				switch (listaRespostas.get(numeroQuestao).getTipoQuestao()) {
				case UMA_ALTERNATIVA:
					if (isRespostaCorreta(conteudo.getQuestao(numeroQuestao), listaRespostas.get(numeroQuestao)))
						numeroQuestoesCorretas++;
					break;
				case MULTIPLAS_ALTERNATIVAS:
					numeroQuestoesCorretas += getTotalRespostasCorretas(conteudo.getQuestao(numeroQuestao), listaRespostas.get(numeroQuestao));
				}
			}
		}

	}

	private Double getTotalRespostasCorretas(Questao questao, Resposta respostas) {
		Double totalAcertos = 0.0;
		Double mediaTotalAcertos = 0.0;

		if (containsResposta()) {
			for (String alternativaAtual : respostas.getRespostas()) {
				if (isAlternativaCorreta(alternativaAtual, questao.getListaAlternativas()))
					totalAcertos++;
			}

			mediaTotalAcertos = totalAcertos / questao.getAlternativasCorretas().size();
		}

		return mediaTotalAcertos;
	}

	private boolean containsResposta() {
		return listaRespostas.size() > 0;
	}

	private boolean isAlternativaCorreta(String alternativaAtual, HashMap<Character, String> alternativasCorretas) {
		return alternativasCorretas.containsValue(alternativaAtual);
	}

	public void calcularNota() {
		double media = (double) numeroQuestoesCorretas / conteudo.getTotalQuestoes();
		nota = media * 100;
	}

	public boolean isRespostaCorreta(Questao questao, Resposta resposta) {
		return questao.getEnunciadoAlternativaCorreta()
				.equals(resposta.getResposta());
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	public Double getNumeroQuestoesCorretas() {
		return numeroQuestoesCorretas;
	}

	public void setNumeroQuestoesCorretas(Double numeroQuestoesCorretas) {
		this.numeroQuestoesCorretas = numeroQuestoesCorretas;
	}

	public String getTempoDecorridoFormatado() {
		return new SimpleDateFormat("HH:mm:ss").format(tempoRegistrado.getTime());
	}

	public String getDataRegistradaFormatado() {
		return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(dataRegistrada);
	}

	public Date getDataRegistrada() {
		return dataRegistrada;
	}

	public Calendar getTempoDecorrido() {
		return tempoRegistrado;
	}

	public void setTempoDecorrido(Calendar tempoDecorrido) {
		this.tempoRegistrado = tempoDecorrido;
	}

}
