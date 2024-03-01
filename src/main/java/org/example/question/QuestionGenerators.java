package org.example.question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QuestionGenerators {
    private static final Random random = new Random();
    private static final int MAX_EASY = 10;
    private static final int MAX_MEDIUM = 100;
    private static final int MAX_HARD = 1000;
    private static Question generateOnLevel(Level level) {
        int max = switch (level) {
            case EASY -> MAX_EASY;
            case MEDIUM -> MAX_MEDIUM;
            case HARD -> MAX_HARD;
        };

        int num1 = random.nextInt(max);
        int num2 = random.nextInt(max);
        List<Integer> choices = new ArrayList<>();
        choices.add(num1 * num2);
        for (int i = 0; i < 3; i++) {
            choices.add(random.nextInt((max-1)*(max-1)+1));
        }
        Collections.shuffle(choices);
        return new Question(num1, num2, choices);
    }
    public static Question generateEasy() {
        return generateOnLevel(Level.EASY);
    }

    public static Question generateMedium() {
        return generateOnLevel(Level.MEDIUM);
    }

    public static Question generateHard() {
        return generateOnLevel(Level.HARD);
    }
}
