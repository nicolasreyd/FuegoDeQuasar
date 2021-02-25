package com.app;

import com.features.Position;

public class TopSecret {
	Position position;
	String message;
	
	
	public Position getUbicacion() {
		return position;
	}


	public void setUbicacion(Position ubicacion) {
		this.position = ubicacion;
	}


	public String getMensaje() {
		return message;
	}


	public void setMensaje(String mensaje) {
		this.message = mensaje;
	}


	public TopSecret(Position _ubicacion, String _mensaje) {
		this.position = _ubicacion;
		this.message = _mensaje;
		
	}
	
}
