package com.franklin.techblog.service;

import com.franklin.techblog.dto.UserDto;
import com.franklin.techblog.entity.BlogUser;

import java.util.List;
import java.util.Optional;


public interface IUserService {

    void createAccount(UserDto userDto);

    boolean authenticateUser(String usernameOrEmail, String password);

    List<BlogUser> getAllUsers();

    Optional<BlogUser> getUserById(Long id);

    Optional<BlogUser> getUserByEmail(String email);

    BlogUser updateUser(Long id, BlogUser updatedBlogUser);

    String deleteUserById(Long id);
}
