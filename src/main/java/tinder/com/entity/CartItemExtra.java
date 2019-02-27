package tinder.com.entity;

public class CartItemExtra extends CartItem {

    private final String category;
    private final String name;
    private final int price;

    public CartItemExtra(int user_id, int item_id, boolean sympathy, String category, String name, int price) {
        this(-1, user_id, item_id, sympathy, category, name, price);
    }

    public CartItemExtra(int id, int user_id, int item_id, boolean sympathy, String category, String name, int price) {
        super(id, user_id, item_id, sympathy);
        this.category = category;
        this.name = name;
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%-8d%-25s%-25s%-12d%-12s", super.getItem2_id(), getCategory(), getName(), getPrice(), super.getSympathy());
    }
}
