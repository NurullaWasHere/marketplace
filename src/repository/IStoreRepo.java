package repository;

import entities.Store;

import java.util.ArrayList;

public interface IStoreRepo {
    boolean createStore(Store store);
    Store getOneStore(int id);
    ArrayList<Store> getAllStores();

}
