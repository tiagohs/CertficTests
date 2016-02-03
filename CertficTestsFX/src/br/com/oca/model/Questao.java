package br.com.oca.model;

import java.util.ArrayList;
import java.util.HashMap;

import br.com.oca.model.enums.TipoQuestao;

public class Questao {
	private TipoQuestao tipoQuestao;
	private String enunciado;
	private String enunciadoExtras;
	private String referencia;
	private HashMap<Character, String> listaAlternativas;
	private Character alternativaCorreta;
	private ArrayList<Character> alternativasCorretas;
	
	public Questao(String _enunciado, HashMap<Character, String> _alternativas, Character _alternativaCorreta, ArrayList<Character> _alternativasCorretas, TipoQuestao _tipoQuestao, String _referencia, String _enunciadoExtras) {
		enunciado = _enunciado;
		listaAlternativas = _alternativas;
		alternativaCorreta = _alternativaCorreta;
		alternativasCorretas = _alternativasCorretas;
		tipoQuestao = _tipoQuestao;
		referencia = _referencia;
		enunciadoExtras = _enunciadoExtras;
	}
	
	public Questao(String _enunciado, TipoQuestao _tipoQuestao, String _referencia, String _enunciadoExtras) {
		this(_enunciado, new HashMap<Character, String>(), ' ', new ArrayList<Character>(), _tipoQuestao, _referencia, _enunciadoExtras);
	}
	
	public Questao(String _enunciado, ArrayList<Character> _alternativasCorretas, String _referencia, String _enunciadoExtras) {
		this(_enunciado, new HashMap<Character, String>(), null, _alternativasCorretas, TipoQuestao.MULTIPLA,_referencia, _enunciadoExtras);
	}
	
	public Questao(String _enunciado, Character _alternativaCorreta, String _referencia, String _enunciadoExtras) {
		this(_enunciado, new HashMap<Character, String>(), _alternativaCorreta, null, TipoQuestao.UNICA, _referencia, _enunciadoExtras);
	}
	
	public Questao() {
		this("", new HashMap<Character, String>(), ' ', new ArrayList<Character>(), TipoQuestao.UNICA, "", null);
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
	
	public String getEnunciadoAlternativaCorreta() {
		return getAlternativa(alternativaCorreta);
	}
	
	public boolean containsAlternativa(Character key) {
		return listaAlternativas.containsKey(key);
	}
	
	public String getAlternativa(Character letra) {
		return listaAlternativas.get(letra);
	}
	
	public int getNumeroTotalAlternativas() {
		return listaAlternativas.size();
	}
	
	public HashMap<Character, String> getListaAlternativas() {
		return listaAlternativas;
	}
	
	public void setListaAlternativas(
			HashMap<Character, String> listaAlternativas) {
		this.listaAlternativas = listaAlternativas;
	}
	
	public TipoQuestao getTipoQuestao() {
		return tipoQuestao;
	}
	
	public void setTipoQuestao(TipoQuestao tipoQuestao) {
		this.tipoQuestao = tipoQuestao;
	}
	
	public ArrayList<Character> getAlternativasCorretas() {
		return alternativasCorretas;
	}
	
	public void setAlternativasCorretas(ArrayList<Character> alternativasCorretas) {
		this.alternativasCorretas = alternativasCorretas;
	}
	
	public void addAlternativaCorreta(Character letra) {
		alternativasCorretas.add(letra);
	}
	
	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	
	public String getEnunciadoExtras() {
		return enunciadoExtras;
	}
	
	public void setEnunciadoExtras(String enunciadoExtras) {
		this.enunciadoExtras = enunciadoExtras;
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
