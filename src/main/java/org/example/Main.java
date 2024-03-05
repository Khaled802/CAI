package org.example;

import org.example.message.Message;
import org.example.message.RandomMessages;
import org.example.help.HelpRepositoryFile;
import org.example.score.UserScore;
import org.example.score.repository.ScoreRepository;
import org.example.score.repository.ScoreRepositoryFile;

import java.io.*;
import java.util.Optional;
import java.util.Scanner;

import static java.lang.StringTemplate.STR;
import static org.example.ControlPanel.*;

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
        ScoreRepository scoreRepository = new ScoreRepositoryFile();
        String name = askQuestion("Enter your name: ");
        int i = 0;
        while (!game.isAnsweredAllQuestions()) {
            System.out.println(++i + "-");
            var question = game.getQuestion();
            char ans = askQuestion(question.toString()).charAt(0);
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
        scoreRepository.addNewScore(new UserScore(name, game.getResult()));
    }

    private static void help() throws IOException {
        HelpRepositoryFile helpRepository = new HelpRepositoryFile();
        System.out.println(helpRepository.getHelp());
    }

    private static void getHighestScore() throws IOException {
        ScoreRepository scoreRepositoryFile = new ScoreRepositoryFile();
        Optional<UserScore> score = scoreRepositoryFile.getMaxScore();
        System.out.println("=================================");
        score.ifPresentOrElse(
                val -> System.out.println(STR. "\{ val.username() } has highest score: \{ val.score() }"),
                () -> System.out.println("No one takes the quiz"));
        System.out.println("=================================");
    }
}