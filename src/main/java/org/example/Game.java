package org.example;

import org.example.question.Level;
import org.example.question.Question;
import org.example.question.QuestionGenerators;

public class Game {
    public final int NUMBER_OF_QUESTIONS = 20;
    private int answered = 0;
    private Level curLevel = Level.EASY;
    private int levelInRow = 0;
    private int answeredCorrectly = 0;

    public Question getQuestion() {
        return switch (curLevel) {
            case EASY -> QuestionGenerators.generateEasy();
            case MEDIUM -> QuestionGenerators.generateMedium();
            case HARD -> QuestionGenerators.generateHard();
        };
    }

    public boolean answer(Question question, char choice) {
        boolean correct = question.isGuessCorrect(choice);
        if (!correct) {
            levelInRow = 0;
            return false;
        }
        answered++;
        answeredCorrectly++;
        if (++levelInRow == 3) {
            upgradeLevel();
            levelInRow = 0;
        }
        return true;
    }

    public void upgradeLevel() {
        if (curLevel == Level.EASY) {
            curLevel = Level.MEDIUM;
        } else if (curLevel == Level.MEDIUM) {
            curLevel = Level.HARD;
        }
    }
    public boolean isAnsweredAllQuestions() {
        return answered == NUMBER_OF_QUESTIONS;
    }
}
