package com.example.ProjectXpress.repo;

import com.example.ProjectXpress.entity.XpressUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<XpressUser, Long> {
    Optional<XpressUser> findByUsername(String username);

    XpressUser findFirstByEmail(String email);

    boolean existsByEmail(String email);
}
