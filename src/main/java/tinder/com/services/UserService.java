package tinder.com.services;


import tinder.com.DAO.CartsDAO_SQL;
import tinder.com.DAO.DAOextra_SQL;
import tinder.com.Interface.DAO;
import tinder.com.entity.CartItemExtra;
import tinder.com.entity.User;
import tinder.com.exceptions.NoNewUsersException;

import java.util.List;
import java.util.Optional;

public class UserService {
    private final DAO<User> users;
    private final CartsDAO_SQL carts;
    private final DAOextra_SQL daoExtra;

    public UserService(DAO users, CartsDAO_SQL carts, DAOextra_SQL daoExtra) {
        this.users = users;
        this.carts = carts;
        this.daoExtra = daoExtra;
    }

    public void addUser(User user) { // TODO перехват LoginMatchException
        users.add(user);
    }

    public void addUser(String name, String occupation, String login, String pass, String photo) { // TODO перехват LoginMatchException
        User user = new User(name, occupation, login, pass, photo);
        users.add(user);
    }

    public void removeUser(int id) { // TODO перехват // TODO перехват ElementNotFoundInDbException
        users.remove(id);
    }

    public boolean checkUserPass(String userLogin, String password) {
        User user = users.get(userLogin.hashCode()); // TODO перехват ElementNotFoundInDbException
        return user.check(password);
    }

    public List<User> getAll() {
        return users.getAll();
    }

    public User getUser(int userId) { // TODO перехват // TODO перехват ElementNotFoundInDbException
        return users.get(userId);
    }

    public boolean isUsersDbEmpty() {
        return users.isEmpty();
    }


// filtration by downloading whole the list of users and selected users:
    public User getUserUnchecked(int user_id) throws NoNewUsersException {
        if (users.isEmpty()) {
            throw new NoNewUsersException("No users in database");
        }
        List<CartItemExtra> checked = carts.getByUserAll(user_id);
        List<User> userList = users.getAll();
        int count = 0;
        while (count < userList.size()) {
            User user = userList.get(count);
            Optional <CartItemExtra> cio = checked.stream()
                    .filter(ci -> ci.getUser2_id() == user.getId())
                    .findFirst();
            if(cio.isPresent() || user.getId() == user_id){
                count++;
            } else {
                return user;
            }
        }
        throw new NoNewUsersException("No new users to show");
    }

    // optimized filtration on the level of db query:
    public User getUserUnchecked(int user_id, int user_per_page) throws NoNewUsersException {
        if(daoExtra.getByUser(user_id, user_per_page).size() != 0){
            return daoExtra.getByUser(user_id, user_per_page).get(0);
        }
        else {
            throw new NoNewUsersException ("No new users to show");
        }
    }
}
