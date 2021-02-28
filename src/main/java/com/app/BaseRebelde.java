package com.app;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.linear.RealVector;

import com.lemmingapex.trilateration.LinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;

public class BaseRebelde {

	List<Satelite> satelites;
	MensajeParser parser;
	static private BaseRebelde instance;

	private BaseRebelde() {
		satelites = new ArrayList<Satelite>();
		parser = new MensajeParser();
	}

	public static BaseRebelde getInstance() {
		if (instance == null) {

			instance = new BaseRebelde();
		}
		return instance;

	}

	private BaseRebelde(List<Satelite> _satelites) {

		this.satelites = _satelites;
		parser = new MensajeParser();
	}

	public List<Satelite> getSatelites() {
		return satelites;
	}

	public void setSatelites(List<Satelite> satelites) {
		this.satelites = satelites;
	}
	
	/**
	Devuelve la ubicacion de la nave imperial en base a la posicion de los satelites activos.
	Deben existir al menos 3 de estos ultimos para la triangulacion.
	@param distancias
	@return Posicion de la nave imperial
	@throws IllegalArgumentException, ApiNoDataException
	*/
	public Position getLocation(double[] distancias) {
		Position position = null;

		try {
			LinearLeastSquaresSolver solver = new LinearLeastSquaresSolver(
					new TrilaterationFunction(getCoordenadas(), distancias));
			RealVector vector = solver.solve();
			position = new Position(vector.getEntry(0), vector.getEntry(1));
		} catch (IllegalArgumentException e) {
			throw new ApiNoDataException("No se puede calcular la distancia.");
		}
		return position;
	}

	/**
	Obtiene las posiciones de todos los satelites activos.
	@param 
	@return posiciones de los satelites activos en formato x,y.
	*/
	public double[][] getCoordenadas() {

		double[][] coordenadas = new double[satelites.size()][2];

		int i = 0;
		int j = 0;

		for (Satelite satelite : satelites) {
			j = 0;
			coordenadas[i][j] = satelite.position.getX();
			j = j + 1;
			coordenadas[i][j] = satelite.position.getY();
			i = i + 1;
		}

		return coordenadas;
	}
	
	
	/**
	Devuelve el mensaje decodificado.
	@param lista de palabras que contiene cada uno de los mensajes interceptados en los
		   satelites activos.
	@return Mensaje decoficado en formato String.
	@throws Exception, ApiNoDataException.
	*/
	public String getMessage(List<List<String>> mensaje_texts) throws Exception {

		if (mensaje_texts.size() == 0 || mensaje_texts == null) {
			throw new Exception("Invalid request!.");
		} else {

			return this.parser.parse(mensaje_texts);
		}

	}
	
	/**
	Agrega un satelite a la flota rebelde.
	@param Satelite
	@return 
	*/
	public void addSatelite(Satelite satelite) {
		this.satelites.add(satelite);

	}

	/**
	Devuelve el satelite de la flota segun nombre.
	@param String nombre del satelite
	@return Satelite encontrado
	*/
	public Satelite findSatelite(String nombre_satelite) {
		Satelite satelitefounded = null;
		for (Satelite satelite : satelites) {
			if (satelite.getName().equals(nombre_satelite))
				satelitefounded = satelite;
		}
		return satelitefounded;
	}

	/**
	Devuelve los mensajes interceptados en cada satelite
	@param 
	@return Lista de Mensajes
	*/
	public List<Mensaje> getSatelitesMessages() {
		List<Mensaje> mensajes = new ArrayList<Mensaje>();

		for (Satelite satelite : this.satelites) {
			if (satelite.getMessage() != null)
				mensajes.add(satelite.getMessage());
		}
		return mensajes;
	}

	/**
	Reemplaza la ubicacion de los satelites de la flota.
	@param Satelite con nueva ubicacion 
	@return
	*/
	public void replaceSatelite(Satelite satelite) {
		Satelite sateliteactual = findSatelite(satelite.name);
		sateliteactual.setPosition(satelite.getPosition());

	}

	/**
	Borra mensajes ya decodificados.
	@paramn 
	@return
	*/
	public void borrarMensajesDescartados() {
		for (Satelite satelite : satelites) {
			satelite.setMessage(null);
		}

	}

}
