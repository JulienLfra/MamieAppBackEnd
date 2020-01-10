package com.mamie.backend.controller;

import com.mamie.backend.model.Personne;
import com.mamie.backend.repository.PersonneRepository;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class PersonneController {

    private final Driver driver;

    private final PersonneRepository personneRepository;

    public PersonneController(Driver driver, PersonneRepository personneRepository) {
        this.driver = driver;
        this.personneRepository = personneRepository;
    }


    @GetMapping(path = "/personnes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Personne> getPersonnesName() {

        Iterable<Personne> p = personneRepository.findAll();
        List<Personne> persons = new ArrayList<>();
        for (Personne person : p) {
            persons.add(person);
        }
        return persons;
    }

    @GetMapping(path = "/personne", produces = MediaType.APPLICATION_JSON_VALUE)
    public Personne getPersonneName(@RequestParam String lastName, @RequestParam String firstName) {

        return personneRepository.findByLastNameAndAndFirstName(lastName,firstName);
    }

    @PutMapping(path ="/personne")
    public void addPersonne(@RequestBody Personne personne) {
        personneRepository.save(personne);
    }

    @DeleteMapping(path="/personne")
    public void deletePersonne(@RequestBody Personne personne) throws Exception {
        if (!isSaveInDataBase(personne)) {
            throw new Exception("La personne n'existe pas!");
        }

        Personne personneToDelete = personneRepository.findByLastName(personne.getLastName());

        personneRepository.delete(personneToDelete);

        if(isSaveInDataBase(personne)) {
            throw new Exception("La personne n'a pas été supprimée!");
        }
    }


    private boolean isSaveInDataBase(Personne personne) {
        return personneRepository.existsByLastName(personne.getLastName());
    }
}
