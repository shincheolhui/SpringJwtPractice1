package org.example.springjwtpractice1.service;

import org.example.springjwtpractice1.dto.CustomUserDetails;
import org.example.springjwtpractice1.entity.UserEntity;
import org.example.springjwtpractice1.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("CustomUserDetailsService.loadUserByUsername");
        UserEntity userEntity = userRepository.findByUsername(username);
        System.out.println("userEntity = " + userEntity);
        if (userEntity == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(userEntity);
    }
}
