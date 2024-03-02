package org.example;

import org.example.question.Question;
import org.example.question.QuestionGenerators;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Stream;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private final static Scanner input = new Scanner(System.in);

    private static void welcome() {
        System.out.println("""
                -----------------------------------
                             WELCOME
                               to
                           THE EDU GAME
                -----------------------------------
                     Do you want to master math
                -----------------------------------
                 """);
    }

    private static char getChoice() {
        System.out.println("""
                > Press S to start the game
                > Press V to view the highest score
                > Press H to help
                > Press Q to quit
                Enter your choice:
                 """);
        return input.nextLine().charAt(0);
    }

    public static void main(String[] args) throws IOException {
        switch (Character.toUpperCase(getChoice())) {
            case 'S' -> startGame();
            case 'V' -> getHighestScore();
            case 'H' -> help();
            case 'Q' -> {
            }
        }
    }

    private static void startGame() {
        Game game = new Game();
        while (!game.isAnsweredAllQuestions()) {
            var question = game.getQuestion();
            System.out.println(question);
            char ans = input.nextLine().charAt(0);
            boolean isCorrect = game.answer(question, ans);
            System.out.println(isCorrect ? "correct" : "wrong");
        }
    }

    private static void help() throws IOException {
        FileReader fileReader = new FileReader(
                Paths.get("src", "main", "java", "org", "example", "data", "help.txt").toString());
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
        bufferedReader.close();
    }

    private static void getHighestScore() throws IOException {
        FileReader fileReader = new FileReader(Paths.get("src", "main", "java", "org", "example", "data", "scores.txt").toString());
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String[] maxScorer = Stream.generate(() -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).takeWhile(Objects::nonNull).map(ele -> ele.split(":")).max(
                Comparator.comparingInt(ele -> Integer.parseInt(ele[1]))).orElse(new String[]{"N/A", "0"});
        System.out.printf("%s has highest score: %s\n", maxScorer[0], maxScorer[1]);
    }
}