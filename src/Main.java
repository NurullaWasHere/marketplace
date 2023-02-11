import controllers.ProductController;
import controllers.StoreController;
import controllers.UserController;
import interfaces.IDB;
import interfaces.PostgresDB;
import repository.*;

public class Main {
    public static void main(String[] args) {
        IDB db = new PostgresDB();
        IProductRepository prodRepo = new ProductRepository(db);
        IStoreRepo storeRepo = new StoreRepo(db);
        IUserRepository userRepo = new UserRepository(db);

        ProductController productController = new ProductController(prodRepo);
        StoreController storeController = new StoreController(storeRepo);
        UserController userController = new UserController(userRepo);

        MyApplication app = new MyApplication(productController, storeController, userController);
        app.start();
    }
}