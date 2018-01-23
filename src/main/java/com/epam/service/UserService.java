package com.epam.service;

import com.epam.dto.AddUserDTO;
import com.epam.dto.UserDTO;
import com.epam.model.User;
import com.epam.repository.UserRepository;
import com.epam.exceptions.*;
import lombok.RequiredArgsConstructor;
import com.epam.mappers.UserMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
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

    public Optional<User> isUserExists(AddUserDTO user) {
        return userRepository.findByLogin(user.getLogin());
    }

    public UserDTO findUserByLogin(String login) throws UserNotFoundException {
        return userMapper.userToUserDto(userRepository.findByLogin(login).orElseThrow(() -> new UserNotFoundException(0)));
    }

    public Optional<User> findUserById(long id) {
        return userRepository.findById(id);
    }

    public UserDTO findOne(Long id) {
        return userMapper.userToUserDto(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id)));
    }

    public List<UserDTO> findAllUsers() {
        return userMapper.usersToUsersDto(userRepository.findAll());
    }

    public UserDTO saveUser(AddUserDTO user) {
        User newUser = userMapper.addUserDtoToUser(user);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userMapper.userToUserDto(userRepository.save(newUser));
    }

    public UserDTO getUserValidateUser(long id, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if (findUserByLogin(principal.getName()).getId() == id) {
            return findOne(id);
        } else throw new AccessDeniedException(id);
    }

    public void updateUserValidateUser(long id, HttpServletRequest request, UserDTO upUser) {
        Principal principal = request.getUserPrincipal();
        if (findUserByLogin(principal.getName()).getId() == id) {
            updateUser(upUser, id);
        } else throw new AccessDeniedException(id);
    }
}
