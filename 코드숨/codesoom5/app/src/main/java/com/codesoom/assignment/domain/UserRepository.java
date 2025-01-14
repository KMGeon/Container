package com.codesoom.assignment.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User , Long> {
    boolean existsByEmail(String s);
}
