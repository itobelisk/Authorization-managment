package com.auth.server.repository;

import com.auth.server.entity.webuser.WebUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WebUserRepository extends JpaRepository<WebUser,Long> {

    Optional<WebUser> findByEmail(String email);
    WebUser findUserByEmail(String email);
    Boolean existsByEmail(String email);

}
