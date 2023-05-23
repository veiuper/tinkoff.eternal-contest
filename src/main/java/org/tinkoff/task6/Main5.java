package org.tinkoff.task6;

import java.util.Scanner;

import java.util.Scanner;

public class Main5 {
    public static void main(String[] args) {
        // Удалена обязательность четного количества учеников
        Object[] incomingParameters = getAndCheckIncomingParameters();
        int[] studentGrowth = (int[]) incomingParameters[1];
        int oddOnEvenPosition = 0;
        int evenOnOddPosition = 0;
        int numberOddsInEvenPosition = 0;
        int numberEvensInOddsPosition = 0;
        for (int i = 1; i <= studentGrowth.length; i++) {
            if (i % 2 == 0) {
                if (studentGrowth[i - 1] % 2 != 0) {
                    evenOnOddPosition = i;
                    numberEvensInOddsPosition++;
                }
            } else {
                if (studentGrowth[i - 1] % 2 == 0) {
                    oddOnEvenPosition = i;
                    numberOddsInEvenPosition++;
                }
            }
        }
        if (numberOddsInEvenPosition != 1 || numberEvensInOddsPosition != 1) {
            System.out.println("-1 -1");
        } else {
            System.out.println(Math.min(oddOnEvenPosition, evenOnOddPosition)
                    + " "
                    + Math.max(oddOnEvenPosition, evenOnOddPosition));
        }
    }

    private static Object[] getAndCheckIncomingParameters() {
        Object[] result = new Object[2];
        Scanner scanner = new Scanner(System.in);
        result[0] = getAndCheckNumberOfStudents(scanner);
        result[1] = getAndCheckStudentGrowth(scanner, (int) result[0]);
        return result;
    }

    private static int getAndCheckNumberOfStudents(final Scanner scanner) {
        int numberOfStudents = Integer.parseInt(scanner.nextLine());
        if (numberOfStudents < 2
                || numberOfStudents > 1000) {
            throw new NumberFormatException();
        }
        return numberOfStudents;
    }

    private static int[] getAndCheckStudentGrowth(final Scanner scanner, final int numberOfStudents) {
        int[] studentGrowth = new int[numberOfStudents];
        String line = scanner.nextLine();
        String[] strings = line.split(" ");
        int numberOfNonEmptyCharacters = 0;
        for (int i = 0; i < strings.length; i++) {
            if (!"".equals(strings[i])) {
                studentGrowth[numberOfNonEmptyCharacters] = Integer.parseInt(strings[i]);
                numberOfNonEmptyCharacters++;
            }
        }
        for (int curStudentGrowth : studentGrowth) {
            if (curStudentGrowth < 1 || curStudentGrowth > 1_000_000_000) {
                throw new NumberFormatException();
            }
        }

        return studentGrowth;
    }
}