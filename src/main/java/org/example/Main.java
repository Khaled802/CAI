package org.example;

import org.example.message.Message;
import org.example.message.RandomMessages;
import org.example.question.Question;
import org.example.question.QuestionGenerators;

import java.io.*;
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

    public static void main(String[] args) throws IOException, InterruptedException {
        switch (Character.toUpperCase(getChoice())) {
            case 'S' -> startGame();
            case 'V' -> getHighestScore();
            case 'H' -> help();
            case 'Q' -> {
            }
        }
    }

    private static void startGame() throws IOException, InterruptedException {
        Game game = new Game();
        System.out.println("Enter your name: ");
        String name = input.nextLine();
        int i = 0;
        while (!game.isAnsweredAllQuestions()) {
            System.out.println(++i + "-");
            var question = game.getQuestion();
            System.out.println(question);
            char ans = input.nextLine().charAt(0);
            boolean isCorrect = game.answer(question, ans);
            System.out.println(
                    isCorrect ? RandomMessages.getMessage(Message.SUCCESS) : RandomMessages.getMessage(Message.FAILED));
            System.out.println("Hit Enter to continue");
            input.nextLine();
            switch (OperatingSystem.getOperatingSystem()) {
                case WINDOWS -> new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                case LINUX -> System.out.print("\033\143");
            }
        }
        System.out.println("Your Result: %" + game.getResult());
        System.out.println(
                game.getResult() >= 75 ? "Congratulations, you are ready to go to the next level!" : " Please ask your teacher for extra help.");
        FileWriter fileWriter = new FileWriter(
                Paths.get("src", "main", "java", "org", "example", "data", "scores.txt").toString(), true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(name);
        bufferedWriter.write(":");
        bufferedWriter.write(Double.toString(game.getResult()));
        bufferedWriter.newLine();
        bufferedWriter.flush();
        fileWriter.close();
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
        FileReader fileReader = new FileReader(
                Paths.get("src", "main", "java", "org", "example", "data", "scores.txt").toString());
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String[] maxScorer = Stream.generate(() -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).takeWhile(Objects::nonNull).map(ele -> ele.split(":")).max(
                Comparator.comparingInt(ele -> Integer.parseInt(ele[1]))).orElse(new String[]{"N/A", "0"});
        fileReader.close();
        System.out.println("=================================");
        System.out.printf("%s has highest score: %s\n", maxScorer[0], maxScorer[1]);
        System.out.println("=================================");
    }
}