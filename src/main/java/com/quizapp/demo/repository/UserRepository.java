package com.quizapp.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.quizapp.demo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
