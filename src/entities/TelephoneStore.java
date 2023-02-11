package entities;

public class TelephoneStore extends Store{
    private final String category;

    public TelephoneStore(int id, String name){
        super(id, name);
        this.category = "Telephone";
    }
}
