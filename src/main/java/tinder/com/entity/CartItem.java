package tinder.com.entity;

public class CartItem {
    private int id;
    private int id1;
    private int id2;
    private boolean sumpathy;

    public CartItem(int id1, int id2, boolean sympathy) {
        this.id=-1;
        this.id1 = id1;
        this.id2 = id2;
        this.sumpathy = sympathy;
    }

    public CartItem(int id, int id1, int id2, boolean sympathy) {
        this.id = id;
        this.id1 = id1;
        this.id2 = id2;
        this.sumpathy = sympathy;
    }

    public int getId() {
        return id;
    }

    public int getId1() {
        return id1;
    }

    public int getId2() {
        return id2;
    }

    public boolean isSumpathy() {
        return sumpathy;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", id1=" + id1 +
                ", id2=" + id2 +
                ", sumpathy=" + sumpathy +
                '}';
    }
}
