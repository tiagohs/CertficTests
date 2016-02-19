package br.com.oca.model;

import java.util.ArrayList;

import br.com.oca.model.enums.TipoQuestao;

public class Resposta {
	private TipoQuestao tipoQuestao;
	private String enunciado;
	private String resposta;
	private ArrayList<String> respostas;
	
	public Resposta(String enunciado, ArrayList<String> respostas) {
		this(enunciado, null, respostas, TipoQuestao.MULTIPLAS_ALTERNATIVAS);
	}
	public Resposta(String enunciado, String resposta) {
		this(enunciado, resposta, null, TipoQuestao.UMA_ALTERNATIVA);
	}
	
	public Resposta(String enunciado) {
		this(enunciado, null, null, null);
	}
	
	public Resposta(String _enunciado, String _resposta, ArrayList<String> _respostas, TipoQuestao _tipoQuestao) {
		enunciado = _enunciado;
		resposta = _resposta;
		respostas = _respostas;
		tipoQuestao = _tipoQuestao;
	}
	
	public String getEnunciado() {
		return enunciado;
	}
	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}
	public String getResposta() {
		return resposta;
	}
	public void setResposta(String resposta) {
		this.resposta = resposta;
	}
	public ArrayList<String> getRespostas() {
		return respostas;
	}
	public void setRespostas(ArrayList<String> respostas) {
		this.respostas = respostas;
	}
	public TipoQuestao getTipoQuestao() {
		return tipoQuestao;
	}
	public void setTipoQuestao(TipoQuestao tipoQuestao) {
		this.tipoQuestao = tipoQuestao;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof Resposta) {
			Resposta resposta = (Resposta) obj;
			return this.getEnunciado().equals(resposta.getEnunciado());
		}
		return false;
	}
}
