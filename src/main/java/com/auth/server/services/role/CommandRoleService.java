package com.auth.server.services.role;

import com.auth.server.entity.role.request.RoleRequest;

@FunctionalInterface
public interface CommandRoleService {
    void createAdminRole(RoleRequest roleRequest);
}
