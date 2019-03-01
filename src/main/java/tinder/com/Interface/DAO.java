package tinder.com.Interface;

import java.util.List;


public interface DAO<T extends Identifiable> {
    void add(T elem);

    List<T> getAll();

    T get(int id);

    void remove(int id);

    boolean isEmpty();

    default void remove(T elem) {
        remove(elem.getId());
    }
}

