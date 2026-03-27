package com.quizapp.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.quizapp.demo.model.QuizResult;

public interface QuizResultRepository extends JpaRepository<QuizResult, Long> {

}
