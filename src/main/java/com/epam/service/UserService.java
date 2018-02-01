package com.epam.service;

import com.epam.dto.AddUserDTO;
import com.epam.dto.UserDTO;
import com.epam.model.User;
import com.epam.repository.UserRepository;
import com.epam.exceptions.*;
import lombok.RequiredArgsConstructor;
import com.epam.mappers.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    public void deleteUserById(long id) {
        userRepository.delete(id);
    }

    public UserDTO updateUser(UserDTO modifiedUser, long id) {
        log.debug("user updated {}", modifiedUser);
        User user = userRepository.findOne(id);
        user.setFirstName(modifiedUser.getFirstName());
        user.setLastName(modifiedUser.getLastName());
        user.setLogin(modifiedUser.getLogin());
        user.setPassword(modifiedUser.getPassword());
        return userMapper.userToUserDto(userRepository.save(user));
    }

    public UserDTO findUserByLogin(String login) throws UserNotFoundException {
        log.debug("user found by login {}", login);
        return userMapper.userToUserDto(userRepository.findByLogin(login).orElseThrow(() -> new UserNotFoundException(0)));
    }

    public Optional<User> findUserById(long id) {
        log.debug("user found by id{}", id);
        return userRepository.findById(id);
    }

    public UserDTO findOne(Long id) {
        log.debug("user found by id {}", id);
        return userMapper.userToUserDto(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id)));
    }

    public List<UserDTO> findAllUsers() {
        log.debug("all users found {}");
        return userMapper.usersToUsersDto(userRepository.findAll().
                stream().filter(item->item.getRole().equals("ROLE_USER")).collect(Collectors.toList()));
    }

    public UserDTO saveUser(AddUserDTO user) {
        log.debug("user saved {}", user);
        User newUser = userMapper.addUserDtoToUser(user);
        newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userMapper.userToUserDto(userRepository.save(newUser));
    }

    public UserDTO getUserValidateUser(long id, String login) {
        log.debug("validated user got {}", id);
        if (findUserByLogin(login).getId() == id) {
            return findOne(id);
        } else {
            throw new AccessDeniedException(id);
        }
    }

    public void updateUserValidateUser(long id, String login, UserDTO upUser) {
        log.debug("validated user updated {}", id);
        if (findUserByLogin(login).getId() == id) {
            updateUser(upUser, id);
        } else {
            throw new AccessDeniedException(id);
        }
    }
}
