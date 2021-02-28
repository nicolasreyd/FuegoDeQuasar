package com.app;

public class TopSecret {
	Position position;
	String message;

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position ubicacion) {
		this.position = ubicacion;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String mensaje) {
		this.message = mensaje;
	}

	public TopSecret(Position _ubicacion, String _mensaje) {
		this.position = _ubicacion;
		this.message = _mensaje;

	}

}
