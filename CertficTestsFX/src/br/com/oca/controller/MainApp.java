package br.com.oca.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.controlsfx.dialog.Dialogs;

import br.com.oca.model.Resposta;
import br.com.oca.model.Tentativa;
import br.com.oca.model.TentativaWrapper;
import br.com.oca.model.conteudo.Conteudo;
import br.com.oca.model.enums.Certificacao;
import br.com.oca.model.enums.Idioma;
import br.com.oca.model.enums.TipoTeste;
import br.com.oca.model.i18n.janelas.JanelasSource;
import br.com.oca.view.HomeController;
import br.com.oca.view.NovoTesteController;
import br.com.oca.view.PropriedadesController;
import br.com.oca.view.QuizController;
import br.com.oca.view.ResultadoController;
import br.com.oca.view.RootLayoutController;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

public class MainApp extends Application {
	private ObservableList<Tentativa> listaTentativas;
	private Stage homeStage;
    private BorderPane rootLayout;
    private JanelasSource label;
    private Idioma idioma;
    
    public MainApp() {
    	listaTentativas = listaTentativas = FXCollections.observableArrayList();
//    	listaTentativas.add(new Tentativa(Certificacao.OCA, TipoTeste.TESTE_1, 90.0, 2.0));
//    	listaTentativas.add(new Tentativa(Certificacao.OCP, TipoTeste.TESTE_2, 80.0, 1.0));
//    	listaTentativas.add(new Tentativa(Certificacao.OCA, TipoTeste.TESTE_1, 90.0, 2.0));
//    	listaTentativas.add(new Tentativa(Certificacao.OCP, TipoTeste.TESTE_2, 80.0, 1.0));
//    	listaTentativas.add(new Tentativa(Certificacao.OCA, TipoTeste.TESTE_1, 90.0, 2.0));
//    	listaTentativas.add(new Tentativa(Certificacao.OCP, TipoTeste.TESTE_2, 80.0, 1.0));
    }
    
    @Override
    public void start(Stage _stagePrimario) {
    	
    	homeStage = _stagePrimario;
        homeStage.setTitle("CertficTests");
        idioma = Idioma.Portugues; 
        label = JanelasSource.getInstance(idioma); 
        
        initRootLayout();

        showHome();
    }

    /**
     * Inicializa o root layout (layout base).
     */
    public void initRootLayout() {
        try {
        	FXMLLoader loader = new FXMLLoader();
        	loader.setResources(label.getBundle());
            rootLayout = (BorderPane) loader.load(this.getClass().getResource("../view/RootLayout.fxml").openStream());

            Scene scene = new Scene(rootLayout);
            homeStage.setScene(scene);
            
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);
            controller.setDialogHome(homeStage);
            
            homeStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showPropriedades() {
    	Window window = new Stage();
    	PauseTransition pause = new PauseTransition(Duration.seconds(30));
    	pause.setOnFinished(e -> window.hide());
    	pause.play();
    	
    	try {
            FXMLLoader loader = new FXMLLoader();
            loader.setResources(label.getBundle());
            AnchorPane page = (AnchorPane) loader.load(this.getClass().getResource("../view/Propriedades.fxml").openStream());
            
            Stage propriedadesStage = new Stage();
            propriedadesStage.initModality(Modality.WINDOW_MODAL);
            propriedadesStage.setTitle(label.getString("propriedadesTitulo"));
            propriedadesStage.setScene(new Scene(page));
            propriedadesStage.show();
          
            PropriedadesController propriedadesController = loader.getController();
            propriedadesController.setDialogStage(propriedadesStage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showHome() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setResources(label.getBundle());
            AnchorPane personOverview = (AnchorPane) loader.load(this.getClass().getResource("../view/Home.fxml").openStream());
            rootLayout.setCenter(personOverview);
            
            HomeController homeController = loader.getController();
            homeController.setHomeStage(homeStage);
            homeController.setMainApp(this);
            homeController.setLoader(loader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showNovoTesteDialog() {
    	Window window = new Stage();
    	PauseTransition pause = new PauseTransition(Duration.seconds(30));
    	pause.setOnFinished(e -> window.hide());
    	pause.play();
    	
    	try {
            FXMLLoader loader = new FXMLLoader();
            loader.setResources(label.getBundle());
            AnchorPane page = (AnchorPane) loader.load(this.getClass().getResource("../view/NovoTeste.fxml").openStream());
            
            Stage novoTesteStage = new Stage();
            novoTesteStage.initModality(Modality.WINDOW_MODAL);
            novoTesteStage.setTitle("Novo Teste");
            novoTesteStage.setScene(new Scene(page));
            novoTesteStage.show();
            
            NovoTesteController novoTesteController = loader.getController();
            novoTesteController.setDialogStage(novoTesteStage);
            novoTesteController.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showQuiz(Certificacao nomeExame, TipoTeste tipoTeste) {
    	
    	Window window = new Stage();
    	PauseTransition pause = new PauseTransition(Duration.seconds(30));
    	pause.setOnFinished(e -> window.hide());
    	pause.play();
    	
    	try {
            FXMLLoader loader = new FXMLLoader();
            loader.setResources(label.getBundle());
            AnchorPane page = (AnchorPane) loader.load(this.getClass().getResource("../view/Quiz.fxml").openStream());
            
            Stage quizStage = new Stage();
            quizStage.initModality(Modality.WINDOW_MODAL);
            quizStage.setTitle("Quiz - CertificTests");
            quizStage.setScene(new Scene(page));
            quizStage.show();
            
            QuizController quizController = loader.getController();
            quizController.setDialogStage(quizStage);
            quizController.setDialogHome(homeStage);
            quizController.setIdioma(idioma);
            quizController.setMainApp(this);
            quizController.iniciarQuiz(nomeExame, tipoTeste);
        } catch (IOException e) {
            e.printStackTrace();
        }
    	
    }
    
    public void showResultadoController(ArrayList<Resposta> listaRespostas, Conteudo conteudo) {
    	
    	Window window = new Stage();
    	PauseTransition pause = new PauseTransition(Duration.seconds(30));
    	pause.setOnFinished(e -> window.hide());
    	pause.play();
    	
    	try {
            FXMLLoader loader = new FXMLLoader();
            loader.setResources(label.getBundle());
            AnchorPane page = (AnchorPane) loader.load(this.getClass().getResource("../view/Resultado.fxml").openStream());
            
            Stage resultadoStage = new Stage();
            resultadoStage.initModality(Modality.WINDOW_MODAL);
            resultadoStage.setTitle("Resultados - CertificTests");
            resultadoStage.setScene(new Scene(page));
            resultadoStage.show();
            
            ResultadoController resultadoController = loader.getController();
            resultadoController.setDialogStage(resultadoStage);
            resultadoController.setListaRespostas(listaRespostas);
            resultadoController.setDialogHome(homeStage);
            resultadoController.setConteudo(conteudo);
            resultadoController.setIdioma(idioma);
            resultadoController.calcularResultados();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void loadPersonDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(TentativaWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            // Reading XML from the file and unmarshalling.
            TentativaWrapper wrapper = (TentativaWrapper) um.unmarshal(file);

            listaTentativas.clear();
            listaTentativas.addAll(wrapper.getPersons());

            // Save the file path to the registry.
            setPersonFilePath(file);

        } catch (Exception e) { // catches ANY exception
            Dialogs.create()
                    .title("Erro")
                    .masthead("Não foi possível carregar dados do arquivo:\n" 
                              + file.getPath()).showException(e);
        }
    }

    public void savePersonDataToFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(TentativaWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Envolvendo nossos dados da pessoa.
            TentativaWrapper wrapper = new TentativaWrapper();
            wrapper.setPersons(listaTentativas);

            // Enpacotando e salvando XML  no arquivo.
            m.marshal(wrapper, file);

            // Saalva o caminho do arquivo no registro.
            setPersonFilePath(file);
        } catch (Exception e) { // catches ANY exception
            Dialogs.create().title("Erro")
                    .masthead("Não foi possível salvar os dados do arquivo:\n" 
                              + file.getPath()).showException(e);
        }
    }
    
    public File getPersonFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    public void setPersonFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            // Update the stage title.
            homeStage.setTitle("CertficTests - " + file.getName());
        } else {
            prefs.remove("filePath");

            // Update the stage title.
            homeStage.setTitle("CertficTests");
        }
    }
    
    public Stage getPrimaryStage() {
        return homeStage;
    }
    
    public ObservableList<Tentativa> getListaTentativas() {
		return listaTentativas;
	}
    
    public static void main(String[] args) {
        launch(args);
    }
    
    public Idioma getIdioma() {
		return idioma;
	}
    
    public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}
    
}
