package com.ems.backend.service;

import com.ems.backend.dto.LoginRequest;
import com.ems.backend.dto.RegisterRequest;

public interface AuthService {

    String register(RegisterRequest request);

    String login(LoginRequest request);
}
