package com.franklin.techblog.mapper;

import com.franklin.techblog.entity.User;
import com.franklin.techblog.dto.UserDto;


public class UserMapper {

    public static UserDto mapToUserDto(User user, UserDto usersDto) {
        usersDto.setName(user.getName());
        usersDto.setEmail(user.getEmail());
        usersDto.setMobileNumber(user.getMobileNumber());
        usersDto.setPassword(user.getPassword());
        return usersDto;
    }

    public static User mapToUser(UserDto userDto, User user) {
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setMobileNumber(userDto.getMobileNumber());
        user.setPassword(userDto.getPassword());

        return user;
    }
}
