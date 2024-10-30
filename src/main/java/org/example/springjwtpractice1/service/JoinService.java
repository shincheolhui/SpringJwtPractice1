package org.example.springjwtpractice1.service;

import org.example.springjwtpractice1.dto.JoinDTO;
import org.example.springjwtpractice1.entity.UserEntity;
import org.example.springjwtpractice1.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public JoinService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void join(JoinDTO joinDTO) {
        System.out.println("joinDTO = " + joinDTO);
        String username = joinDTO.getUsername();
        String password = joinDTO.getPassword();

        // 동일한 회원 존재 여부 확인
        Boolean existsByUsername = userRepository.existsByUsername(username);
        if (existsByUsername) {
            System.out.println("existsByUsername = true");
            return;
        }

        // 회원가입 정보 설정 및 저장
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(bCryptPasswordEncoder.encode(password)); // 비밀번호 암호화
        userEntity.setRole("ROLE_ADMIN");
        userRepository.save(userEntity);
    }
}
