package com.michaeladonis.pokedox.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PokemonDetailsResponse {
    private String habitat;
    private String name;
    private String description;
    private Boolean isLegendary;

    public PokemonDetailsResponse(PokemonDetailsResponseBody responseBody) {
        initializeRequiredFields(responseBody);
        if (responseBody.getFlavor_text_entries() != null && !responseBody.getFlavor_text_entries().isEmpty()) {
            this.description = responseBody.getFlavor_text_entries().get(0).getFlavor_text().replace("\n", " ").replace("\f", " ");
        }
    }

    private void initializeRequiredFields(PokemonDetailsResponseBody responseBody) {
        this.habitat = responseBody.getHabitat().getName();
        this.name = responseBody.getName();
        this.isLegendary = responseBody.getIs_legendary();
    }
}
