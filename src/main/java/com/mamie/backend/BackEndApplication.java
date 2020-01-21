package com.mamie.backend;

import com.mamie.backend.model.Famille;
import com.mamie.backend.model.Membre;
import com.mamie.backend.model.Personne;
import com.mamie.backend.repository.FamilleRepository;
import com.mamie.backend.repository.PersonneRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableNeo4jRepositories
public class BackEndApplication {

	public static void main(String[] args) throws Exception {

		SpringApplication.run(BackEndApplication.class, args);
	}

	@Bean
	CommandLineRunner demo(FamilleRepository familleRepository, PersonneRepository personneRepository) {
		return args -> {

			familleRepository.deleteAll();

			Famille plaideau = new Famille("Plaideau",2);
			Famille bozon = new Famille("Bozon",1);
			Famille molinet = new Famille("Molinet",3);

			List<Famille> team = Arrays.asList(plaideau, bozon, molinet);

			System.out.println("Before linking up with Neo4j...");

			team.stream().forEach(famille -> System.out.println("\t" + famille.toString()));

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
			System.out.println("Lookup each person by name...");
			System.out.println("\t" + familleRepository.findAll());

			personneRepository.deleteAll();



			Personne plaideauG = new Personne("Plaideau","Guillaume","plaideaug83170@gmail.com", new Date("21/09/1996"), "Lyon", "France", "https://scontent-cdg2-1.xx.fbcdn.net/v/t1.0-9/p960x960/68726057_2649903395053759_4484571908636934144_o.jpg?_nc_cat=109&_nc_ohc=7wudLEBcpUEAX_-WYj0&_nc_ht=scontent-cdg2-1.xx&_nc_tp=1002&oh=171313c159ee5353f4427c79ec4ebd4c&oe=5EA19BAB","Dev","Ingé", "Celib", 23);
			Personne bozonG = new Personne("Bozon","Guillaume");
			Personne molinetB = new Personne("Molinet","Benjamin");

			List<Personne> teamP = Arrays.asList(plaideauG, bozonG, molinetB);

			System.out.println("Before linking up with Neo4j...");

			teamP.stream().forEach(personne -> System.out.println("\t" + personne.toString()));

			personneRepository.save(plaideauG);
			personneRepository.save(bozonG);
			personneRepository.save(molinetB);

			System.out.println("Lookup each person by name...");
			System.out.println("\t" + personneRepository.findAll());

			Membre plaideauGuillaume = new Membre();
			plaideauGuillaume.setFamille(plaideau);
			plaideauGuillaume.setPersonne(plaideauG);

		};
	}

}
