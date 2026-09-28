package com.ga.todo.repository;

import com.ga.todo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    // For Registration
    boolean existsByEmailAddress(String emailAddress);

    // For Login (You can use both findUserByEmailAddress or findByEmailAddress) both will return User Object
    User findUserByEmailAddress(String emailAddress);


}
