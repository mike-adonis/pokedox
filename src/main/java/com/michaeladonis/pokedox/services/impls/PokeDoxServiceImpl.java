package com.michaeladonis.pokedox.services.impls;

import com.michaeladonis.pokedox.client.PokemonClient;
import com.michaeladonis.pokedox.dtos.PokemonDetailsResponseBody;
import com.michaeladonis.pokedox.dtos.PokemoneDetailsResponse;
import com.michaeladonis.pokedox.services.BaseService;
import com.michaeladonis.pokedox.services.PokedoxService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class PokeDoxServiceImpl extends BaseService implements PokedoxService {

    private final PokemonClient pokemonClient;

    @Override
    public ResponseEntity<PokemoneDetailsResponse> getPokemonDetails(String pokemonName) {
        PokemonDetailsResponseBody pokemonDetails = pokemonClient.getPokemonDetails(pokemonName);
        return new ResponseEntity<>(new PokemoneDetailsResponse(pokemonDetails), HttpStatus.OK);
    }
}
