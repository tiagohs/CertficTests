package br.com.oca.quiz;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.HashMap;

import br.com.oca.conteudo.OCA;


public class Desenhar extends Canvas {
	private static final long serialVersionUID = 1L;
	
	private OCA conteudo;
	private HashMap<Integer, String> respostas;
	private String[][] questoes;
	
	public Desenhar(HashMap<Integer, String> _respostas) {
		conteudo = OCA.getInstance();
		respostas = _respostas;
		questoes = conteudo.getPerguntas();
	}
	
	public void paint(Graphics g) {
		int x = 10;
		int y = 20;
		
		for (int count = 0; count < conteudo.getNumeroMaximoQuestoes(); count++) {
			g.setFont(new Font("Arial", Font.BOLD, 12));
			g.setColor(Color.BLACK);
			g.drawString(count + 1 + ". " + questoes[count][0], x, y);
			g.setFont(new Font("Arial", Font.PLAIN, 12));
			y += 30;
			g.drawString("      Resposta Correta:" + questoes[count][1], x, y);
			y += 30;
			if (questoes[count][1].equals(respostas.get(count))) 
				exibirRespostaUsuario(g, Color.GREEN, count, x, y);
			 else
				exibirRespostaUsuario(g, Color.RED, count, x, y);

			y += 30;
//			
//			if (y > 400) {
//				y = 20;
//				x = 450;
//			}

		}

		g.setColor(Color.BLUE);
		g.setFont(new Font("Arial", Font.BOLD, 14));
		g.drawString("Você Acertou " + getNumeroQuestoesCorretas() + " Questões.", 300, 500);
	}
	
	private void exibirRespostaUsuario(Graphics g, Color cor, int count, int x, int y) {
		g.setColor(cor);
		g.drawString("      Sua Resposta:" + respostas.get(count), x, y);
	}
	
	private int getNumeroQuestoesCorretas() {
		int numeroQuestoesCorretas = 0;

		for (int count = 0; count < conteudo.getNumeroMaximoQuestoes(); count++) {
			if (questoes[count][1].equals(respostas.get(count)))
				numeroQuestoesCorretas++;
		}

		return numeroQuestoesCorretas;

	}
}
