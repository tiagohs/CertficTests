package br.com.oca.model.conteudo;

import br.com.oca.model.Questao;
import br.com.oca.model.enums.Certificacao;
import br.com.oca.model.enums.Idioma;
import br.com.oca.model.enums.TipoQuestao;
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
		
		Questao questao = new Questao(perguntasSource.getString("teste30.exercicio1.questao"), 'B', perguntasSource.getString("teste30.exercicio1.referencia")
				, "\n\nclass ABCD{\n" + "int x = 10;\n" + "static int y = 20;\n" + "}\n" + "class MNOP extends ABCD{\n" + "int x = 30;\n"
				+ "static int y = 40;\n" + "}\n" + "public class TestClass {\n" + "public static void main(String[] args) {\n" 
				+ "System.out.println(new MNOP().x\n" + "new MNOP().y);\n" + "}\n" + "}\n");
		questao.addAlternativa('A', perguntasSource.getString("teste30.exercicio1.opcao_a"));
		questao.addAlternativa('B', perguntasSource.getString("teste30.exercicio1.opcao_b"));
		questao.addAlternativa('C', perguntasSource.getString("teste30.exercicio1.opcao_c"));
		questao.addAlternativa('D', perguntasSource.getString("teste30.exercicio1.opcao_d"));
		questao.addAlternativa('E', perguntasSource.getString("teste30.exercicio1.opcao_e"));
		
		listaQuestoes.add(questao);
		
		questao = new Questao(perguntasSource.getString("teste30.exercicio2.questao"), 'D', perguntasSource.getString("teste30.exercicio2.referencia"), "");
		questao.addAlternativa('A', perguntasSource.getString("teste30.exercicio2.opcao_a"));
		questao.addAlternativa('B', perguntasSource.getString("teste30.exercicio2.opcao_b"));
		questao.addAlternativa('C', perguntasSource.getString("teste30.exercicio2.opcao_c"));
		questao.addAlternativa('D', perguntasSource.getString("teste30.exercicio2.opcao_d"));
		questao.addAlternativa('E', perguntasSource.getString("teste30.exercicio2.opcao_e"));
		
		listaQuestoes.add(questao);
		
		questao = new Questao(perguntasSource.getString("teste30.exercicio3.questao"), TipoQuestao.MULTIPLA, perguntasSource.getString("teste30.exercicio3.referencia"), "");
		questao.addAlternativa('A', perguntasSource.getString("teste30.exercicio3.opcao_a"));
		questao.addAlternativa('B', perguntasSource.getString("teste30.exercicio3.opcao_b"));
		questao.addAlternativa('C', perguntasSource.getString("teste30.exercicio3.opcao_c"));
		questao.addAlternativa('D', perguntasSource.getString("teste30.exercicio3.opcao_d"));
		questao.addAlternativa('E', perguntasSource.getString("teste30.exercicio3.opcao_e"));
		questao.addAlternativaCorreta(perguntasSource.getString("teste30.exercicio3.opcao_a"));
		questao.addAlternativaCorreta(perguntasSource.getString("teste30.exercicio3.opcao_b"));
		questao.addAlternativaCorreta(perguntasSource.getString("teste30.exercicio3.opcao_c"));
		questao.addAlternativaCorreta(perguntasSource.getString("teste30.exercicio3.opcao_d"));
		
		listaQuestoes.add(questao);
	}
}
