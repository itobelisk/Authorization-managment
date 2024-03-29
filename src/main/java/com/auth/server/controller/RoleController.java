package com.auth.server.controller;

import com.auth.server.annotations.CurrentUser;
import com.auth.server.api.RoleApi;
import com.auth.server.base.BaseResponse;
import com.auth.server.entity.role.request.RoleRequest;
import com.auth.server.services.role.impl.RoleControllerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RoleController implements RoleApi {

    private final RoleControllerServiceImpl roleControllerService;

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<BaseResponse<?>> save(@CurrentUser String accessToken, RoleRequest roleRequest) {
        BaseResponse<?> response = roleControllerService.save(roleRequest);
        return new ResponseEntity<>(response,response.getMessage());
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public ResponseEntity<BaseResponse<?>> all(@CurrentUser String accessToken) {
        BaseResponse<?> response = roleControllerService.all();
        return new ResponseEntity<>(response,response.getMessage());
    }
}
