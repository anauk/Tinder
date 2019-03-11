package tinder.com.entity;

public class CartItemExtra extends CartItem {
    private String name;
    private String occupation;
    private String photo;

    public CartItemExtra(int id1, int id2, boolean sumpathy, String name, String occupation, String photo) {
        this(-1,id1, id2, sumpathy, name, occupation, photo);
    }

    public CartItemExtra(int id, int id1, int id2, boolean sumpathy, String name, String occupation, String photo) {
        super(id, id1, id2, sumpathy);
        this.name=name;
        this.occupation=occupation;
        this.photo=photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
