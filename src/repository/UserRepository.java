package repository;
import entities.User;
import interfaces.IDB;
import org.postgresql.util.PSQLException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
public class UserRepository implements IUserRepository{
    private final IDB db;
    public UserRepository(IDB db){
        this.db = db;
    }

    @Override
    public boolean createUser(User user){
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "INSERT INTO users(id, name, password) values (?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, user.getId());
            st.setString(2, user.getName());
            st.setString(3, user.getPassword());
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

    public User loginUser(String name, String password){
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT id, name, password FROM users WHERE name = ? and password = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                return new User(rs.getInt("id"), rs.getString("name"));
            }
            return null;
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
        return  null;
    }
}
