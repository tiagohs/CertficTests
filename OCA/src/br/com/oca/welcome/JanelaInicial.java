package br.com.oca.welcome;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import br.com.oca.i18n.janelas.JanelasSource;
import br.com.oca.quiz.Quiz;

public class JanelaInicial extends JFrame {
	private static final long serialVersionUID = 1L;
	private final String idioma;

	private JPanel painelFundo;
	private JPanel regiaoTabela;
	private Container janelaPrincipal;
	private JMenuBar menuBar;
	private JMenu menuArquivo;
	private JMenu menuEditar;
	private JMenu menuAjuda;
	private HashMap<Object, Action> actions;

	private JanelasSource label;
	private JTable tabela;
	private JScrollPane barraRolagem;
	private DefaultTableModel modelo = new DefaultTableModel();

	public JanelaInicial() {
		setTitle("CertificTests");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 400);
		setLocation(300, 100);
		setResizable(false);

		idioma = selecionarIdioma();
		label = new JanelasSource(idioma);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		criarMenuBar();
		janelaPrincipal = getContentPane();
		janelaPrincipal.setLayout(null);

		regiaoTabela = new JPanel();
		regiaoTabela.setLocation(10, 10);
		regiaoTabela.setSize(460, 320);
		regiaoTabela.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,
				1));
		criarTabela();

		regiaoTabela.add(painelFundo);
		janelaPrincipal.add(regiaoTabela);
		setVisible(true);
	}

	private String selecionarIdioma() {
		String[] idiomas = { "Português Brasil  (Portuguese)",
				"Inglês (English)" };
		
		String opcao = (String) JOptionPane.showInputDialog(this,
				"Entre com o Idioma Desejado (Enter the Language Desire): ",
				"Escolher Idioma", JOptionPane.QUESTION_MESSAGE, null, idiomas,
				idiomas[0]);
		if (opcao == null) System.exit(0);
		return opcao;
	}

	private void criarTabela() {

		tabela = new JTable(modelo);
		modelo.addColumn(label.getString("tabelaTentativa"));
		modelo.addColumn(label.getString("tabelaTesteEscolhido"));
		modelo.addColumn(label.getString("tabelaNota"));
		tabela.getColumnModel().getColumn(0).setPreferredWidth(10);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(300);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(30);
		modelo.addRow(new String[] { "AAAA", "BBBB" });

		barraRolagem = new JScrollPane(tabela);
		painelFundo = new JPanel();
		painelFundo.setLayout(new BorderLayout());
		painelFundo.add(BorderLayout.CENTER, barraRolagem);
	}

	private void criarMenuBar() {

		createActionTable();
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
		String[] nomesTestes = { "OCA", "OCP" };
		String[] tiposTestes = { label.getString("novoTipoTeste1"), label.getString("novoTipoTeste2"), label.getString("novoTipoTeste3") };
		
		JPanel myPanel = new JPanel();
		myPanel.add(new JLabel(label.getString("novoLabelNomeTeste")));
		JComboBox<String> jcNomesTestes = new JComboBox<String>(nomesTestes);
		myPanel.add(jcNomesTestes);
		myPanel.add(Box.createHorizontalStrut(15)); 
		myPanel.add(new JLabel(label.getString("novoLabelTipoTeste")));
		JComboBox<String> jcTipoTestes = new JComboBox<String>(tiposTestes);
		myPanel.add(jcTipoTestes);

		int result = JOptionPane.showConfirmDialog(null, myPanel, label.getString("novoTesteTitulo"), JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) 
			new Quiz(idioma, (String) jcNomesTestes.getSelectedItem(), (String) jcTipoTestes.getSelectedItem()).setVisible(true);
		
	}

	private HashMap<Object, Action> createActionTable() {
		actions = new HashMap<Object, Action>();
		Action[] actionsArray = new DefaultEditorKit().getActions();

		for (int i = 0; i < actionsArray.length; i++) {
			Action a = actionsArray[i];
			actions.put(a.getValue(Action.NAME), a);
		}
		return actions;
	}

	private Action getActionByName(String name) {
		return actions.get(name);
	}

	public String getIdioma() {
		return idioma;
	}

}
