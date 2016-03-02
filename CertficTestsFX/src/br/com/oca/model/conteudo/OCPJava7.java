package br.com.oca.model.conteudo;

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
	protected void preenxerTeste30Questoes() {

		addQuestao(TESTE_30_QUESTOES, 1, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste30.exercicio1.opcao_0"));
				put('C', perguntasSource.getString("teste30.exercicio1.opcao_2"));
				put('D', perguntasSource.getString("teste30.exercicio1.opcao_3"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 2, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste30.exercicio2.opcao_0"));
			}
		});
		addQuestao(TESTE_30_QUESTOES, 3, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste30.exercicio3.opcao_0"));
			}
		});
		addQuestao(TESTE_30_QUESTOES, 4, new HashMap<Character, String>() {
			{
				put('E', perguntasSource.getString("teste30.exercicio4.opcao_4"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 5, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste30.exercicio5.opcao_0"));
				put('B', perguntasSource.getString("teste30.exercicio5.opcao_1"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 6, new HashMap<Character, String>() {
			{
				put('E', perguntasSource.getString("teste30.exercicio6.opcao_4"));
			}
		});
		addQuestao(TESTE_30_QUESTOES, 7, new HashMap<Character, String>() {
			{
				put('D', perguntasSource.getString("teste30.exercicio7.opcao_3"));
			}
		});
		addQuestao(TESTE_30_QUESTOES, 8, new HashMap<Character, String>() {
			{
				put('D', perguntasSource.getString("teste30.exercicio8.opcao_3"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 9, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste30.exercicio9.opcao_0"));
				put('C', perguntasSource.getString("teste30.exercicio9.opcao_2"));
				put('D', perguntasSource.getString("teste30.exercicio9.opcao_3"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 10, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste30.exercicio10.opcao_0"));
				put('C', perguntasSource.getString("teste30.exercicio10.opcao_2"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 11, new HashMap<Character, String>() {
			{
				put('C', perguntasSource.getString("teste30.exercicio11.opcao_2"));
			}
		});
		addQuestao(TESTE_30_QUESTOES, 12, new HashMap<Character, String>() {
			{
				put('B', perguntasSource.getString("teste30.exercicio12.opcao_1"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 13, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste30.exercicio13.opcao_0"));
				put('E', perguntasSource.getString("teste30.exercicio13.opcao_4"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 14, new HashMap<Character, String>() {
			{
				put('B', perguntasSource.getString("teste30.exercicio14.opcao_1"));
				put('C', perguntasSource.getString("teste30.exercicio14.opcao_2"));
				put('D', perguntasSource.getString("teste30.exercicio14.opcao_3"));
				put('E', perguntasSource.getString("teste30.exercicio14.opcao_4"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 15, new HashMap<Character, String>() {
			{
				put('B', perguntasSource.getString("teste30.exercicio15.opcao_1"));
			}
		});
		addQuestao(TESTE_30_QUESTOES, 16, new HashMap<Character, String>() {
			{
				put('E', perguntasSource.getString("teste30.exercicio16.opcao_4"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 17, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste30.exercicio17.opcao_0"));
				put('B', perguntasSource.getString("teste30.exercicio17.opcao_1"));
				put('C', perguntasSource.getString("teste30.exercicio17.opcao_2"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 18, new HashMap<Character, String>() {
			{
				put('D', perguntasSource.getString("teste30.exercicio18.opcao_3"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 19, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste30.exercicio19.opcao_0"));
				put('C', perguntasSource.getString("teste30.exercicio19.opcao_2"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 20, new HashMap<Character, String>() {
			{
				put('D', perguntasSource.getString("teste30.exercicio20.opcao_3"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 21, new HashMap<Character, String>() {
			{
				put('B', perguntasSource.getString("teste30.exercicio21.opcao_1"));
				put('C', perguntasSource.getString("teste30.exercicio21.opcao_2"));
				put('D', perguntasSource.getString("teste30.exercicio21.opcao_3"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 22, new HashMap<Character, String>() {
			{
				put('B', perguntasSource.getString("teste30.exercicio22.opcao_1"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 23, new HashMap<Character, String>() {
			{
				put('B', perguntasSource.getString("teste30.exercicio23.opcao_1"));
				put('C', perguntasSource.getString("teste30.exercicio23.opcao_2"));
				put('D', perguntasSource.getString("teste30.exercicio23.opcao_3"));
				put('E', perguntasSource.getString("teste30.exercicio23.opcao_4"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 24, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste30.exercicio24.opcao_0"));
				put('B', perguntasSource.getString("teste30.exercicio24.opcao_1"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 25, new HashMap<Character, String>() {
			{
				put('C', perguntasSource.getString("teste30.exercicio25.opcao_2"));
			}
		});
		addQuestao(TESTE_30_QUESTOES, 26, new HashMap<Character, String>() {
			{
				put('E', perguntasSource.getString("teste30.exercicio26.opcao_4"));
			}
		});
		addQuestao(TESTE_30_QUESTOES, 27, new HashMap<Character, String>() {
			{
				put('D', perguntasSource.getString("teste30.exercicio27.opcao_3"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 28, new HashMap<Character, String>() {
			{
				put('C', perguntasSource.getString("teste30.exercicio28.opcao_2"));
				put('D', perguntasSource.getString("teste30.exercicio28.opcao_3"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 29, new HashMap<Character, String>() {
			{
				put('B', perguntasSource.getString("teste30.exercicio29.opcao_1"));
				put('D', perguntasSource.getString("teste30.exercicio29.opcao_3"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 30, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste30.exercicio30.opcao_0"));
				put('B', perguntasSource.getString("teste30.exercicio30.opcao_1"));
				put('D', perguntasSource.getString("teste30.exercicio30.opcao_3"));
				put('E', perguntasSource.getString("teste30.exercicio30.opcao_4"));
			}
		});

	}



	@Override
	protected void preenxerTeste60Questoes() {

		addQuestao(TESTE_60_QUESTOES, 1, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste60.exercicio1.opcao_0"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 2, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste60.exercicio2.opcao_0"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 10, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste60.exercicio10.opcao_0"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 11, new HashMap<Character, String>() {
			{
				put('E', perguntasSource.getString("teste60.exercicio11.opcao_4"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 12, new HashMap<Character, String>() {
			{
				put('B', perguntasSource.getString("teste60.exercicio12.opcao_1"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 13, new HashMap<Character, String>() {
			{
				put('B', perguntasSource.getString("teste60.exercicio13.opcao_1"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 14, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste60.exercicio14.opcao_0"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 15, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste60.exercicio15.opcao_0"));
				put('D', perguntasSource.getString("teste60.exercicio15.opcao_3"));
				put('E', perguntasSource.getString("teste60.exercicio15.opcao_4"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 16, new HashMap<Character, String>() {
			{
				put('C', perguntasSource.getString("teste60.exercicio16.opcao_2"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 17, new HashMap<Character, String>() {
			{
				put('B', perguntasSource.getString("teste60.exercicio17.opcao_1"));
				put('C', perguntasSource.getString("teste60.exercicio17.opcao_2"));
				put('D', perguntasSource.getString("teste60.exercicio17.opcao_3"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 18, new HashMap<Character, String>() {
			{
				put('D', perguntasSource.getString("teste60.exercicio18.opcao_3"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 19, new HashMap<Character, String>() {
			{
				put('B', perguntasSource.getString("teste60.exercicio19.opcao_1"));
			}
		});
		
		addQuestao(TESTE_60_QUESTOES, 20, new HashMap<Character, String>() {
			{
				put('D', perguntasSource.getString("teste60.exercicio20.opcao_3"));
			}
		});
	}

	@Override
	protected void preenxerTeste90Questoes() {

	}

}
