package com.mamie.backend.controller;

import com.mamie.backend.model.Famille;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FamilleController {

    private final Driver driver;

    public FamilleController(Driver driver) {
        this.driver = driver;
    }


    @GetMapping(path = "/familles", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getFamillesName() {

        try (Session session = driver.session()) {
            return session.run("MATCH (f:Famille) RETURN f ORDER BY f.name ASC")
                    .list(r -> r.get("f").asNode().get("name").asString());
        }
    }
}
