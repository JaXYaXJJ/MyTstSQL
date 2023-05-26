package edu.hackeru.evgenyzakalinsky.mytstsql.repository;

import edu.hackeru.evgenyzakalinsky.mytstsql.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
