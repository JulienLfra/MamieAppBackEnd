package com.mamie.backend;

import com.mamie.backend.model.Famille;
import com.mamie.backend.repository.FamilleRepository;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableNeo4jRepositories
public class BackEndApplication {

	private final static Logger log = LoggerFactory.getLogger(BackEndApplication.class);

	public static void main(String[] args) throws Exception {

		SpringApplication.run(BackEndApplication.class, args);
	}

	@Bean
	public Driver neo4jDriver() {
		return GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic( "neo4j", "admin" ));
	}

	@Bean
	CommandLineRunner demo(FamilleRepository familleRepository) {
		return args -> {

			familleRepository.deleteAll();

			Famille plaideau = new Famille("Plaideau");
			Famille bozon = new Famille("Bozon");
			Famille molinet = new Famille("Molinet");

			List<Famille> team = Arrays.asList(plaideau, bozon, molinet);

			log.info("Before linking up with Neo4j...");

			team.stream().forEach(famille -> log.info("\t" + famille.toString()));

			familleRepository.save(plaideau);
			familleRepository.save(bozon);
			familleRepository.save(molinet);

//			greg = familleRepository.findByName(greg.getName());
//			greg.worksWith(roy);
//			greg.worksWith(craig);
//			FamilleRepository.save(greg);
//
//			roy = FamilleRepository.findByName(roy.getName());
//			roy.worksWith(craig);
//			// We already know that roy works with greg
//			personRepository.save(roy);

			// We already know craig works with roy and greg
//
			log.info("Lookup each person by name...");
			log.info("\t" + familleRepository.findAll());
		};
	}

}
