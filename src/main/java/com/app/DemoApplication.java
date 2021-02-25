package com.app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.features.Position;

@SpringBootApplication
public class DemoApplication {


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		/*	
		List<Satelite> satelites = new ArrayList<Satelite>();

		
		satelites.add(new Satelite("Kenobi", new Position(5.0,1.0), 1.0));
		satelites.add(new Satelite("Skywalker", new Position(6.0,1.0), 1.0));
		satelites.add(new Satelite("sato", new Position(6.0,2.0), 1.0));
		
		List<Mensaje> mensajes = new ArrayList<Mensaje>();
		
		List<String> mensajeKenobi = new ArrayList<String>();
		List<String> mensajeSkywalker = new ArrayList<String>();
		List<String> mensajeSato = new ArrayList<String>();
	    mensajeKenobi.add("Hola");
	    mensajeKenobi.add("");
	    mensajeKenobi.add("Nave");
	    mensajeKenobi.add("");
	    mensajeSkywalker.add("");
	    mensajeSkywalker.add("soy");
	    mensajeSkywalker.add("Nave");
	    mensajeSkywalker.add("");
	    mensajeSato.add("Hola");
	    mensajeSato.add("soy");
	    mensajeSato.add("");
	    mensajeSato.add("Imperial");
	    
		mensajes.add(new Mensaje("Kenobi",mensajeKenobi, 1));
		mensajes.add(new Mensaje("Skywalker",mensajeSkywalker, 1));
		mensajes.add(new Mensaje("sato", mensajeSato , 1));
	
		
		BaseRebelde base = new BaseRebelde(satelites);
		
		 double[] distancias = new double[mensajes.size()];
		 for (int i = 0; i < mensajes.size(); i++) {
		    distancias[i] = mensajes.get(i).distancia_satelite;
		 }
	
		System.out.println(base.getLocation(distancias).getX());
		System.out.println(base.getLocation(distancias).getY());
		
		System.out.println(base.getMessage(mensajes));
		*/
			
		

	}
	

}
