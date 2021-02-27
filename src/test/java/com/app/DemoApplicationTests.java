package com.app;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.Math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void locationTest1() {
		this.cleanTest();
		BaseRebelde base = BaseRebelde.getInstance();

		Mensaje msjSky = new Mensaje("Skywalker", new ArrayList<String>(), 1);
		Mensaje msjKenobi = new Mensaje("Kenobi", new ArrayList<String>(), 1);
		Mensaje msjSato = new Mensaje("Sato", new ArrayList<String>(), Math.sqrt(2));

		base.addSatelite(new Satelite("Skywalker", new Position(2, 1), msjSky));
		base.addSatelite(new Satelite("Kenobi", new Position(1, 2), msjKenobi));
		base.addSatelite(new Satelite("Sato", new Position(1, 1), msjSato));

		double[] distancias = new double[3];

		distancias[0] = msjSky.getDistance();
		distancias[1] = msjKenobi.getDistance();
		distancias[2] = msjSato.getDistance();

		Assertions.assertEquals(2.0000000000000004, base.getLocation(distancias).getX());

	}

	@Test
	void locationTestException() {

		this.cleanTest();
		// No hay cantidad suficiente de distancias para calcular.
		BaseRebelde base = BaseRebelde.getInstance();

		Mensaje msjSky = new Mensaje("Skywalker", new ArrayList<String>(), 1);
		Mensaje msjKenobi = new Mensaje("Kenobi", new ArrayList<String>(), 1);

		base.addSatelite(new Satelite("Skywalker", new Position(2, 1), msjSky));
		base.addSatelite(new Satelite("Kenobi", new Position(1, 2), msjKenobi));
		base.addSatelite(new Satelite("Sato", new Position(1, 1)));

		double[] distancias = new double[2];

		distancias[0] = msjSky.getDistance();
		distancias[1] = msjKenobi.getDistance();

		Assertions.assertThrows(ApiNoDataException.class, () -> base.getLocation(distancias));

	}

	@Test
	void locationMessage1() throws Exception {
		this.cleanTest();
		BaseRebelde base = BaseRebelde.getInstance();

		List<String> msjSKy_content = new ArrayList<String>();
		msjSKy_content.add("");
		msjSKy_content.add("");
		msjSKy_content.add("un");
		msjSKy_content.add("mensaje");

		List<String> msjKen_content = new ArrayList<String>();
		msjKen_content.add("este");
		msjKen_content.add("es");
		msjKen_content.add("");
		msjKen_content.add("");
		msjKen_content.add("");

		List<String> msjSato_content = new ArrayList<String>();
		msjSato_content.add("");
		msjSato_content.add("");
		msjSato_content.add("un");
		msjSato_content.add("mensaje");
		msjSato_content.add("clasificado");

		List<List<String>> allMessages = new ArrayList<List<String>>();
		allMessages.add(msjSato_content);
		allMessages.add(msjKen_content);
		allMessages.add(msjSKy_content);

		Assertions.assertEquals("este es un mensaje clasificado", base.getMessage(allMessages));

	}

	@Test
	void locationMessageException() throws Exception {
		this.cleanTest();
		BaseRebelde base = BaseRebelde.getInstance();

		List<List<String>> allMessages = new ArrayList<List<String>>();

		Assertions.assertThrows(Exception.class, () -> base.getMessage(allMessages));

	}

	@Test
	void getCoordenadas1() throws Exception {
		this.cleanTest();
		BaseRebelde base = BaseRebelde.getInstance();

		base.addSatelite(new Satelite("Skywalker", new Position(100, 1)));
		base.addSatelite(new Satelite("Kenobi", new Position(300, -200)));
		base.addSatelite(new Satelite("Sato", new Position(400, 15)));

		double[][] coordenadas = base.getCoordenadas();

		assertEquals(-200, coordenadas[1][1]);
	}

	@Test
	void findSatelite1() throws Exception {
		this.cleanTest();
		BaseRebelde base = BaseRebelde.getInstance();

		base.addSatelite(new Satelite("Skywalker", new Position(100, 1)));
		base.addSatelite(new Satelite("Kenobi", new Position(300, -200)));

		assertEquals(100, base.findSatelite("Skywalker").getPosition().getX());
	}
	
	@Test
	void replaceSatelite1() throws Exception {
		this.cleanTest();
		BaseRebelde base = BaseRebelde.getInstance();

		base.addSatelite(new Satelite("Kenobi", new Position(100, 1)));
		Satelite sateliteKenobi = new Satelite("Kenobi", new Position(300, -200));
		 base.replaceSatelite(sateliteKenobi);
		 
		assertEquals(-200, base.findSatelite("Kenobi").getPosition().getY());
	}

	private void cleanTest() {
		BaseRebelde base = BaseRebelde.getInstance();

		base.satelites.clear();

	}

}
