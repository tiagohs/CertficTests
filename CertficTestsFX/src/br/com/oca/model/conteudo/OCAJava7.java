package br.com.oca.model.conteudo;

import java.util.HashMap;

import br.com.oca.model.enums.Certificacao;
import br.com.oca.model.enums.Idioma;
import br.com.oca.model.enums.TipoTeste;

public class OCAJava7 extends Conteudo {
	private volatile static OCAJava7 instance;

	private OCAJava7(Certificacao nomeTeste, Idioma idiomaTeste, TipoTeste tipoTeste) {
		super(nomeTeste, idiomaTeste, tipoTeste);
	}

	public static OCAJava7 getInstance(Certificacao nomeTeste, Idioma idiomaTeste, TipoTeste tipoTeste) {

		if (instance == null) {
			synchronized (OCAJava7.class) {
				if (instance == null) {
					instance = new OCAJava7(nomeTeste, idiomaTeste, tipoTeste);
				}
			}
		}

		return instance;
	}

	@Override
	protected void preenxerQuestoes() {

		switch (tipoTeste) {
			case TESTE_1:
				preenxerTeste30Questoes();
				break;
			case TESTE_2:
				preenxerTeste60Questoes();
				break;
			case TESTE_3:
				preenxerTeste90Questoes();
		}

	}

	private void preenxerTeste30Questoes() {

		addQuestao(TESTE_30_QUESTOES, 1, 'B');
		addQuestao(TESTE_30_QUESTOES, 2, 'D');

		addQuestao(TESTE_30_QUESTOES, 3, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste30.exercicio3.opcao_0"));
				put('B', perguntasSource.getString("teste30.exercicio3.opcao_1"));
				put('C', perguntasSource.getString("teste30.exercicio3.opcao_2"));
				put('D', perguntasSource.getString("teste30.exercicio3.opcao_3"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 4, 'B');
		addQuestao(TESTE_30_QUESTOES, 5, 'D');
		addQuestao(TESTE_30_QUESTOES, 6, 'E');

		addQuestao(TESTE_30_QUESTOES, 7, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste30.exercicio3.opcao_0"));
				put('B', perguntasSource.getString("teste30.exercicio3.opcao_1"));
				put('D', perguntasSource.getString("teste30.exercicio3.opcao_3"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 8, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste30.exercicio3.opcao_0"));
				put('E', perguntasSource.getString("teste30.exercicio3.opcao_4"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 9, new HashMap<Character, String>() {
			{
				put('C', perguntasSource.getString("teste30.exercicio3.opcao_2"));
				put('D', perguntasSource.getString("teste30.exercicio3.opcao_3"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 10, 'A');

		addQuestao(TESTE_30_QUESTOES, 11, new HashMap<Character, String>() {
			{
				put('B', perguntasSource.getString("teste30.exercicio3.opcao_1"));
				put('C', perguntasSource.getString("teste30.exercicio3.opcao_2"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 12, 'A');

		addQuestao(TESTE_30_QUESTOES, 13, new HashMap<Character, String>() {
			{
				put('C', perguntasSource.getString("teste30.exercicio3.opcao_2"));
				put('D', perguntasSource.getString("teste30.exercicio3.opcao_3"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 14, 'B');
		addQuestao(TESTE_30_QUESTOES, 15, 'D');
		addQuestao(TESTE_30_QUESTOES, 16, 'C');

		addQuestao(TESTE_30_QUESTOES, 17, new HashMap<Character, String>() {
			{
				put('B', perguntasSource.getString("teste30.exercicio3.opcao_1"));
				put('C', perguntasSource.getString("teste30.exercicio3.opcao_2"));
				put('E', perguntasSource.getString("teste30.exercicio3.opcao_4"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 18, new HashMap<Character, String>() {
			{
				put('B', perguntasSource.getString("teste30.exercicio3.opcao_1"));
				put('D', perguntasSource.getString("teste30.exercicio3.opcao_3"));
				put('E', perguntasSource.getString("teste30.exercicio3.opcao_4"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 19, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste30.exercicio3.opcao_0"));
				put('B', perguntasSource.getString("teste30.exercicio3.opcao_1"));
				put('C', perguntasSource.getString("teste30.exercicio3.opcao_2"));
				put('D', perguntasSource.getString("teste30.exercicio3.opcao_3"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 20, 'D');
		addQuestao(TESTE_30_QUESTOES, 21, 'B');

		addQuestao(TESTE_30_QUESTOES, 22, new HashMap<Character, String>() {
			{
				put('C', perguntasSource.getString("teste30.exercicio3.opcao_2"));
				put('D', perguntasSource.getString("teste30.exercicio3.opcao_3"));
				put('E', perguntasSource.getString("teste30.exercicio3.opcao_4"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 23, 'D');

		addQuestao(TESTE_30_QUESTOES, 24, new HashMap<Character, String>() {
			{
				put('B', perguntasSource.getString("teste30.exercicio3.opcao_1"));
				put('E', perguntasSource.getString("teste30.exercicio3.opcao_4"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 25, 'D');
		addQuestao(TESTE_30_QUESTOES, 26, 'B');
		addQuestao(TESTE_30_QUESTOES, 27, 'A');

		addQuestao(TESTE_30_QUESTOES, 28, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste30.exercicio3.opcao_0"));
				put('E', perguntasSource.getString("teste30.exercicio3.opcao_4"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 29, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste30.exercicio3.opcao_0"));
				put('D', perguntasSource.getString("teste30.exercicio3.opcao_3"));
				put('B', perguntasSource.getString("teste30.exercicio3.opcao_4"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 30, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste30.exercicio3.opcao_0"));
				put('B', perguntasSource.getString("teste30.exercicio3.opcao_1"));
				put('C', perguntasSource.getString("teste30.exercicio3.opcao_2"));
				put('E', perguntasSource.getString("teste30.exercicio3.opcao_4"));
			}
		});
	}

	private void preenxerTeste60Questoes() {

		addQuestao(TESTE_60_QUESTOES, 1, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste30.exercicio3.opcao_0"));
				put('B', perguntasSource.getString("teste30.exercicio3.opcao_1"));
				put('C', perguntasSource.getString("teste30.exercicio3.opcao_2"));
				put('D', perguntasSource.getString("teste30.exercicio3.opcao_3"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 2, 'D');

		addQuestao(TESTE_60_QUESTOES, 10, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste30.exercicio3.opcao_0"));
				put('B', perguntasSource.getString("teste30.exercicio3.opcao_1"));
				put('D', perguntasSource.getString("teste30.exercicio3.opcao_3"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 20, new HashMap<Character, String>() {
			{
				put('B', perguntasSource.getString("teste30.exercicio3.opcao_1"));
				put('C', perguntasSource.getString("teste30.exercicio3.opcao_2"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 30, 'D');

		addQuestao(TESTE_60_QUESTOES, 40, 'C');

		addQuestao(TESTE_60_QUESTOES, 50, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste30.exercicio3.opcao_0"));
				put('C', perguntasSource.getString("teste30.exercicio3.opcao_2"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 60, 'B');
	}

	private void preenxerTeste90Questoes() {

	}

}
