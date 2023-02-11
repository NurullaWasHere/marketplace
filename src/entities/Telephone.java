package entities;

public class Telephone extends Product{
    boolean isFine;
    public Telephone(int id, int store_id, String name, String category){
        super(id, store_id, name, category);
        boolean isFine = false;
    }

    public Telephone(int id, int store_id, String name, String category, boolean isFine){
        super(id, store_id, name, category);
        this.isFine = isFine;
    }
    public boolean TakeTest(){
        return this.isFine;
    }

    public class ifIsFine{
        public static String ifIsFine(boolean isFine){
            return isFine ? "This device works well" : "This device has issues";
        }
    }
}
