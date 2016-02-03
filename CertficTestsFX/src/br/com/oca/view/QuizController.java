package br.com.oca.view;

import br.com.oca.model.Conteudo;
import br.com.oca.model.OCA;
import br.com.oca.model.Questao;
import br.com.oca.model.enums.Certificacao;
import br.com.oca.model.enums.Idioma;
import br.com.oca.model.enums.TipoTeste;
import br.com.oca.util.Observer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class QuizController implements Observer {
	@FXML
	private Label labelNumeroQuestao;
	@FXML
	private Label labelNomeExame;
	@FXML
	private Label labelTempoDecorrido;
	@FXML
	private Label labelReferencia;
	@FXML
	private TextField labelEnunciadoQuestao;
	@FXML
	private Pane comboPainel;
	@FXML
	private ToggleGroup radioGroup;
	@FXML
	private RadioButton radioAlternativaA;
	@FXML
	private RadioButton radioAlternativaB;
	@FXML
	private RadioButton radioAlternativaC;
	@FXML
	private RadioButton radioAlternativaD;
	@FXML
	private RadioButton radioAlternativaE;
	@FXML
	private Pane checkPainel;
	@FXML
	private CheckBox checkAlternativaA;
	@FXML
	private CheckBox checkAlternativaB;
	@FXML
	private CheckBox checkAlternativaC;
	@FXML
	private CheckBox checkAlternativaD;
	@FXML
	private CheckBox checkAlternativaE;
	@FXML
	private Button anteriorQuestao;
	@FXML
	private Button proximaQuestao;
	@FXML
	private Button ajuda;
	@FXML
	private Button sair;
	
	private Stage dialogStage;
	
	private Integer contQuestao;
	private Integer numeroAtualQuestao;
	private Certificacao nomeExame;
	private Idioma idioma;
	private TipoTeste tipoTeste;
	
	private Conteudo conteudo;
	
	public QuizController() {
	}
	
	/**
     * Inicializa a classe controlle. Este m�todo � chamado automaticamente
     * ap�s o arquivo fxml ter sido carregado.
     */
    @FXML
    private void initialize() {
    	contQuestao = 0;
    	numeroAtualQuestao = 1;
    }
    
    public void iniciarQuiz(Certificacao _nomeExame, TipoTeste _tipoTeste) {
    	nomeExame = _nomeExame;
    	tipoTeste = _tipoTeste;
    	conteudo = OCA.getInstance(nomeExame, idioma);
    	
    	labelNomeExame.setText(nomeExame.getNome());
    	setNumeroQuestao();
    	verificaQuestao(conteudo.getQuestao(contQuestao));
    	setQuestao(conteudo.getQuestao(contQuestao));
    }
    
    private void setNumeroQuestao() {
    	labelNumeroQuestao.setText("Quest�o " + numeroAtualQuestao + " de " + conteudo.getTotalQuestoes());
    	numeroAtualQuestao++;
    }
    
    private void verificaQuestao(Questao questao) {
    	
    	switch (questao.getTipoQuestao()) {
	    	case MULTIPLA:
	    		comboPainel.setVisible(false);
	    		checkPainel.setVisible(true);
	    		setQuestao(questao);
	    		setMultiplasEscolhas(questao);
	    		break;
	    	case UNICA:
	    		checkPainel.setVisible(false);
	    		comboPainel.setVisible(true);
	    		setQuestao(questao);
	    		setUnicaEscolha(questao);
    	}
    }
    
    private void setQuestao(Questao questao) {
    	labelEnunciadoQuestao.setText(questao.getEnunciado());
    	labelReferencia.setText(questao.getReferencia());
    }
    
    private void setMultiplasEscolhas(Questao questao) {
    	
    	checkAlternativaA.setText(questao.getAlternativa('A'));
    	checkAlternativaB.setText(questao.getAlternativa('B'));
    	checkAlternativaC.setText(questao.getAlternativa('C'));
    	checkAlternativaD.setText(questao.getAlternativa('D'));
    	checkAlternativaE.setText(questao.getAlternativa('E'));
    }
    
    private void setUnicaEscolha(Questao questao) {
    	
    	radioAlternativaA.setText(questao.getAlternativa('A'));
    	radioAlternativaB.setText(questao.getAlternativa('B'));
    	radioAlternativaC.setText(questao.getAlternativa('C'));
    	radioAlternativaD.setText(questao.getAlternativa('D'));
    	radioAlternativaE.setText(questao.getAlternativa('E'));
    }
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    @FXML
	private void opcaoSair() {
		dialogStage.close();
	}
    
    @FXML
    private void handleProximo() {
    	
    	contQuestao++;
    	setNumeroQuestao();
    	verificaQuestao(conteudo.getQuestao(contQuestao));
    	setQuestao(conteudo.getQuestao(contQuestao));
    }
    
    @FXML
    private void handleAnterior() {
    	
    	if (contQuestao > 0) {
    		contQuestao--;
        	verificaQuestao(conteudo.getQuestao(contQuestao));
        	setQuestao(conteudo.getQuestao(contQuestao));
    	}
    }
    
    @FXML
    private void handleAjuda() {
    	
    }
    
    @FXML
    private void handleSair() {
    	dialogStage.close();
    }
    
    public void setNomeExame(Certificacao nomeExame) {
		this.nomeExame = nomeExame;
	}
    
    public void setTipoTeste(TipoTeste tipoTeste) {
		this.tipoTeste = tipoTeste;
	}
    
    public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}

	@Override
	public void update(Idioma idioma) {
		setIdioma(idioma);
	}
}
