package org.example;

import org.example.message.Message;
import org.example.message.RandomMessages;

import java.io.*;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Stream;

import static org.example.ControlPanel.getChoice;
import static org.example.ControlPanel.welcome;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private final static Scanner input = new Scanner(System.in);


    public static void main(String[] args) throws IOException, InterruptedException {
        welcome();

        boolean again = true;
        while (again) {
            switch (Character.toUpperCase(getChoice())) {
                case 'S' -> startGame();
                case 'V' -> getHighestScore();
                case 'H' -> help();
                case 'Q' -> again = false;
            }
            System.out.println("Press Enter to continue...");
            input.nextLine();

        }
    }

    private static void startGame() throws IOException, InterruptedException {
        Game game = new Game();
        System.out.println("Enter your name: ");
        System.out.print("> ");
        String name = input.nextLine();
        int i = 0;
        while (!game.isAnsweredAllQuestions()) {
            System.out.println(++i + "-");
            var question = game.getQuestion();
            System.out.println(question);
            System.out.print("> ");
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
                Comparator.comparingDouble(ele -> Double.parseDouble(ele[1]))).orElse(new String[]{"N/A", "0"});
        fileReader.close();
        System.out.println("=================================");
        System.out.printf("%s has highest score: %s\n", maxScorer[0], maxScorer[1]);
        System.out.println("=================================");
    }
}