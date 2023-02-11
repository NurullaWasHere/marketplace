import controllers.ProductController;
import controllers.StoreController;
import controllers.UserController;
import entities.Product;
import entities.Store;
import entities.User;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MyApplication {
    private final ProductController pc;
    private final StoreController sc;
    private final UserController uc;

    private final Scanner scanner;
    public MyApplication(ProductController pc, StoreController sc, UserController uc){
        this.pc = pc;
        this.sc = sc;
        this.uc = uc;
        scanner = new Scanner(System.in);
    }

    public void start(){
        boolean isLogged = false;
        User currentUser = null;
        while (true){
            System.out.println();
            System.out.println("Welcome to baribar MarketPlace");
            System.out.println("Select option:");
            System.out.println("1. Login");
            System.out.println("2. Sign up");
            try {
                System.out.print("Enter option (1, 2): ");
                int option = scanner.nextInt();
                if(option == 1){
                    currentUser = loginMenu();
                    if(currentUser != null){
                        isLogged = true;
                        break;
                    }
                }
                else if(option == 2){
                    signUp();
                }else{
                    break;
                }

            }catch (InputMismatchException e){
                System.out.println("Input must be integer");
                scanner.nextLine(); // to ignore incorrect input
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("*************************");
        }
        if(isLogged){
            while (true){
                System.out.println("Select option:");
                System.out.println("1. Products by Category");
                System.out.println("2. Products by Store");
                System.out.println("3. CreateProduct");
                System.out.println("4. CreateStore");
                System.out.println("5. MyCart");
                System.out.println("6. Favourites");
                int prodMethod = scanner.nextInt();
                if (prodMethod == 1){
                    System.out.println("Select category:");
                    ArrayList<String> categories = categories();
                    int category_id = scanner.nextInt();
                    ArrayList<Product> productsFromCategory = getProductsByCategory(categories.get(category_id-1));
                    System.out.println("Select a product");
                    int product_id = scanner.nextInt();
                    System.out.println("Select option");
                    System.out.println("BuyProduct");
                    System.out.println("Set to cart");
                    System.out.println("Set to Favourites");
                    int optionForProduct = scanner.nextInt();
                    if(optionForProduct == 1){
                        buyProduct(product_id);
                    }
                    if(optionForProduct == 2){
                        setToUserCart(currentUser.getId(), product_id);
                    }
                    if(optionForProduct == 3){
                        setToUserFavourite(currentUser.getId(), product_id);
                    }
                }
                if (prodMethod == 2){
                    ArrayList<Store> stores = getStores();
                    System.out.println("Select a store");
                    int store_id = scanner.nextInt();
                    ArrayList<Product> productsFromStore = getProductsByStore(store_id);
                    System.out.println("Select a product");
                    int product_id = scanner.nextInt();
                    System.out.println("Select option");
                    System.out.println("1)BuyProduct");
                    System.out.println("2)Set to cart");
                    System.out.println("3)Set to Favourites");
                    int optionForProduct = scanner.nextInt();
                    if(optionForProduct == 1){
                        buyProduct(product_id);
                    }
                    if(optionForProduct == 2){
                        setToUserCart(currentUser.getId(), product_id);
                    }
                    if(optionForProduct == 3){
                        setToUserFavourite(currentUser.getId(), product_id);
                    }
                }
                if (prodMethod == 3){
                    createProductMenu();
                }
                if (prodMethod == 4){
                    createStoreMenu();
                }
                if (prodMethod == 5){
                    showCart(currentUser.getId());
                }
                if (prodMethod == 6){
                    createStoreMenu();
                }
            }
        }
    }

    public void buyProduct(int id){
        Product product = pc.buyProducts(id);
        System.out.println("Product: ");
        System.out.println("1" + ") " + product.getName());
        System.out.println("bought succesfully");
    }
    public User loginMenu(){
        System.out.println("Please enter name");
        String name = scanner.next();
        System.out.println("Please enter password");
        String password = scanner.next();
        User response = uc.loginUser(name, password);
        if(response == null){
            System.out.println("Logged failed");
            return null;
        }
        System.out.println("Logged succesfully");
        return response;
    }

    public void signUp(){
        System.out.println("Please enter id");
        int id  = scanner.nextInt();
        System.out.println("Please enter name");
        String name = scanner.next();
        System.out.println("Please enter password");
        String password = scanner.next();

        String response = uc.createUser(id, name ,password);
        System.out.println(response);
    }

    public ArrayList<String> categories(){
        ArrayList<String> response = pc.AllCategories();
        for (int i = 0; i < response.size(); i++) {
            System.out.println((i+1) + ") " + response.get(i));
        }
        return  response;
    }

    public ArrayList<Store> getStores(){
        ArrayList<Store> response = sc.getAllStores();
        for (int i = 0; i < response.size(); i++) {
            System.out.println((i+1) + ") " +response.get(i).getName());
        }
        return response;
    }

    public void createProductMenu(){
        System.out.println("Please enter id");
        int id  = scanner.nextInt();
        System.out.println("Please enter store id");
        int store_id  = scanner.nextInt();
        System.out.println("Please enter name");
        String name = scanner.next();
        System.out.println("Please enter category");
        String category = scanner.next();

        String response = pc.createProduct(id, store_id , name, category);
        System.out.println(response);
    }
    public void createStoreMenu(){
        System.out.println("Please enter id");
        int id  = scanner.nextInt();
        System.out.println("Please enter name");
        String name = scanner.next();
        String response = sc.createStore(id, name);
        System.out.println(response);
    }

    public ArrayList<Product> getProductsByCategory(String category){
        ArrayList<Product> products = pc.getProductsByCategory(category);
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i+1) + ") " + products.get(i).getName()+ " : " + products.get(i).getPrice() + "$");
        }
        return  products;
    }

    public ArrayList<Product> getProductsByStore(int store_id){
        ArrayList<Product> products = pc.ProductsByStore(store_id);
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i+1) + ") " + products.get(i).getName()+ ": " + products.get(i).getPrice() + "$");
        }
        return  products;
    }
    public void setToUserCart(int user_id, int product_id){
        String response = pc.setToUserCart(user_id, product_id);
        System.out.println(response);
    }
    public void setToUserFavourite(int user_id,int product_id){
        String response = pc.setToFavourite(user_id, product_id);
        System.out.println(response);
    }

    public void showCart(int cart_id){
        ArrayList<Product> products = pc.BuyFromCart(cart_id);
        int allPrice = 0;
        for (int i = 0; i < products.size(); i++) {
            System.out.println(i+1 + ") " + products.get(i).getName() + " : " + products.get(i).getPrice());
            allPrice = allPrice + products.get(i).getPrice();
        };
        System.out.println("Type 1 for buy all products from cart ");
        System.out.println("Type 2 to get back ");
        int nextMove = scanner.nextInt();
        if(nextMove == 1){
            System.out.println("Products from carts boughts in " + allPrice + "$");
        }
    }
}
