package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StringCalculatorTest {

    StringCalculator stringCalculator;

    @BeforeEach
    void setStringCalculator(){
        stringCalculator = new StringCalculator();
    }

    @Test
    void 콜론테스트(){
        String input = "1:2:3";
        Assertions.assertEquals(6, stringCalculator.add(input));
    }

    @Test
    void 콤마테스트(){
        String input = "1,2,3";
        Assertions.assertEquals(6, stringCalculator.add(input));
    }

    @Test
    void 커스텀테스트(){
        String input = "//;\n1;2;3;";
        Assertions.assertEquals(6, stringCalculator.add(input));
    }

    @Test
    void 음수에러테스트(){
        String input = "-1:-2:-3:";
        Assertions.assertThrows(RuntimeException.class, () -> stringCalculator.add(input));
    }

    @Test
    void Null테스트(){
        String input = null;
        Assertions.assertEquals(0, stringCalculator.add(input));
    }

    void 빈문자열테스트(){
        String input = "";
        Assertions.assertEquals(0, stringCalculator.add(input));
    }

    @Test
    void 커스텀구분자이지만다른구분자에러테스트(){
        String input = "//;\n1!2!3!";
        Assertions.assertThrows(RuntimeException.class, () -> stringCalculator.add(input));
    }

    @Test
    void 구분자정의가없지만커스텀구분자에러테스트(){
        String input = "1;2;3;";
        Assertions.assertThrows(RuntimeException.class, () -> stringCalculator.add(input));
    }

    @Test
    void 여러구분자혼용된에러테스트(){
        String input = "//;\n1!2;3!";
        Assertions.assertThrows(RuntimeException.class, () -> stringCalculator.add(input));
    }

}