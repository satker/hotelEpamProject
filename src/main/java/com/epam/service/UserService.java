package com.epam.service;

import com.epam.model.User;
import com.epam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;


    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    public void deleteUserById(long id) {
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

    public Optional<User> findUserByLogin(String login) {
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
