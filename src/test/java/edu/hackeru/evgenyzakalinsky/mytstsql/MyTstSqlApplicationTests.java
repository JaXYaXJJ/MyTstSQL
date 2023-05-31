package edu.hackeru.evgenyzakalinsky.mytstsql;

import edu.hackeru.evgenyzakalinsky.mytstsql.dto.user.UserSignupResponseDto;
import edu.hackeru.evgenyzakalinsky.mytstsql.entity.User;
import edu.hackeru.evgenyzakalinsky.mytstsql.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //integration test = restTemplate
class MyTstSqlApplicationTests {

	@Autowired
	TestRestTemplate restTemplate;

	@Autowired
	UserRepository userRepository;

	//cleanup for MyTstSQLTests db:
	@BeforeEach
	public void cleanup() {
		userRepository.deleteAll();
	}

	@Test
	void contextLoads() {
		Assertions.assertThat(restTemplate).isNotNull();
	}

	@Test
	void postUser_whenUserIsValidReceiveOK() { //200

		User user = createValidUser();

		var response = restTemplate.postForEntity(
				API_V1_USERS, user, Object.class
		);

		Assertions
				.assertThat(response.getStatusCode())
				.isEqualTo(HttpStatus.OK);
	}

	@Test
	void postUser_whenUserIsValid_userSavedToDataBase() {

		User user = createValidUser();
		//controller saved user to db:
		restTemplate.postForEntity(
				API_V1_USERS, user, Object.class
		);

		Assertions
				.assertThat(userRepository.count())
				.isEqualTo(1L);
	}

	@Test
	void postsUser_whenUserIsValid_receiveSuccessMessage() {

		//message: success

		var user = createValidUser();
		var response =
				restTemplate.postForEntity(
						API_V1_USERS, user, UserSignupResponseDto.class
				);

		var dto = response.getBody();

		Assertions.assertThat(dto).isNotNull();
		Assertions.assertThat(dto.getMessage()).isNotNull();
	}

	@Test
	void postUser_whenUserIsValid_passwordIsHashedInDataBase() {

		var user = createValidUser();
		//saves the users to db:
		restTemplate.postForEntity(API_V1_USERS, user, UserSignupResponseDto.class);

		//after the user is saved...
		var users = userRepository.findAll();

		Assertions.assertThat(users).isNotNull();
		Assertions.assertThat(users.size()).isEqualTo(1);
		var userFromDb = users.get(0);

		Assertions.assertThat(userFromDb.getPassword())
				.isNotEqualTo(user.getPassword());
	}


	//helper methods:

	private static final String API_V1_USERS = "/api/v1/users/createUser";

	private User createValidUser() {

		User user = new User();

		user.setName("Kurt");
		user.setAge(27);
		user.setUsername("Nirvana");
		user.setEmail("kurt@mail.com");
		user.setPassword("a12Bc!34Df");
		user.setCountry("USA");

		return user;
	}
}
