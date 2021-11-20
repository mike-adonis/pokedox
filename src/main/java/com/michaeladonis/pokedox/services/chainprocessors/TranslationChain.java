package com.michaeladonis.pokedox.services.chainprocessors;

import com.michaeladonis.pokedox.dtos.PokemonDetailsResponse;

import static com.michaeladonis.pokedox.config.Constants.DOT_JSON;

public class TranslationChain {

    static Processor chain;

    private static void buildChain() {
        chain = new YodaTranslationProcessor(null);
    }

    public static String decideTranslation(PokemonDetailsResponse request) {
        buildChain();
        return chain.process(request).concat(DOT_JSON);
    }
}
