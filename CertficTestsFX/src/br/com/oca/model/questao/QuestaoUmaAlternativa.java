/**
 * 
 * Copyright (c) 2016, CertificTests and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * 
 */

package br.com.oca.model.questao;

import java.util.HashMap;

import br.com.oca.model.enums.TipoQuestao;

/**
 * 
 * A Classe QuestaoUmaAlternativa cria uma quest�o que contenha somente uma
 * alternativa correta.
 * 
 * Classe <code>QuestaoUmaAlternativa</code>
 * 
 * @author Tiago Henrique
 * @version 1.0
 *
 */

public class QuestaoUmaAlternativa extends Questao {
	private static final long serialVersionUID = 1L;

	/**
	 * Caso conter somente uma alternativa correta, a Alternativa correta da
	 * quet�o.
	 */
	private Character alternativaCorreta;

	/**
	 * 
	 * Instancia uma Nova Quest�o, que contenha somente uma alternativa como
	 * correta. Nesse caso n�o � passado a lista de Alternativas.
	 * 
	 * @param enunciado
	 * @param enunciadoExtras
	 * @param numOpcoesCorretas
	 * @param referencia
	 * @param alternativaCorreta
	 */
	public QuestaoUmaAlternativa(String enunciado, String enunciadoExtras, String numOpcoesCorretas, String referencia,
			Character alternativaCorreta) {
		this(enunciado, enunciadoExtras, numOpcoesCorretas, referencia, new HashMap<Character, String>(),
				alternativaCorreta);
	}

	/**
	 * 
	 * Instancia uma Nova Quest�o, que contenha somente uma alternativa como
	 * correta.
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
	 * @param alternativaCorreta
	 */
	public QuestaoUmaAlternativa(String enunciado, String enunciadoExtras, String numOpcoesCorretas, String referencia,
			HashMap<Character, String> listaAlternativas, Character alternativaCorreta) {
		super(TipoQuestao.UMA_ALTERNATIVA, enunciado, enunciadoExtras, numOpcoesCorretas, referencia,
				listaAlternativas);
		this.alternativaCorreta = alternativaCorreta;
	}

	/**
	 * 
	 * Retorna o Enunciado da Alternativa Correta. Usa como auxilio o m�todo
	 * getAlternativa.
	 * 
	 * @return O Enunciado da Alternativa Correta.
	 */
	public String getEnunciadoAlternativaCorreta() {
		return getAlternativa(alternativaCorreta);
	}

	/**
	 * 
	 * Obt�m a Alternativa Correta.
	 * 
	 * @return a Alternativa Correta.
	 */
	public Character getAlternativaCorreta() {
		return alternativaCorreta;
	}

	/**
	 * 
	 * Se Define uma nova alternativa correta.
	 * 
	 * @param alternativaCorreta
	 *            A nova alternativa correta.
	 */
	public void setAlternativaCorreta(Character alternativaCorreta) {
		this.alternativaCorreta = alternativaCorreta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.oca.model.questao.Questao#isRespostaCorreta(java.lang.Character)
	 */
	@Override
	public boolean isRespostaCorreta(Character letraAlternativa) {
		return alternativaCorreta.equals(letraAlternativa);
	}

}
