package com.example.HelloSpringboot.service;

import com.example.HelloSpringboot.dto.UserDto;
import com.example.HelloSpringboot.entity.User;
import com.example.HelloSpringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.DuplicateFormatFlagsException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;

    /**
     * 회원가입
     */
    @Override
    public void save(UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail());
        if (user != null) {
            throw new DuplicateKeyException("이미 가입된 회원입니다.");
        }

        String encPassword = bCryptPasswordEncoder.encode(userDto.getPassword()); // 비밀번호 암호와
        userDto.setPassword(encPassword);
        userDto.setRole("ROLE_USER");

        userRepository.save(userDto.dtoToEntity()); // 사용자 저장
    }
}
