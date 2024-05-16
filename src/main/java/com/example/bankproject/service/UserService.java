package com.example.bankproject.service;


import com.example.bankproject.domain.user.User;
import com.example.bankproject.domain.user.UserEnum;
import com.example.bankproject.domain.user.UserRepository;
import com.example.bankproject.dto.user.UserReqDto;
import com.example.bankproject.dto.user.UserRespDto;
import com.example.bankproject.ex.CustomApiException;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UserService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;


    @Transactional
    public UserRespDto.JoinRespDto 회원가입(UserReqDto.JoinReqDto joinReqDto) {
        // 1. 동일 유저네임 있는지 검사
        Optional<User> userOptional = userRepository.findByUsername(joinReqDto.getUsername());
        if (userOptional.isPresent()) {
            // 유저네임 중복있다면
            throw new CustomApiException("동일한 username이 존재합니다");
        }
        // 2. 패스워드 인코딩 + 회원가입
        User userPs = userRepository.save(joinReqDto.toEntity(passwordEncoder));

        // 3. dto 응답
        return new UserRespDto.JoinRespDto(userPs);

    }




}
