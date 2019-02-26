package tinder.com.entity;

public class User {
    private int id;
    private String name;
    private String login;
    private String password;

    public User() {

    }
    public User(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }
    public User(int id, String name, String login, String password) {
        this.id = login.hashCode();
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String surname) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public boolean check(String password) {
        return password.equals(this.password);
    }
}
