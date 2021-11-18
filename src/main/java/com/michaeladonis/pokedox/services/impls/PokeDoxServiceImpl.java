package com.michaeladonis.pokedox.services.impls;

import com.michaeladonis.pokedox.client.PokemonClient;
import com.michaeladonis.pokedox.dtos.DataResponse;
import com.michaeladonis.pokedox.dtos.PokemonDetailsResponseBody;
import com.michaeladonis.pokedox.dtos.PokemoneDetailsResponse;
import com.michaeladonis.pokedox.services.PokedoxService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class PokeDoxServiceImpl implements PokedoxService {

    private final PokemonClient pokemonClient;

    @Override
    public DataResponse<PokemoneDetailsResponse> getPokemonDetails(String pokemonName) {
        PokemonDetailsResponseBody pokemonDetails = pokemonClient.getPokemonDetails(pokemonName);
        return new DataResponse<>(true, new PokemoneDetailsResponse(pokemonDetails));
    }
}
