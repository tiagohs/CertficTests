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

public class Quiz extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private JPanel janelaPrincipal;
	private JRadioButton opcaoA;
	private JRadioButton opcaoB;
	private JRadioButton opcaoC;
	private JRadioButton opcaoD;
	private ButtonGroup buttonGroupOpcoes;
	private JLabel lblmess;
	private JButton btnext;
	private String[][] alternativas;

	private int numeroQuestao;
	private Conteudo conteudo;
	private HashMap<Integer, String> map;

	public Quiz() {
		conteudo = Conteudo.getInstance();
		map = new HashMap<Integer, String>();
		alternativas = conteudo.getAlternativas();

		setTitle("Oracle Certified Associate, Java SE 7 Programmer");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 700);
		setLocation(300, 100);
		setResizable(false);
		Container cont = getContentPane();
		cont.setLayout(null);

		buttonGroupOpcoes = new ButtonGroup();
		
		opcaoA = new JRadioButton("Choice1", true);
		opcaoB = new JRadioButton("Choice2", false);
		opcaoC = new JRadioButton("Choice3", false);
		opcaoD = new JRadioButton("Choice4", false);

		buttonGroupOpcoes.add(opcaoA);
		buttonGroupOpcoes.add(opcaoB);
		buttonGroupOpcoes.add(opcaoC);
		buttonGroupOpcoes.add(opcaoD);

		lblmess = new JLabel("Escolha a Resposta Correta: ");
		lblmess.setForeground(Color.BLUE);
		lblmess.setFont(new Font("Arial", Font.BOLD, 11));
		btnext = new JButton("Próxima Questão");
		
		btnext.addActionListener(this);
		btnext.setPreferredSize(new Dimension(300, 200));
		janelaPrincipal = new JPanel();
		janelaPrincipal.setLocation(10, 10);
		janelaPrincipal.setSize(780, 650);
		janelaPrincipal.setLayout(new GridLayout(6, 4));
		janelaPrincipal.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		janelaPrincipal.add(lblmess);
		janelaPrincipal.add(opcaoA);
		janelaPrincipal.add(opcaoB);
		janelaPrincipal.add(opcaoC);
		janelaPrincipal.add(opcaoD);

		janelaPrincipal.add(btnext);
		cont.add(janelaPrincipal);
		setVisible(true);
		numeroQuestao = 0;
		setAlternativas(numeroQuestao);
	}

	public void actionPerformed(ActionEvent e) {
		
		if (btnext.getText().equals("Próxima Questão")) {
			if (numeroQuestao < 9) {
				map.put(numeroQuestao, getOpcaoSelecionada());
				numeroQuestao++;
				setAlternativas(numeroQuestao);
			} else {
				map.put(numeroQuestao, getOpcaoSelecionada());
				btnext.setText("Exibir Respostas.");
			}
		} else if (btnext.getText().equals("Exibir Respostas.")) {
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

		lblmess.setText("  " + alternativas[qid][0]);
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
		btnext.setText("Próxima Questão");
	}
}
