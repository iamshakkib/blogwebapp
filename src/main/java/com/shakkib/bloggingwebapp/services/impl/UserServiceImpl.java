package com.shakkib.bloggingwebapp.services.impl;

import com.shakkib.bloggingwebapp.config.AppConstants;
import com.shakkib.bloggingwebapp.entities.Role;
import com.shakkib.bloggingwebapp.entities.User;
import com.shakkib.bloggingwebapp.exceptions.ResourceNotFoundException;
import com.shakkib.bloggingwebapp.helpers.DTOs.UserDTO;
import com.shakkib.bloggingwebapp.repositories.RoleRepository;
import com.shakkib.bloggingwebapp.repositories.UserRepository;
import com.shakkib.bloggingwebapp.services.UserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public String registerNewUser(UserDTO user) {
        User userEntity = this.modelMapper.map(user, User.class);
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        Role role = this.roleRepository.findById(AppConstants.NORMAL_USER).get();
        userEntity.getRoles().add(role);
        this.roleRepository.save(role);
        User registeredUser = this.userRepository.save(userEntity);
        if(Objects.nonNull(registeredUser)) return AppConstants.SUCCESSFULLY_REGISTERED; else return AppConstants.REGISTRATION_FAILED;
    }

    @Override
    public UserDTO createUser(UserDTO user) {
        /*look up here*/
        User userEntity = this.modelMapper.map(user,User.class);
        User createdUser = this.userRepository.save(userEntity);
        UserDTO userDTO = this.modelMapper.map(createdUser, UserDTO.class);
        return userDTO;
    }

    @Override
    public UserDTO updateUser(UserDTO user, Integer userId) {
        User userEntity = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id", userId));
        userEntity.setName(user.getName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        userEntity.setAbout(user.getAbout());

        User updatedUser = this.userRepository.save(userEntity);
        UserDTO userDTO = this.modelMapper.map(updatedUser,UserDTO.class);
        return userDTO;
    }

    @Override
    public UserDTO getUserById(Integer id) {
        User userEntity = this.userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","Id",id));
        UserDTO userDTO = this.modelMapper.map(userEntity,UserDTO.class);
        return userDTO;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        List<UserDTO> userDTOList = users.stream().map(user -> this.modelMapper.map(user,UserDTO.class)).collect(Collectors.toList());
        return userDTOList;
    }

    @Override
    public void deleteUser(Integer userId) {
        User userEntity = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        this.userRepository.delete(userEntity);
    }
}
