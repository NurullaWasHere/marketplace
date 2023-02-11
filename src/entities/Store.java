package entities;

public class Store {
    private int id;
    private String name;

    public Store(){

    }
    public Store(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}
