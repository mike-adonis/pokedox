package com.michaeladonis.pokedox.clients;

import com.michaeladonis.pokedox.dtos.PokemonDetailsResponse;
import com.michaeladonis.pokedox.dtos.TranslatorResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import static com.michaeladonis.pokedox.config.Constants.*;

@Component
public class TranslationClient extends BaseClient {

    private final WebClient webClient;

    public TranslationClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.funtranslations.com/translate/").build();
    }

    public String getTranslatedDescription(PokemonDetailsResponse pokemonDetailsResponse) {
        TranslatorResponse translatorResponse = translate(pokemonDetailsResponse);
        return translatorResponse.getContents().getTranslated();
    }

    private TranslatorResponse translate(PokemonDetailsResponse pokemonDetails) {
        WebClient.ResponseSpec responseSpec = webClient.get().
                uri(uriBuilder ->
                        uriBuilder.path(decideTranslation(pokemonDetails)).
                                queryParam("text", pokemonDetails.getDescription())
                                .build()).retrieve();
        return responseSpec.bodyToMono(TranslatorResponse.class).onErrorReturn(new TranslatorResponse(pokemonDetails.getDescription())).block();
    }

    private String decideTranslation(PokemonDetailsResponse pokemonDetails) {
        if (pokemonDetails.getHabitat().equals("cave") || pokemonDetails.getIsLegendary()) {
            return YODA.concat(".json");
        } else {
            return SHAKESPEARE.concat(".json");
        }
    }

}