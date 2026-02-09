package org.example.service;

public class CommandParser {

    public static String[] split(String input) {
        if (input == null) return new String[0];
        return input.trim().split("\\s+");
    }
}
