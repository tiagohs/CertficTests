package br.com.oca.welcome;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultEditorKit;

import br.com.oca.enums.Certificacao;
import br.com.oca.enums.Idioma;
import br.com.oca.enums.TipoTeste;
import br.com.oca.i18n.janelas.JanelasSource;
import br.com.oca.quiz.Quiz;
import br.com.oca.tentativas.Tentativa;

public class JanelaInicial extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel painelFundo;
	private JPanel regiaoTabela;
	private Container janelaPrincipal;
	private JMenuBar menuBar;
	private JMenu menuArquivo;
	private JMenu menuEditar;
	private JMenu menuAjuda;
	private JTable tabela;
	private JScrollPane barraRolagem;
	private DefaultTableModel modelo = new DefaultTableModel();
	private HashMap<Object, Action> actions;

	private JanelasSource label;
	private Idioma idioma;

	public JanelaInicial() {
		idioma = selecionarIdioma();
		label = JanelasSource.getInstance(idioma);
		
		setTitle("CertificTests");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 400);
		setLocation(300, 100);
		setResizable(false);
		janelaPrincipal = getContentPane();
		janelaPrincipal.setLayout(null);

		criarMenuBar();
		criarTabela();

		setVisible(true);
	}

	private Idioma selecionarIdioma() {

		Idioma opcao = (Idioma) JOptionPane.showInputDialog(this,
				"Entre com o Idioma Desejado (Enter the Language Desire): ",
				"Escolher Idioma", JOptionPane.QUESTION_MESSAGE, null,
				Idioma.values(), Idioma.Portugues);
		if (opcao == null)
			System.exit(0);
		return opcao;
	}

	private void criarTabela() {
		regiaoTabela = new JPanel();
		regiaoTabela.setLocation(10, 10);
		regiaoTabela.setSize(460, 320);
		regiaoTabela.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,
				1));

		tabela = new JTable(modelo);
		modelo.addColumn(label.getString("tabelaTentativa"));
		modelo.addColumn(label.getString("tabelaTesteEscolhido"));
		modelo.addColumn(label.getString("tabelaNota"));
		tabela.getColumnModel().getColumn(0).setPreferredWidth(10);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(300);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(30);
		setColunas();

		barraRolagem = new JScrollPane(tabela);
		painelFundo = new JPanel();
		painelFundo.setLayout(new BorderLayout());
		painelFundo.add(BorderLayout.CENTER, barraRolagem);

		regiaoTabela.add(painelFundo);
		janelaPrincipal.add(regiaoTabela);
	}

	private void setColunas() {
		Tentativa tentativa = null;
		Integer numeroTentativa = 1;
		
		try {
			FileInputStream fis = new FileInputStream("tentativas.bin");
			ObjectInputStream ois = new ObjectInputStream(fis); 
			while (true) {
		        try {
		        	tentativa = (Tentativa) ois.readObject();
		        	modelo.addRow(new String[] { numeroTentativa.toString(), tentativa.getTesteEscolhido().getNome(), tentativa.getNota().toString() });
		        	numeroTentativa++;
		        } catch (EOFException e) {
		            break;
		        } catch (FileNotFoundException e) {
		        	System.out.println("Nao achou.");
		        } 
		    }
			ois.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		
	}

	private void criarMenuBar() {

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		criarActionTable();
		menuArquivo = new JMenu(label.getString("menuArquivo"));
		menuEditar = new JMenu(label.getString("menuEditar"));
		menuAjuda = new JMenu(label.getString("menuAjuda"));
		menuBar.add(menuArquivo);
		menuBar.add(menuEditar);
		menuBar.add(menuAjuda);

		JMenuItem newAction = new JMenuItem(label.getString("menuNovo"));
		newAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opcaoNovoTeste();
			}
		});

		JMenuItem exitAction = new JMenuItem(label.getString("menuSair"));
		exitAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		JMenuItem cutAction = new JMenuItem(label.getString("menuRecortar"));
		cutAction.setAction(getActionByName(DefaultEditorKit.cutAction));

		JMenuItem copyAction = new JMenuItem(label.getString("menuCopiar"));
		copyAction.setAction(getActionByName(DefaultEditorKit.copyAction));

		JMenuItem pasteAction = new JMenuItem(label.getString("menuColar"));
		pasteAction.setAction(getActionByName(DefaultEditorKit.pasteAction));

		JMenuItem aboutAction = new JMenuItem(label.getString("menuSobre"));
		aboutAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						label.getString("menuSobreDescricao"),
						"About CertificTests", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		menuArquivo.add(newAction);
		menuArquivo.addSeparator();
		menuArquivo.add(exitAction);
		menuEditar.add(cutAction);
		menuEditar.add(copyAction);
		menuEditar.add(pasteAction);
		menuAjuda.add(aboutAction);
	}

	public void opcaoNovoTeste() {

		JPanel myPanel = new JPanel();
		myPanel.add(new JLabel(label.getString("novoLabelNomeTeste")));
		JComboBox<Certificacao> jcNomesTestes = new JComboBox<Certificacao>(
				Certificacao.values());
		myPanel.add(jcNomesTestes);
		myPanel.add(Box.createHorizontalStrut(15));
		myPanel.add(new JLabel(label.getString("novoLabelTipoTeste")));
		JComboBox<TipoTeste> jcTipoTestes = new JComboBox<TipoTeste>(
				TipoTeste.values());
		myPanel.add(jcTipoTestes);

		int result = JOptionPane.showConfirmDialog(null, myPanel,
				label.getString("novoTesteTitulo"),
				JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			new Quiz(idioma, (Certificacao) jcNomesTestes.getSelectedItem(),
					(TipoTeste) jcTipoTestes.getSelectedItem())
					.setVisible(true);
			modelo.setRowCount(0);
			setColunas();
		}

	}

	private HashMap<Object, Action> criarActionTable() {
		actions = new HashMap<Object, Action>();
		Action[] actionsArray = new DefaultEditorKit().getActions();

		for (int cont = 0; cont < actionsArray.length; cont++) {
			Action a = actionsArray[cont];
			actions.put(a.getValue(Action.NAME), a);
		}
		return actions;
	}

	private Action getActionByName(String name) {
		return actions.get(name);
	}

	public Idioma getIdioma() {
		return idioma;
	}

}
