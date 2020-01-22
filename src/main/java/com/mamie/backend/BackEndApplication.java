package com.mamie.backend;

import com.mamie.backend.model.Evenement;
import com.mamie.backend.model.Famille;
import com.mamie.backend.model.Personne;
import com.mamie.backend.repository.EvenementRepository;
import com.mamie.backend.repository.FamilleRepository;
import com.mamie.backend.repository.PersonneRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import java.util.ArrayList;
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
	CommandLineRunner demo(FamilleRepository familleRepository, PersonneRepository personneRepository, EvenementRepository evenementRepository) {
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

			System.out.println("Lookup each person by name...");
			System.out.println("\t" + familleRepository.findAll());

			personneRepository.deleteAll();


			List<Famille> GPlaideau = new ArrayList<>();
			GPlaideau.add(plaideau);
			GPlaideau.add(bozon);

			Personne plaideauA = new Personne("Plaideau","Alain","ap@gmail.com", new Date("21/09/1996"), "Lyon", "France", "https://scontent-cdg2-1.xx.fbcdn.net/v/t1.0-9/p960x960/68726057_2649903395053759_4484571908636934144_o.jpg?_nc_cat=109&_nc_ohc=7wudLEBcpUEAX_-WYj0&_nc_ht=scontent-cdg2-1.xx&_nc_tp=1002&oh=171313c159ee5353f4427c79ec4ebd4c&oe=5EA19BAB","Dev","Ingé", "Celib", 23, plaideau);
			Personne plaideauM = new Personne("Plaideau","Murielle","mp@gmail.com", new Date("21/09/1996"), "Lyon", "France", "https://scontent-cdg2-1.xx.fbcdn.net/v/t1.0-9/p960x960/68726057_2649903395053759_4484571908636934144_o.jpg?_nc_cat=109&_nc_ohc=7wudLEBcpUEAX_-WYj0&_nc_ht=scontent-cdg2-1.xx&_nc_tp=1002&oh=171313c159ee5353f4427c79ec4ebd4c&oe=5EA19BAB","Dev","Ingé", "Celib", 23, plaideau);
			List<Personne> parentsPlaideau = new ArrayList<>();
			parentsPlaideau.add(plaideauA);
			parentsPlaideau.add(plaideauM);
			Personne plaideauE = new Personne("Plaideau","Eleonore","ep@gmail.com", new Date("21/09/1996"), "Lyon", "France", "https://scontent-cdg2-1.xx.fbcdn.net/v/t1.0-9/p960x960/68726057_2649903395053759_4484571908636934144_o.jpg?_nc_cat=109&_nc_ohc=7wudLEBcpUEAX_-WYj0&_nc_ht=scontent-cdg2-1.xx&_nc_tp=1002&oh=171313c159ee5353f4427c79ec4ebd4c&oe=5EA19BAB","Dev","Ingé", "Celib", 23, plaideau);
			List<Personne> sibling = new ArrayList<>();
			sibling.add(plaideauE);

			Personne bozonG = new Personne("Bozon","Guillaume","wesh@gmail.com", new Date("21/09/1996"), "Lyon", "France", "https://scontent-cdg2-1.xx.fbcdn.net/v/t1.0-9/p960x960/68726057_2649903395053759_4484571908636934144_o.jpg?_nc_cat=109&_nc_ohc=7wudLEBcpUEAX_-WYj0&_nc_ht=scontent-cdg2-1.xx&_nc_tp=1002&oh=171313c159ee5353f4427c79ec4ebd4c&oe=5EA19BAB","Dev","Ingé", "Celib", 23, bozon);
			Personne plaideauG = new Personne("Plaideau","Guillaume","plaideaug83170@gmail.com", new Date("21/09/1996"), "Lyon", "France", "https://scontent-cdg2-1.xx.fbcdn.net/v/t1.0-9/p960x960/68726057_2649903395053759_4484571908636934144_o.jpg?_nc_cat=109&_nc_ohc=7wudLEBcpUEAX_-WYj0&_nc_ht=scontent-cdg2-1.xx&_nc_tp=1002&oh=171313c159ee5353f4427c79ec4ebd4c&oe=5EA19BAB","Dev","Ingé", "Celib", 23, GPlaideau,sibling,parentsPlaideau,bozonG);
			Personne molinetB = new Personne("Molinet","Benjamin","prout@gmail.com", new Date("21/09/1996"), "Lyon", "France", "https://scontent-cdg2-1.xx.fbcdn.net/v/t1.0-9/p960x960/68726057_2649903395053759_4484571908636934144_o.jpg?_nc_cat=109&_nc_ohc=7wudLEBcpUEAX_-WYj0&_nc_ht=scontent-cdg2-1.xx&_nc_tp=1002&oh=171313c159ee5353f4427c79ec4ebd4c&oe=5EA19BAB","Dev","Ingé", "Celib", 23, molinet);

			List<Personne> teamP = Arrays.asList(plaideauG, bozonG, molinetB);

			System.out.println("Before linking up with Neo4j...");

			teamP.stream().forEach(personne -> System.out.println("\t" + personne.toString()));

			personneRepository.save(plaideauG);
			personneRepository.save(bozonG);
			personneRepository.save(molinetB);

			System.out.println("Lookup each person by name...");
			System.out.println("\t" + personneRepository.findAll());

			evenementRepository.deleteAll();

			Evenement event = new Evenement("Mariage", new Date("21/09/2027"), "Chez Caro", "https://www.photo-up.fr/public/Medias/3-seances/mariage/vignettes/reportage-mariage.jpg", plaideau);
			Evenement event2 = new Evenement("Suicide", new Date("21/09/2027"), "Chez Caro", "https://www.photo-up.fr/public/Medias/3-seances/mariage/vignettes/reportage-mariage.jpg", bozon);
			Evenement event3 = new Evenement("Rattrapage", new Date("21/09/2027"), "Chez Caro", "https://www.photo-up.fr/public/Medias/3-seances/mariage/vignettes/reportage-mariage.jpg", molinet);

			evenementRepository.save(event);
			evenementRepository.save(event2);
			evenementRepository.save(event3);

			System.out.println(evenementRepository.findAll());
		};
	}

}
