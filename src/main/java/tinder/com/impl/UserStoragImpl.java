package tinder.com.impl;

import tinder.com.Interface.UserStorage;

import java.util.HashMap;

public class UserStoragImpl implements UserStorage {
    private final HashMap<String, String> storage = new HashMap<>();
    @Override
    public void register(String name, String password) {
        storage.put(name, password);

    }

    @Override
    public boolean check(String name, String password) {
        return storage.get(name).equals(name)
                &&storage.get(password).equals(password);
    }
}
