package com.mamie.backend.controller;

import com.mamie.backend.model.Evenement;
import com.mamie.backend.model.Famille;
import com.mamie.backend.model.Personne;
import com.mamie.backend.model.SexeEnum;
import com.mamie.backend.repository.EvenementRepository;
import com.mamie.backend.repository.FamilleRepository;
import com.mamie.backend.repository.PersonneRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataNeo4jTest
public class EvenementControllerTest {

    @Autowired
    private EvenementRepository evenementRepository;

    @Autowired
    private FamilleRepository familleRepository;

    @Autowired
    private PersonneRepository personneRepository;

    Evenement evenement1;

    @Before
    public void init() {
        evenementRepository.deleteAll();
    }

    public void init_evemenent() {

        Famille plaideau = new Famille("Plaideau", 1);
        familleRepository.save(plaideau);

        Personne guillaumeP = new Personne("Plaideau", "Guillaume", "plaideaug83170@gmail.com", "21/09/1996", SexeEnum.male, "Lyon", "France", "pasdephoto", "Dev", "pasencore", "Content", 23, plaideau);
        personneRepository.save(guillaumeP);

        evenement1 = new Evenement("Mariage", "21/09/1996", "Chez moi", "/Jenaipas", plaideau);
        Evenement evenement2 = new Evenement("Lune de miel", "22/09/1996", "Bahamas", "/Jenaipas", plaideau);
        Evenement evenement3 = new Evenement("Entretien", "29/10/1996", "Chez mon boss", "/Jenaipas", plaideau);

        List<Evenement> evenements = new ArrayList<>();
        evenements.add(evenement1);
        evenements.add(evenement2);
        evenements.add(evenement3);

        evenementRepository.saveAll(evenements);
    }

    @Test
    public void whenGetEvenements_returnEvenements() {
        init_evemenent();

        EvenementController evenementController = new EvenementController(evenementRepository);

        List<Evenement> result = evenementController.getEvenements();

        assertTrue(result.contains(evenement1));
    }

    @Test
    public void whenGetEvenementsByUserMail_returnEvenements() {
        init_evemenent();

        EvenementController evenementController = new EvenementController(evenementRepository);

        List<Evenement> result = evenementController.getEvenementsByUserMail("plaideaug83170@gmail.com");

        assertTrue(result.contains(evenement1));
    }

    @Test
    public void whenAddEvenement_returnEvenement() {
        Evenement evenement2 = new Evenement("Lune de miel", "22/09/1996", "Bahamas", "/Jenaipas", null);

        EvenementController evenementController = new EvenementController(evenementRepository);
        evenementController.addEvenement(evenement2);

        List<Evenement> result = evenementController.getEvenements();

        assertEquals(evenement2.getNom(), result.get(0).getNom());
        assertEquals(evenement2.getDate(), result.get(0).getDate());
        assertEquals(evenement2.getLieu(), result.get(0).getLieu());
    }

    @Test
    public void whenAddPostEvenement_returnEvenement() {
        Evenement evenement2 = new Evenement("Lune de miel", "22/09/1996", "Bahamas", "/Jenaipas", null);

        EvenementController evenementController = new EvenementController(evenementRepository);
        evenementController.addPostEvenement(evenement2);

        List<Evenement> result = evenementController.getEvenements();

        assertEquals(evenement2.getNom(), result.get(0).getNom());
        assertEquals(evenement2.getDate(), result.get(0).getDate());
        assertEquals(evenement2.getLieu(), result.get(0).getLieu());
    }

    @Test
    public void whenDeleteEvenement_returnNothing() {
        Evenement evenement2 = new Evenement("Lune de miel", "22/09/1996", "Bahamas", "/Jenaipas", null);

        EvenementController evenementController = new EvenementController(evenementRepository);
        evenementController.addEvenement(evenement2);

        List<Evenement> result = evenementController.getEvenements();

        assertEquals(evenement2.getNom(), result.get(0).getNom());
        assertEquals(evenement2.getDate(), result.get(0).getDate());
        assertEquals(evenement2.getLieu(), result.get(0).getLieu());

        evenementController.deleteEvenement(evenement2);

        assertTrue(true);
    }
}
