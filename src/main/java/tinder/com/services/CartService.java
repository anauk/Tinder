package tinder.com.services;




import tinder.com.DAO.CartsDAO_SQL;
import tinder.com.entity.CartItem;
import tinder.com.entity.CartItemExtra;

import java.util.List;

public class CartService {
    private final CartsDAO_SQL carts;

    public CartService(CartsDAO_SQL carts) {
        this.carts = carts;
    }

    public void add (CartItem elem){
        carts.add(elem);
    }



    public List<CartItem> getAll() {
        return carts.getAll();
    }

    public CartItem get(int id) {
        return carts.get(id);
    }

    // TODO перехват ElementNotFoundInDbException
    public void remove(int id) {
        carts.remove(id);
    }

    public boolean isEmpty() {
        return carts.isEmpty();
    }

    // TODO перехват IllegalArgumentException
    public void setQuantity(int quantity, int id){
        carts.setQuantity(quantity, id);
    }

    public List<CartItemExtra> getByUser(int user_id){
        return carts.getByUser(user_id);
    }
}
