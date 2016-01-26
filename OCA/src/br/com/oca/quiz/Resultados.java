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
import br.com.oca.enums.Certificacao;
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
	private Certificacao exame;
	private Integer numQuestoesCorretas;
	private Double nota;
	private HashMap<Integer, String> respostas;
	
	public Resultados(HashMap<Integer, String> _respostas, Conteudo _conteudo, Idioma idioma, TipoTeste _tipoTeste, Certificacao _exame) {
		conteudo = _conteudo;
		respostas = _respostas;
		tipoTeste = _tipoTeste;
		label = JanelasSource.getInstance(idioma);
		exame = _exame;
		
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
		inserirLinha(Color.BLUE, new Font("Arial", Font.BOLD, 14), "                                 " + label.getString("resultadosVoc�Acertou") + " " + numQuestoesCorretas + " Quest�es - Sua Nota: " + nota + " de 100.0.\n");
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
		File file = new File (Tentativa.filename);
        ObjectOutputStream out = null;

        try {
            if (!file.exists ()) 
            	out = new ObjectOutputStream (new FileOutputStream (Tentativa.filename));
            else 
            	out = new AppendingObjectOutputStream (new FileOutputStream (Tentativa.filename, true));
            out.writeObject(new Tentativa(exame, tipoTeste, nota, numQuestoesCorretas));
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

	public JanelasSource getLabel() {
		return label;
	}

	public void setLabel(JanelasSource label) {
		this.label = label;
	}

	public Conteudo getConteudo() {
		return conteudo;
	}

	public void setConteudo(Conteudo conteudo) {
		this.conteudo = conteudo;
	}

	public TipoTeste getTipoTeste() {
		return tipoTeste;
	}

	public void setTipoTeste(TipoTeste tipoTeste) {
		this.tipoTeste = tipoTeste;
	}

	public Certificacao getExame() {
		return exame;
	}

	public void setExame(Certificacao exame) {
		this.exame = exame;
	}

	public Integer getNumQuestoesCorretas() {
		return numQuestoesCorretas;
	}

	public void setNumQuestoesCorretas(Integer numQuestoesCorretas) {
		this.numQuestoesCorretas = numQuestoesCorretas;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	public HashMap<Integer, String> getRespostas() {
		return respostas;
	}

	public void setRespostas(HashMap<Integer, String> respostas) {
		this.respostas = respostas;
	}
	
}
