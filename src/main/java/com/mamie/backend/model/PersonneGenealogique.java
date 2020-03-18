package com.mamie.backend.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Set;


@JsonDeserialize(builder = PersonneGenealogique.PersonneGenealogiqueBuilder.class)
public class PersonneGenealogique {

    private String id;
    private String gender;
    private String name;
    private Set<IdGenealogique> parents;
    private Set<IdGenealogique> siblings;
    private Set<IdGenealogique> spouses;
    private Set<IdGenealogique> children;


    public Set<IdGenealogique> getparents() {
        return parents;
    }

    public void setparents(Set<IdGenealogique> parents) {
        this.parents = parents;
    }

    public Set<IdGenealogique> getsiblings() {
        return siblings;
    }

    public void setsiblings(Set<IdGenealogique> siblings) {
        this.siblings = siblings;
    }

    public Set<IdGenealogique> getspouses() {
        return spouses;
    }

    public void setspouses(Set<IdGenealogique> spouses) {
        this.spouses = spouses;
    }

    public Set<IdGenealogique> getchildren() {
        return children;
    }

    public void setchildren(Set<IdGenealogique> children) {
        this.children = children;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public PersonneGenealogique() {
    }

    public PersonneGenealogique(String id, String gender, String name, Set<IdGenealogique> parents, Set<IdGenealogique> siblings, Set<IdGenealogique> spouses, Set<IdGenealogique> children) {
        this.id = id;
        this.gender = gender;
        this.name = name;
        this.parents = parents;
        this.siblings = siblings;
        this.spouses = spouses;
        this.children = children;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class PersonneGenealogiqueBuilder {
    }
}
