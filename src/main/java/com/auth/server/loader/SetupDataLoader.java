package com.auth.server.loader;


import com.auth.server.entity.privelege.Privilege;
import com.auth.server.entity.role.Role;
import com.auth.server.repository.PrivilegeRepository;
import com.auth.server.repository.WebUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;
    private final WebUserRepository webUserRepository;
    private final PrivilegeRepository privilegeRepository;
    private final PasswordEncoder passwordEncoder;
 
    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
 
//        if (alreadySetup)
//            return;
//        Privilege readPrivilege
//          = createPrivilegeIfNotFound("READ_PRIVILEGE");
//        Privilege writePrivilege
//          = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
//        Privilege writeReadPrivilege
//                = createPrivilegeIfNotFound("WRITE_READ_PRIVILEGE");
//
//        List<Privilege> adminPrivileges = Arrays.asList(
//          readPrivilege, writePrivilege);
//        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
//        createRoleIfNotFound("ROLE_SELLER", adminPrivileges);
//        createRoleIfNotFound("ROLE_AFFILIATE", adminPrivileges);
//        createRoleIfNotFound("ROLE_CALLER", adminPrivileges);
//        createRoleIfNotFound("ROLE_IT", adminPrivileges);
//        createRoleIfNotFound("ROLE_FINANCE", adminPrivileges);
//        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));

//        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
//        WebUser user = new WebUser();
//        user.setFirstName("shant");
//        user.setLastName("khayalian");
//        user.setPassword(passwordEncoder.encode("mnbvcxz00A!"));
//        user.setEmail("noiroxc@gmail.com");
//        user.setRoles(Arrays.asList(adminRole));
//        user.setEnable(true);
//        webUserRepository.save(user);
        alreadySetup = true;
    }

    @Transactional
    Privilege createPrivilegeIfNotFound(String name) {
 
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = Privilege.builder().name(name).build();
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

//    @Transactional
//    Role createRoleIfNotFound(
//      String name, Collection<Privilege> privileges) {
//
//        Role role = roleRepository.findByName(name);
//        if (role == null) {
//            role = Role.builder().name(name).build();
//            role.setPrivileges(privileges);
//            roleRepository.save(role);
//        }
//        return role;
//    }
}