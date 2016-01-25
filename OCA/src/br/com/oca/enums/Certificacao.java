package br.com.oca.enums;

public enum Certificacao {
	OCA("Oracle Certified Associate, Java SE 7 Programmer"), OCP("Oracle Certified Professional, Java SE 7 Programmer");
	
	private String nome;
	
	private Certificacao(String _nome) {
		nome = _nome;
	}
	
	public String getNome() {
		return nome;
	}
	
}
