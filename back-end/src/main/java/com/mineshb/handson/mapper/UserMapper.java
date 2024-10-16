package com.mineshb.handson.mapper;

import com.mineshb.handson.dto.UserDto;
import com.mineshb.handson.entity.UserEntity;

public class UserMapper {

    public static UserDto mapToUserDto(UserEntity user, UserDto customerDto) {
        customerDto.setUserName(user.getUserName());
        customerDto.setEmail(user.getEmail());
        customerDto.setFirstName(user.getFirstName());
        customerDto.setLastName(user.getLastName());
        return customerDto;
    }

    public static UserEntity mapToUserEntity(UserDto userDto, UserEntity user) {
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        return user;
    }
}
