package com.springboot.firststep.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    //Optional 객체를 사용하면 예상치 못한 NullPointerException을 예외처리할수 있다.
    Optional<User> findByEmail(String email);
}
