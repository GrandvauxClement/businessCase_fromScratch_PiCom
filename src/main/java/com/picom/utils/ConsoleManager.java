package com.picom.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author ClementGrandvaux
 */

public class ConsoleManager {

    private Scanner scanner;

    private static ConsoleManager INSTANCE = null;

    private ConsoleManager() {
        scanner = new Scanner(System.in);
    }

    /**
     * Get the only instance of the ConsoleManager
     * @return {@linkplain ConsoleManager}
     */
    public static ConsoleManager getInstance() {
        if (INSTANCE == null){
            INSTANCE = new ConsoleManager();
        }
        return INSTANCE;
    }

    /**
     * Read user input from console as String
     *
     * @return a {@linkplain String}
     */

    public String readUserInput() {
        return scanner.nextLine();
    }

    /**
     * Read user input from console as String
     *
     * @return a {@linkplain Double}
     */
    public double readUserInputDouble() {
        return scanner.nextDouble();
    }

    /**
     * Read user input from console as Integer
     *
     * @return an {@linkplain Integer}
     */

    public Integer readUserInputInteger(){
        boolean answerRight = false;
        int answer = -1;

        do {
            try{
                answer = scanner.nextInt();
                answerRight = true;
            }catch (InputMismatchException e) {
                ConsoleManager.getInstance().printToConsoleError("Please try again", true);
            }
            scanner.nextLine();
        } while (!answerRight);
        return answer;
    }


    /**
     * Print to console
     *
     * @param text      the text to print
     * @param breakLine if a line break needs to be printed after the text
     */
    public void printToConsole(String text, boolean breakLine) {
        if (breakLine) {
            System.out.println(text);
        } else {
            System.out.print(text);
        }
    }

    public void printToConsole(String text) {
        printToConsole(text, true);
    }

    public void printTitle(){
        printLine();
        printToConsole("Welcome on your app");
        printToConsole("You can create the best cart of picnic");
        printLine();
    }
    public void printToConsoleError(String text, boolean breakLine) {
        if (breakLine) {
            System.err.println(text);
        } else {
            System.err.print(text);
        }
    }

    /**
     * Add a line break to the console
     */
    public void consoleLineBreak() {
        System.out.println();
    }

    public void closeScanner() {
        scanner.close();
    }

    public void printLine() {
        printToConsole("|----------------------------------------|", true);
    }

    public void printLineNoLineBreak() {
        printToConsole("-|----------------------------------------|", false);
    }

    public void printTwoLines() {
        printToConsole("|----------------------------------------|", true);
        printToConsole("|----------------------------------------|", true);
    }

}
