package org.example;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StringCalculator {
    private final InputDeterminer inputDeterminer;


    // 여기서 DI를 하는 것은 당장은 과하지 않나. main에서 세부 구현을 모르게 하고 싶으며 그렇다고 DI를 주입하는 클래스를 따로 만드는 것은
    // 당장은 과한 것 같다.
    // 굳이 delimiter를 구분한 이유 : 다른 기준이 생기면 그에 맞는 InputStrategy를 구현하는 delimiter를 추가해주고 등록하면 됨.
    public StringCalculator() {
        List<InputStrategy> inputDeterminers = new LinkedList<>();
        inputDeterminers.add(new CommaDelimiter());
        inputDeterminers.add(new ColonDelimiter());
        inputDeterminers.add(new CustomDelimiter());
        this.inputDeterminer = new InputDeterminer(inputDeterminers);

    }

    public int add(String input){
        List<Integer> numbers = parse(input);
        int sum = 0;
        for (Integer number : numbers) {
            sum += number;
        }
        System.out.println("sum = " + sum);
        return sum;
    }

    // parser는 class를 따로 빼는게 좋을듯.
    private List<Integer> parse(String input){
        String[] stringNumbers = parseString(input);
        LinkedList<Integer> numbers = new LinkedList<>();
        for (String stringNumber : stringNumbers) {
            numbers.add(parseInt(stringNumber));
        }
        return numbers;
    }

    private String[] parseString(String input){
        Input inputType = inputDeterminer.getInputType(input);

        if (inputType == null){
            System.out.println("올바른 입력을 해주세요.");
            throw new RuntimeException();
        }

        return inputType.getInput().split(
                inputType.getDelimiter()
        );
    }



    private int parseInt(String number){
        int num = Integer.parseInt(number);
        if (num < 0)
            throw new RuntimeException();
        return num;
    }



}
