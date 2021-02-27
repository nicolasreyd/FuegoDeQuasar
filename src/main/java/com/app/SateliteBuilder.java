package com.app;

public class SateliteBuilder {

	private Satelite satelite;

	public SateliteBuilder() {
		this.satelite = new Satelite();

	}

	public SateliteBuilder setName(String name) {
		this.satelite.setName(name);
		return this;
	}

	public SateliteBuilder setPosition(double x, double y) {
		this.satelite.setPosition(new Position(x, y));
		return this;
	}

	public SateliteBuilder setMessage(Mensaje mensaje) {
		this.satelite.setMessage(mensaje);
		return this;

	}

	public Satelite build() {
		return this.satelite;
	}
}
