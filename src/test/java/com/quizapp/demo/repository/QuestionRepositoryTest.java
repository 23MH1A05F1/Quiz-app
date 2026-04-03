package com.quizapp.demo.repository;

import com.quizapp.demo.model.Question;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class QuestionRepositoryTest {

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    @DisplayName("Test findByLanguage - returns matching questions")
    void testFindByLanguage() {

        // Arrange (insert test data)
        Question q1 = new Question();
        q1.setQuestion("What is Java?");
        q1.setLanguage("Java");

        Question q2 = new Question();
        q2.setQuestion("What is Python?");
        q2.setLanguage("Python");

        Question q3 = new Question();
        q3.setQuestion("Explain OOP");
        q3.setLanguage("Java");

        questionRepository.save(q1);
        questionRepository.save(q2);
        questionRepository.save(q3);

        // Act
        List<Question> javaQuestions = questionRepository.findByLanguage("Java");

        // Assert
        assertThat(javaQuestions).isNotEmpty();
        assertThat(javaQuestions.size()).isEqualTo(2);
        assertThat(javaQuestions)
                .extracting(Question::getLanguage)
                .containsOnly("Java");
    }

    @Test
    @DisplayName("Test findByLanguage - returns empty when no match")
    void testFindByLanguage_NoResults() {

        // Arrange
        Question q1 = new Question();
        q1.setQuestion("What is C?");
        q1.setLanguage("C");

        questionRepository.save(q1);

        // Act
        List<Question> result = questionRepository.findByLanguage("Java");

        // Assert
        assertThat(result).isEmpty();
    }
}
