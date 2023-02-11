package entities;

public class User {
    private int id;
    private String name;
    private String password;
    public User(){

    }
    public User(int id,String name){
        this.id = id;
        this.name = name;
    }
    public User(int id,String name,String password){
        this.id = id;
        this.name = name;
        this.password = password;
    }
    public int getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getPassword(){
        return this.password;
    }
}
