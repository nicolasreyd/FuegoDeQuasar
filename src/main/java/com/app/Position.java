package com.app;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "Position")
public class Position {

	@Id
	@GeneratedValue
	@JsonIgnore
	private Integer id;

	@Column
	double x;

	@Column
	double y;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public Position(double _x, double _y) {
		this.x = _x;
		this.y = _y;
	}

	public Position() {
	}
}
