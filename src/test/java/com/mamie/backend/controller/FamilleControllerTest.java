package com.mamie.backend.controller;

import com.mamie.backend.model.Famille;
import com.mamie.backend.model.Personne;
import com.mamie.backend.model.SexeEnum;
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
public class FamilleControllerTest {

    @Autowired
    private FamilleRepository familleRepository;

    @Autowired
    private PersonneRepository personneRepository;

    private Famille plaideau;

    @Before
    public void init() {
        familleRepository.deleteAll();
    }

    private void init_famille() {
        familleRepository.deleteAll();

        plaideau = new Famille("Plaideau", 4);
        Famille bozon = new Famille("Bozon", 2);
        familleRepository.save(plaideau);
        familleRepository.save(bozon);

        personneRepository.deleteAll();

        List<Famille> GPlaideau = new ArrayList<>();
        GPlaideau.add(plaideau);
        GPlaideau.add(bozon);

        Personne plaideauA = new Personne("Plaideau", "Alain", "ap@gmail.com", "21/09/1996", SexeEnum.male, "Lyon", "France", "http://benjamin.molinet.free.fr/projetMamie/medias/ap@gmail.com.png", "Dev", "Ingé", "Celib", 23, plaideau);
        Personne plaideauM = new Personne("Plaideau", "Murielle", "mp@gmail.com", "21/09/1996", SexeEnum.female, "Lyon", "France", "http://benjamin.molinet.free.fr/projetMamie/medias/mp@gmail.com.png", "Dev", "Ingé", "Celib", 23, plaideau);
        List<Personne> parentsPlaideau = new ArrayList<>();
        parentsPlaideau.add(plaideauA);
        parentsPlaideau.add(plaideauM);
        Personne plaideauE = new Personne("Plaideau", "Eleonore", "ep@gmail.com", "21/09/1996", SexeEnum.female, "Lyon", "France", "http://benjamin.molinet.free.fr/projetMamie/medias/ep@gmail.com.png", "Dev", "Ingé", "Celib", 23, plaideau);
        List<Personne> sibling = new ArrayList<>();
        sibling.add(plaideauE);

        Personne bozonG = new Personne("Bozon", "Guillaume", "gbozon@gmail.com", "21/09/1996", SexeEnum.male, "Lyon", "France", "http://benjamin.molinet.free.fr/projetMamie/medias/gbozon@gmail.com.png", "Dev", "Ingé", "Celib", 23, bozon);
        Personne plaideauG = new Personne("Plaideau", "Guillaume", "plaideaug83170@gmail.com", "21/09/1996", SexeEnum.male, "Lyon", "France", "http://benjamin.molinet.free.fr/projetMamie/medias/plaideaug83170@gmail.com.png", "Dev", "Ingé", "Celib", 23, GPlaideau, sibling, parentsPlaideau, bozonG);

        personneRepository.save(plaideauG);
        personneRepository.save(bozonG);
    }


    @Test
    public void whenGetFamilles_returnFamilles() {
        init_famille();

        FamilleController familleController = new FamilleController(familleRepository);
        List<Famille> result = familleController.getFamilles();

        assertTrue(result.contains(plaideau));
    }

    @Test
    public void whenGetFamilleNom_returnFamille() {
        init_famille();

        FamilleController familleController = new FamilleController(familleRepository);
        List<Famille> result = familleController.getFamilleNom("Plaideau");

        assertEquals(result.get(0), plaideau);
    }

    @Test
    public void whenGetFamillesMail_returnFamille() {
        init_famille();

        FamilleController familleController = new FamilleController(familleRepository);
        List<Famille> result = familleController.getFamillesMail("plaideaug83170@gmail.com");

        assertTrue(result.contains(plaideau));
    }

    @Test
    public void whenAddFammille_returnFamille() {
        Famille test = new Famille("test", 1);

        FamilleController familleController = new FamilleController(familleRepository);
        familleController.addFamille(test);

        List<Famille> result = familleController.getFamilleNom("test");

        assertEquals(test, result.get(0));
    }

    @Test
    public void whenAddPostFammille_returnFamille() {
        Famille test = new Famille("test", 1);

        FamilleController familleController = new FamilleController(familleRepository);
        familleController.addPostFamille(test);

        List<Famille> result = familleController.getFamilleNom("test");

        assertEquals(test, result.get(0));
    }

    @Test
    public void whenDeleteFammille_returnNothing() {
        Famille test = new Famille("test", 1);

        FamilleController familleController = new FamilleController(familleRepository);
        familleController.addFamille(test);

        List<Famille> result = familleController.getFamilleNom("test");

        assertEquals(test, result.get(0));

        familleController.deleteFamille(test);

        assertTrue(true);
    }

    @Test(expected = Exception.class)
    public void whenDeleteFamilleUnknown_returnException() throws Exception {
        familleRepository.deleteAll();
        Famille test = new Famille("test", 1);

        FamilleController familleController = new FamilleController(familleRepository);
        familleController.deleteFamille(test);
    }
}
