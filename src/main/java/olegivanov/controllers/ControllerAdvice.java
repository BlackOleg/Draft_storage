package olegivanov.controllers;

import olegivanov.exceptions.FileException;
import olegivanov.exceptions.InputDataException;
import olegivanov.exceptions.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@CrossOrigin(origins = "http://localhost:8080")
@RestControllerAdvice
public class ControllerAdvice {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InputDataException.class)
    public String HandlerInputData (InputDataException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public String HandlerUnauthorized (UnauthorizedException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(FileException.class)
    public String HandlerFile (FileException ex) {
        return ex.getMessage();
    }

}
