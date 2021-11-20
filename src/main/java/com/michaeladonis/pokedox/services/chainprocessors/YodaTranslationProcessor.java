package com.michaeladonis.pokedox.services.chainprocessors;

import com.michaeladonis.pokedox.dtos.PokemonDetailsResponse;

import static com.michaeladonis.pokedox.config.Constants.YODA;

class YodaTranslationProcessor extends Processor {
    public YodaTranslationProcessor(Processor processor) {
        super(processor);
    }

    public String process(PokemonDetailsResponse request) {
        if (request.getHabitat().equalsIgnoreCase("cave") || request.getIsLegendary()) {
            return YODA;
        } else {
            return super.process(request);
        }
    }
}