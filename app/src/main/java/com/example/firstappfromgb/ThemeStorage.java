package com.example.firstappfromgb;

import android.content.Context;
import android.content.SharedPreferences;

public class ThemeStorage {
    private static final String KEY_PREFERENCES = "preferences";
    private static final String THEME_KEY = "THEME_KEY";
    private static final String THEME_ONE = "THEME_ONE";
    private static final String THEME_TWO = "THEME_TWO";
    private static ThemeStorage INSTANCE;
    private Context context;
    private SharedPreferences sharedPreferences;

    private ThemeStorage (Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(KEY_PREFERENCES, Context.MODE_PRIVATE);
    }

    public static ThemeStorage getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new ThemeStorage(context);
        }
        return INSTANCE;
    }

    public void saveTheme (Theme theme) {
        sharedPreferences.edit()
                .putString(THEME_KEY, theme.getKey())
                .apply();
    }

    public Theme getTheme(){
        String savedValue = sharedPreferences.getString(THEME_KEY, Theme.ONE.getKey());
        for (Theme theme: Theme.values()) {
            if (theme.getKey().equals(savedValue)) {
                return theme;
            }
        }
        return Theme.ONE;
    }
}
