package com.epam.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String login;
    private String firstName;
    private String lastName;
    private String password;
    private String role;
}
