package br.com.oca.tentativas;

import java.io.Serializable;

import br.com.oca.enums.TipoTeste;

public class Tentativa implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private TipoTeste testeEscolhido;
	private Double nota;
	
	public Tentativa(TipoTeste _testeEscolhido, Double _nota) {
		testeEscolhido = _testeEscolhido;
		nota = _nota;
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
	
	
}
