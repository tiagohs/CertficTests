import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.HashMap;


public class Desenhar extends Canvas {
	private static final long serialVersionUID = 1L;
	
	private Conteudo conteudo;
	private HashMap<Integer, String> respostas;
	private String[][] questoes;
	
	public Desenhar(HashMap<Integer, String> _respostas) {
		conteudo = Conteudo.getInstance();
		respostas = _respostas;
		questoes = conteudo.getPerguntas();
	}
	
	public void paint(Graphics g) {
		int numeroQuestoes = 10;
		int x = 10;
		int y = 20;
		
		for (int i = 0; i < numeroQuestoes; i++) {
			// print the 1st column
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", Font.BOLD, 12));
			g.drawString(i + 1 + ". " + questoes[i][0], x, y);
			y += 30;
			g.setFont(new Font("Arial", Font.PLAIN, 12));
			g.drawString("      Resposta Correta:" + questoes[i][1], x, y);
			y += 30;
			if (questoes[i][1].equals(respostas.get(i))) {
				g.setColor(Color.GREEN);
				g.drawString("      Sua Resposta:" + respostas.get(i), x, y);
			} else {
				g.setColor(Color.RED);
				g.drawString("      Sua Resposta:" + respostas.get(i), x, y);
			}
			
			y += 30;
			
			if (y > 400) {
				y = 20;
				x = 450;
			}

		}

		g.setColor(Color.BLUE);
		g.setFont(new Font("Arial", Font.BOLD, 14));
		g.drawString("Você Acertou " + getNumeroQuestoesCorretas() + " Questões.", 300, 500);
	}
	
	private int getNumeroQuestoesCorretas() {
		int numeroQuestoes = 10;
		int numeroQuestoesCorretas = 0;

		for (int count = 0; count < numeroQuestoes; count++) {
			if (questoes[count][1].equals(respostas.get(count)))
				numeroQuestoesCorretas++;
		}

		return numeroQuestoesCorretas;

	}
}
