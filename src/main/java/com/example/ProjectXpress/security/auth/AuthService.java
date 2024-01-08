package com.example.ProjectXpress.security.auth;

import com.example.ProjectXpress.dto.UserDTO;
import com.example.ProjectXpress.dto.request.SignUpRequest;

public interface AuthService {
    UserDTO createUser(SignUpRequest signUpRequest);
}
