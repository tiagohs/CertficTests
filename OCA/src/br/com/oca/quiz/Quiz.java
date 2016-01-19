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

import br.com.oca.conteudo.ConteudoOCA;

public class Quiz extends JFrame implements ActionListener {
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
	private ConteudoOCA conteudo;
	private HashMap<Integer, String> map;

	public Quiz() {
		conteudo = ConteudoOCA.getInstance();
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
		btAjuda.addActionListener(this);
		btAjuda.setPreferredSize(new Dimension(180, 40));
		
		btProximo.addActionListener(this);
		btProximo.setPreferredSize(new Dimension(100, 40));
		
		regiaoAlternativas.add(btProximo);
		regiaoAlternativas.add(btAjuda);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if (btProximo.getText().equals("Próxima Questão")) {
			if (numeroQuestao < 9) {
				map.put(numeroQuestao, getOpcaoSelecionada());
				numeroQuestao++;
				setAlternativas(numeroQuestao);
			} else {
				map.put(numeroQuestao, getOpcaoSelecionada());
				btProximo.setText("Exibir Respostas.");
			}
		} else if (btProximo.getText().equals("Exibir Respostas.")) {
			new Resultados(map);
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

	public void setAlternativas(int qid) {

		lblQuestao.setText("  " + alternativas[qid][0]);
		opcaoA.setText(alternativas[qid][1]);
		opcaoB.setText(alternativas[qid][2]);
		opcaoC.setText(alternativas[qid][3]);
		opcaoD.setText(alternativas[qid][4]);
		opcaoA.setSelected(true);

	}

	public void reset() {

		numeroQuestao = 0;
		map.clear();
		setAlternativas(numeroQuestao);
		btProximo.setText("Próxima Questão");
	}
}
