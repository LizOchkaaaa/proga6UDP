package org.example.server;

import java.util.Scanner;

public class InputServerReader {
    private static Scanner inputReader = new Scanner(System.in);

    public static Scanner getInputReader() {
        return inputReader;
    }

}
