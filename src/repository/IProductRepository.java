package repository;

import entities.Product;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface IProductRepository {
    boolean createProduct(Product product);
    boolean setToUserCart(int userId, int productId);
    boolean setToFavourites(int id, int productId);
    Product getOneProduct(int id);
    ArrayList<Product> getProductsByCategory(String category);
    ArrayList<Product> getProductsByStore(int store_id);
    ArrayList<String> allCategories();
    Product buyProducts(int id);
    ArrayList<Product> buyFromCart(int id );

}
