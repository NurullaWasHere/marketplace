package controllers;

import entities.Product;
import repository.IProductRepository;
import repository.ProductRepository;

import java.util.ArrayList;

public class ProductController {
    private final IProductRepository repo;

    public ProductController(IProductRepository repo){
        this.repo = repo;
    }

    public String createProduct(int id, int store_id, String name, String category){
        Product product = new Product(id, store_id, name ,category);

        boolean created = repo.createProduct(product);
        return created ? "Product created" : "product create faield";
    }

    public Product getProduct(int id){
        Product product = repo.getOneProduct(id);
        if(product != null){
            return product;
        }
        else {
            return null;
        }
    }
    public Product buyProducts(int id){
        Product product = repo.buyProducts(id);
        return product;
    }

    public String setToUserCart(int user_id, int product_id){
        boolean inserted = repo.setToUserCart(user_id, product_id);

        return inserted ? ("Product by id " + product_id + " inserted into cart of user by id " + user_id) : "Failed to insert product";
    }

    public String setToFavourite(int user_id, int product_id){
        boolean inserted = repo.setToFavourites(user_id, product_id);

        return inserted ? ("Product by id " + product_id + " inserted into favourites of user by id " + user_id) : "Failed to insert product";
    }

    public ArrayList<Product> BuyFromCart(int cart_id){
        ArrayList<Product> bought = repo.buyFromCart(cart_id);
        return bought;
    }

    public ArrayList<String> AllCategories(){
        ArrayList<String> categories = repo.allCategories();
        return categories;
    }

    public ArrayList<Product> getProductsByCategory(String category){
        ArrayList<Product> products = repo.getProductsByCategory(category);
        return products;
    }
    public ArrayList<Product> ProductsByStore(int store_id){
        ArrayList<Product> products = repo.getProductsByStore(store_id);
        return products;
    }
}
