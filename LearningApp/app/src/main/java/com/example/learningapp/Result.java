package com.example.learningapp;

import java.io.Serializable;

public class Result implements Serializable, DisplayableItem {
    private String Title;

    private String Description;
    private boolean Correct;

    public Result(Quiz quiz, String selection) {
        Title = quiz.getQuestion();
        Correct = (quiz.getCorrectAnswer().equals(selection));
        StringBuilder sb = new StringBuilder();
        if (Correct) {
            sb.append("Great Job! You chose the correct answer (");
            sb.append(quiz.getCorrectAnswer());
            sb.append("): ");
            sb.append(quiz.getOptions()[optionToIndex(quiz.getCorrectAnswer())]);
        } else {
            sb.append("You chose the wrong answer (");
            sb.append(selection);
            sb.append(").\nThe correct answer is (");
            sb.append(quiz.getCorrectAnswer());
            sb.append("):");
            sb.append(quiz.getOptions()[optionToIndex(quiz.getCorrectAnswer())]);
        }
        Description = sb.toString();
    }
    @Override
    public String getTitle() {
        return Title;
    }

    @Override
    public String getDescription() {
        return Description;
    }


    // Unused methods
    @Override
    public String getQuestion() {
        return null;
    }

    @Override
    public String[] getOptions() {
        return new String[0];
    }

    private int optionToIndex(String option) {
        switch (option) {
            case "A": return 0;
            case "B": return 1;
            case "C": return 2;
            case "D": return 3;
            default: return -1;
        }
    }
}
