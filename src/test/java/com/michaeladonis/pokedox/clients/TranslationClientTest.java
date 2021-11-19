package com.michaeladonis.pokedox.clients;

import com.michaeladonis.pokedox.dtos.PokemonDetailsResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.michaeladonis.pokedox.TestContants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TranslationClientTest {

    @Autowired
    private TranslationClient translationClient;

    @Test
    void givenLegendaryPokemon_ifTranslationIsYoda_thenSuccess() {
        PokemonDetailsResponse pokemonObject = new PokemonDetailsResponse("rare", "mewtwo", POKEMON_DESCRIPTION, true);
        validateTranslations(pokemonObject,YODA_TRANSLATION);
    }

    @Test
    void givenCavePokemon_ifTranslationIsYoda_thenSuccess() {
        PokemonDetailsResponse pokemonObject = new PokemonDetailsResponse("cave", "mewtwo", POKEMON_DESCRIPTION, false);
        validateTranslations(pokemonObject,YODA_TRANSLATION);
    }

    @Test
    void givenRegularPokemon_ifTranslationIsShakespearean_thenSuccess() {
        PokemonDetailsResponse pokemonObject = new PokemonDetailsResponse("grassland", "bulbasaur", POKEMON_DESCRIPTION_SHAKESPEARE, false);
        validateTranslations(pokemonObject,SHAKESPEARE_TRANSLATION);
    }

    @Test
    void givenCannotTranslate_ifTranslationIsExistingDescription_thenSuccess() {
        PokemonDetailsResponse pokemonObject = new PokemonDetailsResponse("grassland", "bulbasaur", POKEMON_DESCRIPTION_SHAKESPEARE, false);
        validateTranslations(pokemonObject,SHAKESPEARE_TRANSLATION);
    }

    private void validateTranslations(PokemonDetailsResponse pokemonObject, String translation) {
        String translatedDescription = translationClient.getTranslatedDescription(pokemonObject);
        assertEquals(translatedDescription, translation);
    }
}