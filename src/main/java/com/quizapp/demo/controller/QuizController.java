package com.quizapp.demo.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.quizapp.demo.model.Question;
import com.quizapp.demo.repository.QuestionRepository;

@Controller
public class QuizController {

    @Autowired
    private QuestionRepository questionRepository;

    // GET quiz for a specific language
    @GetMapping("/quiz/{language}")
    public String getQuiz(@PathVariable String language, Model model) {

        // Fetch all questions for the selected language
        List<Question> questions = questionRepository.findByLanguage(language);

        // Shuffle the list to randomize questions
        Collections.shuffle(questions);

        // Pick only the first 10 questions
        if (questions.size() > 10) {
            questions = questions.subList(0, 10);
        }

        model.addAttribute("questions", questions);
        model.addAttribute("language", language);

        return "quiz"; // quiz.html must exist
    }

    // POST handler to submit quiz answers
    @PostMapping("/submit-quiz")
    public String submitQuiz(@ModelAttribute QuizSubmission submission, Model model) {

        int score = 0;

        if (submission.getAnswers() != null) {
            for (Map.Entry<Long, Integer> entry : submission.getAnswers().entrySet()) {
                Long questionId = entry.getKey();
                Integer selectedOption = entry.getValue();

                // Fetch question
                Question q = questionRepository.findById(questionId).orElse(null);
                if (q != null && q.getAnswer() != null) {
                    // Compare with correct option (assuming getAnswer() returns the correct option number)
                    if (q.getAnswer().equals(selectedOption.toString())) {
                        score++;
                    }
                }
            }
        }

        model.addAttribute("score", score);
        model.addAttribute("total", submission.getAnswers() != null ? submission.getAnswers().size() : 0);

        return "result"; // result.html must exist
    }
}
