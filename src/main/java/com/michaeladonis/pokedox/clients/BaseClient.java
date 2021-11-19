package com.michaeladonis.pokedox.clients;

import com.michaeladonis.pokedox.exceptions.NotFoundException;
import com.michaeladonis.pokedox.exceptions.ExternalServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class BaseClient {

    public WebClient.ResponseSpec handleRequest(WebClient.ResponseSpec responseSpec) {
        return responseSpec.
                onStatus(httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(new NotFoundException("Resource not found! 😥")))
                .onStatus(httpStatus -> httpStatus.value() == HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        clientResponse -> Mono.error(new ExternalServerException()));

    }
}
