package com.mamie.backend.controller;

import com.mamie.backend.model.Personne;
import com.mamie.backend.repository.PersonneRepository;
import jdk.nashorn.internal.parser.JSONParser;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.PostLoad;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.Format;
import java.util.ArrayList;
import java.util.List;

import org.neo4j.driver.AccessMode;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.neo4j.driver.SessionConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@CrossOrigin(origins = "*")
@RestController
public class PersonneController {

    private final PersonneRepository personneRepository;

    private final Driver driver;

    public PersonneController(PersonneRepository personneRepository, Driver driver) {
        this.personneRepository = personneRepository;
        this.driver = driver;
    }

    @GetMapping(path = "/mariage", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getMariage(@RequestParam String nom, @RequestParam String prenom) {

        try(Session session = driver.session()) {
            return session.run(String.format("MATCH (user1:Personne), (user2:Personne) WHERE user1.nom='%s' AND user1.prenom='%s' AND (user1)-[:Marier]-(user2) RETURN user2",nom, prenom))
                    .list(r -> r.toString());
        }

    }


    @GetMapping(path = "/personnes", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Personne> getPersonnesNom() {

        Iterable<Personne> p = personneRepository.findAll();
        List<Personne> persons = new ArrayList<>();
        for (Personne person : p) {
            persons.add(person);
        }
        return persons;
    }


    //Utile?
    @GetMapping(path = "/personne", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Personne> getPersonneNom(@RequestParam String nom, @RequestParam String prenom) {

        List<Personne> persons = new ArrayList<>();
        Personne result = personneRepository.findByNomAndPrenom(nom,prenom);
        persons.add(result);
        return persons;
    }


    @GetMapping(path = "/personneMail", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Personne> getPersonneNomByMail(@RequestParam String mail) {
        //Because mail is unique
        List<Personne> persons = new ArrayList<>();
        Personne result = personneRepository.findByMail(mail);
        persons.add(result);
        return persons;
    }


    @GetMapping(path = "/membresFamille", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Personne> getPersonnesByFamily(@RequestParam String mail) {

        List<Personne> persons;
        persons = personneRepository.findMembreFamilleByUserMail(mail);
        return persons;
    }

    @PutMapping(path = "/changerPhotoPersonne", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updatePhotoPersonneByMail(@RequestParam String mail, @RequestParam String photo) {
        personneRepository.modifyPhotoPersonneByMail(mail,photo);
    }

    @PutMapping(path = "/changerInfosPersonne", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateInfosPersonneByMail(@RequestParam String mail, @RequestParam String personne) {

//        personneRepository.modifyInfosPersonne(personne);
    }


    @PutMapping(path ="/personne")
    @ResponseStatus(HttpStatus.CREATED)
    public void addPersonne(@RequestBody Personne personne) {
        personneRepository.save(personne);
    }


    @PostMapping(path ="/personne")
    @ResponseStatus(HttpStatus.CREATED)
    public void addPostPersonne(@RequestBody Personne personne) {
        personneRepository.save(personne);
    }


    @DeleteMapping(path="/personne")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePersonne(@RequestBody Personne personne) throws Exception {
        if (!isSaveInDataBase(personne)) {
            throw new Exception("La personne n'existe pas!");
        }

        Personne personneToDelete = personneRepository.findByMail(personne.getMail());

        personneRepository.delete(personneToDelete);

        if(isSaveInDataBase(personne)) {
            throw new Exception("La personne n'a pas été supprimée!");
        }
    }


    private boolean isSaveInDataBase(Personne personne) {
        return personneRepository.existsByNom(personne.getNom());
    }
}
