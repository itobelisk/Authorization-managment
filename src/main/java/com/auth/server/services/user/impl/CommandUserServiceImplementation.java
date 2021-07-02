package com.auth.server.services.user.impl;


import com.auth.server.entity.webuser.WebUser;
import com.auth.server.enums.AuthProvider;
import com.auth.server.repository.RoleRepository;
import com.auth.server.repository.WebUserRepository;
import com.auth.server.services.user.CommandUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommandUserServiceImplementation implements CommandUserService {

    private final WebUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public void addAdmin() {
        WebUser user = new WebUser();
        user.setFirstName("General");
        user.setLastName("Admin");
        user.setEmail("admin@admin.com");
        user.setPassword("mnbvcxz00A!");
        user.setProvider(AuthProvider.local);
        user.setEmailVerified(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singletonList(roleRepository.findByName("ROLE_ADMIN")));

        userRepository.save(user);

    }
}
