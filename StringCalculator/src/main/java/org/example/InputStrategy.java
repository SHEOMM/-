package org.example;

public interface InputStrategy {
    boolean isType(String input);

    String getDelimiter();

    String getInput();
}
