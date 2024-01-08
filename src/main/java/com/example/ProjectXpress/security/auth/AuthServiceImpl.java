package com.example.ProjectXpress.security.auth;

import com.example.ProjectXpress.dto.UserDTO;
import com.example.ProjectXpress.dto.request.SignUpRequest;
import com.example.ProjectXpress.entity.XpressUser;
import com.example.ProjectXpress.repo.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDTO createUser(SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new RuntimeException("Email is already taken");
        }
        XpressUser xpressUser = new XpressUser();
        xpressUser.setEmail(signUpRequest.getEmail());
        xpressUser.setUsername(signUpRequest.getUsername());
        xpressUser.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
       XpressUser createdUser = userRepository.save(xpressUser);
       UserDTO userDTO = new UserDTO();
       userDTO.setId(createdUser.getId());
       userDTO.setEmail(createdUser.getEmail());
       userDTO.setUsername(createdUser.getUsername());
       return userDTO;
    }
}
