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
 * A Classe Questão guarda todas as informações necessárias de uma questão nos
 * testes, o tipo, se for de multipla escolha ou não, as alternativas,
 * enunciado, e métodos auxiliares na aplicação que referêncie uma questão.
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

	/** O Tipo de Questão (Uma ou Multipla Alternativas). */
	private TipoQuestao tipoQuestao;

	/** O Enunciado da Questão. */
	private String enunciado;

	/** Conteúdo Extra dos Enunciados (Códigos, por exemplo). */
	private String enunciadoExtras;

	/**
	 * Numero de Opcoes Corretas da Questão, usado na view para auxiliar o
	 * usuário.
	 */
	private String numOpcoesCorretas;

	/** Referência da Questão, de onde foi retirada (De um Livro ou Site). */
	private String referencia;

	/** A lista de alternativas da Questão. */
	private HashMap<Character, String> listaAlternativas;

	/**
	 * Caso conter somente uma alternativa correta, a Alternativa correta da
	 * quetão.
	 */
	private Character alternativaCorreta;

	/** Caso for de multipla escolha, as alternativas corretas. */
	private ArrayList<String> alternativasCorretas;

	/**
	 * 
	 * Instancia uma Nova Questão.
	 * 
	 * @param _enunciado
	 *            O Enunciado da Questão.
	 * @param _alternativas
	 *            A lista de alternativas da Questão.
	 * @param _alternativaCorreta
	 *            Caso conter somente uma alternativa correta, a Alternativa
	 *            correta da quetão.
	 * @param _alternativasCorretas
	 *            Caso for de multipla escolha, as alternativas corretas da
	 *            quetão.
	 * @param _tipoQuestao
	 *            O Tipo de Questão (Uma ou Multipla Alternativas).
	 * @param _referencia
	 *            Referência da Questão, de onde foi retirada (De um Livro ou
	 *            Site).
	 * @param _enunciadoExtras
	 *            Conteúdo Extra dos Enunciados (Códigos, por exemplo).
	 * @param _numOpcoesCorretas
	 *            Numero de Opcoes Corretas da Questão, usado na view para
	 *            auxiliar o usuário.
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
	 * Instancia uma Nova Questão. Instancia uma Questão sem informar 'a' ou
	 * 'as' alternativas corretas.
	 * 
	 * @param _enunciado
	 *            O Enunciado da Questão.
	 * @param _tipoQuestao
	 *            O Tipo de Questão (Uma ou Multipla Alternativas).
	 * @param _referencia
	 *            Referência da Questão, de onde foi retirada (De um Livro ou
	 *            Site).
	 * @param _enunciadoExtras
	 *            Conteúdo Extra dos Enunciados (Códigos, por exemplo).
	 * @param _numOpcoesCorretas
	 *            Numero de Opcoes Corretas da Questão, usado na view para
	 *            auxiliar o usuário.
	 */
	public Questao(String _enunciado, TipoQuestao _tipoQuestao, String _referencia, String _enunciadoExtras,
			String _numOpcoesCorretas) {
		this(_enunciado, new HashMap<Character, String>(), ' ', new ArrayList<String>(), _tipoQuestao, _referencia,
				_enunciadoExtras, _numOpcoesCorretas);
	}

	/**
	 * 
	 * Instancia uma Nova Questão. Nesse Caso se Instancia uma questão de
	 * multipla escolha, informando as alternativas corretas. E nesse momento já
	 * se é indormado em tipoQuestao que é MULTIPLAS_ALTERNATIVAS.
	 * 
	 * @param _enunciado
	 *            O Enunciado da Questão.
	 * @param _alternativasCorretas
	 *            As alternativas corretas da quetão.
	 * @param _referencia
	 *            Referência da Questão, de onde foi retirada (De um Livro ou
	 *            Site).
	 * @param _enunciadoExtras
	 *            Conteúdo Extra dos Enunciados (Códigos, por exemplo).
	 * @param _numOpcoesCorretas
	 *            Numero de Opcoes Corretas da Questão, usado na view para
	 *            auxiliar o usuário.
	 */
	public Questao(String _enunciado, ArrayList<String> _alternativasCorretas, String _referencia,
			String _enunciadoExtras, String _numOpcoesCorretas) {
		this(_enunciado, new HashMap<Character, String>(), null, _alternativasCorretas,
				TipoQuestao.MULTIPLAS_ALTERNATIVAS, _referencia, _enunciadoExtras, _numOpcoesCorretas);
	}

	/**
	 * 
	 * Instancia uma Nova Questão. Nesse Caso se Instancia uma questão que
	 * contém somente uma alternativa correta, informando a alternativa correta.
	 * E nesse momento já se é indormado em tipoQuestao que é UMA_ALTERNATIVA.
	 * 
	 * @param _enunciado
	 *            O Enunciado da Questão.
	 * @param _alternativaCorreta
	 *            a Alternativa correta da quetão.
	 * @param _referencia
	 *            Referência da Questão, de onde foi retirada (De um Livro ou
	 *            Site).
	 * @param _enunciadoExtras
	 *            Conteúdo Extra dos Enunciados (Códigos, por exemplo).
	 * @param _numOpcoesCorretas
	 *            Numero de Opcoes Corretas da Questão, usado na view para
	 *            auxiliar o usuário.
	 */
	public Questao(String _enunciado, Character _alternativaCorreta, String _referencia, String _enunciadoExtras,
			String _numOpcoesCorretas) {
		this(_enunciado, new HashMap<Character, String>(), _alternativaCorreta, null, TipoQuestao.UMA_ALTERNATIVA,
				_referencia, _enunciadoExtras, _numOpcoesCorretas);
	}

	/**
	 * 
	 * Instancia uma Nova Questão com valores padrões. O Padrão é uma questão
	 * com somente uma alternativa correta.
	 * 
	 */
	public Questao() {
		this("", new HashMap<Character, String>(), ' ', null, TipoQuestao.UMA_ALTERNATIVA, "", "", "");
	}

	/**
	 * 
	 * Adiciona uma Alternativa na Questão.
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
	 * Obtém o Enunciado da Questão.
	 * 
	 * @return O Enunciado da Questão.
	 */
	public String getEnunciado() {
		return enunciado;
	}

	/**
	 * 
	 * Se Define um Novo enunciado para a questão.
	 * 
	 * @param enunciado
	 *            O Novo Enunciado.
	 */
	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	/**
	 * 
	 * Obtém a lista de alternativas da questão.
	 * 
	 * @return A lista de alternativas da questão.
	 * @throws TipoDeQuestaoException
	 *             Verifica o Tipo de Questão, se for de Somente uma alternativa
	 *             Correta, lança uma excessão.
	 */
	public HashMap<Character, String> getlistaAlternativas() throws TipoDeQuestaoException {
		if (tipoQuestao != TipoQuestao.UMA_ALTERNATIVA)
			return listaAlternativas;
		else
			throw new TipoDeQuestaoException("Não é Possível usar essa funcionalidade.");
	}

	/**
	 * 
	 * Se Define uma nova lista de alternativas.
	 * 
	 * @param alternativas
	 *            A nova lista de alternativas Verifica o Tipo de Questão, se
	 *            for de Somente uma alternativa Correta, lança uma excessão.
	 * @throws TipoDeQuestaoException
	 *             Verifica o Tipo de Questão, se for de Somente uma alternativa
	 *             Correta, lança uma excessão.
	 */
	public void setlistaAlternativas(HashMap<Character, String> alternativas) throws TipoDeQuestaoException {
		if (tipoQuestao != TipoQuestao.UMA_ALTERNATIVA)
			this.listaAlternativas = alternativas;
		else
			throw new TipoDeQuestaoException("Não é Possível usar essa funcionalidade.");
	}

	/**
	 * 
	 * Obtém a Alternativa Correta, caso a Questão conter somente uma
	 * alternativa correta.
	 * 
	 * @return A Alternativa Correta.
	 * @throws TipoDeQuestaoException
	 *             Verifica o Tipo de Questão, se for de multipla escolha, lança
	 *             uma excessão.
	 */
	public Character getAlternativaCorreta() throws TipoDeQuestaoException {
		if (tipoQuestao != TipoQuestao.MULTIPLAS_ALTERNATIVAS)
			return alternativaCorreta;
		else
			throw new TipoDeQuestaoException("Não é Possível usar essa funcionalidade.");
	}

	/**
	 * 
	 * Se Define uma Nova Alternativa Correta.
	 * 
	 * @param alternativaCorreta
	 *            A Nova Alternativa Correta.
	 * @throws TipoDeQuestaoException
	 *             Verifica o Tipo de Questão, se for de multipla escolha, lança
	 *             uma excessão.
	 */
	public void setAlternativaCorreta(Character alternativaCorreta) throws TipoDeQuestaoException {
		if (tipoQuestao != TipoQuestao.MULTIPLAS_ALTERNATIVAS)
			this.alternativaCorreta = alternativaCorreta;
		else
			throw new TipoDeQuestaoException("Não é Possível usar essa funcionalidade.");
	}

	/**
	 * 
	 * Retorna o Enunciado da Alternativa Correta. Usa como auxilio o método
	 * getAlternativa.
	 * 
	 * @return O Enunciado da Alternativa Correta.
	 * @throws TipoDeQuestaoException
	 *             Verifica o Tipo de Questão, se for de multipla escolha, lança
	 *             uma excessão.
	 */
	public String getEnunciadoAlternativaCorreta() throws TipoDeQuestaoException {
		if (tipoQuestao != TipoQuestao.MULTIPLAS_ALTERNATIVAS)
			return getAlternativa(alternativaCorreta);
		else
			throw new TipoDeQuestaoException("Não é Possível usar essa funcionalidade.");
	}

	/**
	 * 
	 * Verificia se contém determinada alternativa, a partir da letra.
	 * 
	 * @param letraAlternativa
	 *            A Letra da Alternativa.
	 * @return Retorna true se contém, false se não.
	 */
	public boolean containsAlternativa(Character letraAlternativa) {
		return listaAlternativas.containsKey(letraAlternativa);
	}

	/**
	 * 
	 * Obtém o Numero total de Alternativas.
	 * 
	 * @return o Numero total de Alternativas.
	 */
	public int getNumeroTotalAlternativas() {
		return listaAlternativas.size();
	}

	/**
	 * 
	 * Obtém a Lista de Alternativas da Questão.
	 * 
	 * @return A Lista de Alternativas da Questão.
	 * @throws TipoDeQuestaoException
	 *             Verifica o Tipo de Questão, se for de Somente uma alternativa
	 *             Correta, lança uma excessão.
	 */
	public HashMap<Character, String> getListaAlternativas() throws TipoDeQuestaoException {
		if (tipoQuestao != TipoQuestao.UMA_ALTERNATIVA)
			return listaAlternativas;
		else
			throw new TipoDeQuestaoException("Não é Possível usar essa funcionalidade.");
	}

	/**
	 * 
	 * Se Define uma Nova lista de Alternativas.
	 * 
	 * @param listaAlternativas
	 *            A Nova lista de Alternativas.
	 * @throws TipoDeQuestaoException
	 *             Verifica o Tipo de Questão, se for de Somente uma alternativa
	 *             Correta, lança uma excessão.
	 */
	public void setListaAlternativas(HashMap<Character, String> listaAlternativas) throws TipoDeQuestaoException {
		if (tipoQuestao != TipoQuestao.UMA_ALTERNATIVA)
			this.listaAlternativas = listaAlternativas;
		else
			throw new TipoDeQuestaoException("Não é Possível usar essa funcionalidade.");
	}

	/**
	 * 
	 * Se Obtém uma Alternativa, com base na letra passada por parâmetro.
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
	 * Obtém o Tipo da Questão. TipoQuestao.UNICA_ALTERNATIVA ou
	 * TipoQuestao.MULTIPLAS_ALTERNTIVAS.
	 * 
	 * @return O Tipo da Questão
	 */
	public TipoQuestao getTipoQuestao() {
		return tipoQuestao;
	}

	/**
	 * 
	 * Se Define um novo tipo de Questão. TipoQuestao.UNICA_ALTERNATIVA ou
	 * TipoQuestao.MULTIPLAS_ALTERNTIVAS.
	 * 
	 * @param tipoQuestao
	 *            o novo tipo de Questão.
	 */
	public void setTipoQuestao(TipoQuestao tipoQuestao) {
		this.tipoQuestao = tipoQuestao;
	}

	/**
	 * 
	 * Se A Questão for de Multipla Escolha, retorna toda as alternativas
	 * corretas.
	 * 
	 * @return as alternativas corretas.
	 * @throws TipoDeQuestaoException
	 *             Verifica o Tipo de Questão, se for de Somente uma alternativa
	 *             Correta, lança uma excessão.
	 */
	public ArrayList<String> getAlternativasCorretas() throws TipoDeQuestaoException {
		if (tipoQuestao != TipoQuestao.UMA_ALTERNATIVA)
			return alternativasCorretas;
		else
			throw new TipoDeQuestaoException("Não é Possível usar essa funcionalidade.");
	}

	/**
	 * 
	 * Se A Questão for de Multipla Escolha, Se Define novas alternativas
	 * corretas.
	 * 
	 * @param alternativasCorretas
	 *            As novas alternativas corretas.
	 * @throws TipoDeQuestaoException
	 *             Verifica o Tipo de Questão, se for de Somente uma alternativa
	 *             Correta, lança uma excessão.
	 */
	public void setAlternativasCorretas(ArrayList<String> alternativasCorretas) throws TipoDeQuestaoException {
		if (tipoQuestao != TipoQuestao.UMA_ALTERNATIVA)
			this.alternativasCorretas = alternativasCorretas;
		else
			throw new TipoDeQuestaoException("Não é Possível usar essa funcionalidade.");
	}

	/**
	 * 
	 * Método usado somente se a questão for de multipla escolha, usado para se
	 * adicionar alternativas corretas.
	 * 
	 * @param alternativa
	 *            A Alternativa Correta.
	 * @throws TipoDeQuestaoException
	 *             Verifica o Tipo de Questão, se for de multipla escolha, lança
	 *             uma excessão.
	 */
	public void addAlternativaCorreta(String alternativa) throws TipoDeQuestaoException {
		if (tipoQuestao != TipoQuestao.UMA_ALTERNATIVA)
			alternativasCorretas.add(alternativa);
		else
			throw new TipoDeQuestaoException("Não é Possível usar essa funcionalidade.");
	}

	/**
	 * 
	 * Obtém a Referencia da Questão, de onde foi retirada (De um Livro ou
	 * Site).
	 * 
	 * @return A Referencia da Questão.
	 */
	public String getReferencia() {
		return referencia;
	}

	/**
	 * 
	 * Se Define uma nova Referência, de onde foi retirada (De um Livro ou
	 * Site).
	 * 
	 * @param referencia
	 *            A nova Referência
	 */
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	/**
	 * 
	 * Obtém o Conteúdo Extra dos Enunciados (Códigos, por exemplo).
	 * 
	 * @return o Conteúdo Extra dos Enunciados.
	 */
	public String getEnunciadoExtras() {
		return enunciadoExtras;
	}

	/**
	 * 
	 * Se Define o novo Conteúdo Extra do Enunciado (Códigos, por exemplo).
	 * 
	 * @param enunciadoExtras
	 *            O novo Conteúdo Extra do Enunciado.
	 */
	public void setEnunciadoExtras(String enunciadoExtras) {
		this.enunciadoExtras = enunciadoExtras;
	}

	/**
	 * 
	 * Obtém o Numero de Opcoes Corretas da Questão, usado na view para auxiliar
	 * o usuário.
	 * 
	 * @return O Numero de Opcoes Corretas da Questão
	 */
	public String getNumOpcoesCorretas() {
		return numOpcoesCorretas;
	}

	/**
	 * 
	 * Se Define o novo Numero de Opcoes Corretas da Questão, usado na view para
	 * auxiliar o usuário.
	 * 
	 * @param numOpcoesCorretas
	 *            O novo Numero de Opcoes Corretas da Questão.
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
