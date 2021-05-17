package com.auth.server.mapper;

import com.auth.server.entity.role.Role;
import com.auth.server.entity.role.response.RoleResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleMapper {


    public Role toRoleEntity(String roleName) {
        return Role.builder()
                .name(roleName)
                .build();
    }

    public RoleResponse toResponse(Role save) {
        return RoleResponse.builder()
                .id(save.getId())
                .creationDate(save.getCreatedDate())
                .updatedDate(save.getUpdatedDate())
                .name(save.getName())
                .build();
    }

    public List<RoleResponse> toResponseList(List<Role> all) {
        return all.stream()
                .map(e -> new RoleResponse(
                        e.getId(),
                        e.getCreatedDate(),
                        e.getUpdatedDate(),
                        FilterRoleName(e.getName())
                ))
                .collect(Collectors.toList());
    }

    private String FilterRoleName(String name) {
        return name.substring(name.indexOf("_")+1);
    }

    public Role getRoleById(Role one) {
        return Role.builder()
                .name(one.getName())
                .build();
    }
}
