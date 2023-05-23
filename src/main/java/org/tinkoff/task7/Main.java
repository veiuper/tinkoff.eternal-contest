package org.tinkoff.task7;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Object[] incomingParameters = getAndCheckIncomingParameters();
        int[] students = (int[]) incomingParameters[1];
        int count = 0;
        int numberOfStudent = 0;
        int newValue = 0;
        for (int i = 0; i < students.length; i++) {
            if (i == students.length - 1) {
                if (students[i] != 1) {
                    count++;
                    numberOfStudent = students.length;
                    newValue = 1;
                }
            } else {
                if (students[i] != i + 1 + 1) {
                    count++;
                    numberOfStudent = i + 1;
                    newValue = i + 2;
                }
            }
        }
        if (count == 1) {
            System.out.println(numberOfStudent + " " + newValue);
        } else {
            System.out.println("-1 -1");
        }
    }

    private static Object[] getAndCheckIncomingParameters() {
        Object[] result = new Object[2];
        Scanner scanner = new Scanner(System.in);
        result[0] = getAndCheckNumberOfStudents(scanner);
        result[1] = getAndCheckStudents(scanner, (int) result[0]);
        return result;
    }

    private static int getAndCheckNumberOfStudents(final Scanner scanner) {
        int numberOfStudents = Integer.parseInt(scanner.nextLine());
        if (numberOfStudents < 2
                || numberOfStudents > 100_000) {
            throw new NumberFormatException();
        }
        return numberOfStudents;
    }

    private static int[] getAndCheckStudents(final Scanner scanner, final int numberOfStudents) {
        int[] students = new int[numberOfStudents];
        String line = scanner.nextLine();
        String[] strings = line.split(" ");
        int numberOfNonEmptyCharacters = 0;
        for (int i = 0; i < strings.length; i++) {
            if (!"".equals(strings[i])) {
                students[numberOfNonEmptyCharacters] = Integer.parseInt(strings[i]);
                numberOfNonEmptyCharacters++;
            }
        }
        for (int student : students) {
            if (student > numberOfStudents || student < 1) {
                throw new NumberFormatException();
            }
        }

        return students;
    }
}