package com.app;

import java.util.List;

public class MensajeParser {

	public String parse(List<List<String>> mensaje_texts) {
		String[] mensaje_final = new String[getMaxSize(mensaje_texts)];

		int posicion_ocupada = 0;
		for (List<String> mensaje : mensaje_texts) {
			for (int i = 0; i < mensaje.size(); i++) {
				if (mensaje.get(i).length() > 0) {
					if (mensaje_final[i] == null || posicion_ocupada == 0) {
						mensaje_final[i] = mensaje.get(i);
						posicion_ocupada += 1;
					}
				}
			}
		}
		
		String message = String.join(" ", mensaje_final);
		if(message.contains("null")) {
			throw new ApiNoDataException("No se puede decodificar el mensaje");
		}
		return String.join(" ", message);
		
	}

	private int getMaxSize(List<List<String>> mensaje_texts) {
		int max = 0;
		for (List<String> mensaje : mensaje_texts) {
			if (mensaje.size() > max)
				max = mensaje.size();
		}
		return max;
	}

}
