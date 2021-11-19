package com.michaeladonis.pokedox.services.impls;

import com.michaeladonis.pokedox.clients.PokemonClient;
import com.michaeladonis.pokedox.clients.TranslationClient;
import com.michaeladonis.pokedox.dtos.PokemonDetailsResponse;
import com.michaeladonis.pokedox.services.BaseService;
import com.michaeladonis.pokedox.services.PokedoxService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class PokeDoxServiceImpl extends BaseService implements PokedoxService {

    private final PokemonClient pokemonClient;

    private final TranslationClient translationClient;

    @Override
    public ResponseEntity<PokemonDetailsResponse> getPokemonDetails(String pokemonName) {
        return new ResponseEntity<>(pokemonClient.getPokemonData(pokemonName), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PokemonDetailsResponse> getTranslatedPokemonDetails(String pokemonName) {
        PokemonDetailsResponse pokemonDetailsResponse = pokemonClient.getPokemonData(pokemonName);
        String translation = translationClient.getTranslatedDescription(pokemonDetailsResponse);
        pokemonDetailsResponse.setDescription(translation);
        return new ResponseEntity<>(pokemonDetailsResponse, HttpStatus.OK);
    }




}
