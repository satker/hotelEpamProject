package com.epam.dto;

import lombok.Data;

@Data
public class AddUserDTO {
    private String login;
    private String firstName;
    private String lastName;
    private String password;
}
