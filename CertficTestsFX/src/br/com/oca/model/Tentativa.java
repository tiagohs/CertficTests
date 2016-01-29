package br.com.oca.model;

import java.io.Serializable;

import br.com.oca.model.enums.Certificacao;
import br.com.oca.model.enums.TipoTeste;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Tentativa implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String filename = "tentativas.bin";
	
	private SimpleObjectProperty<Certificacao> exame;
	private SimpleObjectProperty<TipoTeste> tipoTesteEscolhido;
	private SimpleDoubleProperty nota;
	private SimpleDoubleProperty numeroAcertos;
	
	public Tentativa(Certificacao _exame, TipoTeste _testeEscolhido, Double _nota, Double _numeroAcertos) {
		exame = new SimpleObjectProperty<Certificacao>(_exame);
		tipoTesteEscolhido = new SimpleObjectProperty<TipoTeste>(_testeEscolhido);
		nota = new SimpleDoubleProperty(_nota);
		numeroAcertos = new SimpleDoubleProperty(_numeroAcertos);
	}

	public SimpleObjectProperty<Certificacao> getExame() {
		return exame;
	}
	
	public SimpleStringProperty getExameStringProperty() {
		return new SimpleStringProperty(exame.get().getNome());
	}
	
	public String getExameNome() {
		return exame.get().getNome();
	}

	public void setExame(SimpleObjectProperty<Certificacao> exame) {
		this.exame = exame;
	}

	public SimpleObjectProperty<TipoTeste> getTipoTesteEscolhido() {
		return tipoTesteEscolhido;
	}
	
	public SimpleStringProperty getTipoTesteEscolhidoStringProperty() {
		return new SimpleStringProperty(tipoTesteEscolhido.get().getNome());
	}
	
	public String getTipoTesteEscolhidoNome() {
		return tipoTesteEscolhido.get().getNome();
	}

	public void setTipoTesteEscolhido(SimpleObjectProperty<TipoTeste> tipoTesteEscolhido) {
		this.tipoTesteEscolhido = tipoTesteEscolhido;
	}
	
	public String getTesteEscolhidoString() {
		return exame.get().getNome() + " - " + tipoTesteEscolhido.get().getNome();
	}
	
	public SimpleStringProperty getTesteEscolhido() {
		return new SimpleStringProperty(exame.get().getNome() + " - " + tipoTesteEscolhido.get().getNome());
	}
	
	public SimpleDoubleProperty getNota() {
		return nota;
	}
	
	public SimpleStringProperty getNotaStringProperty() {
		return new SimpleStringProperty(Double.toString(nota.get()));
	}
	
	public Double getNotaDouble() {
		return nota.get();
	}

	public void setNota(SimpleDoubleProperty nota) {
		this.nota = nota;
	}

	public SimpleDoubleProperty getNumeroAcertos() {
		return numeroAcertos;
	}
	
	public SimpleStringProperty getNumeroAcertosStringProperty() {
		return new SimpleStringProperty(Double.toString(numeroAcertos.get()));
	}

	public Double getNumeroAcertosDouble() {
		return numeroAcertos.get();
	}
	public void setNumeroAcertos(SimpleDoubleProperty numeroAcertos) {
		this.numeroAcertos = numeroAcertos;
	}
	
	
	
}
