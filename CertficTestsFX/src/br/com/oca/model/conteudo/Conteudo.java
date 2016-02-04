package br.com.oca.model.conteudo;

import java.util.ArrayList;

import br.com.oca.model.Questao;
import br.com.oca.model.enums.Certificacao;
import br.com.oca.model.enums.Idioma;
import br.com.oca.model.enums.TipoTeste;
import br.com.oca.model.i18n.perguntas.PerguntasSource;

public abstract class Conteudo {
	protected ArrayList<Questao> listaQuestoes;
	protected PerguntasSource perguntasSource;
	protected Certificacao nomeTeste;
	protected Idioma idiomaTeste;
	protected TipoTeste tipoTeste;
	
	protected Conteudo(Certificacao _nomeTeste, Idioma _idiomaTeste, TipoTeste _tipoTeste) {
		listaQuestoes = new ArrayList<Questao>();
		nomeTeste = _nomeTeste;
		idiomaTeste = _idiomaTeste;
		tipoTeste = _tipoTeste;
		perguntasSource = PerguntasSource.getInstance(idiomaTeste, nomeTeste);
		
		preenxerQuestoes();
	}
	
	public ArrayList<Questao> getListaQuestoes() {
		return listaQuestoes;
	}

	public void setListaQuestoes(ArrayList<Questao> listaQuestoes) {
		this.listaQuestoes = listaQuestoes;
	}
	
	public Questao getQuestao(int numeroQuestao) {
		return listaQuestoes.get(numeroQuestao);
	}
	
	public Integer getTotalQuestoes() {
		return listaQuestoes.size();
	}
	
	public boolean isEmpty() {
		return listaQuestoes.isEmpty();
	}
	
	public Certificacao getNomeTeste() {
		return nomeTeste;
	}

	public void setNomeTeste(Certificacao nomeTeste) {
		this.nomeTeste = nomeTeste;
	}

	public Idioma getIdiomaTeste() {
		return idiomaTeste;
	}

	public void setIdiomaTeste(Idioma idiomaTeste) {
		this.idiomaTeste = idiomaTeste;
	}

	public TipoTeste getTipoTeste() {
		return tipoTeste;
	}

	public void setTipoTeste(TipoTeste tipoTeste) {
		this.tipoTeste = tipoTeste;
	}

	protected abstract void preenxerQuestoes();
}
