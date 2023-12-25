package org.example;

import java.util.LinkedList;
import java.util.List;

public class InputDeterminer {
    private final List<InputStrategy> inputStrategies;

    public InputDeterminer(List<InputStrategy> inputStrategies) {
        this.inputStrategies = inputStrategies;
    }

    public String getInputType(String input){

        for (InputStrategy inputStrategy : inputStrategies) {
            if(inputStrategy.isType(input))
                return inputStrategy.getDelimiter();
        }
        return null;
    }

}
