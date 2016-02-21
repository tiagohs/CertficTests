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
 * A Classe Calculos realiza todos os Calculos e formata��es referentes aos
 * testes realizados, como O Numero de quest�es corretas, nota, tempo registrado
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
	 * Cont�m os Detalhes para se realizar o teste (Lista de Quest�es, Tipo de
	 * Teste, Idioma, Nome do Exame..).
	 */
	private Conteudo conteudo;
	
	/** Lista com todas as respostas escolhidas pelo Usu�rio no Teste. */
	private ArrayList<Resposta> listaRespostas;

	/** A nota que o usu�rio obteve no teste. */
	private Double nota;

	/** O numero de quest�es que o usu�rio acertou no teste. */
	private Double numeroQuestoesCorretas;

	/** O tempo que o usu�rio levou para se realizar o teste. */
	private Calendar tempoRegistrado;

	/** A data e hora em que o usu�rio realizou o teste. */
	private Date dataRegistrada;

	/**
	 * 
	 * Instancia uma nova Classe <code>Calculos</code>
	 * 
	 * A Classe Calculos realiza todos os Calculos e formata��es referentes aos
	 * testes realizados, como O Numero de quest�es corretas, nota, tempo
	 * registrado durante o teste, etc. Recebe o conteudo, com detalhes do
	 * teste, a lista de respostas do usu�rio, o tempo registrado e a data
	 * registrada. Os Calculos por padr�o, j� s�o realizados assim que se cria
	 * uma instancia da classe Calculos. Se for setado novos valores durante a
	 * execucao da Aplica��o, calcularNumeroQuestoesCorretas e calcularNota
	 * devem ser chamados novamente para se atualizar os resultados.
	 * 
	 * @param _conteudo
	 *            Cont�m os Detalhes para se realizar o teste (Lista de
	 *            Quest�es, Tipo de Teste, Idioma, Nome do Exame..).
	 * @param _listaRespostas
	 *            � a lista contendo as respostas do teste que o usu�rio
	 *            realizou.
	 * @param _tempoRegistrado
	 *            � o tempo de dura��o do teste realizado pelo Usu�rio
	 * @param _dataRegistrada
	 *            � a data e hora em que o usu�rio realizou o teste.
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
	 * Calcula o numero de acertos que o usu�rio obteve no teste. Deve-se chamar
	 * esse m�todo antes de calcularNota. Enquanto houver Respostas para serem
	 * verificadas, e com base no tipo de resposta da Quest�o (UMA ou MULTIPLA
	 * Escolha), se � realizado os C�lculos.
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
	 * Se Realiza os C�lculos para se saber o n�mero de acertos em uma quest�o
	 * que seja de multipla escolha. Esse m�todo � chamado somente se a quest�o
	 * for de multipla Escolha, onde com base nas alternativas corretas j�
	 * registradas, se calcula o n�mero de acertos do usu�rio.
	 * 
	 * @param questao
	 *            Refer�ncia a quest�o com multipla Escolhas.
	 * @param respostas
	 *            Refer�ncia ao Objeto Resposta que cont�m detalhes sobre as
	 *            Respostas do usu�rio.
	 * @return Retorna a m�dia de total de Acertos.
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
	 * Se a Quest�o for de multpla escolha, esse m�todo ser� chamado. Verifica
	 * se determinada alternativa � correta. Por Exemplo, em uma quest�o que
	 * cont�m 4 Alternativas corretas, o usu�rio durante o teste ir� escolher 4
	 * alternativas. Sendo assim, pra cada alternativa escolhida, esse m�todo
	 * verifica se � correta.
	 * 
	 * @param alternativaAtual
	 *            Alternativa que ser� verificada, ou seja, se � correta ou n�o.
	 * @param alternativasCorretas
	 *            HashMap contendo as alternativas corretas da quest�o.
	 * @return Retorna true se a alternativa escolhida pelo usu�rio se encontra
	 *         no HashMap, false se n�o se encontrar.
	 */
	private boolean isAlternativaCorreta(String alternativaAtual, HashMap<Character, String> alternativasCorretas) {
		return alternativasCorretas.containsValue(alternativaAtual);
	}

	/**
	 * 
	 * Verifica se ainda cont�m Quest�es para serem Verificadas.
	 * 
	 * @return Se Ainda possui alguma quest�o, returna true, se n�o, false.
	 */
	private boolean containsResposta() {
		return listaRespostas.size() > 0;
	}

	/**
	 * 
	 * Calcula a nota que o usu�rio recebeu no teste. Tendo o numero de quest�es
	 * Corretas e o total de quest�es realizadas, se faz a m�dia.
	 * 
	 */
	public void calcularNota() {
		double media = (double) numeroQuestoesCorretas / conteudo.getTotalQuestoes();
		nota = media * 100;
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
	public boolean isRespostaCorreta(Questao questao, Resposta resposta) throws TipoDeQuestaoException {
		return questao.getEnunciadoAlternativaCorreta().equals(resposta.getEnunciadoResposta());
	}

	/**
	 * 
	 * Obt�m a Nota do Usu�rio no teste realizado.
	 * 
	 * @return A Nota do Usu�rio.
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
	 * Obt�m o numero de quest�es corretas no teste realizado.
	 * 
	 * @return O numero de quest�es corretas.
	 */
	public Double getNumeroQuestoesCorretas() {
		return numeroQuestoesCorretas;
	}

	/**
	 * 
	 * Se Define um novo Numero de Quest�es Corretas.
	 * 
	 * @param numeroQuestoesCorretas
	 *            O Novo N�mero de Quest�es Corretas.
	 */
	public void setNumeroQuestoesCorretas(Double numeroQuestoesCorretas) {
		this.numeroQuestoesCorretas = numeroQuestoesCorretas;
	}

	/**
	 * 
	 * Obt�m o Tempo Decorrido Formatado, no formato String. O Padr�o usado � o
	 * Brasileiro: 00:00:00.
	 * 
	 * @return O Tempo decorrido no Teste.
	 */
	public String getTempoDecorridoFormatado() {
		return new SimpleDateFormat("HH:mm:ss").format(tempoRegistrado.getTime());
	}

	/**
	 * 
	 * Obt�m a Data e Hora em que o usu�rio fez o Teste, no formato String. O
	 * Padr�o usado � o Brasileiro: 99/99/9999 00:00:00.
	 * 
	 * @return A Data e Hora em que o usu�rio fez o Teste.
	 */
	public String getDataRegistradaFormatado() {
		return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(dataRegistrada);
	}

	/**
	 * 
	 * Obt�m a Data e Hora em que o usu�rio fez o Teste, no formato Date.
	 * 
	 * @return A Data e Hora em que o usu�rio fez o Teste
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
	 * Obt�m o Tempo Decorrido no Teste.
	 * 
	 * @return O Tempo Decorrido no Teste.
	 */
	public Calendar getTempoDecorrido() {
		return tempoRegistrado;
	}

	/**
	 * 
	 * Se Define um Novo Tempo de dura��o do teste.
	 * 
	 * @param tempoDecorrido
	 *            O Novo Tempo de dura��o do teste
	 */
	public void setTempoDecorrido(Calendar tempoDecorrido) {
		this.tempoRegistrado = tempoDecorrido;
	}

}
