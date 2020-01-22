package com.mamie.backend.exception.personne;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ObtenirPersonnesException extends RuntimeException {
    public ObtenirPersonnesException() {
        super("Il n'y a aucune personne!");
    }
}
