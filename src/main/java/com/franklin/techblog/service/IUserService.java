package com.franklin.techblog.service;

import com.franklin.techblog.dto.UserDto;


public interface IUserService {

    void createAccount(UserDto userDto);

    boolean authenticateUser(String usernameOrEmail, String password);

}
