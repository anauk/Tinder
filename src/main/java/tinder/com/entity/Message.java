package tinder.com.entity;

import tinder.com.impl.AbstractSendable;
//сообщение в которм есть текст, что можно получить с пом. метода
public class Message extends AbstractSendable {
    private final String message;

    public Message(String from, String to, String message) {
        super(from, to);
        this.message = message;
    }
    public String getMessage(){
        return message;
    }



}
