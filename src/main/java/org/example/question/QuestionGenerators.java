package org.example.question;

import java.util.*;

public class QuestionGenerators {
    private static final Random random = new Random();
    private static final Bound EASY_BOUND = new Bound(0, 10);
    private static final Bound MEDIUM_BOUND = new Bound(10, 100);
    private static final Bound HARD_BOUND = new Bound(100, 1000);

    private static Question generateOnLevel(Level level) {
        int max = switch (level) {
            case EASY -> EASY_BOUND.max();
            case MEDIUM -> MEDIUM_BOUND.max();
            case HARD -> HARD_BOUND.max();
        };
        int min = switch (level) {
            case EASY -> EASY_BOUND.min();
            case MEDIUM -> MEDIUM_BOUND.min();
            case HARD -> HARD_BOUND.min();
        };

        int num1 = random.nextInt(min, max);
        int num2 = random.nextInt(min, max);
        List<Integer> choices = new ArrayList<>();
        choices.add(num1 * num2);
        for (int i = 0; i < 3; i++) {
            choices.add(random.nextInt(min*min,(max-1)*(max-1)+1));
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
record Bound(int min, int max) {};
