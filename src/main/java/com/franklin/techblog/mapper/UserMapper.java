package com.franklin.techblog.mapper;

import com.franklin.techblog.entity.BlogUser;
import com.franklin.techblog.dto.UserDto;


public class UserMapper {

    public static UserDto mapToUserDto(BlogUser blogUser, UserDto usersDto) {
        usersDto.setName(blogUser.getName());
        usersDto.setEmail(blogUser.getEmail());
        usersDto.setMobileNumber(blogUser.getMobileNumber());
        usersDto.setPassword(blogUser.getPassword());
        return usersDto;
    }

    public static BlogUser mapToUser(UserDto userDto, BlogUser blogUser) {
        blogUser.setName(userDto.getName());
        blogUser.setEmail(userDto.getEmail());
        blogUser.setMobileNumber(userDto.getMobileNumber());
        blogUser.setPassword(userDto.getPassword());

        return blogUser;
    }
}
