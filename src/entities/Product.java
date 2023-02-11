package entities;

public class Product {
    private int id;
    private int store_id;
    private int favourite_id;
    private int cart_id;
    private int price;
    private String name;
    private String category;

    public Product(){

    }
    public Product(int id, int store_id, String name, String category){
        this.id = id;
        this.store_id = store_id;
        this.name = name;
        this.category = category;
    }
    public Product(int id, int store_id, String name, String category, int price){
        this.id = id;
        this.store_id = store_id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public Product(int id, int store_id, int favourite_id, int cart_id, int price, String name, String category){
        this.id = id;
        this.store_id = store_id;
        this.favourite_id = favourite_id;
        this.cart_id = cart_id;
        this.price = price;
        this.name = name;
        this.category = category;
    }

    public int getId() {
        return this.id;
    }

    public int getCart_id() {
        return this.cart_id;
    }

    public int getFavourite_id() {
        return this.favourite_id;
    }

    public int getPrice() {
        return this.price;
    }

    public int getStore_id() {
        return this.store_id;
    }

    public String getCategory() {
        return this.category;
    }

    public String getName() {
        return this.name;
    }
}
