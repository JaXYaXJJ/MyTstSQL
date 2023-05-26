package edu.hackeru.evgenyzakalinsky.mytstsql.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@ControllerAdvice
public class IDNotFoundControllerAdvice {

    @ExceptionHandler(IDNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public Map<String, String> handleIDNotFoundException(
            IDNotFoundException e
    ) {
        return Map.of(
                "message", e.getMessage(),
                "error", e.getClass().getSimpleName()
        );
    }
}
