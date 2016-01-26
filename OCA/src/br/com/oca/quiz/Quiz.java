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
	private JRadioButton radioAlternativa;
	private ButtonGroup buttonGroupOpcoes;
	private JButton botaoProximo;
	private JButton botaoAjuda;
	private JTextArea txtEnunciado;

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

		botaoProximo = new JButton(label.getString("botaoProximo"));
		botaoAjuda = new JButton(label.getString("botaoAjuda"));
		
		botaoAjuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		botaoAjuda.setPreferredSize(new Dimension(180, 40));

		botaoProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verificaEstadoPerguntas();
			}
		});
		botaoProximo.setPreferredSize(new Dimension(160, 40));

		regiaoBotoes.add(botaoProximo);
		regiaoBotoes.add(botaoAjuda);
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
			botaoProximo.setText(label.getString("botaoExibirRespostas"));
			botaoProximo.addActionListener(new ActionListener() {
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
			radioAlternativa = new JRadioButton(alternativa.getKey() + " - " + alternativa.getValue());
			radioAlternativa.setToolTipText(alternativa.getValue());
			buttonGroupOpcoes.add(radioAlternativa);
			regiaoAlternativas.add(radioAlternativa);
		}
		
		buttonGroupOpcoes.getElements().nextElement().setSelected(true);
	}

	public JanelasSource getLabel() {
		return label;
	}

	public void setLabel(JanelasSource label) {
		this.label = label;
	}

	public Idioma getIdioma() {
		return idioma;
	}

	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}

	public Certificacao getExame() {
		return exame;
	}

	public void setExame(Certificacao exame) {
		this.exame = exame;
	}

	public TipoTeste getTipoTeste() {
		return tipoTeste;
	}

	public void setTipoTeste(TipoTeste tipoTeste) {
		this.tipoTeste = tipoTeste;
	}

	public Conteudo getConteudo() {
		return conteudo;
	}

	public void setConteudo(Conteudo conteudo) {
		this.conteudo = conteudo;
	}
	
}
