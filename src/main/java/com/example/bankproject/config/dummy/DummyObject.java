package com.example.bankproject.config.dummy;


import com.example.bankproject.domain.user.User;
import com.example.bankproject.domain.user.UserEnum;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

public class DummyObject {

    // entity 에 save 할 때 
    protected User newUser(String username, String fullname) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encPassword = passwordEncoder.encode("1234");
        return User.builder()
                .username(username)
                .password(encPassword)
                .email(username + "@nate.com")
                .fullname(fullname)
                .role(UserEnum.CUSTOMER)
                .build();
    }

    // 가짜로 만들어서 stub 할 때
    protected User newMockUser(Long id, String username, String fullname) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encPassword = passwordEncoder.encode("1234");
        return User.builder()
                        .id(id)
                        .username(username)
                        .password(encPassword)
                        .email(username + "@nate.com")
                        .fullname(fullname)
                        .role(UserEnum.CUSTOMER)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build();
    }
}
