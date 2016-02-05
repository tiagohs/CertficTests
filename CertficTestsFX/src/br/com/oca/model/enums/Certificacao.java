package br.com.oca.model.enums;

public enum Certificacao {
	OCA_JAVA7("Oracle Certified Associate, Java SE 7 Programmer", "OCA_JAVA7"), OCP_JAVA7("Oracle Certified Professional, Java SE 7 Programmer", "OCP_JAVA7");
	
	private String nomeExtenso;
	private String nomeCurto;
	
	private Certificacao(String _nomeExtenso, String _nomeCurto) {
		nomeExtenso = _nomeExtenso;
		nomeCurto = _nomeCurto;
	}
	
	public String getNomeExtenso() {
		return nomeExtenso;
	}
	
	public String getNomeCurto() {
		return nomeCurto;
	}
	
	@Override
	public String toString() {
		return getNomeExtenso();
	}
}
