package com.app;

public class Satelite {
	// Atributos

	String name;

	Position position;

	Mensaje message;

	public Satelite(String _nombre, Position _coordenadas) {

		this.name = _nombre;
		this.position = _coordenadas;
	}

	public Satelite(String _nombre, Position _coordenadas, Mensaje _mensaje) {

		this.name = _nombre;
		this.position = _coordenadas;
		this.message = _mensaje;
	}

	public Satelite() {

	}

	public Mensaje getMessage() {
		return message;
	}

	public void setMessage(Mensaje message) {
		this.message = message;
	}

	public String getName() {
		return name;
	}

	public void setName(String nombre) {
		this.name = nombre;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position coordenada) {
		this.position = coordenada;
	}

}
