package br.com.oca.model;

import java.io.Serializable;

import br.com.oca.model.enums.Certificacao;
import br.com.oca.model.enums.TipoTeste;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Tentativa implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String filename = "tentativas.bin";
	
	private SimpleObjectProperty<Certificacao> exame;
	private SimpleObjectProperty<TipoTeste> testeEscolhido;
	private SimpleDoubleProperty nota;
	private SimpleDoubleProperty numeroAcertos;
	
	public Tentativa(Certificacao _exame, TipoTeste _testeEscolhido, Double _nota, Double _numeroAcertos) {
		exame = new SimpleObjectProperty<Certificacao>(_exame);
		testeEscolhido = new SimpleObjectProperty<TipoTeste>(_testeEscolhido);
		nota = new SimpleDoubleProperty(_nota);
		numeroAcertos = new SimpleDoubleProperty(_numeroAcertos);
	}

	public SimpleObjectProperty<Certificacao> getExame() {
		return exame;
	}
	
	public String getExameNome() {
		return exame.get().getNome();
	}

	public void setExame(SimpleObjectProperty<Certificacao> exame) {
		this.exame = exame;
	}

	public SimpleObjectProperty<TipoTeste> getTesteEscolhido() {
		return testeEscolhido;
	}
	
	public String getTesteEscolhidoNome() {
		return testeEscolhido.get().getNome();
	}

	public void setTesteEscolhido(SimpleObjectProperty<TipoTeste> testeEscolhido) {
		this.testeEscolhido = testeEscolhido;
	}

	public SimpleDoubleProperty getNota() {
		return nota;
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

	public Double getNumeroAcertosDouble() {
		return numeroAcertos.get();
	}
	public void setNumeroAcertos(SimpleDoubleProperty numeroAcertos) {
		this.numeroAcertos = numeroAcertos;
	}
	
	
	
}
