package br.com.oca.util;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.oca.model.Tentativa;

@XmlRootElement(name = "tentativas")
public class TentativaWrapper {
	private List<Tentativa> listaTentativas;

    @XmlElement(name = "tentativa")
    public List<Tentativa> getPersons() {
        return listaTentativas;
    }

    public void setPersons(List<Tentativa> listaTentativas) {
        this.listaTentativas = listaTentativas;
    }

}
