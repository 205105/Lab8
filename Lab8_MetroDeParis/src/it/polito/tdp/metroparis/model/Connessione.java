package it.polito.tdp.metroparis.model;

public class Connessione {
	
	private int id;
	private Linea linea;
	private Fermata stazP;
	private Fermata stazA;
	
	public Connessione(int id, Linea linea, Fermata stazP, Fermata stazA) {
		super();
		this.id = id;
		this.linea = linea;
		this.stazP = stazP;
		this.stazA = stazA;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Linea getLinea() {
		return linea;
	}

	public void setLinea(Linea linea) {
		this.linea = linea;
	}

	public Fermata getStazP() {
		return stazP;
	}

	public void setStazP(Fermata stazP) {
		this.stazP = stazP;
	}

	public Fermata getStazA() {
		return stazA;
	}

	public void setStazA(Fermata stazA) {
		this.stazA = stazA;
	}
	
	
	

}
