package com.mamie.backend.model;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import javax.validation.constraints.Email;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@NodeEntity
public class Personne {

    @Id
    @GeneratedValue
    private Long id;

    private String nom;
    private String prenom;
    @Email
    private String mail;
    private Date dateDeNaissance;
    private String ville;
    private String pays;
    private String photo;
    private String profession;
    private String diplome;
    private String statut;
    private int age;

    @Relationship(type = "IN", direction = Relationship.OUTGOING)
    private Famille famille;


    private Personne() {
        // Empty constructor required as of Neo4j API 2.0.5
    }


    public Personne(String nom, String prenom, Famille famille) {
        this.nom = nom;
        this.prenom = prenom;
        this.famille = famille;
    }

    public Personne(String nom, String prenom, Date dateDeNaissance, int age, Famille famille) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateDeNaissance = dateDeNaissance;
        this.age = age;
        this.famille = famille;
    }

    public Personne(String nom, String prenom, @Email String mail, Date dateDeNaissance, String ville, String pays, String photo, String profession, String diplome, String statut, int age, Famille famille) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.dateDeNaissance = dateDeNaissance;
        this.ville = ville;
        this.pays = pays;
        this.photo = photo;
        this.profession = profession;
        this.diplome = diplome;
        this.statut = statut;
        this.age = age;
        this.famille = famille;
    }

    @Override
    public String toString() {
        return "Personne{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", mail='" + mail + '\'' +
                ", dateDeNaissance=" + dateDeNaissance +
                ", ville='" + ville + '\'' +
                ", pays='" + pays + '\'' +
                ", photo='" + photo + '\'' +
                ", profession='" + profession + '\'' +
                ", diplome='" + diplome + '\'' +
                ", statut='" + statut + '\'' +
                ", age=" + age +
                ", famille=" + famille +
                '}';
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(Date dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
