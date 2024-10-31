package org.example.springjwtpractice1.repository;

import org.example.springjwtpractice1.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Boolean existsByUsername(String username);
    UserEntity findByUsername(String username);
}
