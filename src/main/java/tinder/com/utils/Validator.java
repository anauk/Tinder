package tinder.com.utils;


import tinder.com.exceptions.IncorrectEntryException;

public interface Validator <T> {
    boolean isValid(T elem) throws IncorrectEntryException;
}
