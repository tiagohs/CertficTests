package br.com.oca.conteudo;

import br.com.oca.enums.Certificacao;
import br.com.oca.enums.Idioma;

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
		
		Questao questao = new Questao(perguntasSource.getString("exercicio1.questao"), 'C');
		questao.addAlternativa('A', perguntasSource.getString("exercicio1.opcao_a"));
		questao.addAlternativa('B', perguntasSource.getString("exercicio1.opcao_b"));
		questao.addAlternativa('C', perguntasSource.getString("exercicio1.opcao_c"));
		questao.addAlternativa('D', perguntasSource.getString("exercicio1.opcao_d"));
		questao.addAlternativa('E', perguntasSource.getString("exercicio1.opcao_e"));
		
		listaQuestoes.add(questao);
		
		questao = new Questao(perguntasSource.getString("exercicio2.questao"), 'A');
		questao.addAlternativa('A', perguntasSource.getString("exercicio2.opcao_a"));
		questao.addAlternativa('B', perguntasSource.getString("exercicio2.opcao_b"));
		
		listaQuestoes.add(questao);
		
	}
}
