package br.com.oca.quiz;
import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import br.com.oca.conteudo.Conteudo;
import br.com.oca.conteudo.Questao;

public class Resultados extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel regiaoResultados;
	private JScrollPane barraRolagem;
	
	private Conteudo conteudo;
	private HashMap<Integer, String> respostas;
	
	public Resultados(HashMap<Integer, String> _respostas, Conteudo _conteudo) {
		conteudo = _conteudo;
		respostas = _respostas;
		
		setTitle("Resultados | CertificTests");
		setSize(850, 550);
		setLocation(300, 10);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		
		regiaoResultados = new JPanel();
		regiaoResultados.setLayout(new BoxLayout(regiaoResultados, BoxLayout.Y_AXIS));
		regiaoResultados.setSize(780, 500);
		regiaoResultados.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1)); 
		barraRolagem = new JScrollPane(regiaoResultados);
		
		add(barraRolagem);
		preenxer();
		setVisible(true);
	}
	
	private void preenxer() {
		int numeroQuestao = 1;
		
		regiaoResultados.add(new JLabel("  "));
		for (int count = 0; count < conteudo.getTotalQuestoes(); count++) {
			inserirLinha(Color.BLACK, new Font("Arial",Font.PLAIN, 12), "    " + numeroQuestao++ + ". " + conteudo.getQuestao(count).getEnunciado());
			regiaoResultados.add(new JLabel("  "));
			inserirLinha(Color.BLACK, new Font("Arial",Font.BOLD, 12), "       Resposta Correta: " + conteudo.getQuestao(count).getEnunciadoAlternativaCorreta());

			if (conteudo.getQuestao(count).getEnunciadoAlternativaCorreta().equals(respostas.get(count))) 
				inserirLinha(Color.GREEN, new Font("Arial",Font.BOLD, 14), "     Sua Resposta:: " + respostas.get(count));
			 else 
				inserirLinha(Color.RED, new Font("Arial", Font.BOLD, 14), "     Sua Resposta:: " + respostas.get(count));
			
			regiaoResultados.add(new JLabel("  "));
			regiaoResultados.add(new JLabel("  "));
		}
		
		inserirLinha(Color.BLUE, new Font("Arial", Font.BOLD, 14), "                                 Você Acertou " + getNumeroQuestoesCorretas() + " Questões.\n");
	}
	
	private void inserirLinha(Color cor, Font fonte, String texto) {
		JLabel label = new JLabel();
		label.setForeground(cor);
		label.setFont(fonte);
		label.setText(texto);
		
		regiaoResultados.add(label);
	}

	private int getNumeroQuestoesCorretas() {
		int numeroQuestoesCorretas = 0;

		for (int count = 0; count < conteudo.getTotalQuestoes(); count++) {
			if (conteudo.getQuestao(count).getEnunciadoAlternativaCorreta().equals(respostas.get(count)))
				numeroQuestoesCorretas++;
		}

		return numeroQuestoesCorretas;
	}
	
}
