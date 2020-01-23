package com.mamie.backend;

import com.mamie.backend.model.Evenement;
import com.mamie.backend.model.Famille;
import com.mamie.backend.model.Personne;
import com.mamie.backend.model.SexeEnum;
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
	
//	@Bean
//	CommandLineRunner demo(FamilleRepository familleRepository, PersonneRepository personneRepository, EvenementRepository evenementRepository) {
//		return args -> {
//
//			familleRepository.deleteAll();
//
//			Famille plaideau = new Famille("Plaideau",4);
//			Famille bozon = new Famille("Bozon",2);
//			Famille molinet = new Famille("Molinet",1);
//
//			List<Famille> team = Arrays.asList(plaideau, bozon, molinet);
//
//			System.out.println("Before linking up with Neo4j...");
//
//			team.stream().forEach(famille -> System.out.println("\t" + famille.toString()));
//
//			familleRepository.save(plaideau);
//			familleRepository.save(bozon);
//			familleRepository.save(molinet);
//
//			System.out.println("Lookup each person by name...");
//			System.out.println("\t" + familleRepository.findAll());
//
//			personneRepository.deleteAll();
//
//
//			List<Famille> GPlaideau = new ArrayList<>();
//			GPlaideau.add(plaideau);
//			GPlaideau.add(bozon);
//
//			Personne plaideauA = new Personne("Plaideau","Alain","ap@gmail.com", "21/09/1996", SexeEnum.male, "Lyon", "France", "http://benjamin.molinet.free.fr/projetMamie/medias/ap@gmail.com.png","Dev","Ingé", "Celib", 23, plaideau);
//			Personne plaideauM = new Personne("Plaideau","Murielle","mp@gmail.com", "21/09/1996", SexeEnum.female, "Lyon", "France", "http://benjamin.molinet.free.fr/projetMamie/medias/mp@gmail.com.png","Dev","Ingé", "Celib", 23, plaideau);
//			List<Personne> parentsPlaideau = new ArrayList<>();
//			parentsPlaideau.add(plaideauA);
//			parentsPlaideau.add(plaideauM);
//			Personne plaideauE = new Personne("Plaideau","Eleonore","ep@gmail.com", "21/09/1996", SexeEnum.female, "Lyon", "France", "http://benjamin.molinet.free.fr/projetMamie/medias/ep@gmail.com.png","Dev","Ingé", "Celib", 23, plaideau);
//			List<Personne> sibling = new ArrayList<>();
//			sibling.add(plaideauE);
//
//			Personne bozonG = new Personne("Bozon","Guillaume","gbozon@gmail.com", "21/09/1996", SexeEnum.male, "Lyon", "France", "http://benjamin.molinet.free.fr/projetMamie/medias/gbozon@gmail.com.png","Dev","Ingé", "Celib", 23, bozon);
//			Personne plaideauG = new Personne("Plaideau","Guillaume","plaideaug83170@gmail.com", "21/09/1996", SexeEnum.male, "Lyon", "France", "http://benjamin.molinet.free.fr/projetMamie/medias/plaideaug83170@gmail.com.png","Dev","Ingé", "Celib", 23, GPlaideau,sibling,parentsPlaideau,bozonG);
//
//			Personne molinetB = new Personne("Molinet","Benjamin","bmolinet@gmail.com", "21/09/1996", SexeEnum.male, "Lyon", "France", "https://scontent-cdt1-1.xx.fbcdn.net/v/t1.0-9/p720x720/64219237_2511438135566953_6095685978989527040_o.jpg?_nc_cat=106&_nc_ohc=BK4j5MPhWsoAX_dGLal&_nc_ht=scontent-cdt1-1.xx&_nc_tp=1002&oh=951f388a9b6c21fddddf59b578b294c9&oe=5ECFFC2F","Dev","Ingé", "Celib", 23, molinet);
//
//			List<Personne> teamP = Arrays.asList(plaideauG, bozonG, molinetB);
//
//			System.out.println("Before linking up with Neo4j...");
//
//			teamP.stream().forEach(personne -> System.out.println("\t" + personne.toString()));
//
//			personneRepository.save(plaideauG);
//			personneRepository.save(bozonG);
//			personneRepository.save(molinetB);
//
//			System.out.println("Lookup each person by name...");
//			System.out.println("\t" + personneRepository.findAll());
//
//			evenementRepository.deleteAll();
//
//			Evenement event = new Evenement("Mariage", "21/09/2027", "Chez Caro", "https://www.photo-up.fr/public/Medias/3-seances/mariage/vignettes/reportage-mariage.jpg", plaideau);
//			Evenement event2 = new Evenement("Vacances", "21/09/2027", "Ski Week", "https://www.djuringa-juniors.fr/media/cache/Sejours_Colonie/Hiver/2018/p/e/permiere-colonie-de-vacances-ski_375x281.jpg", bozon);
//			Evenement event3 = new Evenement("Rattrapage", "21/09/2027", "A CPE", "http://www.retraite-et-travail.com/wp-content/uploads/2019/02/403890.jpg", molinet);
//
//			evenementRepository.save(event);
//			evenementRepository.save(event2);
//			evenementRepository.save(event3);
//
//			System.out.println(evenementRepository.findAll());
//		};
//	}


}
