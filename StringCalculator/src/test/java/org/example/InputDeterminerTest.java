package org.example;

import static org.junit.jupiter.api.Assertions.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class InputDeterminerTest {

    @Test
    void 콜론패턴매치테스트(){

        String input = "1:2:3";
        Matcher matcher = Pattern.compile("[0-9]*:[0-9]*").matcher(input);
        if(matcher.find()){
            Assertions.assertEquals(matcher.regionEnd(), input.length());
        }else{
            throw new RuntimeException();
        }
    }

    @Test
    void 콤마패턴매치테스트(){
        String input = "1,2,3";
        Matcher matcher = Pattern.compile("[0-9]*,[0-9]*").matcher(input);
        if(matcher.find()){
            Assertions.assertEquals(matcher.regionEnd(), input.length());
        }else{
            throw new RuntimeException();
        }
    }


    @Test
    void 커스텀구분자패턴매치테스트(){
        String input = "//;\n1;2;3;";
        Matcher matcher = Pattern.compile("//(.)\n(.*)").matcher(input);
        if(matcher.find()){
            String delimter = matcher.group(1);
            Assertions.assertEquals(delimter, ";");
        }else{
            throw new RuntimeException();
        }

    }

    @Test
    void 커스텀구분자이지만다른구분자에러테스트(){
        String input = "//;\n1!2!3!";
    }

    @Test
    void 구분자정의가없지만커스텀구분자에러테스트(){
        String input = "1;2;3;";
    }

    @Test
    void 여러구분자혼용된에러테스트(){
        String input = "//;\n1!2;3!";
    }

}