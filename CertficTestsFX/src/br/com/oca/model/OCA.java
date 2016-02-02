package br.com.oca.model;

import br.com.oca.model.enums.Certificacao;
import br.com.oca.model.enums.Idioma;
import br.com.oca.model.enums.TipoQuestao;

public class OCA extends Conteudo {
	private volatile static OCA instance;

	private OCA(Certificacao nomeTeste, Idioma idiomaTeste) {
		super(nomeTeste, idiomaTeste);
	}

	public static OCA getInstance(Certificacao nomeTeste, Idioma idiomaTeste) {

		if (instance == null) {
			synchronized (OCA.class) {
				if (instance == null) {
					instance = new OCA(nomeTeste, idiomaTeste);
				}
			}
		}

		return instance;
	}

	@Override
	protected void preenxerQuestoes() {
		
		Questao questao = new Questao(perguntasSource.getString("teste30.exercicio1.questao"), 'B');
		questao.addAlternativa('A', perguntasSource.getString("teste30.exercicio1.opcao_a"));
		questao.addAlternativa('B', perguntasSource.getString("teste30.exercicio1.opcao_b"));
		questao.addAlternativa('C', perguntasSource.getString("teste30.exercicio1.opcao_c"));
		questao.addAlternativa('D', perguntasSource.getString("teste30.exercicio1.opcao_d"));
		questao.addAlternativa('E', perguntasSource.getString("teste30.exercicio1.opcao_e"));
		
		listaQuestoes.add(questao);
		
		questao = new Questao(perguntasSource.getString("teste30.exercicio2.questao"), 'D');
		questao.addAlternativa('A', perguntasSource.getString("teste30.exercicio2.opcao_a"));
		questao.addAlternativa('B', perguntasSource.getString("teste30.exercicio2.opcao_b"));
		questao.addAlternativa('C', perguntasSource.getString("teste30.exercicio2.opcao_c"));
		questao.addAlternativa('D', perguntasSource.getString("teste30.exercicio2.opcao_d"));
		questao.addAlternativa('E', perguntasSource.getString("teste30.exercicio2.opcao_e"));
		questao.addAlternativa('F', perguntasSource.getString("teste30.exercicio2.opcao_f"));
		
		listaQuestoes.add(questao);
		
		questao = new Questao(perguntasSource.getString("teste30.exercicio3.questao"), TipoQuestao.MULTIPLA);
		questao.addAlternativa('A', perguntasSource.getString("teste30.exercicio3.opcao_a"));
		questao.addAlternativa('B', perguntasSource.getString("teste30.exercicio3.opcao_b"));
		questao.addAlternativa('C', perguntasSource.getString("teste30.exercicio3.opcao_c"));
		questao.addAlternativa('D', perguntasSource.getString("teste30.exercicio3.opcao_d"));
		questao.addAlternativa('E', perguntasSource.getString("teste30.exercicio3.opcao_e"));
		questao.addAlternativaCorreta('A');
		questao.addAlternativaCorreta('B');
		questao.addAlternativaCorreta('C');
		questao.addAlternativaCorreta('D');
		
		listaQuestoes.add(questao);
	}
}
