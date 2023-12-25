package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StringCalculator stringCalculator = new StringCalculator();
        Scanner sc = new Scanner(System.in);
        System.out.println("문자열을 입력해주세요.");
        String input = sc.next();
        while(!input.equals("exit")){
            stringCalculator.add(input);
            input = sc.next();
        }
    }
}