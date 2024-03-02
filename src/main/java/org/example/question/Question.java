package org.example.question;

import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class Question {
    private final int num1;
    private final int num2;
    private final List<Integer> choices;

    public Question(int num1, int num2, List<Integer> choices) {
        this.num1 = num1;
        this.num2 = num2;
        this.choices = choices.stream().toList();
    }
    public boolean isGuessCorrect(int ans) {
        return ans == num1 * num2;
    }
    public boolean isGuessCorrect(char ans) {
        ans = Character.toUpperCase(ans);
        return choices.get(ans - 'A') == num1 * num2;
    }
    public String getChoicesFormatted() {
        StringBuilder str = new StringBuilder();
        for (int i = 0, end = choices.size() / 2; i < end; i++) {
            int letter1 = (i * 2);
            int letter2 = (i * 2 + 1);
            str.append((char) (letter1 + 'A'))
                    .append(".")
                    .append(choices.get(letter1))
                    .append("     ")
                    .append((char) (letter2 + 'A'))
                    .append(".")
                    .append(choices.get(letter2))
                    .append("\n");
        }
        return str.toString();
    }

    @Override
    public String toString() {
        return String.format("How mush is %d times %d?\n", num1, num2) +
                getChoicesFormatted();
    }
}
