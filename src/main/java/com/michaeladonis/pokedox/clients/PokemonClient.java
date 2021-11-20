package com.michaeladonis.pokedox.clients;

import com.michaeladonis.pokedox.config.MessageHelperService;
import com.michaeladonis.pokedox.dtos.PokemonDetailsResponse;
import com.michaeladonis.pokedox.dtos.PokemonDetailsResponseBody;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import static com.michaeladonis.pokedox.config.Constants.GET_POKEMON_V2;

@Component
public class PokemonClient extends BaseClient {

    private final WebClient webClient;

    private final MessageHelperService messageHelperService;

    public PokemonClient(WebClient.Builder webClientBuilder, MessageHelperService messageHelperService) {
        this.webClient = webClientBuilder.baseUrl("https://pokeapi.co").build();
        this.messageHelperService = messageHelperService;
    }

    private PokemonDetailsResponseBody fetchPokemonData(String pokemonName) {
        WebClient.ResponseSpec responseSpec = webClient.get().uri(GET_POKEMON_V2, pokemonName).retrieve();
        return handleRequest(responseSpec, messageHelperService.getMessage("pokemon.not.found")).bodyToMono(PokemonDetailsResponseBody.class).block();
    }

    public PokemonDetailsResponse getPokemonData(String pokemonName) {
        PokemonDetailsResponseBody pokemonDetails = fetchPokemonData(pokemonName);
        return new PokemonDetailsResponse(pokemonDetails);
    }

}
