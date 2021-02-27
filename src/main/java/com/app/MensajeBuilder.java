package com.app;

import java.util.Arrays;
import java.util.List;

public class MensajeBuilder {
	private Mensaje mensaje;

	public MensajeBuilder() {
		this.mensaje = new Mensaje();

	}

	public MensajeBuilder setName(String name) {
		this.mensaje.setName(name);
		return this;
	}

	public MensajeBuilder setDistance(double distance) {
		this.mensaje.setDistance(distance);
		return this;
	}

	public MensajeBuilder setMessage(String[] mensaje) {
		List<String> wordList = Arrays.asList(mensaje);  
		this.mensaje.setMessage(wordList);
		return this;

	}

	public Mensaje build() {
		return this.mensaje;
	}
}
