package tinder.com.service;

import tinder.com.entity.CartItem;
import tinder.com.entity.CartItemExtra;
import tinder.com.impl.CartsDAO_SQl;


import java.util.List;

public class CartService {
    private final CartsDAO_SQl carts;

    public CartService(CartsDAO_SQl carts) {
        this.carts = carts;
    }
    public void addCart(CartItem item){
        carts.add(item);
    }

    public CartItem getCart(int id){
        return carts.get(id);
    }
    public void removeCart(int id){
        carts.remove(id);
    }
    public boolean isEmpty(){
        return carts.isEmpty();
    }
    public List<CartItem> getAll(){
        return carts.all();
    }
    public void setSympathy(boolean sympathy, int id){
        carts.setSympathy(sympathy, id);
    }

    public List<CartItemExtra> getByUserAll(int userId){
        return carts.getByUserAll(userId);
    }
    public List<CartItemExtra> getByUserLiked(int userId){
        return carts.getByUserLiked(userId);
    }
}
