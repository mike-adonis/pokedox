package com.michaeladonis.pokedox.clients;

import com.michaeladonis.pokedox.dtos.PokemonDetailsResponse;
import com.michaeladonis.pokedox.dtos.TranslatorResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import static com.michaeladonis.pokedox.config.Constants.TRANSLATE;
import static com.michaeladonis.pokedox.services.chainprocessors.TranslationChain.decideTranslation;

@Component
public class TranslationClient extends BaseClient {

    private final WebClient webClient;

    public TranslationClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.funtranslations.com").build();
    }

    public String getTranslatedDescription(PokemonDetailsResponse pokemonDetailsResponse) {
        TranslatorResponse translatorResponse = translate(pokemonDetailsResponse);
        return translatorResponse.getContents().getTranslated();
    }

    private TranslatorResponse translate(PokemonDetailsResponse pokemonDetails) {
        WebClient.ResponseSpec responseSpec = webClient.get()
                .uri(uriBuilder ->
                        uriBuilder.path(TRANSLATE + decideTranslation(pokemonDetails)).
                                queryParam("text", pokemonDetails.getDescription())
                                .build()).retrieve();
        return responseSpec.bodyToMono(TranslatorResponse.class)
                .onErrorReturn(new TranslatorResponse(pokemonDetails.getDescription())).block();
    }


}