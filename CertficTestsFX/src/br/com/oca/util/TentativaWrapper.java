package br.com.oca.util;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.oca.model.Tentativas;

@XmlRootElement(name = "tentativas")
public class TentativaWrapper {
	private List<Tentativas> listaTentativas;

    @XmlElement(name = "tentativa")
    public List<Tentativas> getPersons() {
        return listaTentativas;
    }

    public void setPersons(List<Tentativas> listaTentativas) {
        this.listaTentativas = listaTentativas;
    }

}
