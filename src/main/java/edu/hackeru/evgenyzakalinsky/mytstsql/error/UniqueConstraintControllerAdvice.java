package edu.hackeru.evgenyzakalinsky.mytstsql.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@ControllerAdvice
public class UniqueConstraintControllerAdvice {

    //אם נזרקת שריאה מסוג מסויים - מה להחזיר בתגובה

    @ExceptionHandler(UniqueConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, String> handleUniqueConstraintViolationException(
            UniqueConstraintViolationException e
    ) {
        return Map.of("message", e.getMessage());
    }
}
