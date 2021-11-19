package com.michaeladonis.pokedox.exceptions;

public class ExternalServerException extends RuntimeException {

    public ExternalServerException() {
        super("Something went wrong on the the servers we rely on! 😥");
    }
}
