package com.shakkib.bloggingwebapp.helpers.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

public class UserDTO {

    private int id;

    @NotBlank
    @Size(min=4, message = "Username must be minimum of 4 characters !!")
    private String name;

    /*scope of improvement since we are using validation framework*/
    @Email(message = "Email address is not valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank
    @Size(min=3, max = 10, message = "Password must be of min of 3 chars")
    private String password;

    private String about;

    private Set<RoleDTO> roles = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDTO> roles) {
        this.roles = roles;
    }

    public UserDTO() {
    }

    @JsonIgnore
    public String getPassword() {
        return this.password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password=password;
    }
}
