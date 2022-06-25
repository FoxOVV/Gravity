package com.example.my_framework.utilites;

import java.util.Random;

public class UtilRandomFW {
    public static int getCasualNumber(int number) {
        Random random = new Random();
        return random.nextInt(number);
    }

    public static int getGap(int minNumber, int maxNumber) {
        return (int) ((Math.random() * ++maxNumber) + minNumber);
    }
}
