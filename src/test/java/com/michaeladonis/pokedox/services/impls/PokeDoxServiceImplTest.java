package com.michaeladonis.pokedox.services.impls;

import com.michaeladonis.pokedox.clients.PokemonClient;
import com.michaeladonis.pokedox.clients.TranslationClient;
import com.michaeladonis.pokedox.dtos.PokemonDetailsResponse;
import com.michaeladonis.pokedox.services.PokedoxService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.michaeladonis.pokedox.util.TestContants.POKEMON_DESCRIPTION;
import static com.michaeladonis.pokedox.util.TestContants.YODA_TRANSLATION;
import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PokeDoxServiceImplTest {


    @Autowired
    private PokedoxService pokedoxService;

    @MockBean
    private TranslationClient translationClient;

    @MockBean
    private PokemonClient pokemonClient;

    @Test
    void givenCorrectPokemonName_ifTranslationIsCorrect_thenSuccess() {
        PokemonDetailsResponse mockPokemonResponse = new PokemonDetailsResponse("rare", "mewtwo", POKEMON_DESCRIPTION, true);
        doReturn(mockPokemonResponse).when(pokemonClient).getPokemonData("mewtwo");
        doReturn(YODA_TRANSLATION).when(translationClient).getTranslatedDescription(mockPokemonResponse);
        PokemonDetailsResponse response = pokedoxService.getTranslatedPokemonDetails("mewtwo").getBody();
        assert response != null;
        Assertions.assertEquals(YODA_TRANSLATION, response.getDescription());
    }

//    @Test()
//    void givenInCorrectPokemonName_ifNotFoundExceptionIsThrown_thenSucceed() {
//        doThrow(new NotFoundException("Pokemon not found")).when(pokemonClient).getPokemonData("mewtwo-incorrect");
//    }
}