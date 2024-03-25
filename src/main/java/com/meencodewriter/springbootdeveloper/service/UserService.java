package com.meencodewriter.springbootdeveloper.service;

import com.meencodewriter.springbootdeveloper.domain.User;
import com.meencodewriter.springbootdeveloper.dto.AddUserRequest;
import com.meencodewriter.springbootdeveloper.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void save(AddUserRequest request) {
        User newUser = User.builder()
                .email(request.getEmail())
                .password(bCryptPasswordEncoder.encode(request.getPassword()))
                .build();
        userRepository.save(newUser);
    }
}
