package br.com.oca.welcome;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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

	private JPanel regiaoTabela;
	private JPanel popUpNovoTeste;
	private Container janelaPrincipal;
	private JMenuBar menuBar;
	private JMenu menuArquivo;
	private JMenu menuEditar;
	private JMenu menuAjuda;
	private JMenuItem itemNovo;
	private JMenuItem itemSair;
	private JMenuItem itemSobre;
	private JMenuItem itemRecortar;
	private JMenuItem itemCopiar;
	private JMenuItem itemColar;
	private JMenuItem itemselecionarTudo;
	private JTable tabela;
	private JScrollPane barraRolagem;
	private DefaultTableModel modelo;
	
	private HashMap<Object, Action> actionTable;
	private JanelasSource label;
	private Idioma idioma;

	public JanelaInicial(Idioma _idioma) {
		if (_idioma == null)
			idioma = selecionarIdioma();
		else
			idioma = _idioma;
		
		label = JanelasSource.getInstance(idioma);
		modelo = new DefaultTableModel();
		actionTable = criarActionTable();
		
		setTitle("CertificTests");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(700, 600);
		setLocation(300, 100);
		setResizable(false);
		janelaPrincipal = getContentPane();
		janelaPrincipal.setLayout(null);

		criarMenuBar();
		criarTabela();

		setVisible(true);
	}

	private Idioma selecionarIdioma() {

		Idioma idiomaEscolhido = (Idioma) JOptionPane.showInputDialog(this,
				"Entre com o Idioma Desejado (Enter the Language Desire): ",
				"Escolher Idioma", JOptionPane.QUESTION_MESSAGE, null,
				Idioma.values(), Idioma.Portugues);
		if (idiomaEscolhido == null)
			System.exit(0);
		return idiomaEscolhido;
	}
	
	private HashMap<Object, Action> criarActionTable() {
		HashMap<Object, Action> actionsTemp = new HashMap<Object, Action>();
		Action[] actionsArray = new DefaultEditorKit().getActions();

		for (int cont = 0; cont < actionsArray.length; cont++) {
			Action a = actionsArray[cont];
			actionsTemp.put(a.getValue(Action.NAME), a);
		}
		
		return actionsTemp;
	}
	
	private void criarTabela() {
		
		regiaoTabela = new JPanel();
		regiaoTabela.setLocation(10, 10);
		regiaoTabela.setSize(660, 520);
		regiaoTabela.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

		tabela = new JTable(modelo);
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		modelo.addColumn(label.getString("tabelaTentativa"));
		modelo.addColumn(label.getString("tabelaTesteEscolhido"));
		modelo.addColumn(label.getString("tabelaNota"));
		modelo.addColumn(label.getString("tabelaAcertos"));
		tabela.setSize(650, 500);
		tabela.getColumnModel().getColumn(0).setMinWidth(10);
		tabela.getColumnModel().getColumn(1).setMinWidth(430);
		tabela.getColumnModel().getColumn(1).setMinWidth(10);
		tabela.getColumnModel().getColumn(2).setMinWidth(10);
		listarTentativas();

		barraRolagem = new JScrollPane(tabela);
		regiaoTabela.setLayout(new BorderLayout());
		regiaoTabela.add(BorderLayout.CENTER, barraRolagem);
		
		janelaPrincipal.add(regiaoTabela);
	}

	private void listarTentativas() {
		Integer numeroTentativa = 1;
		
		try {
			FileInputStream fileInputStream = new FileInputStream(Tentativa.filename);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			while (true) {
				try {
					setRow(objectInputStream, numeroTentativa);
				} catch (EOFException e) {
					break;
				} catch (FileNotFoundException e) {
					break;
				}
				numeroTentativa++;
			}
			objectInputStream.close();
		} catch (FileNotFoundException e) {
			modelo.addRow(new String[] {" ", " ", " ", " "});
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void setRow(ObjectInputStream objectInputStream, Integer numeroTentativa) throws ClassNotFoundException, IOException {
		
		Tentativa tentativa = (Tentativa) objectInputStream.readObject();
		modelo.addRow(new String[] { numeroTentativa.toString(),
				tentativa.getExame().getNome() + " - " + tentativa.getTesteEscolhido().getNome(),
				tentativa.getNota().toString(),
				tentativa.getNumeroAcertos().toString()});
		
	}

	private void criarMenuBar() {

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuArquivo = new JMenu(label.getString("menuArquivo"));
		menuEditar = new JMenu(label.getString("menuEditar"));
		menuAjuda = new JMenu(label.getString("menuAjuda"));
		
		addItensArquivo();
		addItensEditar();
		addItensAjuda();
		
		menuBar.add(menuArquivo);
		menuBar.add(menuEditar);
		menuBar.add(menuAjuda);
		menuArquivo.add(itemNovo);
		menuArquivo.addSeparator();
		menuArquivo.add(itemSair);
		menuEditar.add(itemRecortar);
		menuEditar.add(itemCopiar);
		menuEditar.add(itemColar);
		menuEditar.add(itemColar);
		menuEditar.addSeparator();
		menuEditar.add(itemselecionarTudo);
		menuAjuda.add(itemSobre);
	}
	
	private void addItensArquivo() {
		
		itemNovo = new JMenuItem(label.getString("menuNovo"));
		itemNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opcaoNovoTeste();
			}
		});

		itemSair = new JMenuItem(label.getString("menuSair"));
		itemSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
	}
	
	private void addItensEditar() {
		
		itemRecortar = criarItemEditar(DefaultEditorKit.cutAction, label.getString("menuRecortar"), KeyEvent.VK_X);
		itemCopiar = criarItemEditar(DefaultEditorKit.copyAction, label.getString("menuCopiar"), KeyEvent.VK_C);
		itemColar = criarItemEditar(DefaultEditorKit.pasteAction, label.getString("menuColar"), KeyEvent.VK_V);
		itemselecionarTudo = criarItemEditar(DefaultEditorKit.selectAllAction, label.getString("menuSelecionarTudo"), KeyEvent.VK_A);
		
	}
	
	private JMenuItem criarItemEditar(String nomeAcao, String nomeItem, int atalho) {
		JMenuItem itemTemp = new JMenuItem();
		
		itemTemp.setAction(getActionByName(nomeAcao));
		itemTemp.setText(nomeItem);
		itemTemp.setMnemonic(atalho);
		
		return itemTemp;
	}
	
	private void addItensAjuda() {
		
		itemSobre = new JMenuItem(label.getString("menuSobre"));
		itemSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						label.getString("menuSobreDescricao"),
						label.getString("menuSobre"), JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}

	public void opcaoNovoTeste() {

		popUpNovoTeste = new JPanel();
		JComboBox<Certificacao> jcNomesTestes = new JComboBox<Certificacao>(Certificacao.values());
		JComboBox<TipoTeste> jcTipoTestes = new JComboBox<TipoTeste>(TipoTeste.values());
		
		popUpNovoTeste.add(new JLabel(label.getString("novoLabelNomeTeste")));
		popUpNovoTeste.add(jcNomesTestes);
		popUpNovoTeste.add(Box.createHorizontalStrut(15));
		popUpNovoTeste.add(new JLabel(label.getString("novoLabelTipoTeste")));
		popUpNovoTeste.add(jcTipoTestes);

		int opcaoEscolhida = JOptionPane.showConfirmDialog(null, popUpNovoTeste,
				label.getString("novoTesteTitulo"),
				JOptionPane.OK_CANCEL_OPTION);
		if (opcaoEscolhida == JOptionPane.OK_OPTION) {
			new Quiz(idioma, (Certificacao) jcNomesTestes.getSelectedItem(),
					(TipoTeste) jcTipoTestes.getSelectedItem())
					.setVisible(true);
			this.dispose();
		}

	}

	private Action getActionByName(String name) {
		return actionTable.get(name);
	}

	public Idioma getIdioma() {
		return idioma;
	}

	public JanelasSource getLabel() {
		return label;
	}

	public void setLabel(JanelasSource label) {
		this.label = label;
	}

	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}
	
}
