package repository;

import entities.User;

public interface IUserRepository {
    boolean createUser(User user);
    User loginUser(String name, String password);

}
