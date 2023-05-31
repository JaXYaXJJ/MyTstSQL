package edu.hackeru.evgenyzakalinsky.mytstsql.controller;

import edu.hackeru.evgenyzakalinsky.mytstsql.dto.user.UserSignupRequestDto;
import edu.hackeru.evgenyzakalinsky.mytstsql.dto.user.UserSignupResponseDto;
import edu.hackeru.evgenyzakalinsky.mytstsql.entity.User;
import edu.hackeru.evgenyzakalinsky.mytstsql.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    //props:
    private final UserService userService;

    //register:
    @PostMapping
    public ResponseEntity<UserSignupResponseDto>
            //@Valid for validations
            register(@Valid @RequestBody UserSignupRequestDto dto) {

        //a. save the user
        var responseDto = userService.registerUser(dto);

        //b. location/endpoint of new user in our api:
        var uri = URI.create("/api/v1/users/" + responseDto.getId());

        //c. return response entity (created = 201), body = dto
        return ResponseEntity.created(uri).body(responseDto);
    }

    //for tests:
    @PostMapping("/createUser")
    public UserSignupResponseDto createUser(@RequestBody User user) {

        userService.saveUser(user);

        return UserSignupResponseDto.builder().message("User Saved").build();
    }
}
