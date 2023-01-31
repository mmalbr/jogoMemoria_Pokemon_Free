package com.meafocus.memoriakids01.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class Memory {

    private static final String SHARED_PREFERENCES_NAME = "com.meafocus.memoriakids01";
    private static final String highStartKey = "theme_%d_difficulty_%d";
    private static final String bestTimeKey = "themetime_%d_difficultytime_%d";

    public static void save(int theme, int difficulty, int stars) {

        int highStars = getHighStars(theme, difficulty);

        if (stars > highStars) {
            SharedPreferences sharedPreferences = Shared.context.getSharedPreferences(SHARED_PREFERENCES_NAME,
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            @SuppressLint("DefaultLocale") String key = String.format(highStartKey, theme, difficulty);
            edit.putInt(key, stars).apply();
        }
    }

    public static void saveTime(int theme, int difficulty, int passedSecs) {

        int bestTime = getBestTime(theme, difficulty);

        if (passedSecs < bestTime || bestTime == -1) {
            SharedPreferences sharedPreferences = Shared.context.getSharedPreferences(SHARED_PREFERENCES_NAME,
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            @SuppressLint("DefaultLocale") String timeKey = String.format(bestTimeKey, theme, difficulty);
            editor.putInt(timeKey, passedSecs);
            editor.apply();
        }
    }

    public static int getHighStars(int theme, int difficulty) {

        SharedPreferences sharedPreferences = Shared.context.getSharedPreferences(SHARED_PREFERENCES_NAME,
                Context.MODE_PRIVATE);
        @SuppressLint("DefaultLocale") String key = String.format(highStartKey, theme, difficulty);
        return sharedPreferences.getInt(key, 0);
    }

    public static int getBestTime(int theme, int difficulty) {

        SharedPreferences sharedPreferences = Shared.context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        @SuppressLint("DefaultLocale") String key = String.format(bestTimeKey, theme, difficulty);
        return sharedPreferences.getInt(key, -1);
    }

    public static void saveSounds(int somSit) {
        SharedPreferences sharedPreferences = Shared.context.getSharedPreferences(SHARED_PREFERENCES_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("Sound", somSit);
        editor.apply();
    }

    public static int getSaveSounds() {
        SharedPreferences sharedPreferences = Shared.context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("Sound", 1);
    }

    public static void saveMusic(int somSit) {
        SharedPreferences sharedPreferences = Shared.context.getSharedPreferences(SHARED_PREFERENCES_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("Music", somSit);
        editor.apply();
    }

    public static int getSaveMusic() {
        SharedPreferences sharedPreferences = Shared.context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("Music", 1);
    }




}
