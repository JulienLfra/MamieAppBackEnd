package com.mamie.backend.model;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

@NodeEntity
public class Famille {

    @Id @GeneratedValue private Long id;

    private String name;

    private Famille() {
        // Empty constructor required as of Neo4j API 2.0.5
    }

    public Famille(String name) {
        this.name = name;
    }


    public String toString() {

        return this.name +"'s Familly : " + this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Relationship(type="MEMBER", direction = Relationship.INCOMING)
    private List<Membre> membres;
}
