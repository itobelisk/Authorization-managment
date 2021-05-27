package com.auth.server.repository;

import com.auth.server.entity.webuser.WebUser;
import com.auth.server.entity.webuser.response.WebUserResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface WebUserRepository extends JpaRepository<WebUser,Long> {

    Optional<WebUser> findByEmail(String email);
    Boolean existsByEmail(String email);

}
