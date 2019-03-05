package tinder.com.impl;

import tinder.com.Interface.UserStorage;

import java.util.HashMap;

public class UserStoragImpl implements UserStorage {
    private final HashMap<String, String> storage = new HashMap<>();
    @Override
    public void register(String login, String password) {
        storage.put(login, password);

    }

    @Override
    public boolean check(String login, String password) {
        return storage.get(login).equals(login)
                &&storage.get(password).equals(password);
    }
}
