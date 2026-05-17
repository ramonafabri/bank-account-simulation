package com.ramonafabri.projects.ui;

import java.util.Scanner;

public class ConsoleUI implements UI {

    private final Scanner scanner;

    public ConsoleUI() {
        scanner = new Scanner(System.in);
    }

    @Override
    public String getCommand() {
        return scanner.nextLine();
    }

    @Override
    public void printMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void close() {
        scanner.close();
    }

}
