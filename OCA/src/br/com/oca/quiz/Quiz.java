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
import br.com.oca.enums.Certificacao;
import br.com.oca.enums.Idioma;
import br.com.oca.enums.TipoTeste;
import br.com.oca.i18n.janelas.JanelasSource;

public class Quiz extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel regiaoAlternativas;
	private JPanel regiaoEnunciado;
	private JPanel regiaoBotoes;
	private Container janelaPrincipal;
	private JRadioButton botaoAlternativa;
	private ButtonGroup buttonGroupOpcoes;
	private JButton btProximo;
	private JTextArea txtEnunciado;
	private JButton btAjuda;
	
	private JanelasSource label;
	private int numeroQuestao;
	private Idioma idioma;
	private Certificacao exame;
	private TipoTeste tipoTeste;
	private Conteudo conteudo;
	private HashMap<Integer, String> opcoesSelecionadas;

	public Quiz(Idioma idiomaTeste, Certificacao _exame, TipoTeste _tipoTeste) {
		idioma = idiomaTeste;
		tipoTeste = _tipoTeste;
		exame = _exame;
		conteudo = OCA.getInstance(exame, idioma);
		opcoesSelecionadas = new HashMap<Integer, String>();
		label = JanelasSource.getInstance(idioma);
		numeroQuestao = 0;
		
		setTitle(exame.getNome());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(800, 700);
		setLocation(300, 10);
		setResizable(false);
		janelaPrincipal = getContentPane();
		janelaPrincipal.setLayout(null);

		preenxerJanelaRegiaoEnunciado();
		preenxerJanelaRegiaoAlternativas();
		preenxerJanelaRegiaoBotoes();

		setVisible(true);
	}

	private void preenxerJanelaRegiaoEnunciado() {

		regiaoEnunciado = new JPanel();
		regiaoEnunciado.setLayout(new BoxLayout(regiaoEnunciado, BoxLayout.PAGE_AXIS));
		regiaoEnunciado.setLocation(10, 80);
		regiaoEnunciado.setSize(780, 150);
		regiaoEnunciado.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

		txtEnunciado = new JTextArea(7, 40);
		txtEnunciado.setEditable(false);
		txtEnunciado.setForeground(Color.BLACK);
		txtEnunciado.setOpaque(false);
		txtEnunciado.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 22));
		txtEnunciado.setLineWrap(true);
		txtEnunciado.setWrapStyleWord(true);
	
		regiaoEnunciado.add(txtEnunciado);
		janelaPrincipal.add(regiaoEnunciado);
	}
	
	private void preenxerJanelaRegiaoAlternativas() {

		regiaoAlternativas = new JPanel();
		regiaoAlternativas.setLayout(new GridLayout(6, 2));
		regiaoAlternativas.setLocation(10, 280);
		regiaoAlternativas.setSize(780, 340);
		regiaoAlternativas.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		
		setTextoQuestao(conteudo.getQuestao(numeroQuestao));
		janelaPrincipal.add(regiaoAlternativas);
	}

	private void preenxerJanelaRegiaoBotoes() {

		regiaoBotoes = new JPanel();
		regiaoBotoes.setLocation(10, 620);
		regiaoBotoes.setSize(780, 100);

		btProximo = new JButton(label.getString("botaoProximo"));
		btAjuda = new JButton(label.getString("botaoAjuda"));
		
		btAjuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btAjuda.setPreferredSize(new Dimension(180, 40));

		btProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verificaEstadoPerguntas();
			}
		});
		btProximo.setPreferredSize(new Dimension(160, 40));

		regiaoBotoes.add(btProximo);
		regiaoBotoes.add(btAjuda);
		janelaPrincipal.add(regiaoBotoes);
	}

	private void verificaEstadoPerguntas() {

		opcoesSelecionadas.put(numeroQuestao, getOpcaoSelecionada());

		if (numeroQuestao < (conteudo.getTotalQuestoes() - 1)) {
			numeroQuestao++;
			regiaoAlternativas.removeAll();
			regiaoAlternativas.setForeground(Color.LIGHT_GRAY);
			setTextoQuestao(conteudo.getQuestao(numeroQuestao));
		} else {
			btProximo.setText(label.getString("botaoExibirRespostas"));
			btProximo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					new Resultados(opcoesSelecionadas, conteudo, idioma, tipoTeste, exame);
				}
			});
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

	public void setTextoQuestao(Questao questao) {
		
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

	public JPanel getRegiaoAlternativas() {
		return regiaoAlternativas;
	}

	public void setRegiaoAlternativas(JPanel regiaoAlternativas) {
		this.regiaoAlternativas = regiaoAlternativas;
	}

	public JPanel getRegiaoEnunciado() {
		return regiaoEnunciado;
	}

	public void setRegiaoEnunciado(JPanel regiaoEnunciado) {
		this.regiaoEnunciado = regiaoEnunciado;
	}

	public JPanel getRegiaoBotoes() {
		return regiaoBotoes;
	}

	public void setRegiaoBotoes(JPanel regiaoBotoes) {
		this.regiaoBotoes = regiaoBotoes;
	}

	public Container getJanelaPrincipal() {
		return janelaPrincipal;
	}

	public void setJanelaPrincipal(Container janelaPrincipal) {
		this.janelaPrincipal = janelaPrincipal;
	}

	public JRadioButton getBotaoAlternativa() {
		return botaoAlternativa;
	}

	public void setBotaoAlternativa(JRadioButton botaoAlternativa) {
		this.botaoAlternativa = botaoAlternativa;
	}

	public ButtonGroup getButtonGroupOpcoes() {
		return buttonGroupOpcoes;
	}

	public void setButtonGroupOpcoes(ButtonGroup buttonGroupOpcoes) {
		this.buttonGroupOpcoes = buttonGroupOpcoes;
	}

	public JButton getBtProximo() {
		return btProximo;
	}

	public void setBtProximo(JButton btProximo) {
		this.btProximo = btProximo;
	}

	public JTextArea getTxtEnunciado() {
		return txtEnunciado;
	}

	public void setTxtEnunciado(JTextArea txtEnunciado) {
		this.txtEnunciado = txtEnunciado;
	}

	public JButton getBtAjuda() {
		return btAjuda;
	}

	public void setBtAjuda(JButton btAjuda) {
		this.btAjuda = btAjuda;
	}

	public int getNumeroQuestao() {
		return numeroQuestao;
	}

	public void setNumeroQuestao(int numeroQuestao) {
		this.numeroQuestao = numeroQuestao;
	}

	public Conteudo getConteudo() {
		return conteudo;
	}

	public void setConteudo(Conteudo conteudo) {
		this.conteudo = conteudo;
	}

	public HashMap<Integer, String> getOpcoesSelecionadas() {
		return opcoesSelecionadas;
	}

	public void setOpcoesSelecionadas(HashMap<Integer, String> opcoesSelecionadas) {
		this.opcoesSelecionadas = opcoesSelecionadas;
	}
	
}
