package com.auth.server.controller;

import com.auth.server.api.RoleApi;
import com.auth.server.base.BaseResponse;
import com.auth.server.entity.position.response.PositionsResponse;
import com.auth.server.entity.role.request.RoleRequest;
import com.auth.server.services.role.impl.RoleControllerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class RoleController implements RoleApi {

    private final RoleControllerServiceImpl roleControllerService;

    @Override
    public ResponseEntity<BaseResponse<?>> updatePositions(RoleRequest roleRequest) {
        BaseResponse<?> response = roleControllerService.manageCollection(roleRequest);
        return new ResponseEntity<>(response,response.getMessage());
    }

    @Override
    public ResponseEntity<BaseResponse<?>> all() {
        Map<String, List<PositionsResponse>> responseMap = roleControllerService.all();
        BaseResponse<?> response = new BaseResponse<>(new Date(), true, HttpStatus.OK, responseMap);
        return new ResponseEntity<>(response, response.getMessage());
    }
}
