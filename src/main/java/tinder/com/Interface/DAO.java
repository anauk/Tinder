package tinder.com.Interface;

import java.util.Collection;

public interface DAO<T> {
    T get(int id);
    void put (T entity);
    Collection<T> all();

    void remove(int id);
}
