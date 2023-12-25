package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColonDelimiter implements InputStrategy{
    private final String delimiter = ":";

    private String inputString;
    @Override
    public boolean isType(String input) {
        Matcher matcher = Pattern.compile("[0-9]*:[0-9]*").matcher(input);
        this.inputString = input;
        return matcher.find();
    }

    @Override
    public String getDelimiter() {
        return this.delimiter;
    }

    @Override
    public String getInput() {
        return this.inputString;
    }
}
