package br.com.nava.backend.service;

import br.com.nava.backend.entity.User;

import java.util.List;

public interface UserService {

    void createUser(User user);
    void updateUser(User user);
    void deleteUser(Integer userId);
    List<User> getUsers();
    User getUserById(Integer userId);
}
