package com.tinder.utils;


import com.tinder.exceptions.IncorrectEntryException;

public interface Validator <T> {
    boolean isValid(T elem) throws IncorrectEntryException;
}
