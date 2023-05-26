package edu.hackeru.evgenyzakalinsky.mytstsql.dto.user;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSignupRequestDto {

    //props:
    //validation in request dto:

    @NotNull
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 20, message = "Name should be between 2 and 20 characters")
    private String name;

    @NotNull
    @Min(value = 0, message = "Age should be greater than 0")
    private int age;

    @NotNull
    @Size(min = 2, max = 25)
    private String username;

    @NotNull
    @NotEmpty(message = "Email should not be empty")
    @Email(
            regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",
            message = "Must be a valid email."
    )
    private String email;

    @NotNull
    @Size(min = 6)
    @Pattern(
            regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?\\W).{8,20}$",
            message = "Password must contain at least 8 characters, " +
                    "one or more lower case and uppercase letters, symbol and digits."
    )
    private String password;

    @NotNull
    private String country;
}
