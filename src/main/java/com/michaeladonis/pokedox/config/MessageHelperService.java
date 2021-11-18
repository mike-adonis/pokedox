package com.michaeladonis.pokedox.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MessageHelperService {

    private final MessageSource messageSource;

    public String getMessage(String resource) {
        return messageSource.getMessage(resource, null, LocaleContextHolder.getLocale());
    }

}
