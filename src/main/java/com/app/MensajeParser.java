package com.app;

import java.util.List;

public class MensajeParser {

	public String parse(List<Mensaje> mensajes) {
		String[] mensaje_final = new String[getMaxSize(mensajes)];

		int posicion_ocupada = 0;
		for (Mensaje mensaje : mensajes) {
			for (int i = 0; i < mensaje.getMessage().size(); i++) {
				if (mensaje.getMessage().get(i).length() > 0) {
					if (mensaje_final[i] == null || posicion_ocupada == 0) {
						mensaje_final[i] = mensaje.getMessage().get(i);
						posicion_ocupada += 1;
					}
				}
			}
		}
		
		String message = String.join(" ", mensaje_final);
		if(message.contains("null")) {
			message = "No hay suficiente informacion.";
		}
		return String.join(" ", message);
		
	}

	private int getMaxSize(List<Mensaje> mensajes) {
		int max = 0;
		for (Mensaje mensaje : mensajes) {
			if (mensaje.getMessage().size() > max)
				max = mensaje.getMessage().size();
		}
		return max;
	}

}
