package com.mineshb.handson.repository;

import com.mineshb.handson.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserName(String userName);


    @Query("SELECT u FROM UserEntity u WHERE u.userName LIKE %?1% OR u.firstName LIKE %?1% OR u.lastName LIKE %?1% OR u.email LIKE %?1%")
    Page<UserEntity> searchByAnyField(String searchTerm, Pageable pageable);

}
