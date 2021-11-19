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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

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
    void getPokemonDetails() {

        PokemonDetailsResponse mockPokemonResponse = new PokemonDetailsResponse("rare", "mewtwo", "I love ice cream how about thee", true);
//        doReturn(Optional.of(mockPokemonResponse)).when(pokemonClient).getPokemonDetails("mewtwo");
//        doReturn(Optional.of(mockPokemonResponse)).when(translationClient).translate(mockPokemonResponse);

        PokemonDetailsResponse response = pokedoxService.getTranslatedPokemonDetails("mewtwo").getBody();

        assert response != null;
        Assertions.assertEquals(mockPokemonResponse.getDescription(),response.getDescription());



    }
}