package tinder.com.services;


import tinder.com.Interface.DAO;
import tinder.com.entity.User;

import java.util.List;

public class UserService {
    private DAO<User> users;

    public UserService(DAO users) {
        this.users = users;
    }

    public void addUser (User user) { // TODO перехват LoginMatchException
        users.add(user);
    }

    public void addUser (String name, String occupation, String login, String pass, String photo) { // TODO перехват LoginMatchException
        User user = new User(name, occupation, login, pass, photo);
        users.add(user);
    }

    public void removeUser (int id){ // TODO перехват // TODO перехват ElementNotFoundInDbException
        users.remove(id);
    }

    public boolean checkUserPass(String userLogin, String password) {
        User user = users.get(userLogin.hashCode()); // TODO перехват ElementNotFoundInDbException
        return user.check(password);
    }

    public List<User> getAll() {
        return users.getAll();
    }

    public User getUser(int userId){ // TODO перехват // TODO перехват ElementNotFoundInDbException
        return users.get(userId);
    }

    public boolean isUsersDbEmpty(){
        return users.isEmpty();
    }
}
