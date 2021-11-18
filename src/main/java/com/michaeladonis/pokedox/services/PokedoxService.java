package com.michaeladonis.pokedox.services;

import com.michaeladonis.pokedox.dtos.PokemoneDetailsResponse;
import org.springframework.http.ResponseEntity;

public interface PokedoxService {

    ResponseEntity<PokemoneDetailsResponse> getPokemonDetails(String pokemonName);
}
