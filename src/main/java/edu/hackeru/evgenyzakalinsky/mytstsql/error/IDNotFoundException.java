package edu.hackeru.evgenyzakalinsky.mytstsql.error;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@NoArgsConstructor
public class IDNotFoundException extends RuntimeException {

    public IDNotFoundException(String message) {

        super (message);
    }

    public IDNotFoundException(long id) {

        super("Can't find a resource with ID: %d".formatted(id));
    }
}
