package com.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.math3.linear.RealVector;

import com.features.Position;
import com.lemmingapex.trilateration.LinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;

public class BaseRebelde {
	
	List<Satelite> satelites;
	
	public List<Satelite> getSatelites() {
		return satelites;
	}

	public void setSatelites(List<Satelite> satelites) {
		this.satelites = satelites;
	}

	public BaseRebelde(List<Satelite> _satelites) {
		
		this.satelites = _satelites;
	}
	
	public BaseRebelde() {
		satelites = new ArrayList<Satelite>();
	}
	
	public Position getLocation(double[] distancias) {
		
		LinearLeastSquaresSolver solver = new LinearLeastSquaresSolver(new TrilaterationFunction(getCoordenadas(), distancias));
		RealVector vector = solver.solve();
		
		return new Position(vector.getEntry(0),vector.getEntry(1));
	}
	
	
	private double[][] getCoordenadas(){
		
		double[][] coordenadas = new double[satelites.size()][2];
		
		int i = 0;
		int j = 0;
		
		for (Satelite satelite : satelites) {
			j=0;
			coordenadas[i][j] = satelite.coordenada.getX();
			j=j+1;
			coordenadas[i][j] = satelite.coordenada.getY();
			i=i+1;
		}
		
		return coordenadas;
	}

	public String getMessage(List<Mensaje> mensajes) {
		
		String[] mensaje_final = new String[getMaxSize(mensajes)];
		
		int posicion_ocupada=0;
		for (Mensaje mensaje : mensajes) {
			for (int i = 0; i < mensaje.mensaje_texto.size(); i++) {
				if(mensaje.mensaje_texto.get(i) != "") {
					if(mensaje_final[i] == null || posicion_ocupada == 0) {
					mensaje_final[i] = mensaje.mensaje_texto.get(i);
				    posicion_ocupada += 1;
					}
				}
			}
		}
		
		return String.join(" ", mensaje_final);
		
	}

	private int getMaxSize(List<Mensaje> mensajes) {
		int max = 0;
		for (Mensaje mensaje : mensajes) {
			if(mensaje.mensaje_texto.size() > max)
				max = mensaje.mensaje_texto.size();
		}
		return max;
	}

	public void addSatelite(Satelite satelite) {
		this.satelites.add(satelite);
		
	}


}
