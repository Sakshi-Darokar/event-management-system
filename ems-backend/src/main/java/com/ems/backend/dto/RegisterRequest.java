package com.ems.backend.dto;

import com.ems.backend.enums.Role;
import lombok.Data;

@Data
public class RegisterRequest {

    private String name;
    private String email;
    private String password;


    private Role role;
}
