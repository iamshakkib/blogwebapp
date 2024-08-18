package com.shakkib.bloggingwebapp.services;

import com.shakkib.bloggingwebapp.helpers.DTOs.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO registerNewUser(UserDTO user);

    UserDTO createUser(UserDTO user);

    UserDTO updateUser(UserDTO user, Integer userId);

    UserDTO getUserById(Integer id);

    List<UserDTO> getAllUsers();

    void deleteUser(Integer userId);

}
