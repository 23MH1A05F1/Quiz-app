package com.quizapp.demo.controller;

import java.util.Map;

public class QuizSubmission {

    // Map where key = questionId, value = selected option index
    private Map<Long, Integer> answers;

    // Getter
    public Map<Long, Integer> getAnswers() {
        return answers;
    }

    // Setter
    public void setAnswers(Map<Long, Integer> answers) {
        this.answers = answers;
    }
}
