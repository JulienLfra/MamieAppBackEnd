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
import java.util.List;

@SpringBootApplication
@EnableNeo4jRepositories
public class BackEndApplication {

    public static void main(String[] args) throws Exception {

        SpringApplication.run(BackEndApplication.class, args);
    }

//    @Bean
//    CommandLineRunner demo(FamilleRepository familleRepository, PersonneRepository personneRepository, EvenementRepository evenementRepository) {
//        return args -> {
//
//            familleRepository.deleteAll();
//
//            Famille bozon = new Famille("Bozon",2);
//            Famille molinet = new Famille("Molinet",1);
//
//            Famille plaideau = new Famille("Plaideau", 5);
//
//            List<Famille> team = Arrays.asList(plaideau, bozon, molinet);
//
//            System.out.println("Before linking up with Neo4j...");
//
//            team.stream().forEach(famille -> System.out.println("\t" + famille.toString()));
//
//            familleRepository.save(plaideau);
//            familleRepository.save(bozon);
//            familleRepository.save(molinet);
//
//            System.out.println("Lookup each person by name...");
//            System.out.println("\t" + familleRepository.findAll());
//
//            personneRepository.deleteAll();
//
//
//
//
//            Personne bozonG = new Personne("bozon","guillaume","gbozon@gmail.com", "21/09/1996", SexeEnum.male,
//                    "Lyon", "France", "http://benjamin.molinet.free.fr/projetMamie/medias/gbozon@gmail.com.png","Dev",
//                    "Ingé", "Celib", 23, bozon, 15.3488, 48.8534);
//
//
//
//            Personne molinetB = new Personne("molinet","benjamin","bmolinet@gmail.com", "21/09/1996", SexeEnum.male,
//                    "Lyon", "France", "https://scontent-cdt1-1.xx.fbcdn.net/v/t1.0-9/p720x720/64219237_2511438135566953_6095685978989527040_o.jpg?_nc_cat=106&_nc_ohc=BK4j5MPhWsoAX_dGLal&_nc_ht=scontent-cdt1-1.xx&_nc_tp=1002&oh=951f388a9b6c21fddddf59b578b294c9&oe=5ECFFC2F","Dev","Ingé", "Celib", 23, molinet, 2.3488, 48.8534);
//
//            // MURIELLE
//
//            List<Personne> siblingsMurielle = new ArrayList<>();
//            List<Personne> parentsMurielle = new ArrayList<>();
//
//            List<Famille> listPlaideau = new ArrayList<>();
//            listPlaideau.add(plaideau);
//
//            Personne muriellePlaideau = new Personne("Plaideau", "Murielle", "mp@mail.fr", "23/05/1983",
//                    SexeEnum.female, "Lyon", "France", "https://www.lequimag.be/sites/default/files/styles/article_big/public/actualite/przewalskis-horse-1660918_960_720.jpg?itok=TAiKzjGg",
//                    "Fleuriste", "BAC", "Mariée", 37, listPlaideau, siblingsMurielle, parentsMurielle,
//                    2.3488, 48.8534);
//
//
//            // ALAIN
//
//            List<Personne> siblingsAlain = new ArrayList<>();
//            List<Personne> parentsAlain = new ArrayList<>();
//
//            Personne alainPlaideau = new Personne("Plaideau", "Alain", "ap@mail.fr", "23/05/1983",
//                    SexeEnum.male, "Lyon", "France", "https://www.creativelune.com/wp-content/uploads/2017/04/galerie_photo_cheval_soleil_1208x800_72_creative_lune-1.jpg",
//                    "Fleuriste", "BAC", "Marié", 37, listPlaideau, siblingsAlain, parentsAlain, muriellePlaideau,
//                    2.3488, 48.8534);
//
//
//            // GUILLAUME
//
//            List<Personne> siblingsGuillaume = new ArrayList<>();
//            List<Personne> parentsGuillaume = new ArrayList<>();
//            parentsGuillaume.add(muriellePlaideau);
//            parentsGuillaume.add(alainPlaideau);
//
//            Personne guillaumePlaideau = new Personne("Plaideau", "Guillaume", "plaideaug83170@gmail.com", "23/05/1983",
//                    SexeEnum.male, "Lyon", "France", "https://thumbs.dreamstime.com/b/galop-de-cheval-sur-une-herbe-verte-40391667.jpg",
//                    "Fleuriste", "BAC", "Marié", 37, listPlaideau, siblingsGuillaume, parentsGuillaume,
//                    2.3488, 48.8534);
//
//
//            // ELEONORE
//
//            List<Personne> siblingsEleonore = new ArrayList<>();
//            siblingsEleonore.add(guillaumePlaideau);
//            List<Personne> parentsEleonore = new ArrayList<>();
//            parentsEleonore.add(muriellePlaideau);
//            parentsEleonore.add(alainPlaideau);
//
//            Personne eleonorePlaideau = new Personne("Plaideau", "Eleonore", "leo@mail.fr", "23/05/1983",
//                    SexeEnum.female, "Lyon", "France", "https://www.clubcheval.net/wp-content/uploads/2019/03/1b6d474d60f95a815f7f792428907658-1.jpe",
//                    "Fleuriste", "BAC", "Célibataire", 37, listPlaideau, siblingsEleonore, parentsEleonore,
//                    2.3488, 48.8534);
//
//
//            // STELLA
//
//            List<Personne> siblingsStella = new ArrayList<>();
//            List<Personne> parentsStella = new ArrayList<>();
//
//            Personne stellaDesSources = new Personne("des Sources", "Stella", "stella@mail.fr", "23/05/1983",
//                    SexeEnum.female, "Lyon", "France", "https://fotomelia.com/wp-content/uploads/edd/2015/03/cheval-blanc-photo-en-noir-et-blanc-1560x1040.jpg",
//                    "Fleuriste", "BAC", "Mariée", 37, listPlaideau, siblingsStella, parentsStella, guillaumePlaideau,
//                    2.3488, 48.8534);
//
//
//
//
//
//            List<Personne> teamP = Arrays.asList( bozonG, molinetB, muriellePlaideau, alainPlaideau, guillaumePlaideau,eleonorePlaideau,stellaDesSources);
//
//            System.out.println("Before linking up with Neo4j...");
//
//            teamP.stream().forEach(personne -> System.out.println("\t" + personne.toString()));
//
//            personneRepository.save(bozonG);
//            personneRepository.save(molinetB);
//            personneRepository.save(muriellePlaideau);
//            personneRepository.save(guillaumePlaideau);
//            personneRepository.save(eleonorePlaideau);
//            personneRepository.save(alainPlaideau);
//            personneRepository.save(stellaDesSources);
//
//
//            System.out.println("Lookup each person by name...");
//            System.out.println("\t" + personneRepository.findAll());
//
//            evenementRepository.deleteAll();
//
//            Evenement event2 = new Evenement("Vacances", "21/09/2027", "Ski Week", "https://www.djuringa-juniors.fr/media/cache/Sejours_Colonie/Hiver/2018/p/e/permiere-colonie-de-vacances-ski_375x281.jpg", bozon);
//            Evenement event3 = new Evenement("Rattrapage", "21/09/2027", "A CPE", "http://www.retraite-et-travail.com/wp-content/uploads/2019/02/403890.jpg", molinet);
//
//            Evenement event4 = new Evenement("Confinement", "21/09/2027", "Chez soi",
//                    "https://pbs.twimg.com/profile_images/1239275350292643845/GveP7naw_400x400.jpg", plaideau);
//
//
//            evenementRepository.save(event2);
//            evenementRepository.save(event3);
//            evenementRepository.save(event4);
//
//            System.out.println(evenementRepository.findAll());
//        };
//    }
}
