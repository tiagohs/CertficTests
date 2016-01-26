package br.com.oca.tentativas;

import java.io.Serializable;

import br.com.oca.enums.Certificacao;
import br.com.oca.enums.TipoTeste;

public class Tentativa implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String filename = "tentativas.bin";
	
	private Certificacao exame;
	private TipoTeste testeEscolhido;
	private Double nota;
	private Integer numeroAcertos;
	
	public Tentativa(Certificacao _exame, TipoTeste _testeEscolhido, Double _nota, Integer _numeroAcertos) {
		exame = _exame;
		testeEscolhido = _testeEscolhido;
		nota = _nota;
		numeroAcertos = _numeroAcertos;
	}
	
	public Certificacao getExame() {
		return exame;
	}
	
	public void setExame(Certificacao exame) {
		this.exame = exame;
	}

	public TipoTeste getTesteEscolhido() {
		return testeEscolhido;
	}

	public void setTesteEscolhido(TipoTeste testeEscolhido) {
		this.testeEscolhido = testeEscolhido;
	}

	public Double getNota() {
		return nota;
	}
	
	public void setNota(Double nota) {
		this.nota = nota;
	}
	
	public Integer getNumeroAcertos() {
		return numeroAcertos;
	}
	
	public void setNumeroAcertos(Integer numeroAcertos) {
		this.numeroAcertos = numeroAcertos;
	}
	
}
