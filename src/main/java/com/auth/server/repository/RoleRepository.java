package com.auth.server.repository;

import com.auth.server.entity.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String roleName);

    @Query("select e from Role e where e.name like %?1%")
    Role existsByName(String name);

}
