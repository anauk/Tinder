package tinder.com.impl;

import tinder.com.Interface.Sendable;

public class AbstractSendable implements Sendable {
    protected final  String from;
    protected final String to;

    public AbstractSendable(String from, String to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public String getTo() {
        return to;
    }

    @Override
    public String getFrom() {
        return from;
    }
}
