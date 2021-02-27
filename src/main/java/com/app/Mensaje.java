package com.app;

import java.util.List;

public class Mensaje {

	String name;

	double distance;

	List<String> message;

	public String getNombre_satelite() {
		return name;
	}

	public void setName(String nombre_satelite) {
		this.name = nombre_satelite;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distancia_satelite) {
		this.distance = distancia_satelite;
	}

	public List<String> getMessage() {
		return message;
	}

	public void setMessage(List<String> mensaje_texto) {
		this.message = mensaje_texto;
	}

	public Mensaje(String _nombre_satelite, List<String> _mensaje_texto, double _distancia_satelite) {
		this.name = _nombre_satelite;
		this.message = _mensaje_texto;
		this.distance = _distancia_satelite;

	}

	public Mensaje() {

	}

}
