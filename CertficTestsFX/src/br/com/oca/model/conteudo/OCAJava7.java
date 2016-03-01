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
	protected void preenxerTeste30Questoes() {

		addQuestao(TESTE_30_QUESTOES, 1, new HashMap<Character, String>() {
			{
				put('B', perguntasSource.getString("teste30.exercicio1.opcao_1"));
			}
		});
		addQuestao(TESTE_30_QUESTOES, 2, new HashMap<Character, String>() {
			{
				put('D', perguntasSource.getString("teste30.exercicio2.opcao_3"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 3, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste30.exercicio3.opcao_0"));
				put('B', perguntasSource.getString("teste30.exercicio3.opcao_1"));
				put('C', perguntasSource.getString("teste30.exercicio3.opcao_2"));
				put('D', perguntasSource.getString("teste30.exercicio3.opcao_3"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 4, new HashMap<Character, String>() {
			{
				put('B', perguntasSource.getString("teste30.exercicio4.opcao_1"));
			}
		});
		addQuestao(TESTE_30_QUESTOES, 5, new HashMap<Character, String>() {
			{
				put('D', perguntasSource.getString("teste30.exercicio5.opcao_3"));
			}
		});
		addQuestao(TESTE_30_QUESTOES, 6, new HashMap<Character, String>() {
			{
				put('E', perguntasSource.getString("teste30.exercicio6.opcao_4"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 7, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste30.exercicio7.opcao_0"));
				put('B', perguntasSource.getString("teste30.exercicio7.opcao_1"));
				put('D', perguntasSource.getString("teste30.exercicio7.opcao_3"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 8, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste30.exercicio8.opcao_0"));
				put('E', perguntasSource.getString("teste30.exercicio8.opcao_4"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 9, new HashMap<Character, String>() {
			{
				put('C', perguntasSource.getString("teste30.exercicio9.opcao_2"));
				put('D', perguntasSource.getString("teste30.exercicio9.opcao_3"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 10, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste30.exercicio10.opcao_0"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 11, new HashMap<Character, String>() {
			{
				put('B', perguntasSource.getString("teste30.exercicio11.opcao_1"));
				put('C', perguntasSource.getString("teste30.exercicio11.opcao_2"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 12, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste30.exercicio12.opcao_0"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 13, new HashMap<Character, String>() {
			{
				put('C', perguntasSource.getString("teste30.exercicio13.opcao_2"));
				put('D', perguntasSource.getString("teste30.exercicio13.opcao_3"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 14, new HashMap<Character, String>() {
			{
				put('B', perguntasSource.getString("teste30.exercicio14.opcao_1"));
			}
		});
		addQuestao(TESTE_30_QUESTOES, 15, new HashMap<Character, String>() {
			{
				put('D', perguntasSource.getString("teste30.exercicio15.opcao_3"));
			}
		});
		addQuestao(TESTE_30_QUESTOES, 16, new HashMap<Character, String>() {
			{
				put('C', perguntasSource.getString("teste30.exercicio16.opcao_2"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 17, new HashMap<Character, String>() {
			{
				put('B', perguntasSource.getString("teste30.exercicio17.opcao_1"));
				put('C', perguntasSource.getString("teste30.exercicio17.opcao_2"));
				put('E', perguntasSource.getString("teste30.exercicio17.opcao_4"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 18, new HashMap<Character, String>() {
			{
				put('B', perguntasSource.getString("teste30.exercicio18.opcao_1"));
				put('D', perguntasSource.getString("teste30.exercicio18.opcao_3"));
				put('E', perguntasSource.getString("teste30.exercicio18.opcao_4"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 19, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste30.exercicio19.opcao_0"));
				put('B', perguntasSource.getString("teste30.exercicio19.opcao_1"));
				put('C', perguntasSource.getString("teste30.exercicio19.opcao_2"));
				put('D', perguntasSource.getString("teste30.exercicio19.opcao_3"));
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
			}
		});

		addQuestao(TESTE_30_QUESTOES, 22, new HashMap<Character, String>() {
			{
				put('C', perguntasSource.getString("teste30.exercicio22.opcao_2"));
				put('D', perguntasSource.getString("teste30.exercicio22.opcao_3"));
				put('E', perguntasSource.getString("teste30.exercicio22.opcao_4"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 23, new HashMap<Character, String>() {
			{
				put('D', perguntasSource.getString("teste30.exercicio23.opcao_3"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 24, new HashMap<Character, String>() {
			{
				put('B', perguntasSource.getString("teste30.exercicio24.opcao_1"));
				put('E', perguntasSource.getString("teste30.exercicio24.opcao_4"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 25, new HashMap<Character, String>() {
			{
				put('D', perguntasSource.getString("teste30.exercicio25.opcao_3"));
			}
		});
		addQuestao(TESTE_30_QUESTOES, 26, new HashMap<Character, String>() {
			{
				put('B', perguntasSource.getString("teste30.exercicio26.opcao_1"));
			}
		});
		addQuestao(TESTE_30_QUESTOES, 27, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste30.exercicio27.opcao_0"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 28, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste30.exercicio28.opcao_0"));
				put('E', perguntasSource.getString("teste30.exercicio28.opcao_4"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 29, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste30.exercicio29.opcao_0"));
				put('D', perguntasSource.getString("teste30.exercicio29.opcao_3"));
				put('B', perguntasSource.getString("teste30.exercicio29.opcao_4"));
			}
		});

		addQuestao(TESTE_30_QUESTOES, 30, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste30.exercicio30.opcao_0"));
				put('B', perguntasSource.getString("teste30.exercicio30.opcao_1"));
				put('C', perguntasSource.getString("teste30.exercicio30.opcao_2"));
				put('E', perguntasSource.getString("teste30.exercicio30.opcao_4"));
			}
		});
	}

	@Override
	protected void preenxerTeste60Questoes() {

		addQuestao(TESTE_60_QUESTOES, 1, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste60.exercicio1.opcao_0"));
				put('B', perguntasSource.getString("teste60.exercicio1.opcao_1"));
				put('C', perguntasSource.getString("teste60.exercicio1.opcao_2"));
				put('D', perguntasSource.getString("teste60.exercicio1.opcao_3"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 2, new HashMap<Character, String>() {
			{
				put('D', perguntasSource.getString("teste60.exercicio2.opcao_3"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 3, new HashMap<Character, String>() {
			{
				put('C', perguntasSource.getString("teste60.exercicio3.opcao_2"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 4, new HashMap<Character, String>() {
			{
				put('C', perguntasSource.getString("teste60.exercicio4.opcao_2"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 5, new HashMap<Character, String>() {
			{
				put('B', perguntasSource.getString("teste60.exercicio5.opcao_1"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 6, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste60.exercicio6.opcao_0"));
				put('B', perguntasSource.getString("teste60.exercicio6.opcao_1"));
				put('C', perguntasSource.getString("teste60.exercicio6.opcao_2"));
				put('D', perguntasSource.getString("teste60.exercicio6.opcao_3"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 7, new HashMap<Character, String>() {
			{
				put('C', perguntasSource.getString("teste60.exercicio7.opcao_2"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 8, new HashMap<Character, String>() {
			{
				put('B', perguntasSource.getString("teste60.exercicio8.opcao_1"));
				put('C', perguntasSource.getString("teste60.exercicio8.opcao_2"));
				put('D', perguntasSource.getString("teste60.exercicio8.opcao_3"));
				put('E', perguntasSource.getString("teste60.exercicio8.opcao_4"));
			}
		});
		
		addQuestao(TESTE_60_QUESTOES, 9, new HashMap<Character, String>() {
			{
				put('C', perguntasSource.getString("teste60.exercicio9.opcao_2"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 10, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste60.exercicio10.opcao_0"));
				put('B', perguntasSource.getString("teste60.exercicio10.opcao_1"));
				put('D', perguntasSource.getString("teste60.exercicio10.opcao_3"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 11, new HashMap<Character, String>() {
			{
				put('B', perguntasSource.getString("teste60.exercicio11.opcao_1"));
				put('C', perguntasSource.getString("teste60.exercicio11.opcao_2"));
				put('D', perguntasSource.getString("teste60.exercicio11.opcao_3"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 12, new HashMap<Character, String>() {
			{
				put('D', perguntasSource.getString("teste60.exercicio12.opcao_3"));
			}
		});
		
		addQuestao(TESTE_60_QUESTOES, 13, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste60.exercicio13.opcao_0"));
				put('C', perguntasSource.getString("teste60.exercicio13.opcao_2"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 14, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste60.exercicio14.opcao_0"));
				put('D', perguntasSource.getString("teste60.exercicio14.opcao_3"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 15, new HashMap<Character, String>() {
			{
				put('E', perguntasSource.getString("teste60.exercicio15.opcao_4"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 16, new HashMap<Character, String>() {
			{
				put('E', perguntasSource.getString("teste60.exercicio16.opcao_4"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 17, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste60.exercicio17.opcao_0"));
				put('D', perguntasSource.getString("teste60.exercicio17.opcao_3"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 18, new HashMap<Character, String>() {
			{
				put('B', perguntasSource.getString("teste60.exercicio18.opcao_1"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 19, new HashMap<Character, String>() {
			{
				put('D', perguntasSource.getString("teste60.exercicio19.opcao_3"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 20, new HashMap<Character, String>() {
			{
				put('B', perguntasSource.getString("teste60.exercicio20.opcao_1"));
				put('C', perguntasSource.getString("teste60.exercicio20.opcao_2"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 21, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste60.exercicio21.opcao_0"));
				put('C', perguntasSource.getString("teste60.exercicio21.opcao_2"));
				put('D', perguntasSource.getString("teste60.exercicio21.opcao_3"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 22, new HashMap<Character, String>() {
			{
				put('B', perguntasSource.getString("teste60.exercicio22.opcao_1"));
				put('C', perguntasSource.getString("teste60.exercicio22.opcao_2"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 23, new HashMap<Character, String>() {
			{
				put('D', perguntasSource.getString("teste60.exercicio23.opcao_3"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 24, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste60.exercicio24.opcao_0"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 25, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste60.exercicio25.opcao_0"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 26, new HashMap<Character, String>() {
			{
				put('B', perguntasSource.getString("teste60.exercicio26.opcao_1"));
				put('C', perguntasSource.getString("teste60.exercicio26.opcao_2"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 27, new HashMap<Character, String>() {
			{
				put('D', perguntasSource.getString("teste60.exercicio27.opcao_3"));
				put('E', perguntasSource.getString("teste60.exercicio27.opcao_4"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 28, new HashMap<Character, String>() {
			{
				put('C', perguntasSource.getString("teste60.exercicio28.opcao_2"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 29, new HashMap<Character, String>() {
			{
				put('D', perguntasSource.getString("teste60.exercicio29.opcao_3"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 30, new HashMap<Character, String>() {
			{
				put('D', perguntasSource.getString("teste60.exercicio30.opcao_3"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 31, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste60.exercicio31.opcao_0"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 32, new HashMap<Character, String>() {
			{
				put('B', perguntasSource.getString("teste60.exercicio32.opcao_1"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 33, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste60.exercicio33.opcao_0"));
				put('B', perguntasSource.getString("teste60.exercicio33.opcao_1"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 34, new HashMap<Character, String>() {
			{
				put('C', perguntasSource.getString("teste60.exercicio34.opcao_2"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 35, new HashMap<Character, String>() {
			{
				put('D', perguntasSource.getString("teste60.exercicio35.opcao_3"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 36, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste60.exercicio36.opcao_0"));
				put('B', perguntasSource.getString("teste60.exercicio36.opcao_1"));
				put('C', perguntasSource.getString("teste60.exercicio36.opcao_2"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 37, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste60.exercicio37.opcao_0"));
				put('B', perguntasSource.getString("teste60.exercicio37.opcao_1"));
				put('C', perguntasSource.getString("teste60.exercicio37.opcao_2"));
				put('E', perguntasSource.getString("teste60.exercicio37.opcao_4"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 38, new HashMap<Character, String>() {
			{
				put('B', perguntasSource.getString("teste60.exercicio38.opcao_1"));
				put('C', perguntasSource.getString("teste60.exercicio38.opcao_2"));
				put('D', perguntasSource.getString("teste60.exercicio38.opcao_3"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 39, new HashMap<Character, String>() {
			{
				put('D', perguntasSource.getString("teste60.exercicio39.opcao_3"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 40, new HashMap<Character, String>() {
			{
				put('C', perguntasSource.getString("teste60.exercicio40.opcao_2"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 41, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste60.exercicio41.opcao_0"));
				put('D', perguntasSource.getString("teste60.exercicio41.opcao_3"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 42, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste60.exercicio42.opcao_0"));
				put('B', perguntasSource.getString("teste60.exercicio42.opcao_1"));
				put('C', perguntasSource.getString("teste60.exercicio42.opcao_2"));
				put('D', perguntasSource.getString("teste60.exercicio42.opcao_3"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 43, new HashMap<Character, String>() {
			{
				put('D', perguntasSource.getString("teste60.exercicio43.opcao_3"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 44, new HashMap<Character, String>() {
			{
				put('B', perguntasSource.getString("teste60.exercicio44.opcao_1"));

			}
		});

		addQuestao(TESTE_60_QUESTOES, 45, new HashMap<Character, String>() {
			{
				put('D', perguntasSource.getString("teste60.exercicio45.opcao_3"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 46, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste60.exercicio46.opcao_0"));
				put('B', perguntasSource.getString("teste60.exercicio46.opcao_1"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 47, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste60.exercicio47.opcao_0"));
				put('C', perguntasSource.getString("teste60.exercicio47.opcao_2"));
				put('D', perguntasSource.getString("teste60.exercicio47.opcao_3"));
				put('E', perguntasSource.getString("teste60.exercicio47.opcao_4"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 48, new HashMap<Character, String>() {
			{
				put('B', perguntasSource.getString("teste60.exercicio48.opcao_1"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 49, new HashMap<Character, String>() {
			{
				put('B', perguntasSource.getString("teste60.exercicio49.opcao_1"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 50, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste60.exercicio50.opcao_0"));
				put('C', perguntasSource.getString("teste60.exercicio50.opcao_2"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 51, new HashMap<Character, String>() {
			{
				put('D', perguntasSource.getString("teste60.exercicio51.opcao_3"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 52, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste60.exercicio52.opcao_0"));
				put('C', perguntasSource.getString("teste60.exercicio52.opcao_2"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 53, new HashMap<Character, String>() {
			{
				put('B', perguntasSource.getString("teste60.exercicio53.opcao_1"));
				put('D', perguntasSource.getString("teste60.exercicio53.opcao_3"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 54, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste60.exercicio54.opcao_0"));
				put('C', perguntasSource.getString("teste60.exercicio54.opcao_2"));
				put('D', perguntasSource.getString("teste60.exercicio54.opcao_3"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 55, new HashMap<Character, String>() {
			{
				put('B', perguntasSource.getString("teste60.exercicio55.opcao_1"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 56, new HashMap<Character, String>() {
			{
				put('C', perguntasSource.getString("teste60.exercicio56.opcao_2"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 57, new HashMap<Character, String>() {
			{
				put('C', perguntasSource.getString("teste60.exercicio57.opcao_2"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 58, new HashMap<Character, String>() {
			{
				put('A', perguntasSource.getString("teste60.exercicio58.opcao_0"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 59, new HashMap<Character, String>() {
			{
				put('C', perguntasSource.getString("teste60.exercicio59.opcao_2"));
				put('D', perguntasSource.getString("teste60.exercicio59.opcao_3"));
			}
		});

		addQuestao(TESTE_60_QUESTOES, 60, new HashMap<Character, String>() {
			{
				put('B', perguntasSource.getString("teste60.exercicio60.opcao_1"));
			}
		});
	}

	@Override
	protected void preenxerTeste90Questoes() {

	}

}
