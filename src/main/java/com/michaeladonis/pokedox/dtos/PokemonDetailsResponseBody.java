package com.michaeladonis.pokedox.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public @Data
class PokemonDetailsResponseBody {

    private Habitat habitat;
    private String name;
    private Integer id;
    @JsonAlias("is_legendary")
    private Boolean isLegendary;

    @JsonAlias("flavor_text_entries")
    private List<FlavorTextEntriesItem> flavorTextEntriesItems;

    public @Data
    static class FlavorTextEntriesItem {

        @JsonAlias("flavor_text")
        private String flavorText;
    }

    public @Data
    static class Habitat {
        private String name;
    }
}