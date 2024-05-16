package com.example.bankproject.service;

import com.example.bankproject.config.dummy.DummyObject;
import com.example.bankproject.domain.user.User;
import com.example.bankproject.domain.user.UserEnum;
import com.example.bankproject.domain.user.UserRepository;
import com.example.bankproject.dto.user.UserReqDto;
import com.example.bankproject.dto.user.UserRespDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
class UserServiceTest extends DummyObject {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Spy
    private BCryptPasswordEncoder passwordEncoder;


    @Test
    void 회원가입_test() {

        UserReqDto.JoinReqDto joinReqDto = new UserReqDto.JoinReqDto("ssar", "1234", "ssar@nate.com", "쌀");
//        joinReqDto.setUsername("ssar");

        // stub 1
        when(userRepository.findByUsername(any())).thenReturn(Optional.empty());

        // stub 2
//        User ssar = User.builder()
//                        .id(1L)
//                        .username("ssar")
//                        .password("1234")
//                        .email("ssar@nate.com")
//                        .fullname("쌀")
//                        .role(UserEnum.CUSTOMER)
//                        .createdAt(LocalDateTime.now())
//                        .updatedAt(LocalDateTime.now())
//                        .build();
        User ssar = newMockUser(1L, "ssar", "쌀");
        when(userRepository.save(any())).thenReturn(ssar);

        UserRespDto.JoinRespDto joinRespDto = userService.회원가입(joinReqDto);

        System.out.println("[joinRespDto] : " + joinRespDto);
        assertThat(joinRespDto.getId()).isEqualTo(1L);
        assertThat(joinRespDto.getUsername()).isEqualTo("ssar");

    }

}