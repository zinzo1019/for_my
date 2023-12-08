package com.example.HelloSpringboot.service;

import com.example.HelloSpringboot.config.login.object.PrincipalDetails;
import com.example.HelloSpringboot.dto.UserDto;
import com.example.HelloSpringboot.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

public interface UserService {
    void save(UserDto userDto);

    /** 로그인 한 사용자 정보 가져오기 */
    static User getUserDetails() {
        return ((PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
    }
}
