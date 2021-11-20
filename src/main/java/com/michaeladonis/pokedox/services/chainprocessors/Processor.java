package com.michaeladonis.pokedox.services.chainprocessors;

import com.michaeladonis.pokedox.dtos.PokemonDetailsResponse;

import static com.michaeladonis.pokedox.config.Constants.SHAKESPEARE;

abstract class Processor {

    private Processor processor;

    public Processor(Processor processor) {
        this.processor = processor;
    }

    public String process(PokemonDetailsResponse request) {
        if (processor != null) {
            return processor.process(request);
        } else return SHAKESPEARE;
    }
}