package tinder.com.Interface;

import tinder.com.entity.MessageItem;

import java.util.List;

public interface DAO<T> {
    T get(int id);

    List<T> all();

    void add(T elem);

    void remove(int id);
    boolean isEmpty();
}
