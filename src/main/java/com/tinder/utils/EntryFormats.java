package com.tinder.utils;

public enum EntryFormats {

    LOGIN("[\\w_@.-]{1,128}", "Incorrect login format! Login can contain from 1 to 128 letters, digits or symbols '.', '-', '_' '@' only"),
    PASSWORD("[\\S]{6,25}", "Incorrect password format! Password must contain from 6 to 25 symbols, spaces are not allowed"),
    NAME("[\\w'-]{1,20}\\s?[\\w'-]{1,20}\\s?[\\w'-]{1,20}", "Incorrect name format! Name can contain from 1 to 3 words of 1-20 symbols each. Letters, digits apostrophe and hyphen are allowed only");

    private String format;
    private String message;

    EntryFormats(String format, String message) {
        this.format = format;
        this.message = message;
    }

    public String getFormat() {
        return format;
    }

    public String getMessage() {
        return message;
    }
}
