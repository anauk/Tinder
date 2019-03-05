package tinder.com.exceptions;

import java.io.IOException;

public class ElementNotFoundInDbException extends RuntimeException {
    public ElementNotFoundInDbException(String s){
        super(s);
    }
}
