package com.michaeladonis.pokedox.advice;

import com.michaeladonis.pokedox.dtos.DataResponse;
import com.michaeladonis.pokedox.exceptions.NotFoundException;
import com.michaeladonis.pokedox.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.michaeladonis.pokedox.dtos.DataResponse.*;

@ControllerAdvice
public class ExceptionManager extends ResponseEntityExceptionHandler {

    @ExceptionHandler({NotFoundException.class, WebClientResponseException.NotFound.class})
    public ResponseEntity<DataResponse> handleNotFoundException(NotFoundException exception) {
        return new ResponseEntity<>(getErrorResponse(exception), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<DataResponse> handleValidationException(ValidationException exception) {
        return new ResponseEntity<>(getErrorResponse(exception), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<DataResponse> handleUnknownException(RuntimeException exception) {
        exception.printStackTrace();
        return new ResponseEntity<>(new DataResponse(false, "An Error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(WebClientResponseException.Forbidden.class)
    public ResponseEntity<DataResponse> handleExternalServerException(RuntimeException exception) {
        exception.printStackTrace();
        return new ResponseEntity<>(new DataResponse(false, "A foreign server threw an error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}