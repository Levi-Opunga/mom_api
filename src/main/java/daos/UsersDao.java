package daos;

import models.User;

import java.util.List;

public interface UsersDao {
    void addUser(User user);

    void removeUser(int id);


    User getSpecificUser(int id);

    List<User> searchUser(String search);

    void editUser(User user);
}
