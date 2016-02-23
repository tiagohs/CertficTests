package br.com.oca.model.conteudo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import br.com.oca.model.enums.Certificacao;
import br.com.oca.model.enums.Idioma;
import br.com.oca.model.enums.TipoTeste;

public class OCPJava7 extends Conteudo {
	private volatile static OCPJava7 instance;

	protected OCPJava7(Certificacao nomeTeste, Idioma idiomaTeste, TipoTeste tipoTeste) {
		super(nomeTeste, idiomaTeste, tipoTeste);
	}

	public static OCPJava7 getInstance(Certificacao nomeTeste, Idioma idiomaTeste, TipoTeste tipoTeste) {

		if (instance == null) {
			synchronized (OCPJava7.class) {
				if (instance == null) {
					instance = new OCPJava7(nomeTeste, idiomaTeste, tipoTeste);
				}
			}
		}

		return instance;
	}

	@Override
	protected void preenxerQuestoes() {
		preenxerTeste30Questoes();
		preenxerTeste60Questoes();
		preenxerTeste90Questoes();
	}

	private void preenxerTeste30Questoes() {

		addQuestao(TESTE_30_QUESTOES, 1, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste30.exercicio3.opcao_0"));
				put('C', perguntasSource.getString("teste30.exercicio3.opcao_2"));
				put('D', perguntasSource.getString("teste30.exercicio3.opcao_3"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 2, 'A');
		addQuestao(TESTE_30_QUESTOES, 3, 'A');
		addQuestao(TESTE_30_QUESTOES, 4, 'E');

		addQuestao(TESTE_30_QUESTOES, 5, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste30.exercicio3.opcao_0"));
				put('B', perguntasSource.getString("teste30.exercicio3.opcao_1"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 6, 'E');
		addQuestao(TESTE_30_QUESTOES, 7, 'D');
		addQuestao(TESTE_30_QUESTOES, 8, 'D');

		addQuestao(TESTE_30_QUESTOES, 9, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste30.exercicio3.opcao_0"));
				put('C', perguntasSource.getString("teste30.exercicio3.opcao_2"));
				put('D', perguntasSource.getString("teste30.exercicio3.opcao_3"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 10, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste30.exercicio3.opcao_0"));
				put('C', perguntasSource.getString("teste30.exercicio3.opcao_2"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 11, 'C');
		addQuestao(TESTE_30_QUESTOES, 12, 'B');

		addQuestao(TESTE_30_QUESTOES, 13, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste30.exercicio3.opcao_0"));
				put('E', perguntasSource.getString("teste30.exercicio3.opcao_4"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 14, new HashMap<Character, String>() {
			{
				put('B', perguntasSource.getString("teste30.exercicio3.opcao_1"));
				put('C', perguntasSource.getString("teste30.exercicio3.opcao_2"));
				put('D', perguntasSource.getString("teste30.exercicio3.opcao_3"));
				put('E', perguntasSource.getString("teste30.exercicio3.opcao_4"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 15, 'B');
		addQuestao(TESTE_30_QUESTOES, 16, 'E');

		addQuestao(TESTE_30_QUESTOES, 17, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste30.exercicio3.opcao_0"));
				put('B', perguntasSource.getString("teste30.exercicio3.opcao_1"));
				put('C', perguntasSource.getString("teste30.exercicio3.opcao_2"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 18, 'D');

		addQuestao(TESTE_30_QUESTOES, 19, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste30.exercicio3.opcao_0"));
				put('C', perguntasSource.getString("teste30.exercicio3.opcao_2"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 20, 'D');

		addQuestao(TESTE_30_QUESTOES, 21, new HashMap<Character, String>() {
			{
				put('B', perguntasSource.getString("teste30.exercicio3.opcao_1"));
				put('C', perguntasSource.getString("teste30.exercicio3.opcao_2"));
				put('D', perguntasSource.getString("teste30.exercicio3.opcao_3"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 22, 'B');

		addQuestao(TESTE_30_QUESTOES, 23, new HashMap<Character, String>() {
			{
				put('B', perguntasSource.getString("teste30.exercicio3.opcao_1"));
				put('C', perguntasSource.getString("teste30.exercicio3.opcao_2"));
				put('D', perguntasSource.getString("teste30.exercicio3.opcao_3"));
				put('E', perguntasSource.getString("teste30.exercicio3.opcao_4"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 24, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste30.exercicio3.opcao_0"));
				put('B', perguntasSource.getString("teste30.exercicio3.opcao_1"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 25, 'C');
		addQuestao(TESTE_30_QUESTOES, 26, 'E');
		addQuestao(TESTE_30_QUESTOES, 27, 'D');

		addQuestao(TESTE_30_QUESTOES, 28, new HashMap<Character, String>() {
			{
				put('C', perguntasSource.getString("teste30.exercicio3.opcao_2"));
				put('D', perguntasSource.getString("teste30.exercicio3.opcao_3"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 29, new HashMap<Character, String>() {
			{
				put('B', perguntasSource.getString("teste30.exercicio3.opcao_1"));
				put('D', perguntasSource.getString("teste30.exercicio3.opcao_3"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 30, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste30.exercicio3.opcao_0"));
				put('B', perguntasSource.getString("teste30.exercicio3.opcao_1"));
				put('D', perguntasSource.getString("teste30.exercicio3.opcao_3"));
				put('E', perguntasSource.getString("teste30.exercicio3.opcao_4"));
			}
		});

	}

	private void preenxerTeste60Questoes() {

	}

	private void preenxerTeste90Questoes() {

	}

}
