package com.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.math3.linear.RealVector;

import com.exception.ApiNoDataException;
import com.lemmingapex.trilateration.LinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;

public class BaseRebelde {

	List<Satelite> satelites;
	MensajeParser parser;

	public BaseRebelde() {
		satelites = new ArrayList<Satelite>();
		parser = new MensajeParser();
	}

	public BaseRebelde(List<Satelite> _satelites) {

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
			LinearLeastSquaresSolver solver = new LinearLeastSquaresSolver(new TrilaterationFunction(getCoordenadas(), distancias));
			RealVector vector = solver.solve();
			position = new Position(vector.getEntry(0), vector.getEntry(1));
		} catch (IllegalArgumentException e) {
			throw new ApiNoDataException("Cantidad incorrecta de parametros.");
		}
		return position;
	}

	private double[][] getCoordenadas() {

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

	public String getMessage(List<Mensaje> mensajes) throws Exception {

		if (mensajes.size() == 0 || mensajes == null) {
			throw new Exception("Invalid request!.");
		} else {
			return this.parser.parse(mensajes);
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

}
