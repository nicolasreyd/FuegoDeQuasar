package com.app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {


	public static void main(String[] args) {
		//Data prep
		List<Satelite> satelites = new ArrayList<Satelite>();

		satelites.add(new Satelite("Kenobi", new Position(-500, -200)));
		satelites.add(new Satelite("Skywalker", new Position(100, -100)));
		satelites.add(new Satelite("Sato", new Position(500, 100)));
		
		BaseRebelde.getInstance().setSatelites(satelites);
		SpringApplication.run(DemoApplication.class, args);
		

	}
	

}
