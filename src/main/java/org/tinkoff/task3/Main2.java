package org.tinkoff.task3;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        int result = 0;
        Object[] incomingParameters = getAndCheckIncomingParameters();
        int numberOfPeople = (int) incomingParameters[0];
        int time = (int) incomingParameters[1];
        int[] floorNumbers = (int[]) incomingParameters[2];
        int numberOfOutgoingEmployee = (int) incomingParameters[3];
        int maxDistance = floorNumbers[floorNumbers.length - 1] - floorNumbers[0];
        if (numberOfOutgoingEmployee == 1
                || numberOfOutgoingEmployee == floorNumbers.length
                || time > maxDistance
                || floorNumbers[numberOfOutgoingEmployee - 1] - floorNumbers[1] <= time
                || floorNumbers[floorNumbers.length - 1] - floorNumbers[numberOfOutgoingEmployee - 1] <= time) {
            result = maxDistance;
        } else {
            int sum1 = 2 * floorNumbers[floorNumbers.length - 1]
                    - floorNumbers[0] - floorNumbers[numberOfOutgoingEmployee - 1];
            int sum2 = floorNumbers[floorNumbers.length - 1]
                    - 2 * floorNumbers[0] + floorNumbers[numberOfOutgoingEmployee - 1];
            result = Math.min(sum1, sum2);
        }
        System.out.println(result);
    }

    public static int test(String[] args) {
        int result = 0;
        Scanner scanner = new Scanner(System.in);
        int numberOfPeople = Integer.parseInt(args[0]);
        int time = Integer.parseInt(args[1]);
        String stringFloorNumbers = args[2];
        int[] floorNumbers = new int[numberOfPeople];
        String[] strings = stringFloorNumbers.split(" ");
        int numberOfNonEmptyCharacters = 0;
        for (int i = 0; i < strings.length; i++) {
            if (!"".equals(strings[i])) {
                floorNumbers[numberOfNonEmptyCharacters] = Integer.parseInt(strings[i]);
                numberOfNonEmptyCharacters++;
            }
        }
        int numberOfOutgoingEmployee = Integer.parseInt(args[3]);
        int maxDistance = floorNumbers[floorNumbers.length - 1] - floorNumbers[0];
        if (numberOfOutgoingEmployee == 1
                || numberOfOutgoingEmployee == floorNumbers.length
                || time > maxDistance
                || floorNumbers[numberOfOutgoingEmployee - 1] - floorNumbers[1] <= time
                || floorNumbers[numberOfOutgoingEmployee - 1] - floorNumbers[floorNumbers.length - 1] <= time) {
            result = maxDistance;
        }
        return result;
    }

    private static Object[] getAndCheckIncomingParameters() {
        Object[] result = new Object[4];
        Scanner scanner = new Scanner(System.in);
        Object[] numberOfPeopleAndTime = getAndCheckNumberOfPeopleAndTime(scanner);
        result[0] = numberOfPeopleAndTime[0];
        result[1] = numberOfPeopleAndTime[1];
        result[2] = getAndCheckFloorNumbers(scanner, (int) result[0]);
        result[3] = getAndCheckNumberOfOutgoingEmployee(scanner, (int[]) result[2]);
        return result;
    }

    private static Object[] getAndCheckNumberOfPeopleAndTime(final Scanner scanner) {
        Object[] result = new Object[2];
        int numberOfPeople = 0;
        int time = 0;
        String firstLine = scanner.nextLine();
        String[] strings = firstLine.split(" ");
        int numberOfNonEmptyStrings = 0;
        for (String string : strings) {
            if (!"".equals(string)) {
                numberOfNonEmptyStrings++;
                if (numberOfNonEmptyStrings == 1) {
                    numberOfPeople = Integer.parseInt(string);
                } else if (numberOfNonEmptyStrings == 2) {
                    time = Integer.parseInt(string);
                } else {
                    break;
                }
            }
        }
        if (numberOfPeople < 2 || numberOfPeople > 100 ||
                time < 2 || time > 100) {
            throw new NumberFormatException();
        }
        result[0] = numberOfPeople;
        result[1] = time;
        return result;
    }

    private static int[] getAndCheckFloorNumbers(final Scanner scanner, final int numberOfPeople) {
        int[] floorNumbers = new int[numberOfPeople];
        String secondLine = scanner.nextLine();
        String[] strings = secondLine.split(" ");
        int numberOfNonEmptyCharacters = 0;
        for (int i = 0; i < strings.length; i++) {
            if (!"".equals(strings[i])) {
                floorNumbers[numberOfNonEmptyCharacters] = Integer.parseInt(strings[i]);
                numberOfNonEmptyCharacters++;
            }
        }
        int currentMaxFloorNumber = 0;
        for (int floorNumber : floorNumbers) {
            if (floorNumber < 1 || floorNumber > 100) {
                throw new NumberFormatException();
            }
            if (currentMaxFloorNumber <= floorNumber) {
                currentMaxFloorNumber = floorNumber;
            } else {
                throw new NumberFormatException();
            }
        }
        return floorNumbers;
    }

    private static int getAndCheckNumberOfOutgoingEmployee(final Scanner scanner, final int[] floorNumbers) {
        int numberOfOutgoingEmployee = scanner.nextInt();
        if (floorNumbers.length < numberOfOutgoingEmployee) {
            throw new NumberFormatException();
        }
        return numberOfOutgoingEmployee;
    }
}