package com.michaeladonis.pokedox.dtos;

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
    private Boolean is_legendary;
    private List<FlavorTextEntriesItem> flavor_text_entries;

    public @Data
    static class FlavorTextEntriesItem {
        private String flavor_text;
    }

    public @Data
    static class Habitat {
        private String name;
    }
}