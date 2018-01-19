package com.epam.service;

import com.epam.model.User;
import com.epam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserService {


    private final UserRepository userRepository;

    @Autowired
    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    public void deleteUserById(Long id) {
        userRepository.delete(id);
    }

    public void updateUser(User oldUser) {
        User u = userRepository.findOne(oldUser.getId());
        userRepository.save(u);

    }

    public boolean isUserExists(User user) {
        if (userRepository.findByLogin(user.getLogin()) == null)
            return false;
        else return true;
    }

    public User findUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public User fineOne(Long id) {
        return userRepository.findOne(id);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
