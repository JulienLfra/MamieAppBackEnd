package com.mamie.backend.model;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@NodeEntity
public class Personne {

    @Id
    @GeneratedValue
    private Long id;

    private String lastName;
    private String firstName;
    private Date dateDeNaissance;
    private String Ville;
    private int age;


    private Personne() {
        // Empty constructor required as of Neo4j API 2.0.5
    }


    public Personne(Long id, String lastName, String firstName) {
//        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public Personne(Long id, String lastName, String firstName, Date dateDeNaissance, int age) {
//        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.dateDeNaissance = dateDeNaissance;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(Date dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public String getVille() {
        return Ville;
    }

    public void setVille(String ville) {
        Ville = ville;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Personne{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", dateDeNaissance=" + dateDeNaissance +
                ", Ville='" + Ville + '\'' +
                ", age=" + age +
                ", familles=" + familles +
                '}';
    }

    @Relationship(type = "MEMBER")
    private List<Famille> familles;
}
