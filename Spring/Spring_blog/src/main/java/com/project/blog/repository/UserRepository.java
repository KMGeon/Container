package com.project.blog.repository;

import com.project.blog.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailAndPassword(String email , String password);

    Optional<User> findByEmail(String email);
}
