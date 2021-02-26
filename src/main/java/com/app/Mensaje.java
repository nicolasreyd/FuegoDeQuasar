package com.app;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Mensaje {

	@Id
	@GeneratedValue
	private Integer id;

	@Column
	String name;

	@Column
	double distance;

	@ElementCollection
	List<String> message;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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
