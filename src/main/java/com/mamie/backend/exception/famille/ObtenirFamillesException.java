package com.mamie.backend.exception.famille;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ObtenirFamillesException extends RuntimeException {
    public ObtenirFamillesException() {
        super("Il n'y a aucune famille!");
    }
}
