package com.mamie.backend.model;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Famille {

    @Id
    @GeneratedValue
    private Long id;

    private String nom;
    @GeneratedValue
    private int id_famille;
    private int nombreMembre;


    private Famille() {
        // Empty constructor required as of Neo4j API 2.0.5
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNombreMembre() {
        return nombreMembre;
    }

    public void setNombreMembre(int nombreMembre) {
        this.nombreMembre = nombreMembre;
    }

    public int getId_famille() {
        return id_famille;
    }

    public void setId_famille(int id_famille) {
        this.id_famille = id_famille;
    }

    public Famille(String nom, int nombreMembre) {
        this.nom = nom;
        this.nombreMembre = nombreMembre;
    }


    @Override
    public String toString() {
        return "Famille{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", id_famille=" + id_famille +
                ", nombreMembre=" + nombreMembre +
                '}';
    }
}
