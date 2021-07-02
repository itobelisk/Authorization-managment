package com.auth.server.services.role.impl;

import com.auth.server.entity.role.Role;
import com.auth.server.entity.role.request.RoleRequest;
import com.auth.server.mapper.RoleMapper;
import com.auth.server.repository.RoleRepository;
import com.auth.server.services.role.CommandRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommandRoleServiceImpl implements CommandRoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private static final String ROLE_START = "ROLE_";

    @Override
    public void createAdminRole(RoleRequest roleRequest) {
        String roleName = getFinalName(roleRequest);
        if (roleRepository.existsByName(roleName) == null) {
            Role role = roleMapper.toRoleEntity(roleName);
            roleMapper.toResponse(roleRepository.save(role));
        }
    }

    private String getFinalName(RoleRequest roleRequest) {
        String finalRoleName = "";
        finalRoleName = roleRequest.getName().contains(" ") ? roleRequest.getName().replace(" ", "_") : roleRequest.getName().toUpperCase();
        log.info("finalRoleName {} ", finalRoleName);
        return ROLE_START + finalRoleName.toUpperCase();
    }
}
