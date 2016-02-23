/**
 * 
 * Copyright (c) 2016, CertificTests and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * 
 */
package br.com.oca.model.questao;

import java.util.HashMap;

import br.com.oca.model.enums.TipoQuestao;
import br.com.oca.util.TipoDeQuestaoException;

/**
 * 
 * A Classe QuestaoUmaAlternativa cria uma quest�o que contenha v�rias
 * alternativas corretas.
 * 
 * Classe <code>QuestaoVariasAlternativas</code>
 * 
 * @author Tiago Henrique
 * @version 1.0
 *
 */

public class QuestaoVariasAlternativas extends Questao {
	/** Serial UID usado pelo Java. */
	private static final long serialVersionUID = 1L;

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
	public QuestaoVariasAlternativas(String enunciado, String enunciadoExtras, String numOpcoesCorretas,
			String referencia, HashMap<Character, String> alternativasCorretas) {
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
	public QuestaoVariasAlternativas(String enunciado, String enunciadoExtras, String numOpcoesCorretas,
			String referencia, HashMap<Character, String> listaAlternativas,
			HashMap<Character, String> alternativasCorretas) {
		super(TipoQuestao.MULTIPLAS_ALTERNATIVAS, enunciado, enunciadoExtras, numOpcoesCorretas, referencia,
				listaAlternativas);
		this.alternativasCorretas = alternativasCorretas;
	}

	/**
	 * 
	 * Se A Quest�o for de Multipla Escolha, retorna toda as alternativas
	 * corretas.
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
	 * Se A Quest�o for de Multipla Escolha, Se Define novas alternativas
	 * corretas.
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

	/**
	 * 
	 * M�todo usado somente se a quest�o for de multipla escolha, usado para se
	 * adicionar alternativas corretas.
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
	public int totalAlternativasCorretas() {
		return alternativasCorretas.size();
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
	public Double getTotalRespostasCorretas(HashMap<Character, String> listaRespostas) {

		Double totalAcertos = 0.0;
		Double mediaTotalAcertos = 0.0;

		for (Character alternativaAtual : listaRespostas.keySet()) {
			if (isRespostaCorreta(alternativaAtual))
				totalAcertos++;
		}

		mediaTotalAcertos = totalAcertos / totalAlternativasCorretas();

		return mediaTotalAcertos;
	}

	@Override
	public boolean isRespostaCorreta(Character letraAlternativa) {
		return alternativasCorretas.containsKey(letraAlternativa);
	}
}
