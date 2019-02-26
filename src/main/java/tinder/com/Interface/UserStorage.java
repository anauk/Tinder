package tinder.com.Interface;

public interface UserStorage {
    void register(String name, String password);
    boolean check(String name, String password);
}
