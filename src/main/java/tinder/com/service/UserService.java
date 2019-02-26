package tinder.com.service;

import tinder.com.Interface.DAO;
import tinder.com.entity.User;

import java.util.Collection;
import java.util.List;

public class UserService {
    private DAO<User> users;

    public UserService(DAO<User> users) {
        this.users = users;
    }
    public void addUser(User user){
        users.put(user);
    }
    public void addUser(String name, String login, String password){
        User user = new User(name, login, password);
        users.put(user);
    }
    public User getUser(int id){
        return users.get(id);
    }
    public Collection<User> all(){
        return users.all();
    }
    public boolean checkUserPass(String login, String password){
        User user = users.get(login.hashCode());
        return user.check(password);
    }
    public void removeUser(int id){
        users.remove(id);
    }
}
