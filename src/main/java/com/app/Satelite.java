package com.app;

import com.features.Position;

public class Satelite {
   //Atributos
	String nombre;
	Position coordenada;
	

	public Satelite(String _nombre, Position _coordenadas) {
		
		this.nombre = _nombre;
		this.coordenada = _coordenadas;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Position getCoordenada() {
		return coordenada;
	}

	public void setCoordenada(Position coordenada) {
		this.coordenada = coordenada;
	}
	

}
