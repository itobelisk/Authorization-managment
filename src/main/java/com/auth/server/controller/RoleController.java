package com.auth.server.controller;

import com.auth.server.api.RoleApi;
import com.auth.server.base.BaseResponse;
import com.auth.server.entity.role.request.RoleRequest;
import com.auth.server.services.role.impl.RoleControllerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class RoleController implements RoleApi {

    private final RoleControllerServiceImpl roleControllerService;

    @Override
    public ResponseEntity<BaseResponse<?>> save( String accessToken, RoleRequest roleRequest) {
        BaseResponse<?> response = roleControllerService.save(roleRequest,accessToken);
        return new ResponseEntity<>(response,response.getMessage());
    }

    @Override
    public ResponseEntity<BaseResponse<?>> all() {
        BaseResponse<?> response = roleControllerService.all();
        return new ResponseEntity<>(response,response.getMessage());
    }

    @Override
    public ResponseEntity<BaseResponse<?>> update(String accessToken, RoleRequest roleRequest) {
        BaseResponse<?> response = roleControllerService.update(roleRequest,accessToken);
        return new ResponseEntity<>(response,response.getMessage());
    }

    @Override
    public ResponseEntity<BaseResponse<?>> delete(String accessToken, Long id) {
        BaseResponse<?> response = roleControllerService.delete(accessToken,id);
        return new ResponseEntity<>(response,response.getMessage());
    }

    @Override
    public ResponseEntity<BaseResponse<?>> single(String accessToken, Long id) {
        BaseResponse<?> response = roleControllerService.single(accessToken,id);
        return new ResponseEntity<>(response,response.getMessage());
    }
}
