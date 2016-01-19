package br.com.oca.quiz;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import javax.swing.JFrame;

public class Resultados extends JFrame {
	private static final long serialVersionUID = 1L;

	public Resultados(HashMap<Integer, String> respostas) {
		setTitle("Resultados:");
		setSize(850, 550);
		setBackground(Color.WHITE);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});

		add(new Desenhar(respostas));
		setVisible(true);
	}
	
}
