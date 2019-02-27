package tinder.com.entity;

import tinder.com.Interface.Identifiable;

public class Goods implements Identifiable {
private String category;
private String name;
private int price;
private int id;

    public Goods(int id, String category, String name, int price) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.id = id;
    }

    public Goods(String category, String name, int price) {
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
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", id=" + id +
                '}';
    }

    public String fineToString(){
        return String.format("%-8d%-25s%-25s%-12d", id, category, name, price);
    }
}
