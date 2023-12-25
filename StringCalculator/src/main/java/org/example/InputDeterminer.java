package org.example;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class InputDeterminer {
    private final List<InputStrategy> inputStrategies;

    public InputDeterminer(List<InputStrategy> inputStrategies) {
        this.inputStrategies = inputStrategies;
    }

    public Input getInputType(String input){

        for (InputStrategy inputStrategy : inputStrategies) {
            if(inputStrategy.isType(input))
                return new Input(inputStrategy.getDelimiter(), inputStrategy.getInput());
        }
        return null;
    }

}
