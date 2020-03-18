package com.mamie.backend.controller;

import com.mamie.backend.model.Famille;
import com.mamie.backend.model.IdGenealogique;
import com.mamie.backend.model.Personne;
import com.mamie.backend.model.PersonneGenealogique;
import com.mamie.backend.repository.PersonneRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public List<PersonneGenealogique> getGenealogie(@RequestParam String mail) {

        List<PersonneGenealogique> result = new ArrayList<>();

        Personne user = personneRepository.findByMail(mail);
        List<Personne> parentsUserG = personneRepository.findParents(mail);
        List<Personne> siblingsUserG = personneRepository.findSiblings(mail);
        List<Personne> spousesUserG = personneRepository.findConjoint(mail);
        List<Personne> childrenUserG = personneRepository.findEnfants(mail);


        //!!!!!CREATION USER COURANT!!!!

        Set<IdGenealogique> idParentsUserG = new HashSet<>();
        Set<IdGenealogique> idSiblingsUserG = new HashSet<>();
        Set<IdGenealogique> idSpousesUserG = new HashSet<>();
        Set<IdGenealogique> idChildrenUserG = new HashSet<>();
        Set<IdGenealogique> listVide = new HashSet<>();

        if (!parentsUserG.isEmpty()) {
            for (Personne p : parentsUserG) {
                idParentsUserG.add(new IdGenealogique(p.getId().toString()));
            }
        }

        if (!siblingsUserG.isEmpty()) {
            for (Personne p : siblingsUserG) {
                idSiblingsUserG.add(new IdGenealogique(p.getId().toString()));
            }
        }

        if (!spousesUserG.isEmpty()) {
            for (Personne p : spousesUserG) {
                idSpousesUserG.add(new IdGenealogique(p.getId().toString()));
            }
        }

        if (!childrenUserG.isEmpty()) {
            for (Personne p : childrenUserG) {
                idChildrenUserG.add(new IdGenealogique(p.getId().toString()));
            }
        }

        PersonneGenealogique userG = new PersonneGenealogique(user.getId().toString(),
                user.getGender().toString(),
                user.getPrenom() + " " + user.getNom(),
                idParentsUserG,
                idSiblingsUserG,
                idSpousesUserG,
                idChildrenUserG
        );

        System.out.println(userG);
        result.add(userG);


        //!!!!!CREATION Parents User Courant!!!!

        if (!parentsUserG.isEmpty()) {
            for (Personne p : parentsUserG) {
                Set<IdGenealogique> idSpousesParents = new HashSet<>();
                Set<IdGenealogique> idChildrenParents = new HashSet<>();

                List<Personne> childrenParents = personneRepository.findEnfants(p.getMail());

                if (!childrenParents.isEmpty()) {
                    for (Personne p2 : childrenParents) {
                        idChildrenParents.add(new IdGenealogique(p2.getId().toString()));
                    }
                } else idChildrenParents = listVide;

                List<Personne> spousesParents = personneRepository.findConjoint(p.getMail());

                if (!spousesParents.isEmpty()) {
                    for (Personne p2 : spousesParents) {
                        idSpousesParents.add(new IdGenealogique(p2.getId().toString()));
                    }
                } else idSpousesParents = listVide;


                PersonneGenealogique parent = new PersonneGenealogique(
                        p.getId().toString(),
                        p.getGender().toString(),
                        p.getPrenom() + " " + p.getNom(),
                        listVide,
                        listVide,
                        idSpousesParents,
                        idChildrenParents
                );

                System.out.println(parent);
                result.add(parent);
            }

            //!!!!!CREATION Frere/Soeur User Courant!!!!


            if (!siblingsUserG.isEmpty()) {
                for (Personne p : siblingsUserG) {
                    Set<IdGenealogique> idParentsSibling = new HashSet<>();
                    Set<IdGenealogique> idSiblingsSibling = new HashSet<>();

                    List<Personne> parentsSibling = personneRepository.findParents(user.getMail());

                    if (!parentsSibling.isEmpty()) {
                        for (Personne p2 : parentsSibling) {
                            idParentsSibling.add(new IdGenealogique(p2.getId().toString()));
                        }
                    } else idParentsSibling = listVide;

                    List<Personne> siblingsSibling = personneRepository.findSiblings(p.getMail());

                    if (!siblingsSibling.isEmpty()) {
                        for (Personne p2 : siblingsSibling) {
                            idSiblingsSibling.add(new IdGenealogique(p2.getId().toString()));
                        }
                    } else idSiblingsSibling = listVide;

                    PersonneGenealogique sibling = new PersonneGenealogique(
                            p.getId().toString(),
                            p.getGender().toString(),
                            p.getPrenom() + " " + p.getNom(),
                            idParentsSibling,
                            idSiblingsSibling,
                            listVide,
                            listVide
                    );

                    System.out.println(sibling);
                    result.add(sibling);
                }

            }

            //!!!!!CREATION Epouse User Courant!!!!

            if (!spousesUserG.isEmpty()) {
                for (Personne p : spousesUserG) {
                    Set<IdGenealogique> idSpousesSpouse = new HashSet<>();

                    List<Personne> spousesSpouse = personneRepository.findConjoint(p.getMail());

                    if (!spousesSpouse.isEmpty()) {
                        for (Personne p2 : spousesSpouse) {
                            idSpousesSpouse.add(new IdGenealogique(p2.getId().toString()));
                        }
                    } else idSpousesSpouse = listVide;

                    PersonneGenealogique spouse = new PersonneGenealogique(
                            p.getId().toString(),
                            p.getGender().toString(),
                            p.getPrenom() + " " + p.getNom(),
                            listVide,
                            listVide,
                            idSpousesSpouse,
                            listVide
                    );

                    System.out.println(spouse);
                    result.add(spouse);
                }
            }

        }


        return result;
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

    @GetMapping(path = "/getMembresByFamilyAndMail", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Personne> getMembresByFamilyAndMail(@RequestParam int id, @RequestParam String mail) {

        List<Personne> persons = personneRepository.findById_Famille(id);
        persons.forEach(personne -> {
            if (personneRepository.getABloqueByMail(mail, personne.getMail())) {
                //Est bloqué
                personne.setAbloque(true);
            }
        });
        return persons;
    }

    @GetMapping(path = "/getMembresByFamilyAndMailAppMobile", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Personne> getMembresByFamilyAndMailAppMobile(@RequestParam int id, @RequestParam String mail) {

        List<Personne> persons = personneRepository.findById_Famille(id);
        persons.forEach(personne -> {
            if (personneRepository.getBloquerByMail(mail, personne.getMail())) {
                //Est bloqué
                personne.setBloque(true);
            }
        });
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
    public void updateInfosPersonneByMail(@RequestParam Personne personne) {
        personneRepository.modifyInfosPersonne(personne);
    }

    @PutMapping(path = "/changerPositionPersonne", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updatePositionPersonneByMail(@RequestParam String mail, @RequestParam double latitude, @RequestParam double longitude) {
        personneRepository.modifyPositionPersonne(mail, longitude, latitude);
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
    public void addPostPersonne(@RequestBody Personne personne) {
        try {
            if (!personne.getMail().isEmpty()) {
                personneRepository.save(personne);
                List<Famille> familles = personne.getFamilles();
                familles.forEach(famille -> famille.increaseNombreMembre());
            }
        } catch (NullPointerException e) {
            throw new RuntimeException(String.format("Personne vide , %s", e));
        }
    }

    @GetMapping(path = "/getBloquerByMail", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public boolean getBloquerByMail(@RequestParam String mail1, @RequestParam String mail2) {
        if (personneRepository.getBloquerByMail(mail1, mail2)) return true;
        else return false;
    }

    @PostMapping(path = "/bloquerByMail")
    @ResponseStatus(HttpStatus.CREATED)
    public void bloquerByMail(@RequestParam String mail, @RequestParam String mailABloquer) {
        personneRepository.bloquerPersonne(mail, mailABloquer);
    }

    @PostMapping(path = "/debloquerByMail")
    @ResponseStatus(HttpStatus.CREATED)
    public void debloquerByMail(@RequestParam String mail, @RequestParam String mailADebloquer) {
        personneRepository.debloquerPersonne(mail, mailADebloquer);
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
