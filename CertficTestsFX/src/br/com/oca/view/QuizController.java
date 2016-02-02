package br.com.oca.view;

import br.com.oca.controllers.MainApp;
import br.com.oca.model.Conteudo;
import br.com.oca.model.OCA;
import br.com.oca.model.Questao;
import br.com.oca.model.enums.Certificacao;
import br.com.oca.model.enums.Idioma;
import br.com.oca.model.enums.TipoTeste;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class QuizController {
	@FXML
	private Label numeroQuestao;
	@FXML
	private Label nomeExame;
	@FXML
	private Label tempoDecorrido;
	@FXML
	private TextField enunciadoQuestao;
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
	private MainApp mainApp;
	private Integer numeroAtualQuestao;
	private Conteudo conteudo;
	
	public QuizController(Certificacao certificacao, TipoTeste tipoTeste) {
		conteudo = OCA.getInstance(certificacao, Idioma.Ingles);
	}
	
	/**
     * Inicializa a classe controlle. Este m�todo � chamado automaticamente
     * ap�s o arquivo fxml ter sido carregado.
     */
    @FXML
    private void initialize() {
    	numeroAtualQuestao = 0;
    	setQuestao(conteudo.getQuestao(numeroAtualQuestao));
    }
    
    private void setQuestao(Questao questao) {
    	
    	enunciadoQuestao.setText(questao.getEnunciado());
    }
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    @FXML
	private void opcaoSair() {
		dialogStage.close();
	}
    
    public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
}
