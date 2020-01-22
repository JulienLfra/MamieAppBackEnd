package com.mamie.backend.exception.personne;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonneNonSupprimeeException extends RuntimeException {
    public PersonneNonSupprimeeException(String nom) {
        super(String.format("La personne '%s' n'a pas été supprimée.",nom));
    }
}
