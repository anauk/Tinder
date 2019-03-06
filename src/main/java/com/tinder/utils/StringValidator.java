package com.tinder.utils;


import com.tinder.exceptions.IncorrectEntryException;

public class StringValidator implements Validator<String> {
    private final String regex;
    private final String message;

    public StringValidator(String regex, String message) {
        this.regex = regex;
        this.message = message;
    }

    @Override
    public boolean isValid(String str) throws IncorrectEntryException {

        if (!str.matches(regex)) {
            throw new IncorrectEntryException(message);
        }
        return true;
    }

}
