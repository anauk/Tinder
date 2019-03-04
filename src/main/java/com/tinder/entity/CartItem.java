package com.tinder.entity;

import com.tinder.Interface.Identifiable;

public class CartItem implements Identifiable {
    private int id;
    private int user1_id;
    private int user2_id;
    private boolean sympathy;

    public CartItem(int user1_id, int user2_id, boolean sympathy) {
        this(-1, user1_id, user2_id, sympathy);
    }

    public CartItem(int id, int user1_id, int user2_id, boolean sympathy) {
        this.id = id;
        this.user1_id = user1_id;
        this.user2_id = user2_id;
        this.sympathy = sympathy;
    }
    @Override
    public int getId() {
        return id;
    }

    public int getUser1_id() {
        return user1_id;
    }

    public int getUser2_id() {
        return user2_id;
    }

    public boolean getSympathy() {
        return sympathy;
    }

    @Override
    public String toString() {
        return  '{'+"id=" + id +
                ", user1_id=" + user1_id +
                ", user2_id=" + user2_id +
                ", sympathy=" + sympathy +
                '}';
    }
}
