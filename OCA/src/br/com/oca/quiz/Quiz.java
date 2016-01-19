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
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import br.com.oca.conteudo.OCA;

public class Quiz extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel regiaoAlternativas;
	private JPanel regiaoPergunta;
	private JRadioButton opcaoA;
	private JRadioButton opcaoB;
	private JRadioButton opcaoC;
	private JRadioButton opcaoD;
	private ButtonGroup buttonGroupOpcoes;
	private JLabel lblQuestao;
	private JButton btProximo;
	private JButton btAjuda;
	private String[][] alternativas;

	private int numeroQuestao;
	private OCA conteudo;
	private HashMap<Integer, String> map;

	public Quiz() {
		conteudo = OCA.getInstance();
		map = new HashMap<Integer, String>();
		alternativas = conteudo.getAlternativas();

		setTitle("Oracle Certified Associate, Java SE 7 Programmer");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(800, 700);
		setLocation(300, 100);
		setResizable(false);
		Container janelaPrincipal = getContentPane();
		janelaPrincipal.setLayout(null);
		
		preenxerJanelaRegiaoPerguntas();
		preenxerJanelaRegiaoAlternativas();
		preenxerJanelaRegiaoBotoes();
		
		janelaPrincipal.add(regiaoPergunta);
		janelaPrincipal.add(regiaoAlternativas);
		setVisible(true);
		numeroQuestao = 0;
		setAlternativas(numeroQuestao);
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
		
		opcaoA = new JRadioButton("Choice1", true);
		opcaoB = new JRadioButton("Choice2", false);
		opcaoC = new JRadioButton("Choice3", false);
		opcaoD = new JRadioButton("Choice4", false);
		
		buttonGroupOpcoes = new ButtonGroup();
		buttonGroupOpcoes.add(opcaoA);
		buttonGroupOpcoes.add(opcaoB);
		buttonGroupOpcoes.add(opcaoC);
		buttonGroupOpcoes.add(opcaoD);
		
		regiaoAlternativas.add(opcaoA);
		regiaoAlternativas.add(opcaoB);
		regiaoAlternativas.add(opcaoC);
		regiaoAlternativas.add(opcaoD);
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
						new Resultados(map);
				}
			}
		});
		btProximo.setPreferredSize(new Dimension(100, 40));
		
		regiaoAlternativas.add(btProximo);
		regiaoAlternativas.add(btAjuda);
	}
	
	private void verificaEstadoPerguntas() {

		if (numeroQuestao < conteudo.getNumeroMaximoQuestoes()-1) {
			map.put(numeroQuestao, getOpcaoSelecionada());
			numeroQuestao++;
			setAlternativas(numeroQuestao);
		} else {
			map.put(numeroQuestao, getOpcaoSelecionada());
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

	public void setAlternativas(int numeroQuestao) {

		lblQuestao.setText("  " + alternativas[numeroQuestao][0]);
		opcaoA.setText(alternativas[numeroQuestao][1]);
		opcaoB.setText(alternativas[numeroQuestao][2]);
		opcaoC.setText(alternativas[numeroQuestao][3]);
		opcaoD.setText(alternativas[numeroQuestao][4]);
		opcaoA.setSelected(true);

	}

	public void reset() {

		numeroQuestao = 0;
		map.clear();
		setAlternativas(numeroQuestao);
		btProximo.setText("Próxima Questão");
	}
}
