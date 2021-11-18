package com.michaeladonis.pokedox.exceptions;

public class PokemonServerException extends RuntimeException {

    public PokemonServerException() {
        super("Something went wrong on the pokemon server! 😥");
    }
}
