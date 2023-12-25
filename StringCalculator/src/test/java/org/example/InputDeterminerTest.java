package org.example;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InputDeterminerTest {

    InputDeterminer inputDeterminer;

    @BeforeEach
    void setInputDeterminer(){
        List<InputStrategy> inputDeterminers = new LinkedList<>();
        inputDeterminers.add(new CommaDelimiter());
        inputDeterminers.add(new ColonDelimiter());
        inputDeterminers.add(new CustomDelimiter());
        this.inputDeterminer = new InputDeterminer(inputDeterminers);
    }

    @Test
    void 콜론패턴매치테스트(){

        String input = "1:2:3";
        Assertions.assertEquals(":", this.inputDeterminer.getInputType(input));

    }

    @Test
    void 콤마패턴매치테스트(){
        String input = "1,2,3";
        Assertions.assertEquals(",", this.inputDeterminer.getInputType(input));
    }


    @Test
    void 커스텀구분자패턴매치테스트(){
        String input = "//;\n1;2;3;";
        Assertions.assertEquals(";", this.inputDeterminer.getInputType(input));

    }



}