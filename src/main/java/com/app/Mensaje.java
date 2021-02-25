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
	String nombre_satelite;
	
	@Column
	double distancia_satelite;
	
	@ElementCollection
	List<String> mensaje_texto;
    
	
	
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getNombre_satelite() {
		return nombre_satelite;
	}



	public void setNombre_satelite(String nombre_satelite) {
		this.nombre_satelite = nombre_satelite;
	}



	public double getDistancia_satelite() {
		return distancia_satelite;
	}



	public void setDistancia_satelite(double distancia_satelite) {
		this.distancia_satelite = distancia_satelite;
	}



	public List<String> getMensaje_texto() {
		return mensaje_texto;
	}



	public void setMensaje_texto(List<String> mensaje_texto) {
		this.mensaje_texto = mensaje_texto;
	}



	public Mensaje(String _nombre_satelite, List<String> _mensaje_texto, double _distancia_satelite) {
		this.nombre_satelite = _nombre_satelite;
		this.mensaje_texto = _mensaje_texto;
		this.distancia_satelite = _distancia_satelite;
		
	}

}
