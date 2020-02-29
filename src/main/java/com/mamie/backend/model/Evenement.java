package com.mamie.backend.model;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Relationship;

public class Evenement {

    @Id
    @GeneratedValue
    private Long id;

    private String nom;
    private String date;
    private String lieu;
    private String photo;

    @Relationship(type = "PARTICIPE", direction = Relationship.INCOMING)
    private Famille famille;

    public Evenement() {
    }

    public Evenement(String nom, String date, String lieu, String photo, Famille famille) {
        this.nom = nom;
        this.date = date;
        this.lieu = lieu;
        this.photo = photo;
        this.famille = famille;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Famille getFamille() {
        return famille;
    }

    public void setFamille(Famille famille) {
        this.famille = famille;
    }

    @Override
    public String toString() {
        return "Evenement{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", date=" + date +
                ", lieu='" + lieu + '\'' +
                ", photo='" + photo + '\'' +
                ", famille=" + famille +
                '}';
    }
}
