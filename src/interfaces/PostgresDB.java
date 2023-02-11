package interfaces;

import java.sql.*;

public class PostgresDB implements IDB{
    @Override
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        String connectionUrl = "jdbc:postgresql://localhost:5432/marketplace";
        try {
            Class.forName("org.postgresql.Driver");
            Connection con  = DriverManager.getConnection(connectionUrl, "postgres", "gazizorda101");
            return con;
        }catch (Exception e) {
            e.getStackTrace();
            System.out.println(e);
            System.out.println("Здесь");
            return null;
        }
    }
}
