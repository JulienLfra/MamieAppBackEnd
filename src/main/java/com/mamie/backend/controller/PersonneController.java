package com.mamie.backend.controller;

import com.mamie.backend.model.Personne;
import com.mamie.backend.repository.PersonneRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class PersonneController {

    private final PersonneRepository personneRepository;

    public PersonneController(PersonneRepository personneRepository) {
        this.personneRepository = personneRepository;
    }


    @GetMapping(path = "/personnes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Personne> getPersonnesNom() {

        Iterable<Personne> p = personneRepository.findAll();
        List<Personne> persons = new ArrayList<>();
        for (Personne person : p) {
            persons.add(person);
        }
        return persons;
    }


    @GetMapping(path = "/personne", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Personne> getPersonneNom(@RequestParam String nom, @RequestParam String prenom) {

        List<Personne> persons = new ArrayList<>();
        Personne result = personneRepository.findByNomAndPrenom(nom,prenom);
        persons.add(result);
        return persons;
    }


    @GetMapping(path = "/personneMail", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Personne> getPersonneNomByMail(@RequestParam String mail) {

        List<Personne> persons = new ArrayList<>();
        Personne result = personneRepository.findByMail(mail);
        persons.add(result);
        return persons;
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

        Personne personneToDelete = personneRepository.findByNom(personne.getNom());

        personneRepository.delete(personneToDelete);

        if(isSaveInDataBase(personne)) {
            throw new Exception("La personne n'a pas été supprimée!");
        }
    }


    private boolean isSaveInDataBase(Personne personne) {
        return personneRepository.existsByNom(personne.getNom());
    }
}
