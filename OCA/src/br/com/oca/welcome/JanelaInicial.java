package br.com.oca.welcome;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultEditorKit;

import br.com.oca.quiz.Quiz;


public class JanelaInicial extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel painelFundo; 
	private JPanel regiaoTabela;
	private Container janelaPrincipal;
	private JMenuBar menuBar;
	private JMenu menuArquivo;
	private JMenu menuEditar;
	private JMenu menuAjuda;
	private HashMap<Object, Action> actions;
	
	private JTable tabela; 
	private JScrollPane barraRolagem; 
	private DefaultTableModel modelo = new DefaultTableModel(); 

	public JanelaInicial() {
		setTitle("CertificTests");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 400);
		setLocation(300, 100);
		setResizable(false);
		
		menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        criarMenuBar();
        janelaPrincipal = getContentPane();
		janelaPrincipal.setLayout(null);
		
		regiaoTabela = new JPanel();
		regiaoTabela.setLocation(10, 10);
		regiaoTabela.setSize(460, 320);
		regiaoTabela.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		criarTabela();
		
        regiaoTabela.add(painelFundo);
        janelaPrincipal.add(regiaoTabela);
        setVisible(true);
	}
	
	private void criarTabela() {
		
		tabela = new JTable(modelo); 
        modelo.addColumn("Tentativa"); 
        modelo.addColumn("Teste Escolhido"); 
        modelo.addColumn("Nota");  
        tabela.getColumnModel().getColumn(0).setPreferredWidth(10); 
        tabela.getColumnModel().getColumn(1).setPreferredWidth(300); 
        tabela.getColumnModel().getColumn(1).setPreferredWidth(30); 
        modelo.addRow(new String[] {"AAAA", "BBBB"});
        
        barraRolagem = new JScrollPane(tabela);
        painelFundo = new JPanel(); 
        painelFundo.setLayout(new BorderLayout()); 
        painelFundo.add(BorderLayout.CENTER, barraRolagem);  
		
	}
	
	private void criarMenuBar() {
		
		createActionTable();
		menuArquivo = new JMenu("Arquivo");
        menuEditar = new JMenu("Editar");
        menuAjuda = new JMenu("Ajuda");
        menuBar.add(menuArquivo);
        menuBar.add(menuEditar);
        menuBar.add(menuAjuda);
        
        JMenuItem newAction = new JMenuItem("Novo");
        newAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Quiz().setVisible(true);
			}
		});
        
        JMenuItem exitAction = new JMenuItem("Sair");
        exitAction.addActionListener(new ActionListener() { 
        	public void actionPerformed(ActionEvent e) {
        	    System.exit(0);
        	}
        });
        
        JMenuItem cutAction = new JMenuItem("Recortar");
        cutAction.setAction(getActionByName(DefaultEditorKit.cutAction));
        
        JMenuItem copyAction = new JMenuItem("Copiar");
        copyAction.setAction(getActionByName(DefaultEditorKit.copyAction));
        
        JMenuItem pasteAction = new JMenuItem("Colar");
        pasteAction.setAction(getActionByName(DefaultEditorKit.pasteAction));
        
        JMenuItem aboutAction = new JMenuItem("About CertificTests");
        aboutAction.addActionListener(new ActionListener() { 
        	public void actionPerformed(ActionEvent e) {
        		JOptionPane.showMessageDialog(null,"CertificTests\n\n"
        									+ "Versão: 1.0.0\n"
        									+ "(c) Copyright CertificTests contributors and others 2016.  All rights reserved.\n\n"
        									+ "Autor: Tiago H. Silva.\n"
        									+ "Adaptado encima da Versão de Dara Yuk: http://java.worldbestlearningcenter.com/\n"
        									, "About CertificTests", JOptionPane.INFORMATION_MESSAGE);
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

}
