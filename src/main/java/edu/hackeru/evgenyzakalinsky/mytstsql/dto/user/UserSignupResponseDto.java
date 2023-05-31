package edu.hackeru.evgenyzakalinsky.mytstsql.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSignupResponseDto {

    //no validations:
    private Long id;
    private String name;
    private String username;
    private String email;
    private String country;
    private Date registered;

    //for tests:
    private String message;

    //no password:
}
