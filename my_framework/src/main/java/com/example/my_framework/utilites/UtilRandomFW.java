package com.example.my_framework.utilites;

import java.util.Random;

public class UtilRandomFW {
    public static int getCasualNumber(int number) {
        Random random = new Random();
        int casualNumber = random.nextInt(number);
        return casualNumber;
    }

    public static int getGap(int minNumber, int maxNumber) {
        int gap = (int) ((Math.random() * ++maxNumber) + minNumber);
        return gap;
    }
}
