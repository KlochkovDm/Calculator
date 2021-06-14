package com.example.calculator;

public enum AppTheme {

    DARK(R.style.Theme_Calculator_Dark, "dark"),
    LIGHT(R.style.Theme_Calculator_Light, "light");


    AppTheme(int resource, String key) {
        this.resource = resource;
        this.key = key;
    }

    private int resource;
    private String key;

    public int getResource() {
        return resource;
    }

    public String getKey() {
        return key;
    }
}
