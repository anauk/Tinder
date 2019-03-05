package tinder.com.service;

import tinder.com.Interface.DAO;
import tinder.com.entity.CartItemExtra;
import tinder.com.entity.User;
import tinder.com.exceptions.NoNewUsersException;
import tinder.com.impl.CartsDAO_SQl;
import tinder.com.impl.DAOextra_SQL;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class UserService {
    private DAO<User> users;
    private final CartsDAO_SQl carts;
    private final DAOextra_SQL daoExtra;

    public UserService(DAO<User> users, CartsDAO_SQl carts,  DAOextra_SQL daoExtra) {
        this.users = users;
        this.carts = carts;
        this.daoExtra = daoExtra;
    }
    public void addUser(User user) { // TODO перехват LoginMatchException
        users.add(user);
    }
    public void addUser(String name, String login, String pass, String occupation, String photo) { // TODO перехват LoginMatchException
        User user = new User(name, login, pass, occupation, photo);
        users.add(user);
    }

    public User getUser(int id){
        return users.get(id);
    }
    public List<User> getAll(){
        return users.all();
    }
    public boolean checkUserPass(String login, String password){
        User user = users.get(login.hashCode());
        return user.check(password);
    }
    public void removeUser(int id){
        users.remove(id);
    }
    public boolean isUserDBEmpty(){
        return users.isEmpty();
    }

    public User getUserUnchecked(int id) throws NoNewUsersException {
        if (users.isEmpty()) {
            throw new NoNewUsersException("No users in database");
        }
        List<CartItemExtra> checked = carts.getByUserAll(id);
        List<User> userList = users.all();
        int count = 0;
        while (count < userList.size()) {
            User user = userList.get(count);
            Optional<CartItemExtra> cio = checked.stream()
                    .filter(ci -> ci.getId2() == user.getId())
                    .findFirst();
            if(cio.isPresent() || user.getId() == id){
                count++;
            } else {
                return user;
            }
        }
        throw new NoNewUsersException("No new users to show");
    }
    public User getUserUnchecked(int id, int user_per_page) throws NoNewUsersException {
        if(daoExtra.getByUser(id, user_per_page).size() != 0){
            return daoExtra.getByUser(id, user_per_page).get(0);
        }
        else {
            throw new NoNewUsersException ("No new users to show");
        }
    }
}
