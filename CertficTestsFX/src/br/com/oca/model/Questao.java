/**
 * 
 * Copyright (c) 2016, CertificTests and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * 
 */

package br.com.oca.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import br.com.oca.model.enums.TipoQuestao;
import br.com.oca.util.TipoDeQuestaoException;

/**
 * 
 * A Classe Quest�o guarda todas as informa��es necess�rias de uma quest�o nos
 * testes, o tipo, se for de multipla escolha ou n�o, as alternativas,
 * enunciado, e m�todos auxiliares na aplica��o que refer�ncie uma quest�o.
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

	/** O Tipo de Quest�o (Uma ou Multipla Alternativas). */
	private TipoQuestao tipoQuestao;

	/** O Enunciado da Quest�o. */
	private String enunciado;

	/** Conte�do Extra dos Enunciados (C�digos, por exemplo). */
	private String enunciadoExtras;

	/**
	 * Numero de Opcoes Corretas da Quest�o, usado na view para auxiliar o
	 * usu�rio.
	 */
	private String numOpcoesCorretas;

	/** Refer�ncia da Quest�o, de onde foi retirada (De um Livro ou Site). */
	private String referencia;

	/** A lista de alternativas da Quest�o. */
	private HashMap<Character, String> listaAlternativas;

	/**
	 * Caso conter somente uma alternativa correta, a Alternativa correta da
	 * quet�o.
	 */
	private Character alternativaCorreta;

	/** Caso for de multipla escolha, as alternativas corretas. */
	private ArrayList<String> alternativasCorretas;

	/**
	 * 
	 * Instancia uma Nova Quest�o.
	 * 
	 * @param _enunciado
	 *            O Enunciado da Quest�o.
	 * @param _alternativas
	 *            A lista de alternativas da Quest�o.
	 * @param _alternativaCorreta
	 *            Caso conter somente uma alternativa correta, a Alternativa
	 *            correta da quet�o.
	 * @param _alternativasCorretas
	 *            Caso for de multipla escolha, as alternativas corretas da
	 *            quet�o.
	 * @param _tipoQuestao
	 *            O Tipo de Quest�o (Uma ou Multipla Alternativas).
	 * @param _referencia
	 *            Refer�ncia da Quest�o, de onde foi retirada (De um Livro ou
	 *            Site).
	 * @param _enunciadoExtras
	 *            Conte�do Extra dos Enunciados (C�digos, por exemplo).
	 * @param _numOpcoesCorretas
	 *            Numero de Opcoes Corretas da Quest�o, usado na view para
	 *            auxiliar o usu�rio.
	 */
	public Questao(String _enunciado, HashMap<Character, String> _alternativas, Character _alternativaCorreta,
			ArrayList<String> _alternativasCorretas, TipoQuestao _tipoQuestao, String _referencia,
			String _enunciadoExtras, String _numOpcoesCorretas) {
		enunciado = _enunciado;
		listaAlternativas = _alternativas;
		alternativaCorreta = _alternativaCorreta;
		alternativasCorretas = _alternativasCorretas;
		tipoQuestao = _tipoQuestao;
		referencia = _referencia;
		enunciadoExtras = _enunciadoExtras;
		numOpcoesCorretas = _numOpcoesCorretas;
	}

	/**
	 * 
	 * Instancia uma Nova Quest�o. Instancia uma Quest�o sem informar 'a' ou
	 * 'as' alternativas corretas.
	 * 
	 * @param _enunciado
	 *            O Enunciado da Quest�o.
	 * @param _tipoQuestao
	 *            O Tipo de Quest�o (Uma ou Multipla Alternativas).
	 * @param _referencia
	 *            Refer�ncia da Quest�o, de onde foi retirada (De um Livro ou
	 *            Site).
	 * @param _enunciadoExtras
	 *            Conte�do Extra dos Enunciados (C�digos, por exemplo).
	 * @param _numOpcoesCorretas
	 *            Numero de Opcoes Corretas da Quest�o, usado na view para
	 *            auxiliar o usu�rio.
	 */
	public Questao(String _enunciado, TipoQuestao _tipoQuestao, String _referencia, String _enunciadoExtras,
			String _numOpcoesCorretas) {
		this(_enunciado, new HashMap<Character, String>(), ' ', new ArrayList<String>(), _tipoQuestao, _referencia,
				_enunciadoExtras, _numOpcoesCorretas);
	}

	/**
	 * 
	 * Instancia uma Nova Quest�o. Nesse Caso se Instancia uma quest�o de
	 * multipla escolha, informando as alternativas corretas. E nesse momento j�
	 * se � indormado em tipoQuestao que � MULTIPLAS_ALTERNATIVAS.
	 * 
	 * @param _enunciado
	 *            O Enunciado da Quest�o.
	 * @param _alternativasCorretas
	 *            As alternativas corretas da quet�o.
	 * @param _referencia
	 *            Refer�ncia da Quest�o, de onde foi retirada (De um Livro ou
	 *            Site).
	 * @param _enunciadoExtras
	 *            Conte�do Extra dos Enunciados (C�digos, por exemplo).
	 * @param _numOpcoesCorretas
	 *            Numero de Opcoes Corretas da Quest�o, usado na view para
	 *            auxiliar o usu�rio.
	 */
	public Questao(String _enunciado, ArrayList<String> _alternativasCorretas, String _referencia,
			String _enunciadoExtras, String _numOpcoesCorretas) {
		this(_enunciado, new HashMap<Character, String>(), null, _alternativasCorretas,
				TipoQuestao.MULTIPLAS_ALTERNATIVAS, _referencia, _enunciadoExtras, _numOpcoesCorretas);
	}

	/**
	 * 
	 * Instancia uma Nova Quest�o. Nesse Caso se Instancia uma quest�o que
	 * cont�m somente uma alternativa correta, informando a alternativa correta.
	 * E nesse momento j� se � indormado em tipoQuestao que � UMA_ALTERNATIVA.
	 * 
	 * @param _enunciado
	 *            O Enunciado da Quest�o.
	 * @param _alternativaCorreta
	 *            a Alternativa correta da quet�o.
	 * @param _referencia
	 *            Refer�ncia da Quest�o, de onde foi retirada (De um Livro ou
	 *            Site).
	 * @param _enunciadoExtras
	 *            Conte�do Extra dos Enunciados (C�digos, por exemplo).
	 * @param _numOpcoesCorretas
	 *            Numero de Opcoes Corretas da Quest�o, usado na view para
	 *            auxiliar o usu�rio.
	 */
	public Questao(String _enunciado, Character _alternativaCorreta, String _referencia, String _enunciadoExtras,
			String _numOpcoesCorretas) {
		this(_enunciado, new HashMap<Character, String>(), _alternativaCorreta, null, TipoQuestao.UMA_ALTERNATIVA,
				_referencia, _enunciadoExtras, _numOpcoesCorretas);
	}

	/**
	 * 
	 * Instancia uma Nova Quest�o com valores padr�es. O Padr�o � uma quest�o
	 * com somente uma alternativa correta.
	 * 
	 */
	public Questao() {
		this("", new HashMap<Character, String>(), ' ', null, TipoQuestao.UMA_ALTERNATIVA, "", "", "");
	}

	/**
	 * 
	 * Adiciona uma Alternativa na Quest�o.
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
	 * Obt�m o Enunciado da Quest�o.
	 * 
	 * @return O Enunciado da Quest�o.
	 */
	public String getEnunciado() {
		return enunciado;
	}

	/**
	 * 
	 * Se Define um Novo enunciado para a quest�o.
	 * 
	 * @param enunciado
	 *            O Novo Enunciado.
	 */
	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	/**
	 * 
	 * Obt�m a lista de alternativas da quest�o.
	 * 
	 * @return A lista de alternativas da quest�o.
	 * @throws TipoDeQuestaoException
	 *             Verifica o Tipo de Quest�o, se for de Somente uma alternativa
	 *             Correta, lan�a uma excess�o.
	 */
	public HashMap<Character, String> getlistaAlternativas() throws TipoDeQuestaoException {
		if (tipoQuestao != TipoQuestao.UMA_ALTERNATIVA)
			return listaAlternativas;
		else
			throw new TipoDeQuestaoException("N�o � Poss�vel usar essa funcionalidade.");
	}

	/**
	 * 
	 * Se Define uma nova lista de alternativas.
	 * 
	 * @param alternativas
	 *            A nova lista de alternativas Verifica o Tipo de Quest�o, se
	 *            for de Somente uma alternativa Correta, lan�a uma excess�o.
	 * @throws TipoDeQuestaoException
	 *             Verifica o Tipo de Quest�o, se for de Somente uma alternativa
	 *             Correta, lan�a uma excess�o.
	 */
	public void setlistaAlternativas(HashMap<Character, String> alternativas) throws TipoDeQuestaoException {
		if (tipoQuestao != TipoQuestao.UMA_ALTERNATIVA)
			this.listaAlternativas = alternativas;
		else
			throw new TipoDeQuestaoException("N�o � Poss�vel usar essa funcionalidade.");
	}

	/**
	 * 
	 * Obt�m a Alternativa Correta, caso a Quest�o conter somente uma
	 * alternativa correta.
	 * 
	 * @return A Alternativa Correta.
	 * @throws TipoDeQuestaoException
	 *             Verifica o Tipo de Quest�o, se for de multipla escolha, lan�a
	 *             uma excess�o.
	 */
	public Character getAlternativaCorreta() throws TipoDeQuestaoException {
		if (tipoQuestao != TipoQuestao.MULTIPLAS_ALTERNATIVAS)
			return alternativaCorreta;
		else
			throw new TipoDeQuestaoException("N�o � Poss�vel usar essa funcionalidade.");
	}

	/**
	 * 
	 * Se Define uma Nova Alternativa Correta.
	 * 
	 * @param alternativaCorreta
	 *            A Nova Alternativa Correta.
	 * @throws TipoDeQuestaoException
	 *             Verifica o Tipo de Quest�o, se for de multipla escolha, lan�a
	 *             uma excess�o.
	 */
	public void setAlternativaCorreta(Character alternativaCorreta) throws TipoDeQuestaoException {
		if (tipoQuestao != TipoQuestao.MULTIPLAS_ALTERNATIVAS)
			this.alternativaCorreta = alternativaCorreta;
		else
			throw new TipoDeQuestaoException("N�o � Poss�vel usar essa funcionalidade.");
	}

	/**
	 * 
	 * Retorna o Enunciado da Alternativa Correta. Usa como auxilio o m�todo
	 * getAlternativa.
	 * 
	 * @return O Enunciado da Alternativa Correta.
	 * @throws TipoDeQuestaoException
	 *             Verifica o Tipo de Quest�o, se for de multipla escolha, lan�a
	 *             uma excess�o.
	 */
	public String getEnunciadoAlternativaCorreta() throws TipoDeQuestaoException {
		if (tipoQuestao != TipoQuestao.MULTIPLAS_ALTERNATIVAS)
			return getAlternativa(alternativaCorreta);
		else
			throw new TipoDeQuestaoException("N�o � Poss�vel usar essa funcionalidade.");
	}

	/**
	 * 
	 * Verificia se cont�m determinada alternativa, a partir da letra.
	 * 
	 * @param letraAlternativa
	 *            A Letra da Alternativa.
	 * @return Retorna true se cont�m, false se n�o.
	 */
	public boolean containsAlternativa(Character letraAlternativa) {
		return listaAlternativas.containsKey(letraAlternativa);
	}

	/**
	 * 
	 * Obt�m o Numero total de Alternativas.
	 * 
	 * @return o Numero total de Alternativas.
	 */
	public int getNumeroTotalAlternativas() {
		return listaAlternativas.size();
	}

	/**
	 * 
	 * Obt�m a Lista de Alternativas da Quest�o.
	 * 
	 * @return A Lista de Alternativas da Quest�o.
	 * @throws TipoDeQuestaoException
	 *             Verifica o Tipo de Quest�o, se for de Somente uma alternativa
	 *             Correta, lan�a uma excess�o.
	 */
	public HashMap<Character, String> getListaAlternativas() throws TipoDeQuestaoException {
		if (tipoQuestao != TipoQuestao.UMA_ALTERNATIVA)
			return listaAlternativas;
		else
			throw new TipoDeQuestaoException("N�o � Poss�vel usar essa funcionalidade.");
	}

	/**
	 * 
	 * Se Define uma Nova lista de Alternativas.
	 * 
	 * @param listaAlternativas
	 *            A Nova lista de Alternativas.
	 * @throws TipoDeQuestaoException
	 *             Verifica o Tipo de Quest�o, se for de Somente uma alternativa
	 *             Correta, lan�a uma excess�o.
	 */
	public void setListaAlternativas(HashMap<Character, String> listaAlternativas) throws TipoDeQuestaoException {
		if (tipoQuestao != TipoQuestao.UMA_ALTERNATIVA)
			this.listaAlternativas = listaAlternativas;
		else
			throw new TipoDeQuestaoException("N�o � Poss�vel usar essa funcionalidade.");
	}

	/**
	 * 
	 * Se Obt�m uma Alternativa, com base na letra passada por par�metro.
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
	 * Obt�m o Tipo da Quest�o. TipoQuestao.UNICA_ALTERNATIVA ou
	 * TipoQuestao.MULTIPLAS_ALTERNTIVAS.
	 * 
	 * @return O Tipo da Quest�o
	 */
	public TipoQuestao getTipoQuestao() {
		return tipoQuestao;
	}

	/**
	 * 
	 * Se Define um novo tipo de Quest�o. TipoQuestao.UNICA_ALTERNATIVA ou
	 * TipoQuestao.MULTIPLAS_ALTERNTIVAS.
	 * 
	 * @param tipoQuestao
	 *            o novo tipo de Quest�o.
	 */
	public void setTipoQuestao(TipoQuestao tipoQuestao) {
		this.tipoQuestao = tipoQuestao;
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
	public ArrayList<String> getAlternativasCorretas() throws TipoDeQuestaoException {
		if (tipoQuestao != TipoQuestao.UMA_ALTERNATIVA)
			return alternativasCorretas;
		else
			throw new TipoDeQuestaoException("N�o � Poss�vel usar essa funcionalidade.");
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
	public void setAlternativasCorretas(ArrayList<String> alternativasCorretas) throws TipoDeQuestaoException {
		if (tipoQuestao != TipoQuestao.UMA_ALTERNATIVA)
			this.alternativasCorretas = alternativasCorretas;
		else
			throw new TipoDeQuestaoException("N�o � Poss�vel usar essa funcionalidade.");
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
	public void addAlternativaCorreta(String alternativa) throws TipoDeQuestaoException {
		if (tipoQuestao != TipoQuestao.UMA_ALTERNATIVA)
			alternativasCorretas.add(alternativa);
		else
			throw new TipoDeQuestaoException("N�o � Poss�vel usar essa funcionalidade.");
	}

	/**
	 * 
	 * Obt�m a Referencia da Quest�o, de onde foi retirada (De um Livro ou
	 * Site).
	 * 
	 * @return A Referencia da Quest�o.
	 */
	public String getReferencia() {
		return referencia;
	}

	/**
	 * 
	 * Se Define uma nova Refer�ncia, de onde foi retirada (De um Livro ou
	 * Site).
	 * 
	 * @param referencia
	 *            A nova Refer�ncia
	 */
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	/**
	 * 
	 * Obt�m o Conte�do Extra dos Enunciados (C�digos, por exemplo).
	 * 
	 * @return o Conte�do Extra dos Enunciados.
	 */
	public String getEnunciadoExtras() {
		return enunciadoExtras;
	}

	/**
	 * 
	 * Se Define o novo Conte�do Extra do Enunciado (C�digos, por exemplo).
	 * 
	 * @param enunciadoExtras
	 *            O novo Conte�do Extra do Enunciado.
	 */
	public void setEnunciadoExtras(String enunciadoExtras) {
		this.enunciadoExtras = enunciadoExtras;
	}

	/**
	 * 
	 * Obt�m o Numero de Opcoes Corretas da Quest�o, usado na view para auxiliar
	 * o usu�rio.
	 * 
	 * @return O Numero de Opcoes Corretas da Quest�o
	 */
	public String getNumOpcoesCorretas() {
		return numOpcoesCorretas;
	}

	/**
	 * 
	 * Se Define o novo Numero de Opcoes Corretas da Quest�o, usado na view para
	 * auxiliar o usu�rio.
	 * 
	 * @param numOpcoesCorretas
	 *            O novo Numero de Opcoes Corretas da Quest�o.
	 */
	public void setNumOpcoesCorretas(String numOpcoesCorretas) {
		this.numOpcoesCorretas = numOpcoesCorretas;
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
