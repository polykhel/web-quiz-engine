package com.mpollente.webquizengine.service;

import com.mpollente.webquizengine.mapper.Mapper;
import com.mpollente.webquizengine.repo.UserRepo;
import com.mpollente.webquizengine.domain.User;
import com.mpollente.webquizengine.dto.UserDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public void save(UserDto userDto) {
        User user = Mapper.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }
}
