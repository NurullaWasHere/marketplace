package repository;

import entities.Product;
import interfaces.IDB;

import java.sql.*;
import java.util.ArrayList;

public class ProductRepository implements IProductRepository{
    private final IDB db;
    public ProductRepository(IDB db){
        this.db = db;
    }
    @Override
    public boolean createProduct(Product product){
        Connection con = null;
        try {
            con = this.db.getConnection();
            String sql = "INSERT INTO products(id, store_id, name, category) VALUES (?, ?, ? , ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, product.getId());
            st.setInt(2,product.getStore_id());
            st.setString(3, product.getName());
            st.setString(4, product.getCategory());
            st.execute();
            return true;
        }catch (SQLException throwables){
            System.out.println("здесь 3");
            throwables.printStackTrace();
        }catch (ClassNotFoundException e){
            System.out.println("There 2");
            e.printStackTrace();
        }
        finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return  false;
    }

    @Override
    public Product getOneProduct(int id){
        Connection con = null;
        try {
            con = this.db.getConnection();
            String sql = "SELECT FROM products(id, name, category, price, store_id) WHERE id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                return new Product(rs.getInt(1), rs.getInt(2), rs.getString(6), rs.getString(7), rs.getInt(5));
            }
        }catch (SQLException throwables){
            System.out.println("здесь 3");
            throwables.printStackTrace();
        }catch (ClassNotFoundException e){
            System.out.println("There 2");
            e.printStackTrace();
        }
        finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return new Product();
    }

    @Override
    public Product buyProducts(int id){
        Connection con = null;
        try{
            con = this.db.getConnection();
            String sql = "SELECT id, store_id, name, category, price FROM products WHERE id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                return new Product(rs.getInt("id"), rs.getInt("store_id"), rs.getString("name"), rs.getString("category"), rs.getInt("price"));
            }
        }
            catch (SQLException throwables){
                throwables.printStackTrace();
            }catch (ClassNotFoundException e){
                System.out.println("There 3");
                e.printStackTrace();
            }finally {
                try {
                    con.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            return null;
        }
        public boolean setToUserCart(int userId, int productId){
            Connection con = null;
            try{
                con = this.db.getConnection();
                String sql = "UPDATE products SET cart_id = ? WHERE id = ?";
                PreparedStatement st = con.prepareStatement(sql);
                st.setInt(1, userId);
                st.setInt(2, productId);
                st.execute();
                return true;
            }catch (SQLException throwables){
                throwables.printStackTrace();
            }catch (ClassNotFoundException e){
                System.out.println("There 3");
                e.printStackTrace();
            }finally {
                try {
                    con.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            return false;
        }

    public boolean setToFavourites(int userId, int productId){
        Connection con = null;
        try{
            con = this.db.getConnection();
            String sql = "UPDATE products SET favourite_id = ? WHERE  id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, userId);
            st.setInt(2, productId);
            st.execute();
            return true;
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }catch (ClassNotFoundException e){
            System.out.println("There 3");
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }
    public ArrayList<Product> buyFromCart(int id){
        Connection con = null;
        try{
            con = this.db.getConnection();
            ArrayList<Product> products = new ArrayList<Product>();
            String sql = "SELECT * FROM products WHERE cart_id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1,  id);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                products.add(new Product(rs.getInt(1), rs.getInt(2), rs.getString(6), rs.getString(7)));
            }
            return products;
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }catch (ClassNotFoundException e){
            System.out.println("There 3");
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    public ArrayList<String> allCategories(){
        Connection con = null;
        try{
            con = this.db.getConnection();
            ArrayList<String> categories = new ArrayList<String>();
            String sql = "SELECT category FROM products";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                categories.add(rs.getString(1));
            }
            return categories;
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }catch (ClassNotFoundException e){
            System.out.println("There 3");
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    public ArrayList<Product> getProductsByCategory(String category){
        Connection con = null;
        try{
            con = this.db.getConnection();
            ArrayList<Product> products = new ArrayList<Product>();
            String sql = "SELECT * FROM products WHERE category = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, category);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                products.add(new Product(rs.getInt(1), rs.getInt(2), rs.getString(6), rs.getString(7)));
            }
            return products;
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }catch (ClassNotFoundException e){
            System.out.println("There 3");
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    public ArrayList<Product> getProductsByStore(int store_id){
        Connection con = null;
        try{
            con = this.db.getConnection();
            ArrayList<Product> products = new ArrayList<Product>();
            String sql = "SELECT * FROM products WHERE store_id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, store_id);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                products.add(new Product(rs.getInt(1), rs.getInt(2), rs.getString(6), rs.getString(7), rs.getInt(5)));
            }
            return products;
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }catch (ClassNotFoundException e){
            System.out.println("There 3");
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }
}
