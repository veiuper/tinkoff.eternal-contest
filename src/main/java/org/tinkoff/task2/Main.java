package org.tinkoff.task2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int result = 0;
        Scanner scanner = new Scanner(System.in);
        int numberOfPeople = scanner.nextInt();
        if (numberOfPeople < 1 || numberOfPeople > 2 * Math.pow(10, 9)) {
            throw new NumberFormatException();
        }
        result = (int) Math.ceil(Math.log(numberOfPeople) / Math.log(2));
        System.out.println(result);
    }
}