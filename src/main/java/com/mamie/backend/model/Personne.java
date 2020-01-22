package com.mamie.backend.model;

import org.neo4j.ogm.annotation.*;

import javax.validation.constraints.Email;
import java.util.*;

@NodeEntity
public class Personne {

    @Id
    @GeneratedValue
    private Long id;

    private String nom;
    private String prenom;
    @Email
    @Required
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
    private List<Famille> familles = new ArrayList<>();

    @Relationship(type = "Sibling", direction = Relationship.OUTGOING)
    private List<Personne> sibling = new ArrayList<>();

    @Relationship(type = "Enfant_de", direction = Relationship.OUTGOING)
    private List<Personne> parents = new ArrayList<>();

    @Relationship(type = "Mariage", direction = Relationship.OUTGOING)
    private Personne epoux;


    private Personne() {
        // Empty constructor required as of Neo4j API 2.0.5
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
        this.familles.add(famille);
    }

    public Personne(String nom, String prenom, @Email String mail, Date dateDeNaissance, String ville, String pays, String photo, String profession, String diplome, String statut, int age, List<Famille> familles) {
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
        this.familles.addAll(familles);
    }

    public Personne(String nom, String prenom, @Email String mail, Date dateDeNaissance, String ville, String pays, String photo, String profession, String diplome, String statut, int age, List<Famille> familles, List<Personne> sibling, List<Personne> parents, Personne epoux) {
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
        this.familles = familles;
        this.sibling = sibling;
        this.parents = parents;
        this.epoux = epoux;
    }

    public Personne(String nom, String prenom, String mail) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
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
                ", familles=" + familles +
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

    public List<Famille> getFamilles() {
        return familles;
    }

    public void setFamilles(List<Famille> familles) {
        this.familles = familles;
    }
}
