package com.franklin.techblog.Service;

import com.franklin.techblog.dto.UserDto;
import org.springframework.stereotype.Service;


public interface IUserService {

    void createAccount(UserDto userDto);
}
