package com.app;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static io.restassured.RestAssured.given;

import java.lang.Math;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

class DemoApplicationTests {

	@BeforeEach
	public void setUp() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
	}

	@Test
	void contextLoads() {
	}

	@Test
	void locationTest1() {
		this.cleanTest();
		BaseRebelde base = BaseRebelde.getInstance();

		Mensaje msjSky = new MensajeBuilder().setDistance(1).setName("Skywalker").build();
		Mensaje msjKenobi = new MensajeBuilder().setDistance(1).setName("Kenobi").build();
		Mensaje msjSato = new MensajeBuilder().setDistance(Math.sqrt(2)).setName("Sato").build();

		Satelite sateliteSky = new SateliteBuilder().setName("Skywalker").setPosition(2, 1).setMessage(msjSky).build();
		Satelite sateliteKenobi = new SateliteBuilder().setName("Kenobi").setPosition(1, 2).setMessage(msjKenobi)
				.build();
		Satelite sateliteSato = new SateliteBuilder().setName("Sato").setPosition(1, 1).setMessage(msjSato).build();

		base.addSatelite(sateliteSky);
		base.addSatelite(sateliteKenobi);
		base.addSatelite(sateliteSato);

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

		Mensaje msjSky = new MensajeBuilder().setDistance(1).setName("Skywalker").build();
		Mensaje msjKenobi = new MensajeBuilder().setDistance(1).setName("Kenobi").build();

		Satelite sateliteSky = new SateliteBuilder().setName("Skywalker").setPosition(2, 1).setMessage(msjSky).build();
		Satelite sateliteKenobi = new SateliteBuilder().setName("Kenobi").setPosition(1, 2).setMessage(msjKenobi)
				.build();
		Satelite sateliteSato = new SateliteBuilder().setName("Sato").setPosition(1, 1).build();

		base.addSatelite(sateliteSky);
		base.addSatelite(sateliteKenobi);
		base.addSatelite(sateliteSato);

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

		Satelite sateliteSky = new SateliteBuilder().setName("Skywalker").setPosition(100, 1).build();
		Satelite sateliteKenobi = new SateliteBuilder().setName("Kenobi").setPosition(300, -200).build();
		Satelite sateliteSato = new SateliteBuilder().setName("Sato").setPosition(400, 15).build();

		base.addSatelite(sateliteSky);
		base.addSatelite(sateliteKenobi);
		base.addSatelite(sateliteSato);

		double[][] coordenadas = base.getCoordenadas();

		assertEquals(-200, coordenadas[1][1]);
	}

	@Test
	void findSatelite1() throws Exception {
		this.cleanTest();
		BaseRebelde base = BaseRebelde.getInstance();

		Satelite sateliteSky = new SateliteBuilder().setName("Skywalker").setPosition(100, 1).build();
		Satelite sateliteKenobi = new SateliteBuilder().setName("Kenobi").setPosition(300, -200).build();

		base.addSatelite(sateliteSky);
		base.addSatelite(sateliteKenobi);

		assertEquals(100, base.findSatelite("Skywalker").getPosition().getX());
	}

	@Test
	void replaceSatelite1() throws Exception {
		this.cleanTest();
		BaseRebelde base = BaseRebelde.getInstance();

		base.addSatelite(new SateliteBuilder().setName("Kenobi").setPosition(100, 1).build());

		Satelite sateliteKenobi = new SateliteBuilder().setName("Kenobi").setPosition(300, -200).build();
		base.replaceSatelite(sateliteKenobi);

		assertEquals(-200, base.findSatelite("Kenobi").getPosition().getY());
	}

	@Test
	public void testTopSecret() throws URISyntaxException {
		this.cleanTest();
		BaseRebelde base = BaseRebelde.getInstance();

		Satelite sateliteSky = new SateliteBuilder().setName("Skywalker").setPosition(2, 1).build();
		Satelite sateliteKenobi = new SateliteBuilder().setName("Kenobi").setPosition(1, 2).build();
		Satelite sateliteSato = new SateliteBuilder().setName("Sato").setPosition(1, 1).build();

		base.addSatelite(sateliteSky);
		base.addSatelite(sateliteKenobi);
		base.addSatelite(sateliteSato);

		String[] contet_sky = { "Hola", "", "es", "", "mensaje" };
		Mensaje msjSky = new MensajeBuilder().setName("Skywalker").setDistance(1).setMessage(contet_sky).build();
		String[] contet_kenobi = { "", "este", "es", "un", "mensaje" };
		Mensaje msjKenobi = new MensajeBuilder().setName("Kenobi").setDistance(1).setMessage(contet_kenobi).build();
		String[] contet_sato = { "", "este", "es", "un", "mensaje" };
		Mensaje msjSato = new MensajeBuilder().setName("Sato").setDistance(Math.sqrt(2)).setMessage(contet_sato)
				.build();

		System.out.println(msjKenobi.getName());

		List<Mensaje> mensajes_request = new ArrayList<Mensaje>();
		mensajes_request.add(msjKenobi);
		mensajes_request.add(msjSky);
		mensajes_request.add(msjSato);

		Response response = given().contentType("application/json").accept("application/json").body(mensajes_request)
				.when().post(RestAssured.baseURI + ":8080/api/topsecret").then().statusCode(200)
				.contentType("application/json").extract().response();

		String message = response.jsonPath().getString("message");
		assertEquals("Hola este es un mensaje", message);

	}

	@Test
	public void test_topsecret_split_get() throws URISyntaxException {
		this.cleanTest();

		String[] contet_sky = { "Hola", "", "es", "", "mensaje" };
		Mensaje msjSky = new MensajeBuilder().setDistance(1).setMessage(contet_sky).build();
		String[] contet_kenobi = { "", "este", "es", "un", "mensaje" };
		Mensaje msjKenobi = new MensajeBuilder().setDistance(1).setMessage(contet_kenobi).build();
		String[] contet_sato = { "", "este", "es", "un", "mensaje" };
		Mensaje msjSato = new MensajeBuilder().setDistance(Math.sqrt(2)).setMessage(contet_sato).build();

		given().contentType("application/json").accept("application/json").body(msjKenobi).when()
				.post(RestAssured.baseURI + ":8080/api/topsecret_split/Kenobi").then().statusCode(200);

		given().contentType("application/json").accept("application/json").body(msjSky).when()
				.post(RestAssured.baseURI + ":8080/api/topsecret_split/Skywalker").then().statusCode(200);

		given().contentType("application/json").accept("application/json").body(msjSato).when()
				.post(RestAssured.baseURI + ":8080/api/topsecret_split/Sato").then().statusCode(200);

		Response response = given().contentType("application/json").accept("application/json").when()
				.get(RestAssured.baseURI + ":8080/api/topsecret_split/").then().statusCode(200)
				.contentType("application/json").extract().response();

		String message = response.jsonPath().getString("message");
		assertEquals("Hola este es un mensaje", message);

	}

	@Test
	public void test_topsecret_split_post() throws URISyntaxException {
		this.cleanTest();

		String[] contet_kenobi = { "", "este", "es", "un", "mensaje" };
		Mensaje msjKenobi = new MensajeBuilder().setDistance(1).setMessage(contet_kenobi).build();

		Response response = given().contentType("application/json").accept("application/json").body(msjKenobi).when()
				.post(RestAssured.baseURI + ":8080/api/topsecret_split/Kenobi").then().statusCode(200).extract()
				.response();

		assertEquals(200, response.getStatusCode());

	}

	@Test
	public void test_update_position_put() throws URISyntaxException {
		this.cleanTest();

		Satelite sateliteSky_original = new SateliteBuilder().setName("Skywalker").setPosition(1, 2).build();
		BaseRebelde.getInstance().satelites.add(sateliteSky_original);
		Satelite sateliteSky_new = new SateliteBuilder().setName("Skywalker").setPosition(2, -92).build();

		given().contentType("application/json").accept("application/json").body(sateliteSky_new).when()
				.put(RestAssured.baseURI + ":8080/api/updatePosition").then().statusCode(200);

	}

	@Test
	public void test_get_satelite() throws URISyntaxException {
		this.cleanTest();
		BaseRebelde base = BaseRebelde.getInstance();

		Satelite sateliteSky_original = new SateliteBuilder().setName("Skywalker").setPosition(1, -200).build();
		base.satelites.add(sateliteSky_original);

		Response response = given().contentType("application/json").accept("application/json").when()
				.get(RestAssured.baseURI + ":8080/api/Skywalker").then().statusCode(200).extract().response();

		String name = response.jsonPath().get("name");
		assertEquals("Skywalker", name);

	}

	private void cleanTest() {
		BaseRebelde base = BaseRebelde.getInstance();

		base.satelites.clear();

	}

}
