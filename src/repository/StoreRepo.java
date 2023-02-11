package repository;

import entities.Store;
import interfaces.IDB;

import java.sql.*;
import java.util.ArrayList;

public class StoreRepo implements IStoreRepo{
    private final IDB db;

    public StoreRepo(IDB db){
        this.db = db;
    }

    @Override
    public boolean createStore(Store store){
        Connection con = null;
        try {
            con = this.db.getConnection();
            String sql = "INSERT INTO stores(id, name) values (?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, store.getId());
            st.setString(2, store.getName());
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

    public Store getOneStore(int id){
        Connection con = null;
        try{
            con = this.db.getConnection();
            String sql = "SELECT (id, name) from stores WHERE id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                return new Store(rs.getInt(1), rs.getString(2));
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
        return null;
    }

    public ArrayList<Store> getAllStores(){
        Connection con = null;
        try{
            con = this.db.getConnection();
            ArrayList<Store> stores = new ArrayList<Store>();
            String sql = "SELECT * from stores";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                stores.add(new Store(rs.getInt(1), rs.getString(2)));
            }
            return  stores;
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
        return null;
    }
}
