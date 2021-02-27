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

	public String getMessage(List<List<String>> mensaje_texts) throws Exception {

		if (mensaje_texts.size() == 0 || mensaje_texts == null) {
			throw new Exception("Invalid request!.");
		} else {
			
		return	this.parser.parse(mensaje_texts); 
		}
		
	}

	/*
	 * private int getMaxSize(List<Mensaje> mensajes) { int max = 0; for (Mensaje
	 * mensaje : mensajes) { if (mensaje.getMessage().size() > max) max =
	 * mensaje.getMessage().size(); } return max; }
	 */
	public void addSatelite(Satelite satelite) {
		this.satelites.add(satelite);

	}

	public Satelite findSatelite(String nombre_satelite) {
		Satelite satelitefounded = null;
		for (Satelite satelite : satelites) {
			if (satelite.getName().equals(nombre_satelite))
				satelitefounded = satelite;
		}
		return satelitefounded;
	}

	public List<Mensaje> getSatelitesMessages() {
		List<Mensaje> mensajes = new ArrayList<Mensaje>();

		for (Satelite satelite : this.satelites) {
			if (satelite.getMessage() != null)
				mensajes.add(satelite.getMessage());
		}
		return mensajes;
	}

	public void replaceSatelite(Satelite satelite) {
		Satelite sateliteactual = findSatelite(satelite.name);
		satelites.remove(sateliteactual);
		this.satelites.add(satelite);

	}

	public void borrarMensajesDescartados() {
		for (Satelite satelite : satelites) {
			satelite.setMessage(null);
		}
		
	}

}
