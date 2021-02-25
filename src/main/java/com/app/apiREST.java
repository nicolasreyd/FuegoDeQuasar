package com.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.features.Position;

@RestController
@RequestMapping("api")
public class apiREST {
	
	private MensajeDAO MensajeDAO;
	private BaseRebelde base;
	
	public apiREST(MensajeDAO mensajeInterface) {
		MensajeDAO = mensajeInterface;
		List<Satelite> satelites = new ArrayList<Satelite>();

		satelites.add(new Satelite("Kenobi", new Position(-500, -200)));
		satelites.add(new Satelite("Skywalker", new Position(100, -100)));
		satelites.add(new Satelite("Sato", new Position(500, 100)));
		
		base = new BaseRebelde(satelites);
	}
	
	
	@PostMapping("/topsecret")
	public @ResponseBody TopSecret insertar(@RequestBody List<Mensaje> mensajes) {
		for (Mensaje mensaje : mensajes) {
			MensajeDAO.save(mensaje);
		}

		 double[] distancias = new double[mensajes.size()];
		 for (int i = 0; i < mensajes.size(); i++) {
		    distancias[i] = mensajes.get(i).distancia_satelite;
		 }
		
		TopSecret secret = new TopSecret(base.getLocation(distancias), base.getMessage(mensajes));
		return secret;
	
	}
	
	@DeleteMapping("/eliminar/{id}")
	public void borrar(@PathVariable("id") Integer id) {
		MensajeDAO.deleteById(id);
	}
	
	@GetMapping("/listar")
	public List<Mensaje> list () {
		return MensajeDAO.findAll();

	}
	
	@PutMapping("/actualizar")
	public void update(@RequestBody Mensaje mensaje) {
		MensajeDAO.save(mensaje);
		
	}
	
	/*
	@GetMapping("/listar/{auto}")
	public Optional<Auto> getAuto(@PathVariable("auto") Integer auto) {
		return MensajeDAO.findById(auto);
	}*/
	
}
