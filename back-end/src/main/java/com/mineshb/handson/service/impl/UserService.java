package com.mineshb.handson.service.impl;

import com.mineshb.handson.config.PaginationConfig;

import com.mineshb.handson.dto.UserDto;
import com.mineshb.handson.entity.UserEntity;
import com.mineshb.handson.exception.ResourceNotFoundException;
import com.mineshb.handson.exception.UserAlreadyExistException;
import com.mineshb.handson.mapper.UserMapper;
import com.mineshb.handson.repository.UserRepository;
import com.mineshb.handson.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final PaginationConfig paginationConfig;
    /**
     * @param userDto - User Object
     */
    @Override
    public void createUser(UserDto userDto) {

        UserEntity user = UserMapper.mapToUserEntity(userDto, new UserEntity());
        Optional<UserEntity> optionalUser = userRepository.findByUserName(user.getUserName());
        if(optionalUser.isPresent()) {
            throw new UserAlreadyExistException("User already registered with given username "
                    +userDto.getUserName());
        }
        UserEntity savedUser = userRepository.save(user);
    }

    /**
     * @param userName - Input User Name
     * @return User Details based on a given userName
     */
    @Override
    public UserDto fetchUser(String userName) {

        UserEntity user = userRepository.findByUserName(userName).orElseThrow(
                () -> new ResourceNotFoundException("User", "UserName", userName)
        );
        return UserMapper.mapToUserDto(user, new UserDto());
    }

    /**
     * @param userDto - UserDto Object
     * @return boolean indicating if the update of User details is successful or not
     */
    @Override
    public boolean updateUser(UserDto userDto) {
        boolean isUpdated = false;
        if(userDto !=null ){
            UserEntity user = userRepository.findByUserName(userDto.getUserName()).orElseThrow(
                    () -> new ResourceNotFoundException("User", "UserName", userDto.getUserName())
            );
            UserMapper.mapToUserEntity(userDto, user);
            UserEntity savedUser = userRepository.save(user);
            isUpdated = true;
        }
        return  isUpdated;
    }

    /**
     * @param userName - Input User Name
     * @return boolean indicating if the delete of User details is successful or not
     */
    @Override
    public boolean deleteUser(String userName) {
        UserEntity user = userRepository.findByUserName(userName).orElseThrow(
                () -> new ResourceNotFoundException("User", "userName", userName)
        );
        userRepository.deleteById(user.getUserId());
        return true;
    }

    /**
     * @param page - Page Number
     * @param size - Page Size e.g., Number of Items per Page
     * @return List of all the User Details
     */
    @Override
    public Page<UserDto> fetchAllUsers(int page) {
        Pageable pageable = PageRequest.of(page, paginationConfig.getDefaultPageSize());
        Page<UserEntity> userPage = userRepository.findAll(pageable);
        return userPage.map(user -> UserMapper.mapToUserDto(user, new UserDto()));
    }

    /**
     * @param keyword Keyword to be searched
     * @param page - Page Number
     * @param size - Page Size e.g., Number of Items per Page
     * @return List of Users with matching keyword in any field
     */
    @Override
    public Page<UserDto> searchUsersByKeyword(String keyword, int page) {
        Pageable pageable = PageRequest.of(page, paginationConfig.getDefaultPageSize());
        Page<UserEntity> userPage = userRepository.searchByAnyField(keyword, pageable);
        return userPage.map(user -> UserMapper.mapToUserDto(user, new UserDto()));
    }


}
