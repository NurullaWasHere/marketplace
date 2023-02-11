package entities;

public class Admin extends User{
    private final boolean isAdmin;
    public Admin(int id,String name,String password){
        super(id, name , password);
        this.isAdmin = true;
    }

    public class toCheckAdmin{
        public static boolean checkAdmin(boolean isAdmin){
            return isAdmin;
        }
    }
}
