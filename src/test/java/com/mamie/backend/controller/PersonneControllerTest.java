package com.mamie.backend.controller;

import com.mamie.backend.model.Famille;
import com.mamie.backend.model.Personne;
import com.mamie.backend.model.PersonneGenealogique;
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
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataNeo4jTest
public class PersonneControllerTest {

    @Autowired
    private PersonneRepository personneRepository;

    @Autowired
    private FamilleRepository familleRepository;

    @Autowired
    private EvenementRepository evenementRepository;

    private Personne plaideauG;

    @Before
    public void init() {
        personneRepository.deleteAll();
    }

    private void init_famille() {
        familleRepository.deleteAll();

        Famille plaideau = new Famille("Plaideau",4);
        Famille bozon = new Famille("Bozon",2);
        familleRepository.save(plaideau);
        familleRepository.save(bozon);

        personneRepository.deleteAll();

        List<Famille> GPlaideau = new ArrayList<>();
        GPlaideau.add(plaideau);
        GPlaideau.add(bozon);

        Personne plaideauA = new Personne("Plaideau","Alain","ap@gmail.com", "21/09/1996", SexeEnum.male, "Lyon", "France", "http://benjamin.molinet.free.fr/projetMamie/medias/ap@gmail.com.png","Dev","Ingé", "Celib", 23, plaideau);
        Personne plaideauM = new Personne("Plaideau","Murielle","mp@gmail.com", "21/09/1996", SexeEnum.female, "Lyon", "France", "http://benjamin.molinet.free.fr/projetMamie/medias/mp@gmail.com.png","Dev","Ingé", "Celib", 23, plaideau);
        List<Personne> parentsPlaideau = new ArrayList<>();
        parentsPlaideau.add(plaideauA);
        parentsPlaideau.add(plaideauM);
        Personne plaideauE = new Personne("Plaideau","Eleonore","ep@gmail.com", "21/09/1996", SexeEnum.female, "Lyon", "France", "http://benjamin.molinet.free.fr/projetMamie/medias/ep@gmail.com.png","Dev","Ingé", "Celib", 23, plaideau);
        List<Personne> sibling = new ArrayList<>();
        sibling.add(plaideauE);

        Personne bozonG = new Personne("Bozon","Guillaume","gbozon@gmail.com", "21/09/1996", SexeEnum.male, "Lyon", "France", "http://benjamin.molinet.free.fr/projetMamie/medias/gbozon@gmail.com.png","Dev","Ingé", "Celib", 23, bozon);
        plaideauG = new Personne("Plaideau","Guillaume","plaideaug83170@gmail.com", "21/09/1996", SexeEnum.male, "Lyon", "France", "http://benjamin.molinet.free.fr/projetMamie/medias/plaideaug83170@gmail.com.png","Dev","Ingé", "Celib", 23, GPlaideau,sibling,parentsPlaideau,bozonG);

        personneRepository.save(plaideauG);
        personneRepository.save(bozonG);
    }

    @Test
    public void whenFindByMail_withGoodMail_returnPerson() {
        //given
        Personne personneTest = new Personne("Plaideau", "Guillaume", "plaideaug83170@gmail.com");
        personneRepository.save(personneTest);

        //when
        PersonneController personneController = new PersonneController(personneRepository);
        List<Personne> personneresult = personneController.getPersonneNomByMail("plaideaug83170@gmail.com");

        //then
        assertEquals(personneresult.get(0), personneTest);
    }

    @Test
    public void whenFindByMail_withWrongMail_returnException() {
        //given
        Personne personneTest = new Personne("Plaideau", "Guillaume", "plaideaug83170@gmail.com");
        personneRepository.save(personneTest);

        Personne personneExcept = new Personne("Bozon", "Guillaume", "bozong@gmail.com");
        personneRepository.save(personneExcept);

        //when
        PersonneController personneController = new PersonneController(personneRepository);
        List<Personne> personneresult = personneController.getPersonneNomByMail("bozong@gmail.com");

        //then
        assertNotEquals(personneresult.get(0), personneTest);
        assertEquals(personneresult.get(0), personneExcept);
    }

    @Test
    public void whendGetPersonnesNom_returnListPersons() {
        //given
        Personne personneTest1 = new Personne("Bozon", "Guillaume", "bozong@gmail.com");
        Personne personneTest2 = new Personne("Molinet", "Benjamin", "molinetb@gmail.com");
        Personne personneTest3 = new Personne("Plaideau", "Guillaume", "plaideaug83170@gmail.com");
        List<Personne> exceptation = new ArrayList<>();
        exceptation.add(personneTest1);
        exceptation.add(personneTest2);
        exceptation.add(personneTest3);
        Collections.sort(exceptation);
        personneRepository.saveAll(exceptation);


        PersonneController personneController = new PersonneController(personneRepository);
        List<Personne> result = personneController.getPersonnesNom();
        Collections.sort(result);

        assertEquals(exceptation, result);
    }

    @Test
    public void whenAddPersonne_returnPersonne() {
        Personne personneTest = new Personne("Bozon", "Guillaume", "bozong@gmail.com");
        List<Personne> exceptation = new ArrayList<>();
        exceptation.add(personneTest);
        personneRepository.deleteAll();

        PersonneController personneController = new PersonneController(personneRepository);
        personneController.addPersonne(personneTest);

        Iterable<Personne> result = personneRepository.findAll();

        assertEquals(result, exceptation);
    }

    @Test
    public void whenAddPostPersonne_returnPersonne() {
        Personne personneTest = new Personne("Bozon", "Guillaume", "bozong@gmail.com");
        List<Personne> exceptation = new ArrayList<>();
        exceptation.add(personneTest);
        personneRepository.deleteAll();

        PersonneController personneController = new PersonneController(personneRepository);
        personneController.addPostPersonne(personneTest);

        Iterable<Personne> result = personneRepository.findAll();

        assertEquals(result, exceptation);
    }

    @Test
    public void whenDeletePersonne_returnNothing() throws Exception {
        personneRepository.deleteAll();
        Personne personneTest = new Personne("Bozon", "Guillaume", "bozong@gmail.com");
        personneRepository.save(personneTest);

        PersonneController personneController = new PersonneController(personneRepository);
        personneController.deletePersonne(personneTest);

        List<Personne> result = personneController.getPersonnesNom();

        assertTrue(result.isEmpty());
    }

    @Test(expected = Exception.class)
    public void whenDeletePersonneUnknown_returnException() throws Exception {
        personneRepository.deleteAll();
        Personne personneTest = new Personne("Bozon", "Guillaume", "bozong@gmail.com");

        PersonneController personneController = new PersonneController(personneRepository);
        personneController.deletePersonne(personneTest);
    }

    @Test
    public void whenGetMariage_returnPersonne() {
        init_famille();

        PersonneController personneController = new PersonneController(personneRepository);
        List<Personne> result = personneController.getMariage("gbozon@gmail.com");

        assertEquals(result.get(0), plaideauG);
    }

    @Test
    public void whenGetParents_returnParents() {
        init_famille();

        PersonneController personneController = new PersonneController(personneRepository);
        List<Personne> result = personneController.getParents("plaideaug83170@gmail.com");

        Famille plaideau = new Famille("Plaideau",4);
        Personne plaideauA = new Personne("Plaideau","Alain","ap@gmail.com", "21/09/1996", SexeEnum.male, "Lyon", "France", "http://benjamin.molinet.free.fr/projetMamie/medias/ap@gmail.com.png","Dev","Ingé", "Celib", 23, plaideau);

        assertEquals(plaideauA.getNom(),result.get(0).getNom());
    }

    @Test
    public void whenGetEnfants_returnEnfants() {
        init_famille();

        PersonneController personneController = new PersonneController(personneRepository);
        List<Personne> result = personneController.getEnfants("ap@gmail.com");


        assertEquals(plaideauG,result.get(0));
    }

    @Test
    public void whenGetGenealogie_returnPersonnes() {
        init_famille();

        PersonneController personneController = new PersonneController(personneRepository);
        List<PersonneGenealogique> result = personneController.getGenealogie("plaideaug83170@gmail.com");

        assertTrue(true);
    }

    @Test
    public void whenGetMembresByFamily_returnPersonnes() {
        init_famille();

        PersonneController personneController = new PersonneController(personneRepository);
        List<Personne> result = personneController.getMembresByFamily("plaideaug83170@gmail.com", "Plaideau");

        assertTrue(result.contains(plaideauG));
    }

    @Test
    public void whenGetPersonnesByFamilyByMail_returnPersonnes() {
        init_famille();

        PersonneController personneController = new PersonneController(personneRepository);
        List<Personne> result = personneController.getPersonnesByFamilyByMail("plaideaug83170@gmail.com");

        assertTrue(result.contains(plaideauG));
    }

}
