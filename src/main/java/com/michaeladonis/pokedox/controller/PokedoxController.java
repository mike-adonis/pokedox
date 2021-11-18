package com.michaeladonis.pokedox.controller;

import com.michaeladonis.pokedox.dtos.DataResponse;
import com.michaeladonis.pokedox.services.PokedoxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Api(tags = {"Pokedox Controller"})
@SwaggerDefinition(tags = {
        @Tag(name = "Pokemon", description = "Fetches details about a pokemon")
})
public class PokedoxController {

    private final PokedoxService pokedoxService;

    @PostMapping("pokemon/{pokemoneName}")
    public DataResponse getPokemon(@PathVariable String pokemonName) {
        return pokedoxService.getPokemonDetails(pokemonName);
    }

}
