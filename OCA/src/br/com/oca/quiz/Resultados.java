package br.com.oca.quiz;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import br.com.oca.conteudo.Conteudo;
import br.com.oca.enums.Idioma;
import br.com.oca.enums.TipoTeste;
import br.com.oca.i18n.janelas.JanelasSource;
import br.com.oca.tentativas.Tentativa;
import br.com.oca.util.AppendingObjectOutputStream;

public class Resultados extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel regiaoResultados;
	private JScrollPane barraRolagem;
	
	private JanelasSource label;
	private Conteudo conteudo;
	private TipoTeste tipoTeste;
	private Integer numQuestoesCorretas;
	private Double nota;
	private HashMap<Integer, String> respostas;
	
	public Resultados(HashMap<Integer, String> _respostas, Conteudo _conteudo, Idioma idioma, TipoTeste _tipoTeste) {
		conteudo = _conteudo;
		respostas = _respostas;
		tipoTeste = _tipoTeste;
		label = JanelasSource.getInstance(idioma);
		
		setTitle(label.getString("resultadosTitulo"));
		setSize(850, 550);
		setLocation(300, 10);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		
		preenxerRegiaoResultados();
		setVisible(true);
	}
	
	private void preenxerRegiaoResultados() {
		int numeroQuestao = 1;
		
		regiaoResultados = new JPanel();
		regiaoResultados.setLayout(new BoxLayout(regiaoResultados, BoxLayout.Y_AXIS));
		regiaoResultados.setSize(780, 500);
		regiaoResultados.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1)); 
		barraRolagem = new JScrollPane(regiaoResultados);
		add(barraRolagem);
		
		inserirLinhaEmBranco();
		for (int count = 0; count < respostas.size(); count++) {
			setTextoEnunciado(count, numeroQuestao);
			inserirLinhaEmBranco();
			setTextoRespostas(count);
			inserirLinhaEmBranco();
			inserirLinhaEmBranco();
			numeroQuestao++;
		}
		
		numQuestoesCorretas = calcularNumeroQuestoesCorretas();
		System.out.println(calcularNota());
		nota = calcularNota();
		registrarTentativa();
		inserirLinha(Color.BLUE, new Font("Arial", Font.BOLD, 14), "                                 " + label.getString("resultadosVocêAcertou") + " " + numQuestoesCorretas + " Questões - Sua Nota: " + nota + " de 100.0.\n");
	}
	
	private void setTextoEnunciado(int count, int numeroQuestao) {
		inserirLinha(Color.BLACK, new Font("Arial",Font.PLAIN, 12), "    " + numeroQuestao + ". " + conteudo.getQuestao(count).getEnunciado());
	}
	
	private void setTextoRespostas(int count) {
		
		inserirLinha(Color.BLACK, new Font("Arial",Font.BOLD, 12), "       " + label.getString("resultadosResposCorreta") + conteudo.getQuestao(count).getEnunciadoAlternativaCorreta());
		if (isRespostaCorreta(count)) 
			inserirLinha(Color.GREEN, new Font("Arial",Font.BOLD, 14), "     " + label.getString("resultadosSuaResposta") + respostas.get(count));
		 else 
			inserirLinha(Color.RED, new Font("Arial", Font.BOLD, 14), "     " + label.getString("resultadosSuaResposta") + respostas.get(count));
	}
	
	private void inserirLinha(Color cor, Font fonte, String texto) {
		JLabel label = new JLabel();
		label.setForeground(cor);
		label.setFont(fonte);
		label.setText(texto);
		
		regiaoResultados.add(label);
	}
	
	private void inserirLinhaEmBranco() {
		regiaoResultados.add(new JLabel("  "));
	}

	private int calcularNumeroQuestoesCorretas() {
		int numeroQuestoesCorretas = 0;

		for (int count = 0; count < conteudo.getTotalQuestoes(); count++) {
			if (isRespostaCorreta(count))
				numeroQuestoesCorretas++;
		}

		return numeroQuestoesCorretas;
	}
	
	private boolean isRespostaCorreta(int count) {
		return conteudo.getQuestao(count).getEnunciadoAlternativaCorreta().equals(respostas.get(count));
	}
	
	private void registrarTentativa() {
		File file = new File ("tentativas.bin");
        ObjectOutputStream out = null;

        try {
            if (!file.exists ()) 
            	out = new ObjectOutputStream (new FileOutputStream ("tentativas.bin"));
            else 
            	out = new AppendingObjectOutputStream (new FileOutputStream ("tentativas.bin", true));
            out.writeObject(new Tentativa(tipoTeste, nota));
            out.flush ();
            out.close();
        } catch (Exception e){
            e.printStackTrace ();
        } 
	}
	
	private Double calcularNota() {
		double media = (double) numQuestoesCorretas / conteudo.getTotalQuestoes();
		return media * 100;
	}
}
