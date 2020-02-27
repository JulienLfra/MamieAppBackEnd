package com.mamie.backend.exception.famille;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ObtenirFamilleException extends RuntimeException {
    public ObtenirFamilleException(String nom) {
        super(String.format("Cette famille '%s' n'existe pas.", nom));
    }
}
