package com.mineshb.handson.service;

import com.mineshb.handson.dto.UserDto;

import java.util.List;

public interface IUserService {
    void createUser(UserDto userDto);

    UserDto fetchUser(String userName);

    boolean updateUser(UserDto userDto);

    boolean deleteUser(String userName);

    List<UserDto> fetchAllUsers();
}
