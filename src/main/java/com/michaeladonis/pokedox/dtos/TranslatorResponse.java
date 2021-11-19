package com.michaeladonis.pokedox.dtos;

import lombok.Data;

public @Data
class TranslatorResponse {
    private Contents contents;
    private Success success;

    public TranslatorResponse(String textToTranslate) {
        this.contents = new Contents(textToTranslate);
    }

    public @Data
    static class Success {
        private int total;
    }

    public @Data
    static class Contents {
        public Contents(String translation) {
            this.translation = translation;
        }

        private String translation;
        private String translated;
        private String text;
    }
}