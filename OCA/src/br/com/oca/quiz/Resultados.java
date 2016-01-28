package br.com.oca.quiz;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
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
import br.com.oca.welcome.JanelaInicial;

public class Resultados extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel regiaoResultados;
	private JPanel regiaoBotoes;
	private JButton botaoVoltar;
	private JScrollPane barraRolagem;
	
	private JanelasSource label;
	private Conteudo conteudo;
	private TipoTeste tipoTeste;
	private Idioma idioma;
	private Certificacao exame;
	private Double numQuestoesCorretas;
	private Double nota;
	private HashMap<Integer, Resposta> respostas;
	
	public Resultados(HashMap<Integer, Resposta> _respostas, Conteudo _conteudo, Idioma _idioma, TipoTeste _tipoTeste, Certificacao _exame) {
		conteudo = _conteudo;
		respostas = _respostas;
		tipoTeste = _tipoTeste;
		label = JanelasSource.getInstance(idioma);
		exame = _exame;
		idioma = _idioma;
		
		setTitle(label.getString("resultadosTitulo"));
		setSize(980, 700);
		setLocation(300, 20);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		
		preenxerRegiaoResultados();
		preenxerRegiaoBotoes();
		
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
		nota = calcularNota();
		registrarTentativa();
		inserirLinha(Color.BLUE, new Font("Arial", Font.BOLD, 14), "                                 " + label.getString("resultadosVocêAcertou") + " " + numQuestoesCorretas + " Questões - Sua Nota: " + nota + " de 100.0.\n");
	}
	
	private void preenxerRegiaoBotoes() {
		
		regiaoBotoes = new JPanel();
		regiaoBotoes.setLocation(30, 200);
		regiaoBotoes.setSize(780, 200);
		
		botaoVoltar = new JButton(label.getString("resultadosVoltar"));
		
		botaoVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new JanelaInicial(idioma);
			}
		});
		botaoVoltar.setPreferredSize(new Dimension(150, 50));
		
		regiaoBotoes.add(botaoVoltar);
		regiaoResultados.add(regiaoBotoes);
	}
	
	private void setTextoEnunciado(int count, int numeroQuestao) {
		inserirLinha(Color.BLACK, new Font("Arial",Font.PLAIN, 12), "    " + numeroQuestao + ". " + conteudo.getQuestao(count).getEnunciado());
	}
	
	private void setTextoRespostas(int count) {
		
		inserirLinha(Color.BLACK, new Font("Arial",Font.BOLD, 12), "       " + label.getString("resultadosResposCorreta") + conteudo.getQuestao(count).getEnunciadoAlternativaCorreta());

		switch (respostas.get(count).getTipoQuestao()) {
			case UNICA:
				if (isRespostaCorreta(count)) 
					inserirLinha(Color.GREEN, new Font("Arial",Font.BOLD, 14), "     " + label.getString("resultadosSuaResposta") + respostas.get(count).getResposta());
				 else 
					inserirLinha(Color.RED, new Font("Arial", Font.BOLD, 14), "     " + label.getString("resultadosSuaResposta") + respostas.get(count).getResposta());
				break;
			case MULTIPLA:
				exibirMultiplasRespostas(count);
	}

	}
	
	private void exibirMultiplasRespostas(int count) {
		
		for (int i = 0; i < respostas.get(count).getRespostas().size(); i++) {
			if (conteudo.getQuestao(count).equals(respostas.get(count).getRespostas().get(i))) {
				inserirLinha(Color.GREEN, new Font("Arial",Font.BOLD, 14), "     " + label.getString("resultadosSuaResposta") + respostas.get(count).getResposta());
			} else {
				inserirLinha(Color.GREEN, new Font("Arial",Font.BOLD, 14), "     " + label.getString("resultadosSuaResposta") + respostas.get(count).getResposta());
			}
		}
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

	private Double calcularNumeroQuestoesCorretas() {
		Double numeroQuestoesCorretas = 0.0;

		for (int count = 0; count < conteudo.getTotalQuestoes(); count++) {
			
			switch (respostas.get(count).getTipoQuestao()) {
				case UNICA:
					if (isRespostaCorreta(count))
						numeroQuestoesCorretas++;
					break;
				case MULTIPLA:
					numeroQuestoesCorretas += totalAcertoQuestao(count);
			}
			
		}

		return numeroQuestoesCorretas;
	}
	
	private boolean isRespostaCorreta(int count) {
		return conteudo.getQuestao(count).getEnunciadoAlternativaCorreta().equals(respostas.get(count).getResposta());
	}
	
	private Double totalAcertoQuestao(int count) {
		Double soma = 0.0;
		
		for (String resp : respostas.get(count).getRespostas()) {
			if (conteudo.getQuestao(count).getListaAlternativas().containsValue(resp)) {
				soma++;
			}
				
		}
		
		Double temp = (Double) soma / conteudo.getQuestao(count).getListaAlternativas().size();
		return temp;
	}
	
	private void registrarTentativa() {
		File file = new File (Tentativa.filename);
        ObjectOutputStream out = null;

        try {
            if (!file.exists ()) 
            	out = new ObjectOutputStream (new FileOutputStream(Tentativa.filename));
            else 
            	out = new AppendingObjectOutputStream (new FileOutputStream(Tentativa.filename, true));
            out.writeObject(new Tentativa(exame, tipoTeste, nota, numQuestoesCorretas));
            out.flush();
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

	public Double getNumQuestoesCorretas() {
		return numQuestoesCorretas;
	}

	public void setNumQuestoesCorretas(Double numQuestoesCorretas) {
		this.numQuestoesCorretas = numQuestoesCorretas;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	public HashMap<Integer, Resposta> getRespostas() {
		return respostas;
	}

	public void setRespostas(HashMap<Integer, Resposta> respostas) {
		this.respostas = respostas;
	}
	
}
