package org.tinkoff.task1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int tariffCost = 0;
        int tariffSize = 0;
        int costOfExcess = 0;
        int internetTrafficSize = 0;
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        String[] strings = string.split(" ");
        int count = 0;
        for (int i = 0; i < strings.length; i++) {
            if (!"".equals(strings[i])) {
                count++;
                if (count == 1) {
                    tariffCost = Integer.parseInt(strings[i]);
                } else if (count == 2) {
                    tariffSize = Integer.parseInt(strings[i]);
                } else if (count == 3) {
                    costOfExcess = Integer.parseInt(strings[i]);
                } else if (count == 4) {
                    internetTrafficSize = Integer.parseInt(strings[i]);
                } else {
                    break;
                }
            }
        }
        if (tariffCost < 1 || tariffCost > 100 ||
                tariffSize < 1 || tariffSize > 100 ||
                costOfExcess < 1 || costOfExcess > 100 ||
                internetTrafficSize < 1 || internetTrafficSize > 100) {
            throw new NumberFormatException();
        }
        long result = tariffCost;
        int exceedingTariff = internetTrafficSize - tariffSize;
        if (exceedingTariff > 0) {
            result += exceedingTariff * costOfExcess;
        }
        System.out.println(result);
    }
}