package controllers;

import entities.User;
import repository.IUserRepository;

public class UserController {
    private final IUserRepository repo;
    public UserController(IUserRepository repo){
        this.repo = repo;
    }
    public String createUser(int id, String name, String password){
        User user = new User(id, name, password);
        boolean created = repo.createUser(user);
        return created ? "User created!" : "User create failed";
    }

    public User loginUser(String name, String password){
        User user = repo.loginUser(name, password);
        return user;
    }

}
