package com.mamie.backend.exception.famille;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FamilleNonSupprimeeException extends RuntimeException {
    public FamilleNonSupprimeeException(String nom) {
        super(String.format("La famille '%s' n'a pas été supprimée.",nom));
    }
}
