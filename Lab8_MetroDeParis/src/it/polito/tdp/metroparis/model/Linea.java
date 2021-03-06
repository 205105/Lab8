package it.polito.tdp.metroparis.model;

public class Linea {
	
	private int id;
	private String nome;
	private double velocitÓ;
	private double intervallo;
	private String colore;
	
	public Linea(int id, String nome, double velocitÓ, double intervallo, String colore) {
		super();
		this.id = id;
		this.nome = nome;
		this.velocitÓ = velocitÓ;
		this.intervallo = intervallo;
		this.colore = colore;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getVelocitÓ() {
		return velocitÓ;
	}

	public void setVelocitÓ(double velocitÓ) {
		this.velocitÓ = velocitÓ;
	}

	public double getIntervallo() {
		return intervallo;
	}

	public void setIntervallo(double intervallo) {
		this.intervallo = intervallo;
	}

	public String getColore() {
		return colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}
	
	

}
