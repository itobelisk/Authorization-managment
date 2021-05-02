package com.auth.server.services.role.impl;

import com.auth.server.base.BaseResponse;
import com.auth.server.entity.role.Role;
import com.auth.server.entity.role.request.RoleRequest;
import com.auth.server.entity.role.response.RoleResponse;
import com.auth.server.exception.RoleAlreadyCreatedException;
import com.auth.server.mapper.RoleMapper;
import com.auth.server.repository.RoleRepository;
import com.auth.server.services.role.RoleControllerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleControllerServiceImpl implements RoleControllerService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private static final String ROLE_START = "ROLE_";

    @Override
    public BaseResponse<?> save(RoleRequest roleRequest) {
        String roleName = getFinalName(roleRequest);
        if (roleRepository.existsByName(roleName) != null) throw new RoleAlreadyCreatedException();
        RoleResponse response = null;
        try {
            Role role = roleMapper.toRoleEntity(roleName);
            response = roleMapper.toResponse(roleRepository.save(role));
        } catch (Exception e) {
            return new BaseResponse<>(new Date(), false, HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return new BaseResponse<>(new Date(), true, HttpStatus.OK, response);
    }


    @Override
    public BaseResponse<?> all() {
        List<RoleResponse> response = null;
        try {
            response = roleMapper.toResponseList(roleRepository.findAll());
        } catch (Exception e) {
            return new BaseResponse<>(new Date(), false, HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return new BaseResponse<>(new Date(), true, HttpStatus.OK, response);
    }

    private String getFinalName(RoleRequest roleRequest) {
        return ROLE_START + roleRequest.getName().toUpperCase();
    }
}
