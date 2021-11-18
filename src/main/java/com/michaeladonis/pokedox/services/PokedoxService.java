package com.michaeladonis.pokedox.services;

import com.michaeladonis.pokedox.dtos.DataResponse;
import com.michaeladonis.pokedox.dtos.PokemoneDetailsResponse;

public interface PokedoxService {


    DataResponse<PokemoneDetailsResponse> getPokemonDetails(String pokemonName);
}
