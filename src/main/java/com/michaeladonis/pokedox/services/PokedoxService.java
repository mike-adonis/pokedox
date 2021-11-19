package com.michaeladonis.pokedox.services;

import com.michaeladonis.pokedox.dtos.PokemonDetailsResponse;
import org.springframework.http.ResponseEntity;

public interface PokedoxService {

    ResponseEntity<PokemonDetailsResponse> getPokemonDetails(String pokemonName);

    ResponseEntity<PokemonDetailsResponse> getTranslatedPokemonDetails(String pokemonName);
}
