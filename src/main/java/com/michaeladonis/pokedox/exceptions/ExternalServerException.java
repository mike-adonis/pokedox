package com.michaeladonis.pokedox.exceptions;

public class ExternalServerException extends RuntimeException {

    public ExternalServerException() {
        super("Something went wrong on one of the servers we rely on! 😥");
    }
}
