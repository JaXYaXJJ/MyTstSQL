package edu.hackeru.evgenyzakalinsky.mytstsql;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class MyTstSqlApplication {

	public static void main(String[] args) {

		SpringApplication.run(MyTstSqlApplication.class, args);
	}
}
