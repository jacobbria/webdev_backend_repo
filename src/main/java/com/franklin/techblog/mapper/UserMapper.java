package com.franklin.techblog.mapper;

import com.franklin.techblog.entity.Users;
import com.franklin.techblog.dto.UserDto;


public class UserMapper {

    public static UserDto mapToCustomerDto(Users users, UserDto usersDto) {
        usersDto.setName(users.getName());
        usersDto.setEmail(users.getEmail());
        usersDto.setMobileNumber(users.getMobileNumber());
        usersDto.setPassword(users.getPassword());
        return usersDto;
    }

    public static Users mapToCustomer(UserDto userDto, Users users) {
        users.setName(userDto.getName());
        users.setEmail(userDto.getEmail());
        users.setMobileNumber(userDto.getMobileNumber());
        users.setPassword(userDto.getPassword());
        return users;
    }
}
