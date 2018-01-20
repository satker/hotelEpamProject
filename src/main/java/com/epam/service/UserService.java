package com.epam.service;

import com.epam.dto.UserDTO;
import com.epam.model.User;
import com.epam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import com.epam.mappers.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public long getId(UserDTO userDTO) {
        Optional<User> user = userRepository.findByLogin(userDTO.getLogin());
        return user.get().getId();
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    public void deleteUserById(long id) {
        userRepository.delete(id);
    }

    public UserDTO updateUser(UserDTO modifiedUser, long id) {
        User user = userRepository.findOne(id);
        user.setFirstName(modifiedUser.getFirstName());
        user.setLastName(modifiedUser.getLastName());
        user.setLogin(modifiedUser.getLogin());
        user.setPassword(modifiedUser.getPassword());
        return userMapper.userToUserDto(userRepository.save(user));

    }

    public boolean isUserExists(UserDTO user) {
        return !(userRepository.findByLogin(user.getLogin()).isPresent());

    }

    public Optional<User> findUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public UserDTO fineOne(Long id) {
        return userMapper.userToUserDto(userRepository.findOne(id));
    }

    public List<UserDTO> findAllUsers() {
        return userMapper.usersToUsersDto(userRepository.findAll());
    }

    public void saveUser(UserDTO user) {
        userRepository.save(userRepository.findByLogin(user.getLogin()).get());
    }
}
