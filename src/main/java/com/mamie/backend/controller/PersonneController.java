package com.mamie.backend.controller;

import com.mamie.backend.model.Personne;
import com.mamie.backend.repository.PersonneRepository;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonneController {

    private final Driver driver;

    private final PersonneRepository personneRepository;

    public PersonneController(Driver driver, PersonneRepository personneRepository) {
        this.driver = driver;
        this.personneRepository = personneRepository;
    }


    @GetMapping(path = "/personnes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getPersonnesName() {

        try (Session session = driver.session()) {
            return session.run("MATCH (p:Personne) RETURN p ORDER BY p.lastName ASC")
                    .list(r -> r.get("p").asNode().get("lastName").asString());
        }
    }

    @PutMapping(path ="/personne")
    public void addFamille(@RequestBody Personne personne) {
        personneRepository.save(personne);
    }

    @DeleteMapping(path="/personne")
    public void deleteFamille(@RequestBody Personne personne) throws Exception {
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
