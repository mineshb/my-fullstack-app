package com.mineshb.handson.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Users",
        description = "Schema to hold User information"
)
public class UserDto {

    @NotEmpty(message = "Username can not be a null or empty")
    @Size(min = 5, max = 30, message = "The length of the UserName should be between 5 and 30")
    @Schema(
            description = "Username of User", example = "john20"
    )
    private String userName;

    @NotEmpty(message = "Email can not be a null or empty")
    @Email(message = "Email address should be a valid value")
    @Schema(
            description = "Email of User", example = "abc.xyz@domain.com"
    )
    private String email;


    @Schema(
            description = "First name of User", example = "John"
    )
    private String firstName;

    @Schema(
            description = "Last name of User", example = "Doe"
    )
    private String lastName;

}