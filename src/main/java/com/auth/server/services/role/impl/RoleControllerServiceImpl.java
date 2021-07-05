package com.auth.server.services.role.impl;

import com.auth.server.base.BaseResponse;
import com.auth.server.entity.role.Role;
import com.auth.server.entity.role.request.RoleRequest;
import com.auth.server.entity.role.response.RoleResponse;
import com.auth.server.entity.webuser.WebUser;
import com.auth.server.exception.AdminCanNotBeDeleteException;
import com.auth.server.exception.AdminCanNotBeUpdateException;
import com.auth.server.exception.RoleAlreadyCreatedException;
import com.auth.server.exception.RoleIdNotFoundException;
import com.auth.server.mapper.RoleMapper;
import com.auth.server.repository.RoleRepository;
import com.auth.server.services.role.RoleControllerService;
import com.auth.server.util.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleControllerServiceImpl implements RoleControllerService {
    private final UserUtils userUtils;
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private static final String ROLE_START = "ROLE_";

    @Override
    public BaseResponse<?> save(RoleRequest roleRequest) {
        WebUser webUser = userUtils.getUserId();
        log.info("webUser {} ", webUser);
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

    @Override
    public BaseResponse<?> update(RoleRequest roleRequest) {
        WebUser webUser = userUtils.getUserId();
        log.info("webUser {} ", webUser);
        if (!roleRepository.existsById(roleRequest.getId())) throw new RoleIdNotFoundException();
        if(roleRequest.getName().toLowerCase().contains("admin")) throw new AdminCanNotBeUpdateException();
        String roleName = getFinalName(roleRequest);
        RoleResponse response = null;
        try {
            Role role = roleMapper.getRoleById(roleRepository.getOne(roleRequest.getId()));
            role.setId(roleRequest.getId());
            role.setName(roleName);
            response = roleMapper.toResponse(roleRepository.save(role));
        } catch (Exception e) {
            return new BaseResponse<>(new Date(), false, HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return new BaseResponse<>(new Date(), true, HttpStatus.OK, response);
    }




    @Override
    public BaseResponse<?> delete(Long id) {
        WebUser webUser = userUtils.getUserId();
        log.info("webUser {} ", webUser);
        if (!roleRepository.existsById(id)) throw new RoleIdNotFoundException();
        Role role = roleRepository.getOne(id);
        if (role.getName().toLowerCase().contains("admin")) throw new AdminCanNotBeDeleteException();
        roleRepository.deleteById(id);
        if (roleRepository.existsById(id)) return new BaseResponse<>(new Date(), false, HttpStatus.BAD_REQUEST, "Not deleted");
        return new BaseResponse<>(new Date(), true, HttpStatus.OK, "Delete successfully");
    }

    @Override
    public BaseResponse<?> single(Long id) {
        WebUser webUser = userUtils.getUserId();
        log.info("webUser {} ", webUser);
        if (!roleRepository.existsById(id)) throw new RoleIdNotFoundException();
        RoleResponse role = roleMapper.toSingleRole(roleRepository.getOne(id));
        return new BaseResponse<>(new Date(), true, HttpStatus.OK, role);
    }

    private String getFinalName(RoleRequest roleRequest) {
        String finalRoleName = "";
        finalRoleName = roleRequest.getName().contains(" ") ? roleRequest.getName().replace(" ", "_") : roleRequest.getName().toUpperCase();
        log.info("finalRoleName {} ", finalRoleName);
        return ROLE_START + finalRoleName.toUpperCase();
    }

    private String getFinalNameCollection(RoleRequest roleRequest) {
        String finalRoleName = "";
        finalRoleName = roleRequest.getRoleRequestsCollection().iterator().next().getName().contains(" ") ? roleRequest.getRoleRequestsCollection().iterator().next().getName().replace(" ", "_") : roleRequest.getRoleRequestsCollection().iterator().next().getName().toUpperCase();
        log.info("finalRoleName {} ", finalRoleName);
        return ROLE_START + finalRoleName.toUpperCase();
    }

    @Override
    public BaseResponse<?> saveCollection(RoleRequest roleRequest) {
        RoleResponse response = null;
        for (int i = 0; i < roleRequest.getRoleRequestsCollection().size(); i++) {
            String roleName = getFinalNameCollection(roleRequest);
            if (roleRepository.existsByName(roleName) != null) throw new RoleAlreadyCreatedException();
            try {
                Role role = roleMapper.toRoleEntity(roleName);
                response = roleMapper.toResponse(roleRepository.save(role));
            } catch (Exception e) {
                return new BaseResponse<>(new Date(), false, HttpStatus.BAD_REQUEST, e.getMessage());
            }
        }
        return new BaseResponse<>(new Date(), true, HttpStatus.OK, response);
    }

    public BaseResponse<?> updateCollection(RoleRequest roleRequest) {
        RoleResponse response = null;
        for (int i = 0; i < roleRequest.getRoleRequestsCollection().size(); i++) {
            if (!roleRepository.existsById(roleRequest.getId())) throw new RoleIdNotFoundException();
            if (roleRequest.getName().toLowerCase().contains("admin")) throw new AdminCanNotBeUpdateException();
            String roleName = getFinalName(roleRequest);

            try {
                Role role = roleMapper.getRoleById(roleRepository.getOne(roleRequest.getId()));
                role.setId(roleRequest.getId());
                role.setName(roleName);
                response = roleMapper.toResponse(roleRepository.save(role));
            } catch (Exception e) {
                return new BaseResponse<>(new Date(), false, HttpStatus.BAD_REQUEST, e.getMessage());
            }
        }
        return new BaseResponse<>(new Date(), true, HttpStatus.OK, response);
    }

}
