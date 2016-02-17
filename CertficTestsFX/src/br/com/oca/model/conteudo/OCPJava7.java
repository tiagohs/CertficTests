package br.com.oca.model.conteudo;

import java.util.ArrayList;
import java.util.Arrays;

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

		addQuestao(TESTE_30_QUESTOES, 1,
				new ArrayList<String>(Arrays.asList(perguntasSource.getString("teste30.exercicio1.opcao_0"),
						perguntasSource.getString("teste30.exercicio1.opcao_2"),
						perguntasSource.getString("teste30.exercicio1.opcao_3"))));

		addQuestao(TESTE_30_QUESTOES, 2, 'A');
		addQuestao(TESTE_30_QUESTOES, 3, 'A');
		addQuestao(TESTE_30_QUESTOES, 4, 'E');

		addQuestao(TESTE_30_QUESTOES, 5,
				new ArrayList<String>(Arrays.asList(perguntasSource.getString("teste30.exercicio5.opcao_0"),
						perguntasSource.getString("teste30.exercicio5.opcao_1"))));

		addQuestao(TESTE_30_QUESTOES, 6, 'E');
		addQuestao(TESTE_30_QUESTOES, 7, 'D');
		addQuestao(TESTE_30_QUESTOES, 8, 'D');

		addQuestao(TESTE_30_QUESTOES, 9,
				new ArrayList<String>(Arrays.asList(perguntasSource.getString("teste30.exercicio9.opcao_0"),
						perguntasSource.getString("teste30.exercicio9.opcao_2"),
						perguntasSource.getString("teste30.exercicio9.opcao_3"))));

		addQuestao(TESTE_30_QUESTOES, 10,
				new ArrayList<String>(Arrays.asList(perguntasSource.getString("teste30.exercicio10.opcao_1"),
						perguntasSource.getString("teste30.exercicio10.opcao_2"))));

		addQuestao(TESTE_30_QUESTOES, 11, 'C');
		addQuestao(TESTE_30_QUESTOES, 12, 'B');

		addQuestao(TESTE_30_QUESTOES, 13,
				new ArrayList<String>(Arrays.asList(perguntasSource.getString("teste30.exercicio13.opcao_0"),
						perguntasSource.getString("teste30.exercicio13.opcao_4"))));

		addQuestao(TESTE_30_QUESTOES, 14,
				new ArrayList<String>(Arrays.asList(perguntasSource.getString("teste30.exercicio14.opcao_1"),
						perguntasSource.getString("teste30.exercicio14.opcao_2"),
						perguntasSource.getString("teste30.exercicio14.opcao_3"),
						perguntasSource.getString("teste30.exercicio14.opcao_4"))));

		addQuestao(TESTE_30_QUESTOES, 15, 'B');
		addQuestao(TESTE_30_QUESTOES, 16, 'E');

		addQuestao(TESTE_30_QUESTOES, 17,
				new ArrayList<String>(Arrays.asList(perguntasSource.getString("teste30.exercicio17.opcao_0"),
						perguntasSource.getString("teste30.exercicio17.opcao_1"),
						perguntasSource.getString("teste30.exercicio17.opcao_2"))));

		addQuestao(TESTE_30_QUESTOES, 18, 'D');

		addQuestao(TESTE_30_QUESTOES, 19,
				new ArrayList<String>(Arrays.asList(perguntasSource.getString("teste30.exercicio19.opcao_0"),
						perguntasSource.getString("teste30.exercicio19.opcao_2"))));

		addQuestao(TESTE_30_QUESTOES, 20, 'D');

		addQuestao(TESTE_30_QUESTOES, 21,
				new ArrayList<String>(Arrays.asList(perguntasSource.getString("teste30.exercicio21.opcao_1"),
						perguntasSource.getString("teste30.exercicio21.opcao_2"),
						perguntasSource.getString("teste30.exercicio21.opcao_3"))));

		addQuestao(TESTE_30_QUESTOES, 22, 'B');

		addQuestao(TESTE_30_QUESTOES, 23,
				new ArrayList<String>(Arrays.asList(perguntasSource.getString("teste30.exercicio23.opcao_0"),
						perguntasSource.getString("teste30.exercicio23.opcao_1"),
						perguntasSource.getString("teste30.exercicio23.opcao_3"),
						perguntasSource.getString("teste30.exercicio23.opcao_4"))));

		addQuestao(TESTE_30_QUESTOES, 24,
				new ArrayList<String>(Arrays.asList(perguntasSource.getString("teste30.exercicio24.opcao_0"),
						perguntasSource.getString("teste30.exercicio24.opcao_1"))));

		addQuestao(TESTE_30_QUESTOES, 25, 'C');
		addQuestao(TESTE_30_QUESTOES, 26, 'E');
		addQuestao(TESTE_30_QUESTOES, 27, 'D');
		
		addQuestao(TESTE_30_QUESTOES, 28,
				new ArrayList<String>(Arrays.asList(perguntasSource.getString("teste30.exercicio28.opcao_2"),
						perguntasSource.getString("teste30.exercicio28.opcao_3"))));
		
		addQuestao(TESTE_30_QUESTOES, 29,
				new ArrayList<String>(Arrays.asList(perguntasSource.getString("teste30.exercicio29.opcao_1"),
						perguntasSource.getString("teste30.exercicio29.opcao_3"))));
		
		addQuestao(TESTE_30_QUESTOES, 30,
				new ArrayList<String>(Arrays.asList(perguntasSource.getString("teste30.exercicio30.opcao_0"),
						perguntasSource.getString("teste30.exercicio30.opcao_1"),
						perguntasSource.getString("teste30.exercicio30.opcao_3"))));
	}



	private void preenxerTeste60Questoes() {

	}

	private void preenxerTeste90Questoes() {

	}

}
