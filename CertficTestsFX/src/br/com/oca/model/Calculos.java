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
import br.com.oca.util.TipoDeQuestaoException;

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
				try {
					switch (listaRespostas.get(numeroQuestao).getTipoQuestao()) {
					case UMA_ALTERNATIVA:
						
							if (isRespostaCorreta(conteudo.getQuestao(numeroQuestao), listaRespostas.get(numeroQuestao)))
								numeroQuestoesCorretas++;
						
						break;
					case MULTIPLAS_ALTERNATIVAS:
						numeroQuestoesCorretas += getTotalRespostasCorretas(conteudo.getQuestao(numeroQuestao),
								listaRespostas.get(numeroQuestao));
					}
				} catch (TipoDeQuestaoException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * 
	 * Se Realiza os Cálculos para se saber o número de acertos em uma questão
	 * que seja de multipla escolha. Esse método é chamado somente se a questão
	 * for de multipla Escolha, onde com base nas alternativas corretas já
	 * registradas, se calcula o número de acertos do usuário.
	 * 
	 * @param questao
	 *            Referência a questão com multipla Escolhas.
	 * @param respostas
	 *            Referência ao Objeto Resposta que contém detalhes sobre as
	 *            Respostas do usuário.
	 * @return Retorna a média de total de Acertos.
	 * @throws TipoDeQuestaoException 
	 */
	private Double getTotalRespostasCorretas(Questao questao, Resposta respostas) throws TipoDeQuestaoException {
		Double totalAcertos = 0.0;
		Double mediaTotalAcertos = 0.0;

		if (containsResposta()) {
			for (String alternativaAtual : respostas.getEnunciadoRespostas()) {
				if (isAlternativaCorreta(alternativaAtual, questao.getListaAlternativas()))
					totalAcertos++;
			}

			mediaTotalAcertos = totalAcertos / questao.getAlternativasCorretas().size();
		}

		return mediaTotalAcertos;
	}

	/**
	 * 
	 * Se a Questão for de multpla escolha, esse método será chamado. Verifica
	 * se determinada alternativa é correta. Por Exemplo, em uma questão que
	 * contém 4 Alternativas corretas, o usuário durante o teste irá escolher 4
	 * alternativas. Sendo assim, pra cada alternativa escolhida, esse método
	 * verifica se é correta.
	 * 
	 * @param alternativaAtual
	 *            Alternativa que será verificada, ou seja, se é correta ou não.
	 * @param alternativasCorretas
	 *            HashMap contendo as alternativas corretas da questão.
	 * @return Retorna true se a alternativa escolhida pelo usuário se encontra
	 *         no HashMap, false se não se encontrar.
	 */
	private boolean isAlternativaCorreta(String alternativaAtual, HashMap<Character, String> alternativasCorretas) {
		return alternativasCorretas.containsValue(alternativaAtual);
	}

	/**
	 * 
	 * Verifica se ainda contém Questões para serem Verificadas.
	 * 
	 * @return Se Ainda possui alguma questão, returna true, se não, false.
	 */
	private boolean containsResposta() {
		return listaRespostas.size() > 0;
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
	public boolean isRespostaCorreta(Questao questao, Resposta resposta) throws TipoDeQuestaoException {
		return questao.getEnunciadoAlternativaCorreta().equals(resposta.getEnunciadoResposta());
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
