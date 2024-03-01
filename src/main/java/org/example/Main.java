package org.example;

import org.example.question.Question;
import org.example.question.QuestionGenerators;

import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private final static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            Question question = QuestionGenerators.generateEasy();
            System.out.println(question);
            System.out.println("enter your answer");
            char ans = Character.toUpperCase(input.nextLine().charAt(0));
            System.out.println(question.isGuessCorrect(ans) ? "Correct" : "Wrong");
        }
    }
}