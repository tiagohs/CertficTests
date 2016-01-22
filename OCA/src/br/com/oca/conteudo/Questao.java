package br.com.oca.conteudo;

import java.util.HashMap;

public class Questao {
	private String enunciado;
	private HashMap<Character, String> listaAlternativas;
	private Character alternativaCorreta;
	
	public Questao(String _enunciado, HashMap<Character, String> _alternativas, Character _alternativaCorreta) {
		enunciado = _enunciado;
		listaAlternativas = _alternativas;
		alternativaCorreta = _alternativaCorreta;
	}
	
	public Questao(String _enunciado, Character _alternativaCorreta) {
		this(_enunciado, new HashMap<Character, String>(), _alternativaCorreta);
	}
	
	public Questao(String enunciado) {
		this(enunciado, new HashMap<Character, String>(), ' ');
	}

	public Questao(HashMap<Character, String> alternativas) {
		this("", alternativas, ' ');
	}

	public Questao() {
		this("", new HashMap<Character, String>(), ' ');
	}
	
	public Questao(Character alternativaCorreta) {
		this("", new HashMap<Character, String>(), alternativaCorreta);
	}

	public void addAlternativa(Character letra, String enunciado) {
		listaAlternativas.put(letra, enunciado);
	}
	
	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public HashMap<Character, String> getlistaAlternativas() {
		return listaAlternativas;
	}

	public void setlistaAlternativas(HashMap<Character, String> alternativas) {
		this.listaAlternativas = alternativas;
	}
	
	public Character getAlternativaCorreta() {
		return alternativaCorreta;
	}

	public void setAlternativaCorreta(Character alternativaCorreta) {
		this.alternativaCorreta = alternativaCorreta;
	}

	public boolean containsAlternativa(Character key) {
		return listaAlternativas.containsKey(key);
	}
	
	public String getAlternativa(String letra) {
		return listaAlternativas.get(letra);
	}
	
	public int getNumeroTotalAlternativas() {
		return listaAlternativas.size();
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof Questao) {
			Questao questao = (Questao) obj;
			return getEnunciado().equals(questao.getEnunciado());
		}
		
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
