package com.michaeladonis.pokedox.clients;

import com.michaeladonis.pokedox.exceptions.ExternalServerException;
import com.michaeladonis.pokedox.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class BaseClient {

    public WebClient.ResponseSpec handleRequest(WebClient.ResponseSpec responseSpec, String exceptionMessage) {
        return responseSpec.
                onStatus(httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(new NotFoundException(exceptionMessage)))
                .onStatus(httpStatus -> httpStatus.value() == HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        clientResponse -> Mono.error(new ExternalServerException()));

    }
}
