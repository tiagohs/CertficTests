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
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import br.com.oca.conteudo.Conteudo;
import br.com.oca.conteudo.OCA;
import br.com.oca.conteudo.Questao;

public class Quiz extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel regiaoAlternativas;
	private JPanel regiaoPergunta;
	private JPanel regiaoBotoes;
	private Container janelaPrincipal;
	private JRadioButton botaoAlternativa;
	private ButtonGroup buttonGroupOpcoes;
	private JButton btProximo;
	private JTextArea txtEnunciado;
	private JButton btAjuda;

	private int numeroQuestao;
	private Conteudo conteudo;
	private HashMap<Integer, String> opcoesSelecionadas;

	public Quiz(String idiomaTeste, String nomeTeste, String tipoTeste) {
		conteudo = OCA.getInstance(nomeTeste, idiomaTeste);
		opcoesSelecionadas = new HashMap<Integer, String>();
		numeroQuestao = 0;
		
		setTitle("Oracle Certified Associate, Java SE 7 Programmer");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(800, 700);
		setLocation(300, 10);
		setResizable(false);
		janelaPrincipal = getContentPane();
		janelaPrincipal.setLayout(null);

		preenxerJanelaRegiaoPerguntas();
		preenxerJanelaRegiaoAlternativas();
		preenxerJanelaRegiaoBotoes();

		janelaPrincipal.add(regiaoPergunta);
		janelaPrincipal.add(regiaoAlternativas);
		janelaPrincipal.add(regiaoBotoes);
		setVisible(true);
	}

	private void preenxerJanelaRegiaoPerguntas() {

		regiaoPergunta = new JPanel();
		regiaoPergunta.setLayout(new BoxLayout(regiaoPergunta, BoxLayout.PAGE_AXIS));
		regiaoPergunta.setLocation(10, 80);
		regiaoPergunta.setSize(780, 150);
		regiaoPergunta.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

		txtEnunciado = new JTextArea(7, 40);
		txtEnunciado.setEditable(false);
		txtEnunciado.setForeground(Color.BLACK);
		txtEnunciado.setOpaque(false);
		txtEnunciado.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 22));
		txtEnunciado.setLineWrap(true);
		txtEnunciado.setWrapStyleWord(true);
	
		regiaoPergunta.add(txtEnunciado);
		
	}
	
	private void preenxerJanelaRegiaoAlternativas() {

		regiaoAlternativas = new JPanel();
		regiaoAlternativas.setLayout(new GridLayout(6, 2));
		regiaoAlternativas.setLocation(10, 280);
		regiaoAlternativas.setSize(780, 340);
		regiaoAlternativas.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		
		setAlternativas(conteudo.getQuestao(numeroQuestao));
	}

	private void preenxerJanelaRegiaoBotoes() {

		regiaoBotoes = new JPanel();
		regiaoBotoes.setLocation(10, 620);
		regiaoBotoes.setSize(780, 100);

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
						setVisible(false);
						new Resultados(opcoesSelecionadas, conteudo);
				}
			}
		});
		btProximo.setPreferredSize(new Dimension(160, 40));

		regiaoBotoes.add(btProximo);
		regiaoBotoes.add(btAjuda);
	}

	private void verificaEstadoPerguntas() {

		opcoesSelecionadas.put(numeroQuestao, getOpcaoSelecionada());

		if (numeroQuestao < (conteudo.getTotalQuestoes() - 1)) {
			numeroQuestao++;
			regiaoAlternativas.removeAll();
			regiaoAlternativas.setForeground(Color.LIGHT_GRAY);
			setAlternativas(conteudo.getQuestao(numeroQuestao));
		} else {
			btProximo.setText("Exibir Respostas.");
		}
	}

	private String getOpcaoSelecionada() {
		
		for (Enumeration<AbstractButton> buttons = buttonGroupOpcoes.getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();

			if (button.isSelected()) 
				return button.getToolTipText();
		}

		return null;
	}

	public void setAlternativas(Questao questao) {
		
		txtEnunciado.setText(questao.getEnunciado());
		
		buttonGroupOpcoes = new ButtonGroup();
		for (Map.Entry<Character, String> alternativa : questao.getlistaAlternativas().entrySet()) {
			botaoAlternativa = new JRadioButton(alternativa.getKey() + " - " + alternativa.getValue());
			botaoAlternativa.setToolTipText(alternativa.getValue());
			buttonGroupOpcoes.add(botaoAlternativa);
			regiaoAlternativas.add(botaoAlternativa);
		}
		
		buttonGroupOpcoes.getElements().nextElement().setSelected(true);
	}

}
