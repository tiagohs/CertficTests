package br.com.oca.model.conteudo;

import java.util.ArrayList;
import java.util.HashMap;

import br.com.oca.model.enums.Certificacao;
import br.com.oca.model.enums.Idioma;
import br.com.oca.model.enums.TipoTeste;
import br.com.oca.model.i18n.perguntas.PerguntasSource;
import br.com.oca.model.questao.Questao;
import br.com.oca.model.questao.QuestaoUmaAlternativa;
import br.com.oca.model.questao.QuestaoVariasAlternativas;

public abstract class Conteudo {
	public static final int TESTE_30_QUESTOES = 30;
	public static final int TESTE_60_QUESTOES = 60;
	public static final int TESTE_90_QUESTOES = 90;

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

	protected abstract void preenxerQuestoes();

	protected void addQuestao(int quantidadeQuestao, int numQuestao, Character alternativaCorreta) {
		Questao questao = new QuestaoUmaAlternativa(
				perguntasSource.getString("teste" + quantidadeQuestao + ".exercicio" + numQuestao + ".questao"),
				perguntasSource.getString("teste" + quantidadeQuestao + ".exercicio" + numQuestao + ".enunExtra"),
				perguntasSource.getString("teste" + quantidadeQuestao + ".exercicio" + numQuestao + ".numOpcao"),
				perguntasSource.getString("teste" + quantidadeQuestao + ".exercicio" + numQuestao + ".referencia"),
				alternativaCorreta);
		questao.addAlternativa('A',
				perguntasSource.getString("teste" + quantidadeQuestao + ".exercicio" + numQuestao + ".opcao_0"));
		questao.addAlternativa('B',
				perguntasSource.getString("teste" + quantidadeQuestao + ".exercicio" + numQuestao + ".opcao_1"));
		questao.addAlternativa('C',
				perguntasSource.getString("teste" + quantidadeQuestao + ".exercicio" + numQuestao + ".opcao_2"));
		questao.addAlternativa('D',
				perguntasSource.getString("teste" + quantidadeQuestao + ".exercicio" + numQuestao + ".opcao_3"));
		questao.addAlternativa('E',
				perguntasSource.getString("teste" + quantidadeQuestao + ".exercicio" + numQuestao + ".opcao_4"));

		listaQuestoes.add(questao);
	}

	protected void addQuestao(int quantidadeQuestao, int numQuestao, HashMap<Character, String> alternativasCorretas) {

		Questao questao = new QuestaoVariasAlternativas(
				perguntasSource.getString("teste" + quantidadeQuestao + ".exercicio" + numQuestao + ".questao"),
				perguntasSource.getString("teste" + quantidadeQuestao + ".exercicio" + numQuestao + ".enunExtra"),
				perguntasSource.getString("teste" + quantidadeQuestao + ".exercicio" + numQuestao + ".numOpcao"),
				perguntasSource.getString("teste" + quantidadeQuestao + ".exercicio" + numQuestao + ".referencia"),
				alternativasCorretas);
		questao.addAlternativa('A',
				perguntasSource.getString("teste" + quantidadeQuestao + ".exercicio" + numQuestao + ".opcao_0"));
		questao.addAlternativa('B',
				perguntasSource.getString("teste" + quantidadeQuestao + ".exercicio" + numQuestao + ".opcao_1"));
		questao.addAlternativa('C',
				perguntasSource.getString("teste" + quantidadeQuestao + ".exercicio" + numQuestao + ".opcao_2"));
		questao.addAlternativa('D',
				perguntasSource.getString("teste" + quantidadeQuestao + ".exercicio" + numQuestao + ".opcao_3"));
		questao.addAlternativa('E',
				perguntasSource.getString("teste" + quantidadeQuestao + ".exercicio" + numQuestao + ".opcao_4"));

		listaQuestoes.add(questao);
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

}
