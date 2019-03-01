package tinder.com.utils;

public enum EntryFormats {

    LOGIN("[\\w_@.-]{1,128}", "Incorrect login format! Login can contain from 1 to 128 letters, digits or symbols '.', '-', '_' '@' only"),
    PASSWORD("[\\S]{6,25}", "Incorrect password format! Password must contain from 6 to 25 symbols, spaces are not allowed"),
    NAME("[\\w'-]{1,25}", "Incorrect name format! Name can contain from 1 to 25 symbols: letters, digits apostrophe and hyphen only");

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
