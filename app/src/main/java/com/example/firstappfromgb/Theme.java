package com.example.firstappfromgb;

import androidx.annotation.StringRes;

public enum Theme {

    ONE(R.style.Theme_FirstAppFromGB, R.string.theme_one, false, "THEME_ONE"),
    TWO(R.style.Theme_FirstAppFromGB, R.string.theme_two, true, "THEME_TWO");

    private @StringRes
    final int theme;

    private @StringRes
    final int title;

    private boolean modeNight;

    private final String key;

    Theme(int theme, int title, boolean mode, String key) {
        this.theme = theme;
        this.title = title;
        this.modeNight = mode;
        this.key = key;
    }

    private final String MODE_DAY = "DAY";
    private final String MODE_NIGHT = "NIGHT";

    public int getTitle() {
        return title;
    }

    public String getKey() {
        return key;
    }

    public int getTheme() {
        return theme;
    }

    public boolean isModeNight() {
        return modeNight;
    }
}
