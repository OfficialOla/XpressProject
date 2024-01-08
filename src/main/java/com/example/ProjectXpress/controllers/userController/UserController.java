package com.example.ProjectXpress.controllers.userController;

import com.example.ProjectXpress.dto.UserDTO;
import com.example.ProjectXpress.dto.request.SignUpRequest;
import com.example.ProjectXpress.security.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody SignUpRequest signUpRequest){
        UserDTO createUser = authService.createUser(signUpRequest);
        if (createUser == null)
            return new ResponseEntity<>("User is not created, try again later", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(createUser, HttpStatus.CREATED);
    }
}
