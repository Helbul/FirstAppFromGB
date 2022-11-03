package com.example.firstappfromgb;

public enum Buttons {
    BUTTON1 ("1"),
    BUTTON2 ("2"),
    BUTTON3 ("3"),
    BUTTON4 ("4"),
    BUTTON5 ("5"),
    BUTTON6 ("6"),
    BUTTON7 ("7"),
    BUTTON8 ("8"),
    BUTTON9 ("9"),
    BUTTON0 ("0"),

    BUTTON_CE ("CE"),
    BUTTON_DEL ("DEL"),
    BUTTON_DOT ("."),
    BUTTON_MINUS ("-"),
    BUTTON_PLUS ("+"),
    BUTTON_MULTIPLY ("*"),
    BUTTON_DIVIDE ("/"),
    BUTTON_EQUAL ("=");

    private String title;

    public String getTitle() {
        return title;
    }

    Buttons(String title) {
        this.title = title;
    }
}
