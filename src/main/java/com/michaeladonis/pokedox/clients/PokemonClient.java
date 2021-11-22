package com.michaeladonis.pokedox.clients;

import com.michaeladonis.pokedox.config.MessageHelperService;
import com.michaeladonis.pokedox.dtos.PokemonDetailsResponse;
import com.michaeladonis.pokedox.dtos.PokemonDetailsResponseBody;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import static com.michaeladonis.pokedox.config.Constants.V2_GET_POKEMON;

@Component
public class PokemonClient extends BaseClient {

    private static final String BASEURL = "https://pokeapi.co";
    private final WebClient webClient;

    private final MessageHelperService messageHelperService;

    public PokemonClient(WebClient.Builder webClientBuilder, MessageHelperService messageHelperService) {
        this.webClient = webClientBuilder.baseUrl(BASEURL).build();
        this.messageHelperService = messageHelperService;
    }

    private PokemonDetailsResponseBody fetchPokemonData(String pokemonName) {
        WebClient.ResponseSpec responseSpec = webClient.get().uri(V2_GET_POKEMON, pokemonName).retrieve();
        return handleRequest(responseSpec, messageHelperService.getMessage("pokemon.not.found"))
                .bodyToMono(PokemonDetailsResponseBody.class).retry(2).block();
    }

    @Cacheable(cacheNames = "pokemons", key = "#pokemonName")
    public PokemonDetailsResponse getPokemonData(String pokemonName) {
        PokemonDetailsResponseBody pokemonDetails = fetchPokemonData(pokemonName);
        return new PokemonDetailsResponse(pokemonDetails);
    }

}
