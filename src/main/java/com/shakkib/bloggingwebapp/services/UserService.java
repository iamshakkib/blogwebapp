package com.shakkib.bloggingwebapp.services;

import com.shakkib.bloggingwebapp.helpers.DTOs.UserDTO;

import java.util.List;

public interface UserService {

    String registerNewUser(UserDTO user) throws Exception;

    UserDTO createUser(UserDTO user);

    UserDTO updateUser(UserDTO user, Integer userId);

    UserDTO getUserById(Integer id);

    List<UserDTO> getAllUsers();

    void deleteUser(Integer userId);

}
