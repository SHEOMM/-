package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomDelimiter implements InputStrategy{

    private String delimiter;
    private String inputString;

    @Override
    public boolean isType(String input) {
        Matcher matcher = Pattern.compile("//(.)\n(.*)").matcher(input);
        if(matcher.find()){
            this.delimiter = matcher.group(1);
            this.inputString = matcher.group(2);
            return true;
        }
        return false;
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
