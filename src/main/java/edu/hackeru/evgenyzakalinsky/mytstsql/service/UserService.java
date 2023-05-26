package edu.hackeru.evgenyzakalinsky.mytstsql.service;

import edu.hackeru.evgenyzakalinsky.mytstsql.dto.user.UserSignupRequestDto;
import edu.hackeru.evgenyzakalinsky.mytstsql.dto.user.UserSignupResponseDto;
import edu.hackeru.evgenyzakalinsky.mytstsql.entity.User;
import edu.hackeru.evgenyzakalinsky.mytstsql.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    //props:
    private final UserRepository userRepository;

    //before use model mapper or mapstruct:
    //dto -> entity -> save entity to db -> response dto
    //a. request dto -> user entity:
    //b. save entity to db:
    //c. convert the entity to response object:
    //d. return the response mapped object:

    //model mapper request dto -> user -> response dto
    private final ModelMapper mapper; //shared beans -> bean for model mapper:

    public UserSignupResponseDto registerUser(UserSignupRequestDto requestDto) {

        //a. request dto -> user entity:
        var user = mapper.map(requestDto, User.class);

        //b. save entity to db:
        var saved = userRepository.save(user);

        //c. convert the entity to response object + return the response mapped object:
        return mapper.map(saved, UserSignupResponseDto.class);
    }
}
