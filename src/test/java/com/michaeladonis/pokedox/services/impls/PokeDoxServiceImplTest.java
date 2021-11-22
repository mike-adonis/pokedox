package com.michaeladonis.pokedox.services.impls;

import com.michaeladonis.pokedox.clients.PokemonClient;
import com.michaeladonis.pokedox.clients.TranslationClient;
import com.michaeladonis.pokedox.dtos.PokemonDetailsResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.michaeladonis.pokedox.util.TestContants.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class PokeDoxServiceImplTest {

    @InjectMocks
    private PokeDoxServiceImpl pokeDoxService;

    @Mock
    private TranslationClient translationClient;

    @Mock
    private PokemonClient pokemonClient;

    @Test
    void givenCavePokemonName_ifTranslationIsYoda_thenSucceed() {
        PokemonDetailsResponse mockPokemonResponse = new PokemonDetailsResponse("cave", "mewtwo", POKEMON_DESCRIPTION, false);
        doReturn(mockPokemonResponse).when(pokemonClient).getPokemonData("mewtwo");
        doReturn(YODA_TRANSLATION).when(translationClient).getTranslatedDescription(mockPokemonResponse);
        PokemonDetailsResponse response = pokeDoxService.getTranslatedPokemonDetails("mewtwo").getBody();
        assert response != null;
        Assertions.assertEquals(YODA_TRANSLATION, response.getDescription());
    }

    @Test
    void givenLegendaryPokemonName_ifTranslationIsYoda_thenSucceed() {
        PokemonDetailsResponse mockPokemonResponse = new PokemonDetailsResponse("rare", "mewtwo", POKEMON_DESCRIPTION, true);
        doReturn(mockPokemonResponse).when(pokemonClient).getPokemonData("mewtwo");
        doReturn(YODA_TRANSLATION).when(translationClient).getTranslatedDescription(mockPokemonResponse);
        PokemonDetailsResponse response = pokeDoxService.getTranslatedPokemonDetails("mewtwo").getBody();
        assert response != null;
        Assertions.assertEquals(YODA_TRANSLATION, response.getDescription());
    }

    @Test
    void givenRegularPokemonName_ifTranslationIsShakespearean_thenSucceed() {
        PokemonDetailsResponse mockPokemonResponse = new PokemonDetailsResponse("rare", "mewtwo", POKEMON_DESCRIPTION, false);
        doReturn(mockPokemonResponse).when(pokemonClient).getPokemonData("mewtwo");
        doReturn(SHAKESPEARE_TRANSLATION).when(translationClient).getTranslatedDescription(mockPokemonResponse);
        PokemonDetailsResponse response = pokeDoxService.getTranslatedPokemonDetails("mewtwo").getBody();
        assert response != null;
        Assertions.assertEquals(SHAKESPEARE_TRANSLATION, response.getDescription());
    }


    @Test
    void givenValidPokemonName_ifPokemonMatchesReturnedPokemon_thenSucceed() {
        PokemonDetailsResponse mockPokemonResponse = new PokemonDetailsResponse("rare", "mewtwo", POKEMON_DESCRIPTION, false);
        doReturn(mockPokemonResponse).when(pokemonClient).getPokemonData("mewtwo");
        PokemonDetailsResponse response = pokeDoxService.getPokemonDetails("mewtwo").getBody();
        assert response != null;
        Assertions.assertEquals("mewtwo", response.getName());
    }

}