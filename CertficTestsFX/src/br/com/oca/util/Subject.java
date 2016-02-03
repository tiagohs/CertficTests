package br.com.oca.util;

public interface Subject {
	
	public void notifyAllObservers();
	public void addObserver(Observer novoObserver);
	public void removeObserve(Observer novoObserver);
}
