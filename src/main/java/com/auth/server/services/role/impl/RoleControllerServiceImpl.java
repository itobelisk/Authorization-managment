package com.auth.server.services.role.impl;

import com.auth.server.annotations.validator.UserValidator;
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
import com.auth.server.util.CookieUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleControllerServiceImpl implements RoleControllerService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private static final String ROLE_START = "ROLE_";
    private final UserValidator userValidator;

    @Override
    public BaseResponse<?> save(RoleRequest roleRequest, String accessToken) {
        String accessTokens = CookieUtils.deserialize(accessToken);
        log.info("accessTokens {} ", accessTokens);
        WebUser webUser = userValidator.getCurrentUser("Bearer " + accessTokens);
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
    public BaseResponse<?> update(RoleRequest roleRequest, String accessToken) {
        String accessTokens = CookieUtils.deserialize(accessToken);
        log.info("accessTokens {} ", accessTokens);
        WebUser webUser = userValidator.getCurrentUser("Bearer " + accessTokens);
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
    public BaseResponse<?> delete(String accessToken, Long id) {
        String accessTokens = CookieUtils.deserialize(accessToken);
        log.info("accessTokens {} ", accessTokens);
        WebUser webUser = userValidator.getCurrentUser("Bearer " + accessTokens);
        log.info("webUser {} ", webUser);
        if (!roleRepository.existsById(id)) throw new RoleIdNotFoundException();
        Role role = roleRepository.getOne(id);
        if (role.getName().toLowerCase().contains("admin")) throw new AdminCanNotBeDeleteException();
        roleRepository.deleteById(id);
        if (roleRepository.existsById(id)) return new BaseResponse<>(new Date(), false, HttpStatus.BAD_REQUEST, "Not deleted");
        return new BaseResponse<>(new Date(), true, HttpStatus.OK, "Delete successfully");
    }

    @Override
    public BaseResponse<?> single(String accessToken, Long id) {
        String accessTokens = CookieUtils.deserialize(accessToken);
        log.info("accessTokens {} ", accessTokens);
        WebUser webUser = userValidator.getCurrentUser("Bearer " + accessTokens);
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
}
