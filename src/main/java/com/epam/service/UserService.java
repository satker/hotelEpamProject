package com.epam.service;

import com.epam.dto.AddUserDTO;
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

    public boolean isUserExists(AddUserDTO user) {
        return userRepository.findByLogin(user.getLogin()).isPresent();
    }

    public Optional<User> findUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public Optional<User> findUserById(long id) {
        return userRepository.findById(id);
    }

    public UserDTO findOne(Long id) {
        return userMapper.userToUserDto(userRepository.findOne(id));
    }

    public List<UserDTO> findAllUsers() {
        return userMapper.usersToUsersDto(userRepository.findAll());
    }

    public void saveUser(AddUserDTO user) {
        userRepository.save(userMapper.addUserDtoToUser(user));
    }
}
