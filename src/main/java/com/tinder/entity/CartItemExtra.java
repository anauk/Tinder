package com.tinder.entity;

public class CartItemExtra extends CartItem {

    private final String name;
    private final String occupation;
    private final String photo;

    public CartItemExtra(int user1_id, int user2_id, boolean sympathy, String name, String occupation, String photo) {
        this(-1, user1_id, user2_id, sympathy, name, occupation, photo);
    }

    public CartItemExtra(int id, int user1_id, int user2_id, boolean sympathy, String name, String occupation, String photo) {
        super(id, user1_id, user2_id, sympathy);
        this.name = name;
        this.occupation = occupation;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getPhoto() {
        return photo;
    }

}
