package edu.hackeru.evgenyzakalinsky.mytstsql.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "users",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})}
)
public class User {

    //validations in entity:
    //user pc/phone -> ui android/react -> backend controller -> db

    @NotNull
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 20, message = "Name should be between 2 amd 20 characters")
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
    @Size(min = 8)
//    @Pattern(
//            regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?\\W).{8,20}$",
//            message = "Password must contain at least 8 characters, " +
//                    "one or more lower case and uppercase letters, symbol and digits."
//    )
    private String password; //in real projects: we save the password one-way hash**

    @NotNull
    private String country;

    @CreationTimestamp
    private Date registered;
}
