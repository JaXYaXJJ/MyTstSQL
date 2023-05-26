package edu.hackeru.evgenyzakalinsky.mytstsql.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;
import java.util.Map;

@ControllerAdvice
public class GlobalErrorControllerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) //500
    public Map<String, String> handleUnknownException(Exception e) {

        //catch all
        //log, send sms, log server
        return Map.of(
                "message", e.getMessage(),
                "timestamp", new Date().toString()
        );
    }
}
