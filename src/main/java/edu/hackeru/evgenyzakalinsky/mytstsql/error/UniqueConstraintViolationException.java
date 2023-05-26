package edu.hackeru.evgenyzakalinsky.mytstsql.error;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST) //400 status code:
@NoArgsConstructor
public class UniqueConstraintViolationException extends RuntimeException {

    //checked exceptions: extend Exception
    //try/catch הכומפיילר מכריח לעטוף את הקוד ב
    //throws או להכריז שהמתודה

    //non-checked exceptions: extends RuntimeException
    //שגיאות זמן ריצה - לא יייבים להכריז שהמתודה זורקת שגיאה

    //constructor that calls super(message):
    public UniqueConstraintViolationException(String message) {
        super(message);
    }
}
