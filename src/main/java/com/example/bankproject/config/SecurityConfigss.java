//package com.example.bankproject.config;
//
//
//import com.example.bankproject.domain.user.UserEnum;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//@Configuration
//public class SecurityConfigss {
//    private final Logger log = LoggerFactory.getLogger(getClass());
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        log.debug("디버그 : BCryptPasswordEncoder 빈 등록됨");
//        return new BCryptPasswordEncoder();
//    }
//
//    // 모든 필터 등록
//
//
//    // JWT 서버 만들 예정. Session 사용 안함.
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        log.debug("디버그 : filterChain 빈 등록됨");
//        http.headers().frameOptions().disable();
//        http.csrf().disable();  // enable이면 postman 작동안함 (메타코딩 시큐리티 강의)
//        http.cors().configurationSource(configurationSource());
//
//        // jSessionId 를 서버쪽에서 관리안하겠다는 뜻
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        http.formLogin().disable();
//        http.httpBasic().disable();
//
//        // Exception 가로채기
//        http.exceptionHandling().authenticationEntryPoint((request, response, authException) -> {
//            CustomResponseUtil.unAuthentication(response, "로그인을 해주세요.");
//        });
//
//        http.authorizeHttpRequests()
//                .antMatchers("/api/s/**").authenticated()
//                .antMatchers("/api/admin/**").hasRole(""+ UserEnum.ADMIN)
//                .anyRequest().permitAll();
//
//        return http.build();
//    }
//
//
//    public CorsConfigurationSource configurationSource() {
//        log.debug("디버그 : configurationSource cors 설정이 SecurityFilterChain에 등록됨");
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.addAllowedHeader("*");
//        configuration.addAllowedMethod("*");  // GET, POST, PUT, DELETE
//        configuration.addAllowedOriginPattern("*");  // 모든 IP 주소 허용 (프론트엔드 IP 만 허용)
//        configuration.setAllowCredentials(true);  // 클라이언트에서 쿠키 요청 허용
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
//
//}
