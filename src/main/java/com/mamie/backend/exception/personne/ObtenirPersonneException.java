package com.mamie.backend.exception.personne;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ObtenirPersonneException extends RuntimeException {
    public ObtenirPersonneException(String nom) {
        super(String.format("Cette personne '%s' n'existe pas.", nom));
    }
}
