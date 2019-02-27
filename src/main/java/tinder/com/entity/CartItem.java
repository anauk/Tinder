package tinder.com.entity;

import tinder.com.Interface.Identifiable;

public class CartItem implements Identifiable {
    private int id;
    private int user1_id;
    private int item2_id;
    private boolean sympathy;

    public CartItem(int user1_id, int item2_id, boolean sympathy) {
        this(-1, user1_id, item2_id, sympathy);
    }

    public CartItem(int id, int user1_id, int item2_id, boolean sympathy) {
        this.id = id;
        this.user1_id = user1_id;
        this.item2_id = item2_id;
        this.sympathy = sympathy;
    }
    @Override
    public int getId() {
        return id;
    }

    public int getUser1_id() {
        return user1_id;
    }

    public int getItem2_id() {
        return item2_id;
    }

    public boolean getSympathy() {
        return sympathy;
    }

    @Override
    public String toString() {
        return  '{'+"id=" + id +
                ", user1_id=" + user1_id +
                ", item2_id=" + item2_id +
                ", sympathy=" + sympathy +
                '}';
    }
}
