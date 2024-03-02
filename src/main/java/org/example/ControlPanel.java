package org.example;

import java.util.Scanner;

public class ControlPanel {
    private final static Scanner input = new Scanner(System.in);

    public static void welcome() {
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

    public static char getChoice() {
        System.out.println("""
                > Press S to start the game
                > Press V to view the highest score
                > Press H to help
                > Press Q to quit
                Enter your choice:
                 """);
        System.out.print("> ");
        return input.nextLine().charAt(0);
    }
}
