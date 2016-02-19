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

	public void calcularNota() {
		double media = (double) numeroQuestoesCorretas / conteudo.getTotalQuestoes();
		nota = media * 100;
	}

	public void calcularNumeroQuestoesCorretas() {

		if (listaRespostas.size() > 0) {
			for (int count = 0; count < conteudo.getTotalQuestoes(); count++) {

				switch (listaRespostas.get(count).getTipoQuestao()) {
					case UNICA:
						if (isRespostaCorreta(count))
							numeroQuestoesCorretas++;
						break;
					case MULTIPLA:
						numeroQuestoesCorretas += totalAcertoQuestao(count);
					}
			}
		}

	}

	public Double totalAcertoQuestao(int count) {
		Double soma = 0.0;
		Double temp = 0.0;

		if (listaRespostas.size() > 0) {
			for (String resp : listaRespostas.get(count).getRespostas()) {
				if (conteudo.getQuestao(count).getListaAlternativas().containsValue(resp))
					soma++;
			}
			
			temp = soma / conteudo.getQuestao(count).getAlternativasCorretas().size();
		}
		
		return temp;
	}

	public boolean isRespostaCorreta(int count) {
		return conteudo.getQuestao(count).getEnunciadoAlternativaCorreta()
				.equals(listaRespostas.get(count).getResposta());
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
