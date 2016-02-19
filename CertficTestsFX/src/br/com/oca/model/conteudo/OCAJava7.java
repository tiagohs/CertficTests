package br.com.oca.model.conteudo;

import java.util.ArrayList;
import java.util.Arrays;

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
		preenxerTeste30Questoes();
		preenxerTeste60Questoes();
		preenxerTeste90Questoes();
	}

	private void preenxerTeste30Questoes() {

		addQuestao(TESTE_30_QUESTOES, 1, 'B');
		addQuestao(TESTE_30_QUESTOES, 2, 'D');

		addQuestao(TESTE_30_QUESTOES, 3,
				new ArrayList<String>(Arrays.asList(perguntasSource.getString("teste30.exercicio3.opcao_0"),
						perguntasSource.getString("teste30.exercicio3.opcao_1"),
						perguntasSource.getString("teste30.exercicio3.opcao_2"),
						perguntasSource.getString("teste30.exercicio3.opcao_3"))));

//		addQuestao(TESTE_30_QUESTOES, 4, 'B');
//		addQuestao(TESTE_30_QUESTOES, 5, 'D');
//		addQuestao(TESTE_30_QUESTOES, 6, 'E');
//
//		addQuestao(TESTE_30_QUESTOES, 7,
//				new ArrayList<String>(Arrays.asList(perguntasSource.getString("teste30.exercicio7.opcao_0"),
//						perguntasSource.getString("teste30.exercicio7.opcao_1"),
//						perguntasSource.getString("teste30.exercicio7.opcao_3"))));
//
//		addQuestao(TESTE_30_QUESTOES, 8,
//				new ArrayList<String>(Arrays.asList(perguntasSource.getString("teste30.exercicio8.opcao_0"),
//						perguntasSource.getString("teste30.exercicio8.opcao_4"))));
//
//		addQuestao(TESTE_30_QUESTOES, 9,
//				new ArrayList<String>(Arrays.asList(perguntasSource.getString("teste30.exercicio9.opcao_2"),
//						perguntasSource.getString("teste30.exercicio9.opcao_3"))));
//
//		addQuestao(TESTE_30_QUESTOES, 10, 'A');
//
//		addQuestao(TESTE_30_QUESTOES, 11,
//				new ArrayList<String>(Arrays.asList(perguntasSource.getString("teste30.exercicio11.opcao_1"),
//						perguntasSource.getString("teste30.exercicio11.opcao_2"))));
//
//		addQuestao(TESTE_30_QUESTOES, 12, 'A');
//
//		addQuestao(TESTE_30_QUESTOES, 13,
//				new ArrayList<String>(Arrays.asList(perguntasSource.getString("teste30.exercicio13.opcao_2"),
//						perguntasSource.getString("teste30.exercicio13.opcao_3"))));
//
//		addQuestao(TESTE_30_QUESTOES, 14, 'B');
//		addQuestao(TESTE_30_QUESTOES, 15, 'D');
//		addQuestao(TESTE_30_QUESTOES, 16, 'C');
//
//		addQuestao(TESTE_30_QUESTOES, 17,
//				new ArrayList<String>(Arrays.asList(perguntasSource.getString("teste30.exercicio17.opcao_1"),
//						perguntasSource.getString("teste30.exercicio17.opcao_2"),
//						perguntasSource.getString("teste30.exercicio17.opcao_4"))));
//
//		addQuestao(TESTE_30_QUESTOES, 18,
//				new ArrayList<String>(Arrays.asList(perguntasSource.getString("teste30.exercicio18.opcao_1"),
//						perguntasSource.getString("teste30.exercicio18.opcao_3"),
//						perguntasSource.getString("teste30.exercicio18.opcao_4"))));
//
//		addQuestao(TESTE_30_QUESTOES, 19,
//				new ArrayList<String>(Arrays.asList(perguntasSource.getString("teste30.exercicio19.opcao_0"),
//						perguntasSource.getString("teste30.exercicio19.opcao_1"),
//						perguntasSource.getString("teste30.exercicio19.opcao_2"),
//						perguntasSource.getString("teste30.exercicio19.opcao_3"))));
//
//		addQuestao(TESTE_30_QUESTOES, 20, 'D');
//		addQuestao(TESTE_30_QUESTOES, 21, 'B');
//
//		addQuestao(TESTE_30_QUESTOES, 22,
//				new ArrayList<String>(Arrays.asList(perguntasSource.getString("teste30.exercicio22.opcao_2"),
//						perguntasSource.getString("teste30.exercicio22.opcao_3"),
//						perguntasSource.getString("teste30.exercicio22.opcao_4"))));
//
//		addQuestao(TESTE_30_QUESTOES, 23, 'D');
//
//		addQuestao(TESTE_30_QUESTOES, 24,
//				new ArrayList<String>(Arrays.asList(perguntasSource.getString("teste30.exercicio24.opcao_1"),
//						perguntasSource.getString("teste30.exercicio24.opcao_4"))));
//
//		addQuestao(TESTE_30_QUESTOES, 25, 'D');
//		addQuestao(TESTE_30_QUESTOES, 26, 'B');
//		addQuestao(TESTE_30_QUESTOES, 27, 'A');
//		
//		addQuestao(TESTE_30_QUESTOES, 28,
//				new ArrayList<String>(Arrays.asList(perguntasSource.getString("teste30.exercicio28.opcao_0"),
//						perguntasSource.getString("teste30.exercicio28.opcao_4"))));
//		
//		addQuestao(TESTE_30_QUESTOES, 29,
//				new ArrayList<String>(Arrays.asList(perguntasSource.getString("teste30.exercicio29.opcao_0"),
//						perguntasSource.getString("teste30.exercicio29.opcao_3"),
//						perguntasSource.getString("teste30.exercicio29.opcao_4"))));
//		
//		addQuestao(TESTE_30_QUESTOES, 30,
//				new ArrayList<String>(Arrays.asList(perguntasSource.getString("teste30.exercicio30.opcao_0"),
//						perguntasSource.getString("teste30.exercicio30.opcao_1"),
//						perguntasSource.getString("teste30.exercicio30.opcao_2"),
//						perguntasSource.getString("teste30.exercicio30.opcao_4"))));
	}

	private void preenxerTeste60Questoes() {
		
	}

	private void preenxerTeste90Questoes() {

	}

}
