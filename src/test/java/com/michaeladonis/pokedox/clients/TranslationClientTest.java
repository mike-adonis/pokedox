package com.michaeladonis.pokedox.clients;

import com.michaeladonis.pokedox.dtos.PokemonDetailsResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.michaeladonis.pokedox.util.TestContants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TranslationClientTest {

    @Autowired
    private TranslationClient translationClient;

    @Test
    void givenLegendaryPokemon_ifTranslationIsYoda_thenSuccess() {
        PokemonDetailsResponse pokemonObject = new PokemonDetailsResponse("rare", "mewtwo", POKEMON_DESCRIPTION, true);
        assertMatchingTranslations(pokemonObject,YODA_TRANSLATION);
    }

    @Test
    void givenCavePokemon_ifTranslationIsYoda_thenSuccess() {
        PokemonDetailsResponse pokemonObject = new PokemonDetailsResponse("cave", "mewtwo", POKEMON_DESCRIPTION, false);
        assertMatchingTranslations(pokemonObject,YODA_TRANSLATION);
    }

    @Test
    void givenRegularPokemon_ifTranslationIsShakespearean_thenSuccess() {
        PokemonDetailsResponse pokemonObject = new PokemonDetailsResponse("grassland", "bulbasaur", POKEMON_DESCRIPTION_SHAKESPEARE, false);
        assertMatchingTranslations(pokemonObject,SHAKESPEARE_TRANSLATION);
    }

    @Test
    void givenCannotTranslate_ifTranslationIsExistingDescription_thenSuccess() {
//        doReturn(new ResponseEntity<>(mockPokemonResponse, HttpStatus.OK)).when(translationClientMock).("mewtwo");

        PokemonDetailsResponse pokemonObject = new PokemonDetailsResponse("grassland", "bulbasaur", POKEMON_DESCRIPTION_SHAKESPEARE, false);
        assertMatchingTranslations(null,SHAKESPEARE_TRANSLATION);
    }

    private void assertMatchingTranslations(PokemonDetailsResponse pokemonObject, String translation) {
        String translatedDescription = translationClient.getTranslatedDescription(pokemonObject);
        assertEquals(translatedDescription, translation);
    }
}