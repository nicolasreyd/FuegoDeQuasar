package com.app;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Type;

@Entity
public class Satelite {
	// Atributos

	@Id
	@GeneratedValue
	private Integer id;

	@Column
	String name;

	@OneToOne(cascade=CascadeType.ALL)
	Position position;

	public Satelite(String _nombre, Position _coordenadas) {

		this.name = _nombre;
		this.position = _coordenadas;
	}
	
	public Satelite() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
