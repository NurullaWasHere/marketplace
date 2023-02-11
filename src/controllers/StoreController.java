package controllers;

import entities.Store;
import repository.IStoreRepo;
import repository.StoreRepo;

import java.util.ArrayList;

public class StoreController {
    private final IStoreRepo repo;

    public StoreController(IStoreRepo repo){
        this.repo = repo;
    }
    public String createStore(int id, String name){
        Store store = new Store(id, name);

        boolean created = repo.createStore(store);

        return created ? "Store created" : "Store creation failed";
    }

    public Store getOneStore(int id){
        Store store = repo.getOneStore(id);
        if(store == null){
            return null;
        }
        return store;
    }

    public ArrayList<Store> getAllStores(){
        ArrayList<Store> stores = repo.getAllStores();
        if(stores == null){
            return null;
        }
        return stores;
    }
}
