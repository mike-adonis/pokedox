package com.michaeladonis.pokedox.clients;

import com.michaeladonis.pokedox.dtos.PokemonDetailsResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PokemonClientTest {

    @Autowired
    private PokemonClient pokemonClient;

    @Test
    void  givenPokemonNam_ifResponseMatchesRequest_thenSuccess() {
        PokemonDetailsResponse pokemonDetails = pokemonClient.getPokemonData("mewtwo");
        assertEquals("mewtwo", pokemonDetails.getName());
    }
}