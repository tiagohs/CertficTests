package br.com.oca.quiz;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import br.com.oca.conteudo.Conteudo;
import br.com.oca.conteudo.OCA;
import br.com.oca.conteudo.Questao;
import br.com.oca.i18n.perguntas.PerguntasSource;

public class Quiz extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel regiaoAlternativas;
	private JPanel regiaoPergunta;
	private JRadioButton botaoAlternativa;
	private ButtonGroup buttonGroupOpcoes;
	private JLabel lblQuestao;
	private JButton btProximo;
	private JButton btAjuda;
	private String[][] alternativas;
	
	private PerguntasSource label;
	private String nomeTeste;
	private String tipoTeste;
	private int numeroQuestao;
	private Conteudo conteudo;
	private HashMap<Integer, Questao> opcoesSelecionadas;

	public Quiz(String idiomaTeste, String _nomeTeste, String _tipoTeste) {
		conteudo = OCA.getInstance(_nomeTeste, _tipoTeste);
		opcoesSelecionadas = new HashMap<Integer, Questao>();
		alternativas = conteudo.getAlternativas();

		setTitle("Oracle Certified Associate, Java SE 7 Programmer");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(800, 700);
		setLocation(300, 100);
		setResizable(false);
		Container janelaPrincipal = getContentPane();
		janelaPrincipal.setLayout(null);
		
		nomeTeste = _nomeTeste;
		tipoTeste = _tipoTeste;
		label = new PerguntasSource(idiomaTeste, nomeTeste);
		
		preenxerJanelaRegiaoPerguntas();
		preenxerJanelaRegiaoAlternativas();
		preenxerJanelaRegiaoBotoes();
		
		janelaPrincipal.add(regiaoPergunta);
		janelaPrincipal.add(regiaoAlternativas);
		setVisible(true);
		numeroQuestao = 0;
		setAlternativas(conteudo.getQuestao(numeroQuestao));
	}
	
	private void preenxerJanelaRegiaoPerguntas() {
		
		regiaoPergunta = new JPanel();
		regiaoPergunta.setLocation(10, 10);
		regiaoPergunta.setSize(780, 250);
		regiaoPergunta.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		
		lblQuestao = new JLabel("Escolha a Resposta Correta: ");
		lblQuestao.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 22));
		regiaoPergunta.add(lblQuestao);
	}
	
	private void preenxerJanelaRegiaoAlternativas() {
		regiaoAlternativas = new JPanel();
		regiaoAlternativas.setLayout(new GridLayout(6,2));
		regiaoAlternativas.setLocation(10, 290);
		regiaoAlternativas.setSize(780, 360);
		regiaoAlternativas.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		
		
	}
	
	private void preenxerJanelaRegiaoBotoes() {
		
		btProximo = new JButton("Próxima Questão");
		btAjuda = new JButton("Ajuda com a Questão");
		
		btAjuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btAjuda.setPreferredSize(new Dimension(180, 40));
		
		btProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				switch (btProximo.getText()) {
					case "Próxima Questão":
						verificaEstadoPerguntas();
					break;
					case "Exibir Respostas.":
						new Resultados(opcoesSelecionadas, conteudo);
				}
			}
		});
		btProximo.setPreferredSize(new Dimension(100, 40));
		
		regiaoAlternativas.add(btProximo);
		regiaoAlternativas.add(btAjuda);
	}
	
	private void verificaEstadoPerguntas() {
		
		opcoesSelecionadas.put(numeroQuestao, conteudo.getQuestao(numeroQuestao));
		if (numeroQuestao <= conteudo.getNumeroMaximoQuestoes()) {
			numeroQuestao++;
			setAlternativas(conteudo.getQuestao(numeroQuestao));
		} else {
			btProximo.setText("Exibir Respostas.");
		}
	}

	public String getOpcaoSelecionada() {
		String opcaoSelecionada = null;
		
		Enumeration<AbstractButton> buttons = buttonGroupOpcoes.getElements();

		while (buttons.hasMoreElements()) {
			JRadioButton temp = (JRadioButton) buttons.nextElement();
			
			if (temp.isSelected()) {
				opcaoSelecionada = temp.getText();
			}
		}

		return (opcaoSelecionada);
	}

	public void setAlternativas(Questao questao) {
		
		lblQuestao.setText("  " + questao.getEnunciado());
		buttonGroupOpcoes = new ButtonGroup();
		
		for (Map.Entry<Character, String> alternativa : questao.getlistaAlternativas().entrySet()) {
			botaoAlternativa = new JRadioButton(alternativa.getKey() + " - " + alternativa.getValue());
			buttonGroupOpcoes.add(botaoAlternativa);
			regiaoAlternativas.add(botaoAlternativa);
		}
		
	}

}
