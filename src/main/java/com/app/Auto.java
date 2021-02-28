package com.app;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Auto {
    
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column
	private String Marca;
	
	@Column
	private String Modelo;
	
	@Column
	private Integer Anio;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMarca() {
		return Marca;
	}

	public void setMarca(String marca) {
		Marca = marca;
	}

	public String getModelo() {
		return Modelo;
	}

	public void setModelo(String modelo) {
		Modelo = modelo;
	}

	public Integer getAnio() {
		return Anio;
	}

	public void setAnio(Integer anio) {
		Anio = anio;
	}
	
}
