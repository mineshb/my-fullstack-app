package com.mineshb.handson.service.impl;

import com.mineshb.handson.dto.UserDto;
import com.mineshb.handson.entity.UserEntity;
import com.mineshb.handson.exception.ResourceNotFoundException;
import com.mineshb.handson.exception.UserAlreadyExistException;
import com.mineshb.handson.mapper.UserMapper;
import com.mineshb.handson.repository.UserRepository;
import com.mineshb.handson.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private UserRepository userRepository;
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
     * @return List of all the User Details
     */
    @Override
    public List<UserDto> fetchAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> UserMapper.mapToUserDto(user, new UserDto())) // Create a new UserDto for each user
                .collect(Collectors.toList());
    }

}
