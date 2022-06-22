package com.example.gravity.utilites;

import android.content.SharedPreferences;

import com.example.my_framework.CoreFW;

public class SettingsGame {
    public static int[] distance = {5000,4000,3000,2000,1000};

    public static void saveSettings(CoreFW coreFW) {
        SharedPreferences.Editor editor = coreFW.getSharedPreferences().edit();
        editor.clear();

        for (int i = 0; i < 5; i++) {
            editor.putInt("passedDistance"+i, distance[i]);
        }

        editor.apply();
    }

    public static void loadSettings(CoreFW coreFW) {
        for (int i = 0; i < 5; i++) {
            distance[i] = coreFW.getSharedPreferences().getInt("passedDistance"+i, distance[i]);
        }
    }

    public static void addDistance(int values) {
        for (int i = 0; i < 5; i++) {

            if (distance[i]==values) {
                break;
            }

            if (distance[i]<values) {
                for (int j = 4; j > i; j--) {
                    distance[j]=distance[j-1];
                }
                distance[i] = values;
                break;
            }
        }
    }
}
