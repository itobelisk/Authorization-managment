package com.auth.server.controller;

import com.auth.server.api.RoleApi;
import com.auth.server.base.BaseResponse;
import com.auth.server.entity.role.request.RoleRequest;
import com.auth.server.services.role.impl.RoleControllerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RoleController implements RoleApi {

    private final RoleControllerServiceImpl roleControllerService;

    @Override
    public ResponseEntity<BaseResponse<?>> save(RoleRequest roleRequest) {
        BaseResponse<?> response = roleControllerService.save(roleRequest);
        return new ResponseEntity<>(response,response.getMessage());
    }

    @Override
    public ResponseEntity<BaseResponse<?>> all() {
        BaseResponse<?> response = roleControllerService.all();
        return new ResponseEntity<>(response,response.getMessage());
    }

    @Override
    public ResponseEntity<BaseResponse<?>> update(RoleRequest roleRequest) {
        BaseResponse<?> response = roleControllerService.update(roleRequest);
        return new ResponseEntity<>(response,response.getMessage());
    }

    @Override
    public ResponseEntity<BaseResponse<?>> delete(Long id) {
        BaseResponse<?> response = roleControllerService.delete(id);
        return new ResponseEntity<>(response,response.getMessage());
    }

    @Override
    public ResponseEntity<BaseResponse<?>> single(Long id) {
        BaseResponse<?> response = roleControllerService.single(id);
        return new ResponseEntity<>(response,response.getMessage());
    }

    @Override
    public ResponseEntity<BaseResponse<?>> savePositions(RoleRequest roleRequest) {
        BaseResponse<?> response = roleControllerService.saveCollection(roleRequest);
        return new ResponseEntity<>(response,response.getMessage());
    }

    @Override
    public ResponseEntity<BaseResponse<?>> updatePositions(RoleRequest roleRequest) {
        BaseResponse<?> response = roleControllerService.updateCollection(roleRequest);
        return new ResponseEntity<>(response,response.getMessage());
    }
}
