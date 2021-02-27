package com.app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class ApiREST {

	private BaseRebelde base;

	public ApiREST() {
		base = BaseRebelde.getInstance();
	}

	@PostMapping("/topsecret")
	public @ResponseBody TopSecret insertar(@RequestBody List<Mensaje> mensajes) throws Exception {

		double[] distancias = new double[mensajes.size()];

		// Recorro los mensajes y los guardo en sus respectivos satelites.
		// Tambien armo lista de distancias para pasarle al metodo getLocation.
		for (int i = 0; i < mensajes.size(); i++) {
			distancias[i] = mensajes.get(i).getDistance();
			base.findSatelite(mensajes.get(i).getName()).setMessage(mensajes.get(i));
			;
		}


		TopSecret secret = new TopSecret(base.getLocation(distancias), base.getMessage(getAllMessages(mensajes)));
		base.borrarMensajesDescartados();
		return secret;

	}

	@PostMapping("/topsecret_split/{satelite_name}")
	public void topSecret_split_post(@PathVariable String satelite_name, @RequestBody Mensaje mensaje) {
		mensaje.setName(satelite_name);

		Satelite satelite = base.findSatelite(mensaje.getName());
		satelite.setMessage(mensaje);

	}

	@GetMapping("/topsecret_split/")
	public TopSecret topSecret_split_get() {

		List<Mensaje> mensajes = base.getSatelitesMessages();

		int message_size = mensajes.size();

		if (message_size < 3)
			throw new ApiNoDataException("No es posible calcular, faltan mensajes");
		double[] distancias = new double[message_size];
		TopSecret secret = null;

		int i = 0;
		for (Mensaje msj : mensajes) {
			distancias[i] = msj.getDistance();
			i = +1;
		}

		try {
			secret = new TopSecret(base.getLocation(distancias), base.getMessage(getAllMessages(mensajes)));
		} catch (Exception e) {
			throw new ApiNoDataException("No hay suficientes datos");
		}
		
		base.borrarMensajesDescartados();

		return secret;

	}
	

	@PutMapping("/updatePosition")
	public void updateSatelite(@RequestBody Satelite satelite) {
		BaseRebelde.getInstance().replaceSatelite(satelite);

	}
	
	private List<List<String>> getAllMessages(List<Mensaje> mensajes){
		List<List<String>> mensaje_texts = new ArrayList<List<String>>();
		for (Mensaje mensaje : mensajes) {
			mensaje_texts.add(mensaje.getMessage());
		}
		return mensaje_texts;
	}


}
