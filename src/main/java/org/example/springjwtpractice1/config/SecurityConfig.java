package org.example.springjwtpractice1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 사용자 비밀번호 암호화
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // csrf disable, session stateless 로 하기 때문에 csrf 공격에 대한 처리하지 않음
        http.csrf(auth -> auth.disable());

        // form 로그인 방식 disable, JWT 방식으로 하기 때문에 필요없음
        http.formLogin(auth -> auth.disable());

        // http basic 인증 방식 disable, JWT 방식으로 하기 때문에 필요없음
        http.httpBasic(auth -> auth.disable());

        // 경로별 인가 작업
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/main", "/join", "/login", "/logout").permitAll()
                .requestMatchers("/admin").hasAnyRole("ADMIN")
                .anyRequest().authenticated()// 로그인한 모든 사용자
        );

        // session stateless 설정, JWT 는 session stateless 로 설정해야함
        http.sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        return http.build();
    }
}
