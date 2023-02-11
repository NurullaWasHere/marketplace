package entities;

public class ComputerStore extends Store{
    private final String category;

    public ComputerStore(int id, String name){
        super(id, name);
        this.category = "Telephone";
    }
}
