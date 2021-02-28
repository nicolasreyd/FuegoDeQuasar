package com.features;

public class Position {
	
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

	double x;
	double y;
	
	public Position(double _x, double _y) {
		this.x = _x;
		this.y = _y;
	}
}
