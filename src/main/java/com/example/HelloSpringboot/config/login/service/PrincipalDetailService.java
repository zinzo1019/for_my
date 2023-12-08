package com.example.HelloSpringboot.config.login.service;

import com.example.HelloSpringboot.entity.User;
import com.example.HelloSpringboot.config.login.object.PrincipalDetails;
import com.example.HelloSpringboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Security에서 loginProcessUrl("/login") 요청이 오면
 * 자동으로 UserDetailsService 타입으로 IoC되어 있는 loadUserByUsername 함수가 실행됨.
 */

@Service
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 사용자 저장
     */
    public User save(User user) {
        checkDuplication(user);
        return userRepository.save(user);
    }

    /**
     * 이미 가입된 회원인지 확인
     */
    private void checkDuplication(User user) {
        User findByEmail = userRepository.findByEmail(user.getEmail());
        if (findByEmail != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new PrincipalDetails(user);
    }
}