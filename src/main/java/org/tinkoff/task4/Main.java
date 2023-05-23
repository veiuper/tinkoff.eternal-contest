package org.tinkoff.task4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        long result = 0;
        Object[] incomingParameters = getAndCheckIncomingParameters();
        int amountOfNumbers = (int) incomingParameters[0];
        int numberOfReplacementOperations = (int) incomingParameters[1];
        int[] numbers = (int[]) incomingParameters[2];
        List<Integer> costs = new ArrayList<>();
        int denominator = 10;
        for (int i = 0; i < amountOfNumbers; i++) {
            int rank = 0;
            int tmpNumber = numbers[i];
            while (tmpNumber != 0) {
                int remainderOfDivision = tmpNumber % denominator;
                tmpNumber = tmpNumber / denominator;
                int curCost = (int) ((9 - remainderOfDivision) * Math.pow(10, rank));
                if (curCost != 0) {
                    costs.add(curCost);
                }
                rank++;
            }
        }
        costs.sort(Collections.reverseOrder());
        for (int i = 0; i < numberOfReplacementOperations && i < costs.size(); i++) {
            result += costs.get(i);
        }
        System.out.println(result);
    }

    private static Object[] getAndCheckIncomingParameters() {
        Object[] result = new Object[3];
        Scanner scanner = new Scanner(System.in);
        Object[] amountOfNumbersAndNumberOfReplacementOperations
                = getAndCheckAmountOfNumbersAndNumberOfReplacementOperations(scanner);
        result[0] = amountOfNumbersAndNumberOfReplacementOperations[0];
        result[1] = amountOfNumbersAndNumberOfReplacementOperations[1];
        result[2] = getAndCheckNumbers(scanner, (int) result[0]);
        return result;
    }

    private static Object[] getAndCheckAmountOfNumbersAndNumberOfReplacementOperations(final Scanner scanner) {
        Object[] result = new Object[2];
        int amountOfNumbers = 0;
        int numberOfReplacementOperations = 0;
        String firstLine = scanner.nextLine();
        String[] strings = firstLine.split(" ");
        int numberOfNonEmptyStrings = 0;
        for (String string : strings) {
            if (!"".equals(string)) {
                numberOfNonEmptyStrings++;
                if (numberOfNonEmptyStrings == 1) {
                    amountOfNumbers = Integer.parseInt(string);
                } else if (numberOfNonEmptyStrings == 2) {
                    numberOfReplacementOperations = Integer.parseInt(string);
                } else {
                    break;
                }
            }
        }
        if (amountOfNumbers < 1 || amountOfNumbers > 1000 ||
                numberOfReplacementOperations < 1 || numberOfReplacementOperations > 10000) {
            throw new NumberFormatException();
        }
        result[0] = amountOfNumbers;
        result[1] = numberOfReplacementOperations;
        return result;
    }

    private static int[] getAndCheckNumbers(final Scanner scanner, final int amountOfNumbers) {
        int[] numbers = new int[amountOfNumbers];
        String secondLine = scanner.nextLine();
        String[] strings = secondLine.split(" ");
        int amountOfNonEmptyCharacters = 0;
        for (String string : strings) {
            if (!"".equals(string)) {
                numbers[amountOfNonEmptyCharacters] = Integer.parseInt(string);
                amountOfNonEmptyCharacters++;
            }
        }
        for (int number : numbers) {
            if (number < 1 || number > Math.pow(10, 9)) {
                throw new NumberFormatException();
            }
        }
        return numbers;
    }
}