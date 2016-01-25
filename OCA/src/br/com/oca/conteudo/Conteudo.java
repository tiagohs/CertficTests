package br.com.oca.conteudo;

import java.util.ArrayList;

import br.com.oca.enums.Certificacao;
import br.com.oca.enums.Idioma;
import br.com.oca.i18n.perguntas.PerguntasSource;

public abstract class Conteudo {
	protected ArrayList<Questao> listaQuestoes;
	protected PerguntasSource perguntasSource;
	
	protected Conteudo(Certificacao nomeTeste, Idioma idiomaTeste) {
		listaQuestoes = new ArrayList<Questao>();
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
	
	protected abstract void preenxerQuestoes();
}
