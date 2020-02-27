package com.mamie.backend.controller;

import com.mamie.backend.model.Famille;
import com.mamie.backend.model.Personne;
import com.mamie.backend.repository.PersonneRepository;
import org.springframework.http.HttpStatus;
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

    @GetMapping(path = "/mariage", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Personne> getMariage(@RequestParam String mail) {
        return personneRepository.findConjoint(mail);
    }

    @GetMapping(path = "/siblings", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Personne> getSiblings(@RequestParam String mail) {
//        return personneRepository.findSiblings(mail);
        return null;
    }

    @GetMapping(path = "/parents", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Personne> getParents(@RequestParam String mail) {
        return personneRepository.findParents(mail);
    }

    @GetMapping(path = "/enfants", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Personne> getEnfants(@RequestParam String mail) {
        return personneRepository.findEnfants(mail);
    }

    @GetMapping(path = "/genealogie", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getGenealogie(@RequestParam String mail) {
        String arbre = "";

        Personne user = personneRepository.findByMail(mail);
        List<Personne> parents = personneRepository.findParents(mail);
        List<Personne> siblings = personneRepository.findSiblings(mail);
        List<Personne> spouses = personneRepository.findConjoint(mail);
        List<Personne> children = personneRepository.findEnfants(mail);


        String userRoot = "{" +
                "\"id :\" \"" + user.getId() + "\"," +

                "\"gender :\" \"" + user.getGender() + "\"," +

                "}";

        String lesPersonnes = "";

        String objetContenantLesPersonnes = "[" + lesPersonnes + "]";

        String jsonFinal = "{" + objetContenantLesPersonnes + "}";

        return arbre;
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

    @GetMapping(path = "/membresFamilleByNom", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Personne> getMembresByFamily(@RequestParam String mail, @RequestParam String nomFamille) {

        return personneRepository.findByNomFamille(mail, nomFamille);
    }

    @GetMapping(path = "/membresFamilleById", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Personne> getMembresByFamily(@RequestParam int id) {

        return personneRepository.findById_Famille(id);
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


    @GetMapping(path = "/membresFamilleByMail", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Personne> getPersonnesByFamilyByMail(@RequestParam String mail) {

        List<Personne> persons;
        persons = personneRepository.findMembreFamilleByUserMail(mail);
        return persons;
    }

    @PutMapping(path = "/changerPhotoPersonne", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updatePhotoPersonneByMail(@RequestParam String mail, @RequestParam String photo) {
        personneRepository.modifyPhotoPersonneByMail(mail, photo);
    }

    @PutMapping(path = "/changerInfosPersonne", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateInfosPersonneByMail(@RequestParam String mail, @RequestParam String personne) {

//        personneRepository.modifyInfosPersonne(personne);
    }


    @PutMapping(path = "/personne")
    @ResponseStatus(HttpStatus.CREATED)
    public void addPersonne(@RequestBody Personne personne) {
        personneRepository.save(personne);
        List<Famille> familles = personne.getFamilles();
        familles.forEach(famille -> famille.increaseNombreMembre());
    }


    @PostMapping(path = "/personne")
    @ResponseStatus(HttpStatus.CREATED)
//    @RequestHeader("Content-Type: application/json")
    public void addPostPersonne(@RequestBody Personne personne) {
        try {
            if (personne.getMail().isEmpty()) {
                personneRepository.save(personne);
                List<Famille> familles = personne.getFamilles();
                familles.forEach(famille -> famille.increaseNombreMembre());
            }
        } catch (NullPointerException e) {
            throw new RuntimeException(String.format("Personne vide , %s", e));
        }
    }


    @DeleteMapping(path = "/personne")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePersonne(@RequestBody Personne personne) throws Exception {
        if (!isSaveInDataBase(personne)) {
            throw new Exception("La personne n'existe pas!");
        }

        Personne personneToDelete = personneRepository.findByMail(personne.getMail());

        personneRepository.delete(personneToDelete);

        List<Famille> familles = personne.getFamilles();
        familles.forEach(famille -> famille.decreaseNombreMembre());

        if (isSaveInDataBase(personne)) {
            throw new Exception("La personne n'a pas été supprimée!");
        }
    }


    private boolean isSaveInDataBase(Personne personne) {
        return personneRepository.existsByNom(personne.getNom());
    }
}
