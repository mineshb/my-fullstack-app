package com.mineshb.handson.entity;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
@Table(name="users")
public class UserEntity extends BaseEntity {

    @Column(name="user_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name="user_name")
    private String userName;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;
}
