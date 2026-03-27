package com.quizapp.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.quizapp.demo.model.Question;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByLanguage(String language);

}
