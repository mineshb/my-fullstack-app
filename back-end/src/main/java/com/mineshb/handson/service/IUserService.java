package com.mineshb.handson.service;

import com.mineshb.handson.dto.UserDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IUserService {
    void createUser(UserDto userDto);

    UserDto fetchUser(String userName);

    boolean updateUser(UserDto userDto);

    boolean deleteUser(String userName);

    Page<UserDto> fetchAllUsers(int page);

    Page<UserDto> searchUsersByKeyword(String keyword, int page);
}
