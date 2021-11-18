package com.michaeladonis.pokedox.controller;


import com.michaeladonis.pokedox.dtos.PokemoneDetailsResponse;
import com.michaeladonis.pokedox.services.PokedoxService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.doReturn;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class PokedoxControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PokedoxService pokedoxService;


    @Test
    @DisplayName("Test find pokemon by name")
    void givenNamePokemon_ifNameMatches_thenSuccess() throws Exception {

//        Setup mock service
        PokemoneDetailsResponse mockPokemonResponse = new PokemoneDetailsResponse("rare", "mewtwo", "Some nice description", true);
        doReturn(new ResponseEntity<>(mockPokemonResponse, HttpStatus.OK)).when(pokedoxService).getPokemonDetails("mewtwo");

        mockMvc.perform(get("/pokemon/{pokemonName}", "mewtwo"))
//                check response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))

                //check response body
                .andExpect(jsonPath("$.name", is("mewtwo")))
                .andExpect(jsonPath("$.habitat", is("rare")))
                .andExpect(jsonPath("$.description", is("Some nice description")))
                .andExpect(jsonPath("$.isLegendary", is(true)));
    }




}