package br.com.oca.model.conteudo;

import java.util.ArrayList;
import java.util.Arrays;

import br.com.oca.model.enums.Certificacao;
import br.com.oca.model.enums.Idioma;
import br.com.oca.model.enums.TipoTeste;

public class OCA extends Conteudo {
	private volatile static OCA instance;

	private OCA(Certificacao nomeTeste, Idioma idiomaTeste, TipoTeste tipoTeste) {
		super(nomeTeste, idiomaTeste, tipoTeste);
	}

	public static OCA getInstance(Certificacao nomeTeste, Idioma idiomaTeste, TipoTeste tipoTeste) {

		if (instance == null) {
			synchronized (OCA.class) {
				if (instance == null) {
					instance = new OCA(nomeTeste, idiomaTeste, tipoTeste);
				}
			}
		}

		return instance;
	}

	@Override
	protected void preenxerQuestoes() {
		
		addQuestao(TESTE_30_QUESTOES, 1, "\n\nclass ABCD{\n" + "int x = 10;\n" + "static int y = 20;\n" + "}\n" + "class MNOP extends ABCD{\n"
						+ "int x = 30;\n" + "static int y = 40;\n" + "}\n" + "public class TestClass {\n"
						+ "public static void main(String[] args) {\n" + "System.out.println(new MNOP().x\n"
						+ "new MNOP().y);\n" + "}\n" + "}\n", 'B');
		addQuestao(TESTE_30_QUESTOES, 2, "", 'D');
		addQuestao(TESTE_30_QUESTOES, 3, "", new ArrayList<String>(Arrays.asList(perguntasSource.getString("teste30.exercicio3.opcao_0"),
						perguntasSource.getString("teste30.exercicio3.opcao_1"),
						perguntasSource.getString("teste30.exercicio3.opcao_2"),
						perguntasSource.getString("teste30.exercicio3.opcao_3"))));

	}

}
