package com.app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.exception.ApiNoDataException;
import com.exception.ApiRequestException;

@RestController
@RequestMapping("api")
public class ApiREST {

	private MensajeDAO MensajeDAO;
	private SateliteDAO SateliteDAO;
	private BaseRebelde base;

	public ApiREST(MensajeDAO mensajeInterface, SateliteDAO satelitenterface) {
		MensajeDAO = mensajeInterface;
		SateliteDAO = satelitenterface;
		List<Satelite> satelites = new ArrayList<Satelite>();

		satelites.add(new Satelite("Kenobi", new Position(-500, -200)));
		satelites.add(new Satelite("Skywalker", new Position(100, -100)));
		satelites.add(new Satelite("Sato", new Position(500, 100)));

		base = new BaseRebelde(satelites);
	}

	@PostMapping("/topsecret")
	public @ResponseBody TopSecret insertar(@RequestBody List<Mensaje> mensajes) throws Exception {
		for (Mensaje mensaje : mensajes) {
			MensajeDAO.save(mensaje);
		}

		double[] distancias = new double[mensajes.size()];
		for (int i = 0; i < mensajes.size(); i++) {
			distancias[i] = mensajes.get(i).getDistance();
		}

		TopSecret secret = new TopSecret(base.getLocation(distancias), base.getMessage(mensajes));
		return secret;

	}

	@DeleteMapping("/eliminar/{id}")
	public void borrar(@PathVariable("id") Integer id) {
		MensajeDAO.deleteById(id);
	}

	@GetMapping("/listMessages")
	public List<Mensaje> list() {
		return MensajeDAO.findAll();

	}
	
	@PostMapping("/insertSatelite")
	public void insertSatelite(@RequestBody Satelite satelite) {
		SateliteDAO.save(satelite);
	}
	
	@PostMapping("/topsecret_split/{satelite_name}")
	public void topSecret_split_post(@PathVariable String satelite_name, @RequestBody Mensaje mensaje) {
		mensaje.setName(satelite_name);
		MensajeDAO.save(mensaje);
	}
	
	@GetMapping("/topsecret_split/")
	public TopSecret topSecret_split_get() {
		
		List<Mensaje> mensajes = MensajeDAO.findAll();
		double[] distancias = new double[mensajes.size()];
		TopSecret secret = null;
		
		int i = 0;
		for (Mensaje msj : mensajes) {
			distancias[i] = msj.getDistance();
			i=+1;
		}

		try {
			secret = new TopSecret(base.getLocation(distancias), base.getMessage(mensajes));
		} catch (Exception e) {
			throw new ApiNoDataException("No hay suficientes datos");
		}
		
		return secret;
		
	}

	@PutMapping("/updatePosition")
	public void updateSatelite(@RequestBody Satelite satelite) {
		SateliteDAO.save(satelite);

	}


}
