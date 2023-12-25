package org.example;

public class Input {
    private final String delimiter;
    private final String input;

    public Input(String delimiter, String input) {
        this.delimiter = delimiter;
        this.input = input;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public String getInput() {
        return input;
    }
}
