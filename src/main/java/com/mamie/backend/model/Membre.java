package com.mamie.backend.model;

import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "MEMBER")
public class Membre {

    @Id
    @GeneratedValue
    Long id;

    @StartNode
    private Personne personne;

    @EndNode
    private Famille famille;

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public Famille getFamille() {
        return famille;
    }

    public void setFamille(Famille famille) {
        this.famille = famille;
    }

    public Membre() {

    }

    public Membre(Personne personne, Famille famille) {
        this.personne = personne;
        this.famille = famille;
    }
}
