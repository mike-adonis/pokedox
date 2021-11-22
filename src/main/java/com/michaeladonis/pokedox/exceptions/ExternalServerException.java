package com.michaeladonis.pokedox.exceptions;

public class ExternalServerException extends RuntimeException {
    private static String errorMessage = "Something went wrong on one of the servers we rely on! 😥";

    public ExternalServerException() {
        super(errorMessage);
    }

    public ExternalServerException(String message) {
        super(message.isEmpty() ? errorMessage : message);
    }
}
