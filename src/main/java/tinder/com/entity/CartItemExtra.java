package tinder.com.entity;

public class CartItemExtra extends CartItem {
    private final String name;
    private final String occupation;
    private final String photo;
    public CartItemExtra(int id1, int id2, boolean sympathy, String name, String occupation, String photo) {
        this(-1,id1, id2, sympathy, name, occupation, photo);
    }

    public CartItemExtra(int id, int id1, int id2, boolean sympathy, String name, String occupation, String photo) {
        super(id, id1, id2, sympathy);
        this.name=name;
        this.occupation=occupation;
        this.photo=photo;
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
