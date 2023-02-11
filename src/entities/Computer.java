package entities;

public class Computer extends Product{
    private final String characteristiks;
    public Computer(int id, int store_id, String name, String category){
        super(id, store_id, name, category);
        this.characteristiks = null;
    }

    public Computer(int id, int store_id, String name, String category, String characteristiks){
        super(id, store_id, name, category);
        this.characteristiks = characteristiks;
    }

    public String getCharacteristiks() {
        return this.characteristiks;
    }


}
