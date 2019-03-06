package com.tinder.entity;

import com.tinder.Interface.Identifiable;

public class User implements Identifiable {
    private final int id;
    private final String name;
    private String occupation;
    private final String login;
    private String password;
    private String photo;


    public User(String name, String occupation, String login, String password, String photo) {
        this.id = login.hashCode();
        this.name = name;
        this.occupation = occupation;
        this.login = login;
        this.password = password;
        this.photo = photo;
    }

    public boolean check(String password) {
        return password.equals(this.password);
    }

    @Override
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", occupation='" + occupation + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}
